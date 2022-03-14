package com.software.development.softwaredevelopment.chapter6.domain.user;

import static com.software.development.softwaredevelopment.chapter6.enums.FollowStatus.ALREADY_FOLLOWING;
import static com.software.development.softwaredevelopment.chapter6.enums.FollowStatus.SUCCESS;

import com.software.development.softwaredevelopment.chapter6.ReceiverEndPoint;
import com.software.development.softwaredevelopment.chapter6.domain.Position;
import com.software.development.softwaredevelopment.chapter6.domain.twoot.Twoot;
import com.software.development.softwaredevelopment.chapter6.enums.FollowStatus;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public class User {
    private final String id;
    private final byte[] password;
    private final byte[] salt;
    private final Set<User> followers = new HashSet<>();
    private final Set<String> following = new HashSet<>();

    private Position lastSeenPosition;
    private ReceiverEndPoint receiverEndPoint;

    public User(String id, byte[] password, byte[] salt, Position lastSeenPosition) {
        this.id = id;
        this.password = password;
        this.salt = salt;
        this.lastSeenPosition = lastSeenPosition;
    }

    public byte[] getPassword() {
        return password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public String getId() {
        return id;
    }

    public boolean receiveTwoot(final Twoot twoot) {
        if (isLoggedOn()) {
            receiverEndPoint.onTwoot(twoot);
            lastSeenPosition = twoot.getPosition();
            return true;
        }

        return false;
    }

    public boolean isLoggedOn() {
        return receiverEndPoint != null;
    }

    public FollowStatus addFollower(final User user){
        if(followers.add(user)){
            user.following.add(id);
            return SUCCESS;
        }else{
            return ALREADY_FOLLOWING;
        }
    }

    public void onLogon(final ReceiverEndPoint receiverEndPoint) {
        this.receiverEndPoint = receiverEndPoint;
    }

    public void onLogoff() {
        receiverEndPoint = null;
    }

    public Stream<User> followers() {
        return followers.stream();
    }

    public Set<String> getFollowing() {
        return following;
    }


}