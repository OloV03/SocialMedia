package com.IlyaJukov.SocialMedia.services.user;

import com.IlyaJukov.SocialMedia.models.User;
import org.springframework.stereotype.Service;

@Service
public class FriendsService implements FriendRequestProxy{

    @Override
    public void friendRequest(User currentUser, User newFriend) {
        newFriend.addFollower(currentUser);
        follow(currentUser, newFriend);
    }

    public void follow(User currentUser, User following) {
        currentUser.addFollowing(following);
    }

    @Override
    public void unfollow(User currentUser, User following) {
        currentUser.deleteFollowing(following);
        following.deleteFollower(currentUser);
    }

    @Override
    public void approveRequest(User currentUser, User newFriend) {
        if (!currentUser.getFollowers().contains(newFriend))
            throw new RuntimeException();

        // making users friends
        currentUser.addFriend(newFriend);
        newFriend.addFriend(currentUser);

        // reply subscription
        follow(currentUser, newFriend);

    }

    @Override
    public void deleteFriend(User currentUser, User friend) {
        if (!currentUser.getFriends().contains(friend))
            throw new RuntimeException();

        currentUser.deleteFriend(friend);
    }
}
