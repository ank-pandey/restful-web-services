package com.abcdeveloper.rest.webservices.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.abcdeveloper.rest.webservices.bean.User;
import com.abcdeveloper.rest.webservices.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	private static Integer userCount = 0;
	
	static {
		users.add(new User(++userCount, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(++userCount, "Bravo", LocalDate.now().minusYears(25)));
		users.add(new User(++userCount, "Chris", LocalDate.now().minusYears(20)));
		users.add(new User(++userCount, "David", LocalDate.now().minusYears(17)));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user){
		if(null == user.getId()) {
			log.info("Initial user count is: {}", userCount);
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		 Optional<User> user = users.stream().filter(u -> u.getId().equals(id)).findFirst();
		 if(user.isEmpty()) {
			 throw new UserNotFoundException("id - "+id+" not found in records!!"); 
		 }
		return user.get();
	}
	
	public User deleteById(int id) {
		//users.removeIf(u -> u.getId().equals(id));
		Iterator<User> iterator = users.iterator(); 
		
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}else {
				 throw new UserNotFoundException("id - "+id+" not found in records!!"); 
			 }
		}		
		return null;
	}
}
