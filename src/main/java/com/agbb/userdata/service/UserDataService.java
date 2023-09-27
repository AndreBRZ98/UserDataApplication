package com.agbb.userdata.service;

import java.util.List;

import com.agbb.userdata.dto.UserDTO;
import com.agbb.userdata.exception.UserDataException;

public interface UserDataService {
	
	public Integer addUser(UserDTO userDTO) throws UserDataException;
	public List<UserDTO> getDetailsByUserName(String userName) throws UserDataException;

}
