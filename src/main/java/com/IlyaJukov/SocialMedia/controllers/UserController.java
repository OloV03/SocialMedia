package com.IlyaJukov.SocialMedia.controllers;

import com.IlyaJukov.SocialMedia.models.User;
import com.IlyaJukov.SocialMedia.models.request.TargetRequest;
import com.IlyaJukov.SocialMedia.services.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<User> getAll() {
        return service.getAll();
    }

    @PutMapping("/follow")
    public void follow(@RequestBody TargetRequest request) {
        service.follow(request.getCurrentUserId(), request.getTargetUserId());
    }

    @PatchMapping("/unfollow")
    public void unfollow(@RequestBody TargetRequest request) {
        service.unfollow(request.getCurrentUserId(), request.getTargetUserId());
    }

    @PostMapping("/friendRequest")
    public void friendRequest(@RequestBody TargetRequest request) {
        service.friendRequest(request.getCurrentUserId(), request.getTargetUserId());
    }

    @PatchMapping("/approveFriend")
    public void approveFriend(@RequestBody TargetRequest request) {
        service.approveFriend(request.getCurrentUserId(), request.getTargetUserId());
    }

    @DeleteMapping("/deleteFriend")
    public void deleteFriend(@RequestBody TargetRequest request) {
        service.deleteFriend(request.getCurrentUserId(), request.getCurrentUserId());
    }

    @GetMapping("/{userId}/getFollowing")
    public List<User> getFollowing(@PathVariable long userId) {
        return service.getFollowingList(userId);
    }

    @GetMapping("/{userId}/getFriends")
    public List<User> getFriends(@PathVariable long userId) {
        return service.getFriendsList(userId);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public String handleException(EntityNotFoundException exception) {
        return exception.getMessage();
    }
}
