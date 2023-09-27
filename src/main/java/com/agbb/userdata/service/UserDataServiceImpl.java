package com.agbb.userdata.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agbb.userdata.dto.UserDTO;
import com.agbb.userdata.entity.User;
import com.agbb.userdata.exception.UserDataException;
import com.agbb.userdata.repository.UserDataRepository;
import com.agbb.userdata.validator.UserValidator;

import jakarta.transaction.Transactional;

@Service(value = "userDataService")
@Transactional
public class UserDataServiceImpl implements UserDataService {
	
	@Autowired
	private UserDataRepository userDataRepository;

	@Override
	public Integer addUser(UserDTO userDTO) throws UserDataException {
		UserValidator userValidator = new UserValidator();
		userValidator.validate(userDTO);
		User user = new User();
		user.setCity(userDTO.getCity());
		user.setPassword(userDTO.getPassword());
		user.setPhoneNo(userDTO.getPhoneNo());
		user.setUserName(userDTO.getUserName());
		Integer userId = userDataRepository.save(user).getUserId();
		return userId;
	}

	@Override
	public List<UserDTO> getDetailsByUserName(String userName) throws UserDataException {
		Iterable<User> userIterable= userDataRepository.findByUserName(userName);
		List<UserDTO> userDTOs = new ArrayList<>();
		userIterable.forEach(user->{
			UserDTO userDTO = new UserDTO();
			userDTO.setCity(user.getCity());
			userDTO.setPassword(user.getPassword());
			userDTO.setPhoneNo(user.getPhoneNo());
			userDTO.setUserId(user.getUserId());
			userDTO.setUserName(user.getUserName());
			userDTOs.add(userDTO);
		});
		if(userDTOs.isEmpty()) {
			throw new UserDataException("Service.NO_DETAILS_FOUND");
		}
		return userDTOs;
	}

}
