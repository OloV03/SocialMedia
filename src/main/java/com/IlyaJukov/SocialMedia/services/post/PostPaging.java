package com.IlyaJukov.SocialMedia.services.post;

import com.IlyaJukov.SocialMedia.models.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostPaging extends PagingAndSortingRepository<Post, Long> {
    List<Post> findAllByUserIdIn(List<Long> usersId, PageRequest pageRequest);
}
