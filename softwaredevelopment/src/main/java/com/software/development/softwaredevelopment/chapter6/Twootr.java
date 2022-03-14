package com.software.development.softwaredevelopment.chapter6;

import com.software.development.softwaredevelopment.chapter6.domain.Position;
import com.software.development.softwaredevelopment.chapter6.domain.twoot.Twoot;
import com.software.development.softwaredevelopment.chapter6.domain.twoot.TwootQuery;
import com.software.development.softwaredevelopment.chapter6.domain.twoot.TwootRepository;
import com.software.development.softwaredevelopment.chapter6.domain.user.User;
import com.software.development.softwaredevelopment.chapter6.domain.user.UserRepository;
import com.software.development.softwaredevelopment.chapter6.enums.FollowStatus;
import com.software.development.softwaredevelopment.chapter6.util.KeyGenerator;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class Twootr {

    private final TwootRepository twootRepository;
    private final UserRepository userRepository;

    public Twootr(TwootRepository twootRepository, UserRepository userRepository) {
        this.twootRepository = twootRepository;
        this.userRepository = userRepository;
    }

    public Optional<SenderEndPoint> onLogon(final String userId, final String password,
        final ReceiverEndPoint receiverEndPoint) {

        Objects.requireNonNull(userId, "userId");
        Objects.requireNonNull(password, "password");

        var authenticatedUser = userRepository
            .get(userId)
            .filter(userOfSameId -> {
                var hashedPassword = KeyGenerator.hash(password, userOfSameId.getSalt());
                return Arrays.equals(hashedPassword, userOfSameId.getPassword());
            });

        authenticatedUser.ifPresent(user -> {
            user.onLogon(receiverEndPoint);
            twootRepository.query(
                new TwootQuery()
                    .inUsers(user.getFollowing())
                    .lastSeenPosition(user.getLastSeenPosition()),
                user::receiveTwoot);
        });
        return authenticatedUser.map(user -> new SenderEndPoint(user, this));
/*        User user = new User(userId, password.getBytes(StandardCharsets.UTF_16),
            Position.INITIAL_POSITION);
        return Optional.of(new SenderEndPoint(user, this));*/
    }

    public FollowStatus onFollow(User user, String userIdToFollow) {
        return FollowStatus.SUCCESS;
    }

    public Position onSendTwoot(String id, User user, String content) {
        final String userId = user.getId();
        final Twoot twoot = new Twoot(id, userId, content, Position.INITIAL_POSITION);
        user.getFollowers().stream().filter(User::isLoggedOn)
            .forEach(follower -> follower.receiveTwoot(twoot));
        return twoot.getPosition();
    }

}