package com.abcdeveloper.rest.webservices.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties({"field1", "field2"})
public class StaticBean {
	private String field1;
	@JsonIgnore
	private String field2;
	private String field3;
}
