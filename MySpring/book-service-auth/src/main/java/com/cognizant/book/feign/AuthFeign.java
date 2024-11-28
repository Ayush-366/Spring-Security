package com.cognizant.book.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;

//@Headers("Content-Type: application/json")
//@FeignClient(name = "emp-ws", url = "${feign.url}")
@FeignClient(name = "auth-ws", url = "${EMPLOYEE_SERVICE:http://localhost:9192}")
public interface AuthFeign {
	@GetMapping("/validate") 
	public boolean getTokenValidity(@RequestHeader("Authorization") String token);
}


