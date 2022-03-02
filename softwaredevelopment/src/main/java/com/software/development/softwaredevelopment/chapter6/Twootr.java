package com.software.development.softwaredevelopment.chapter6;

import com.software.development.softwaredevelopment.chapter6.domain.Position;
import com.software.development.softwaredevelopment.chapter6.domain.twoot.Twoot;
import com.software.development.softwaredevelopment.chapter6.domain.user.User;
import com.software.development.softwaredevelopment.chapter6.enums.FollowStatus;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class Twootr {

    public Optional<SenderEndPoint> onLogon(
        final String userId, final String password, final ReceiverEndPoint receiverEndPoint) {

        User user = new User(userId, password.getBytes(StandardCharsets.UTF_16), Position.INITIAL_POSITION);

        return Optional.of(new SenderEndPoint(user, this));
    }

    public FollowStatus onFollow(User user, String userIdToFollow) {
        return FollowStatus.SUCCESS;
    }

    public Position onSendTwoot(String id, User user, String content) {
        final String userId = user.getId();
        final Twoot twoot = new Twoot(id, userId, content, Position.INITIAL_POSITION);
        user.getFollowers().stream()
            .filter(User::isLoggedOn)
            .forEach(follower -> follower.receiveTwoot(twoot));
        return twoot.getPosition();
    }

}