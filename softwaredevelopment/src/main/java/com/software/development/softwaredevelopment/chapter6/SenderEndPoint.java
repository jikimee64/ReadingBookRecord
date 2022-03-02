package com.software.development.softwaredevelopment.chapter6;

import com.software.development.softwaredevelopment.chapter6.domain.Position;
import com.software.development.softwaredevelopment.chapter6.domain.user.User;
import com.software.development.softwaredevelopment.chapter6.enums.FollowStatus;
import java.util.Objects;

public class SenderEndPoint {

    private final User user;
    private final Twootr twootr;

    SenderEndPoint(final User user, final Twootr twootr) {
        Objects.requireNonNull(user, "user");
        Objects.requireNonNull(twootr, "twootr");

        this.user = user;
        this.twootr = twootr;
    }

    public FollowStatus onFollow(final String userIdToFollow) {
        Objects.requireNonNull(userIdToFollow, "userIdToFollow");

        return twootr.onFollow(user, userIdToFollow);
    }

    public Position onSendTwoot(final String id, final String content){
        Objects.requireNonNull(content, "content");

        return twootr.onSendTwoot(id, user, content);
    }

}