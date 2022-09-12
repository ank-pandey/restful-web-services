package com.abcdeveloper.rest.webservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

import com.abcdeveloper.rest.webservices.bean.User;
import com.abcdeveloper.rest.webservices.dao.UserDaoService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserDaoService userDaoService;
	
	public UserController(UserDaoService userDaoService) {
		this.userDaoService = userDaoService;
	}
	
	@GetMapping
	public List<User> retrieveAllUsers() {
		return userDaoService.findAll();	
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/{id}")
	public EntityModel<User> retrieveUser(@PathVariable String id) {
		User user = userDaoService.findOne(Integer.parseInt(id));
		EntityModel<User> entity = EntityModel.of(user);
		WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entity.add(linkToUsers.withRel("all-users"));
		return entity;
	}
	
	@DeleteMapping("/{id}")
	public User deleteUser(@PathVariable String id) {
		return userDaoService.deleteById(Integer.parseInt(id));	
	}
}
