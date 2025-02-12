package com.cg.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.AuthRequest;
import com.cg.dto.AuthResponse;
import com.cg.jwt.service.JwtUtil;

@RestController
public class UserController {
	  
	@Autowired
	private JwtUtil jwtService;
 
	    @Autowired
	    private AuthenticationManager authenticationManager;
 
	   
	    @PostMapping("/authenticate")   //POST : http://localhost:9091/products/authenticate
	    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	        if (authentication.isAuthenticated()) {
	            return jwtService.generateToken(authRequest.getUsername());
	        } else {
	            throw new UsernameNotFoundException("invalid user request !");
	        }
	    }
	    
	    @GetMapping(value = "/validate")
    	public boolean getValidation(@RequestHeader("Authorization") String token){
    		token = token.substring(7);
    		AuthResponse auth = new AuthResponse();
    	
    		//log.info("Token validation for "+jwtUtil.extractUsername(token));
    		
    		if(jwtService.validateToken(token)) {
    			
    			System.out.println("Token validated");
    			return true;
    		}
    		else {
    			System.out.println("Token NOT validated");
    			return false;

    	}
}
}