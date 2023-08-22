package com.IlyaJukov.SocialMedia.services.post;

import com.IlyaJukov.SocialMedia.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> { }
