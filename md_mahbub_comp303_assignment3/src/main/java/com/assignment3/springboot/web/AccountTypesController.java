package com.assignment3.springboot.web;

import com.assignment3.springboot.model.AccountType;
import com.assignment3.springboot.repository.AccountTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AccountTypesController {

	@Autowired
	AccountTypesRepository accTypeRepository;

	// RESTful API Endpoints
	@GetMapping("/get-actype/{id}")
	public ResponseEntity<AccountType> getUserInfo(@PathVariable("id") Integer id) {
		Optional<AccountType> accountData = accTypeRepository.findById(id);

		if (accountData.isPresent()) {
			return new ResponseEntity<>(accountData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@RequestMapping(value = "/actypes", method = RequestMethod.GET)
	Iterable<AccountType> getAllUsers() {
		return accTypeRepository.findAll();
	}

	@RequestMapping(value = "/save-actype", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	void addNewUser(@RequestBody AccountType acc) throws Exception {
		System.out.println("Insert");
		System.out.println(acc.getId());

		accTypeRepository.save(acc);
	}

	@RequestMapping(value = "/actype/{accId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	void updateUserInfo(@PathVariable("accId") Integer accId, @RequestBody AccountType acc) throws Exception {
		acc.setId(accId);
		accTypeRepository.save(acc);
	}

	@RequestMapping(value = "/actype/{accId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	void deleteUser(@PathVariable("accId") Integer accId) throws Exception {
		accTypeRepository.deleteById(accId);
	}

}
