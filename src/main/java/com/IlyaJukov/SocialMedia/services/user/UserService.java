package com.IlyaJukov.SocialMedia.services.user;

import com.IlyaJukov.SocialMedia.models.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repo;
    private final FriendsService friendsService;

    public UserService(UserRepository repo, FriendsService friendsService) {
        this.repo = repo;
        this.friendsService = friendsService;
    }

    public void follow(long currentId, long newFollowing) {
        friendsService.follow(getUserById(currentId), getUserById(newFollowing));
    }

    public void unfollow(long currentId, long following) {
        friendsService.unfollow(getUserById(currentId), getUserById(following));
    }

    public List<User> getFollowingList(long currentId) {
        return repo.getUsersByIdIn(
                getUserById(currentId).getFollowing()
                        .stream()
                        .map(User::getId)
                        .collect(Collectors.toList())
        );
    }

    public List<User> getFriendsList(long currentId) {
        return repo.getUsersByIdIn(
                getUserById(currentId).getFriends()
                        .stream()
                        .map(User::getId)
                        .collect(Collectors.toList())
        );
    }

    public void approveFriend(long currentId, long newFriend) {
        friendsService.approveRequest(getUserById(currentId), getUserById(newFriend));
    }

    public User getUserByLogin(String login) {
        return repo.findByLogin(login)
                .stream().findFirst()
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public void save(User user) {
        try {
            repo.save(user);
        } catch (DataIntegrityViolationException exception) {
            throw new IllegalArgumentException("Login duplicated");
        }
    }

    public User getUserById(long userId) {
        return repo.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public void friendRequest(long currentId, long newFriend) {
        friendsService.friendRequest(getUserById(currentId), getUserById(newFriend));
    }

    public void deleteFriend(long currentId, long newFriend) {
        friendsService.deleteFriend(getUserById(currentId), getUserById(newFriend));
    }

    public List<User> getAll() {
        return repo.getAll();
    }

    public UserDetailsService loadUserByLogin() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return getUserByLogin(username);
            }
        };
    }
}
