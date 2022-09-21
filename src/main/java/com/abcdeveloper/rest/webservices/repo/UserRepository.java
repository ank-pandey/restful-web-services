package com.abcdeveloper.rest.webservices.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abcdeveloper.rest.webservices.bean.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
