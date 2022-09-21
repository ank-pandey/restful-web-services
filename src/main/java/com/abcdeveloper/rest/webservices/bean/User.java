package com.abcdeveloper.rest.webservices.bean;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_details")
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	//@JsonProperty("user_name")
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String name;
	
	//@JsonProperty("birth_date")
	@Past(message = "Birth Date should be in the past")
	private LocalDate birthDate;
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Post> posts;

	public User(Integer id, String name, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
}
