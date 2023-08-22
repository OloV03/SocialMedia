package com.IlyaJukov.SocialMedia.controllers;

import com.IlyaJukov.SocialMedia.models.Post;
import com.IlyaJukov.SocialMedia.services.post.PostService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/posts")
@Controller
public class PostController {
    private int countPostsOnPage = 20;

    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{userId}/getPosts")
    public List<Post> getPosts(@PathVariable Long userId, @RequestParam int page) {
        return postService.getPosts(userId, page, countPostsOnPage);
    }

    @PostMapping("/addPost")
    public void save(@RequestBody Post post) {
        postService.save(post);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }

    @PatchMapping("/update/{id}")
    public void update(@RequestBody Post post, @PathVariable Long id) {
        postService.update(post);
    }

    @PatchMapping("/update")
    public void updatePageSize(@RequestParam int pageSize) {
        countPostsOnPage = pageSize;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFoundException(EntityNotFoundException exception) {
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException exception) {
        return exception.getMessage();
    }
}
