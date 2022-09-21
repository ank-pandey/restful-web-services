package com.abcdeveloper.rest.webservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcdeveloper.rest.webservices.bean.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
