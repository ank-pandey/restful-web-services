package com.abcdeveloper.rest.webservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abcdeveloper.rest.webservices.bean.Name;
import com.abcdeveloper.rest.webservices.bean.PersonV1;
import com.abcdeveloper.rest.webservices.bean.PersonV2;

@RestController
public class VersioningController {

	@GetMapping("/v1/person")
	public PersonV1 getV1Person() {
		return new PersonV1("Bob Tob");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getV2Person() {
		return new PersonV2(new Name("Bob", "Tob"));
	}
	
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getV1PersonWithParam() {
		return new PersonV1("Bob Tob");
	}
	
	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getV2PersonWithParam() {
		return new PersonV2(new Name("Bob", "Tob"));
	}
	
	@GetMapping(path = "/person", headers = "X-API-VERSION=1")
	public PersonV1 getV1PersonWithHeader() {
		return new PersonV1("Bob Tob");
	}
	
	@GetMapping(path = "/person", headers = "X-API-VERSION=2")
	public PersonV2 getV2PersonWithHeader() {
		return new PersonV2(new Name("Bob", "Tob"));
	}
	
	@GetMapping(path = "/person", produces  = "application/abc.app-v1+json")
	public PersonV1 getV1PersonWithProduces() {
		return new PersonV1("Bob Tob");
	}
	
	@GetMapping(path = "/person", produces = "application/abc.app-v2+json")
	public PersonV2 getV2PersonWithProduces() {
		return new PersonV2(new Name("Bob", "Tob"));
	}
}
