package com.assignment3.springboot.repository;

import com.assignment3.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);

}
