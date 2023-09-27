package com.agbb.userdata.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agbb.userdata.dto.UserDTO;
import com.agbb.userdata.exception.UserDataException;
import com.agbb.userdata.service.UserDataService;

@RestController
@RequestMapping(value = "/api")
public class UserDataAPI {
	
	@Autowired
	private Environment environment;
	@Autowired
	private UserDataService userDataService;
	
	@PostMapping(value = "/user")
	public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) 
			throws UserDataException{
		Integer userId = userDataService.addUser(userDTO);
		String successMessage = environment.getProperty("API.ADDITION_SUCCESS")+userId;
		return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/user/{userName}")
	public ResponseEntity<List<UserDTO>> getDetailsByUserName(@PathVariable String userName) 
			throws UserDataException{
		List<UserDTO> userDTOs = userDataService.getDetailsByUserName(userName);
		return new ResponseEntity<>(userDTOs, HttpStatus.OK);
		
	}
}
