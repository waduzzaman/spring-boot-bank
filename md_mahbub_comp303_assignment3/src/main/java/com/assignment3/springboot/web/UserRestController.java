package com.assignment3.springboot.web;

import com.assignment3.springboot.model.User;
import com.assignment3.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserRestController {

	@Autowired
    UserRepository userRepository;

	// RESTful API Endpoints


	@GetMapping("/get-user/{id}")
	public ResponseEntity<User> getUserInfo(@PathVariable("id") Integer id) {
		Optional<User> accountData = userRepository.findById(id);

		if (accountData.isPresent()) {
			return new ResponseEntity<>(accountData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@RequestMapping(value = "/users", method = RequestMethod.GET)
	Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@RequestMapping(value = "/save-user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	void addNewUser(@RequestBody User acc) throws Exception {
		System.out.println("Insert");
		System.out.println(acc.getId());

		userRepository.save(acc);
	}

	@RequestMapping(value = "/user/{accId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	void updateUserInfo(@PathVariable("accId") Integer accId, @RequestBody User acc) throws Exception {
		acc.setId(accId);
		userRepository.save(acc);
	}

	@RequestMapping(value = "/user/{accId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	void deleteUser(@PathVariable("accId") Integer accId) throws Exception {
		userRepository.deleteById(accId);
	}

}
