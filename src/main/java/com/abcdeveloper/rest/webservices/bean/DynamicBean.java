package com.abcdeveloper.rest.webservices.bean;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFilter("DynamicBeanFilter")
public class DynamicBean {
	private String field1;
	private String field2;
	private String field3;
}
