package com.demo.h2.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.h2.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
}
