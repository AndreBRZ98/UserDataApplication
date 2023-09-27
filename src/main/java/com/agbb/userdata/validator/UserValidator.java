package com.agbb.userdata.validator;

import com.agbb.userdata.dto.UserDTO;
import com.agbb.userdata.exception.UserDataException;

public class UserValidator {
	
	public void validate(UserDTO userDTO) throws UserDataException {
		if(!validatePassword(userDTO.getPassword())) {
			throw new UserDataException("Validator.INVALID_PASSWORD");
		}
	}
	
	public boolean validatePassword(String password) {
		return password.matches("[a-zA-Z0-9]{10}");
	}

}
