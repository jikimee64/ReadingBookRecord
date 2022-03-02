package com.software.development.softwaredevelopment.chapter6.domain.user;

import com.software.development.softwaredevelopment.chapter6.ReceiverEndPoint;
import com.software.development.softwaredevelopment.chapter6.domain.Position;
import com.software.development.softwaredevelopment.chapter6.domain.twoot.Twoot;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

@Getter
public class User {
    private final String id;
    private final byte[] password;
    private final Set<User> followers = new HashSet<>();

    private Position lastSeenPosition;
    private ReceiverEndPoint receiverEndPoint;

    public User(String id, byte[] password, Position lastSeenPosition) {
        this.id = id;
        this.password = password;
        this.lastSeenPosition = lastSeenPosition;
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

    public void onLogon(final ReceiverEndPoint receiverEndPoint) {
        this.receiverEndPoint = receiverEndPoint;
    }

    public void onLogoff() {
        receiverEndPoint = null;
    }

}