package com.agbb.userdata.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.agbb.userdata.entity.User;

public interface UserDataRepository extends CrudRepository<User, Integer>{

	public List<User> findByUserName(String userName);
}
