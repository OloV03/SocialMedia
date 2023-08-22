package com.IlyaJukov.SocialMedia;

import com.IlyaJukov.SocialMedia.models.Post;
import com.IlyaJukov.SocialMedia.models.User;
import com.IlyaJukov.SocialMedia.services.post.PostRepository;
import com.IlyaJukov.SocialMedia.services.post.PostService;
import com.IlyaJukov.SocialMedia.services.user.UserRepository;
import com.IlyaJukov.SocialMedia.services.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SocialMediaApiApplicationTests {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PostService postService;
	@Autowired
	private PostRepository postRepo;

	@Test
	void userFinding() {
		var tUser = new User();
		tUser.setName("tUser");
		tUser.setLogin("testLog");
		tUser.setPass("123");

		userRepo.save(tUser);
		var result = userService.getUserByLogin(tUser.getLogin());

		assertEquals(result.getName(), tUser.getName());

		userRepo.delete(tUser);
	}

	@Test
	void duplicateLogin() {
		var tUser1 = new User();
		tUser1.setName("tUser1");
		tUser1.setLogin("testLog");
		tUser1.setPass("123");
		userRepo.save(tUser1);

		var tUser2 = new User();
		tUser2.setName("tUser2");
		tUser2.setLogin("testLog");
		tUser2.setPass("123");

		assertThrows(
				IllegalArgumentException.class,
				() -> userService.save(tUser2)
		);

		userRepo.delete(tUser1);
		userRepo.delete(tUser2);
	}

	@Test
	void duplicatePostTitle() {
		var post1 = new Post();
		post1.setUserId(1L);
		post1.setTitle("title1");
		postService.save(post1);

		var post2 = new Post();
		post2.setUserId(1L);
		post2.setTitle("title1");

		assertThrows(
				IllegalArgumentException.class,
				() -> postService.save(post2)
		);

		postRepo.delete(post1);
		postRepo.delete(post2);
	}
}
