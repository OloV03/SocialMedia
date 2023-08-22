package com.IlyaJukov.SocialMedia.services.user;

import com.IlyaJukov.SocialMedia.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> getAll();

    List<User> getUsersByIdIn(List<Long> ids);

    Optional<User> findByLogin(String login);
}
