package com.abcdeveloper.rest.webservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abcdeveloper.rest.webservices.bean.Post;
import com.abcdeveloper.rest.webservices.bean.User;
import com.abcdeveloper.rest.webservices.exception.UserNotFoundException;
import com.abcdeveloper.rest.webservices.repo.PostRepository;
import com.abcdeveloper.rest.webservices.repo.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jpa/users")
public class UserJpaController {

	private UserRepository userRepository;
	
	private PostRepository postRepository;
	
	public UserJpaController(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}
	
	@GetMapping
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();	
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/{id}")
	public EntityModel<User> retrieveUser(@PathVariable String id) {
		Optional<User> user = userRepository.findById(Integer.parseInt(id));
		if(user.isEmpty())
			throw new UserNotFoundException("User not found for Id :" + id);
		EntityModel<User> entity = EntityModel.of(user.get());
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entity.add(linkToUsers.withRel("all-users"));
		return entity;
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Integer id) {
		 userRepository.deleteById(id);	
	}
	
	@GetMapping("/{id}/posts")
	public List<Post> retrievePosts(@PathVariable String id) {
		Optional<User> user = userRepository.findById(Integer.parseInt(id));
		if(user.isEmpty())
			throw new UserNotFoundException("User not found for Id :" + id);
		
		return user.get().getPosts();
	}
	
	@PostMapping("/{id}/posts")
	public ResponseEntity<Object> createPosts(@PathVariable String id, @Valid @RequestBody Post post) {
		Optional<User> user = userRepository.findById(Integer.parseInt(id));
		if(user.isEmpty())
			throw new UserNotFoundException("User not found for Id :" + id);
		post.setUser(user.get());
		Post savedPost = postRepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
