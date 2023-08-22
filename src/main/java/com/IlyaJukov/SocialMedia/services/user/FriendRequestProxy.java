package com.IlyaJukov.SocialMedia.services.user;

import com.IlyaJukov.SocialMedia.models.User;
import org.springframework.stereotype.Component;

@Component
public interface FriendRequestProxy {
    void friendRequest(User currentUser, User newFriend);

    void follow(User currentUser, User following);

    void unfollow(User currentUser, User following);

    void approveRequest(User currentUser, User follower);

    void deleteFriend(User currentUser, User friend);
}
