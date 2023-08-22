package com.IlyaJukov.SocialMedia.services.post;

import com.IlyaJukov.SocialMedia.models.Post;
import com.IlyaJukov.SocialMedia.models.User;
import com.IlyaJukov.SocialMedia.services.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostPaging pagingRepo;
    private final UserRepository userRepo;
    private final PostRepository repo;
    public PostService(PostPaging pagingRepo, UserRepository userRepo, PostRepository repo) {
        this.pagingRepo = pagingRepo;
        this.userRepo = userRepo;
        this.repo = repo;
    }

    public List<Post> getPosts(long userId, int pageNumber, int postCount) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return pagingRepo.findAllByUserIdIn(
                user.getFollowing().stream().map(User::getId).toList(),
                PageRequest.of(pageNumber, postCount, Sort.by("dateCreated"))
        );
    }

    public void save(Post post) {
        try {
            repo.save(post);
        } catch (DataIntegrityViolationException exception) {
            throw new IllegalArgumentException("Post with title" + post.getTitle() + " already exist");
        }
    }
    public void update(Post post) {
        repo.findById(post.getId())
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        repo.save(post);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
