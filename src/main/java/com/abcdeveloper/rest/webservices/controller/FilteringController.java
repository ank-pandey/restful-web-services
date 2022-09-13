package com.abcdeveloper.rest.webservices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abcdeveloper.rest.webservices.bean.DynamicBean;
import com.abcdeveloper.rest.webservices.bean.StaticBean;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public StaticBean filtering() {
		return new StaticBean("value1", "value2", "value3");
	}
	
	@GetMapping("/filtering-list")
	public List<StaticBean> filteringList() {
		return Arrays.asList(new StaticBean("value1", "value2", "value3"),
				new StaticBean("value4", "value5", "value6"));
	}
	
	@GetMapping("/filtering-dynamic")
	public MappingJacksonValue filteringDynamic() {
		DynamicBean dynamicBean = new DynamicBean("value1", "value2", "value3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dynamicBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
	
	@GetMapping("/filtering-dynamic-list")
	public MappingJacksonValue filteringDynamicList() {
		 List<DynamicBean> list = Arrays.asList(new DynamicBean("value1", "value2", "value3"),
				new DynamicBean("value4", "value5", "value6"));
		 MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		 SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2");
		 FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicBeanFilter", filter);
		 mappingJacksonValue.setFilters(filters);
		 return mappingJacksonValue;
	}
}
