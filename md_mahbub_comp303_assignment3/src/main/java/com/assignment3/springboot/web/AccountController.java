package com.assignment3.springboot.web;

import com.assignment3.springboot.model.Account;
import com.assignment3.springboot.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AccountController {
	
	@Autowired
    AccountRepository accService;
	
	@ResponseBody
	@RequestMapping("")
	public Iterable<Account> home() {
		        return accService.findAll();
	}


    @GetMapping("/get-account/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") Integer id) {
        Optional<Account> accountData = accService.findById(id);

        if (accountData.isPresent()) {
            return new ResponseEntity<>(accountData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    Iterable<Account> getAccounts() {
        return accService.findAll();
    }

    @RequestMapping(value = "/save-account", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    void addAccount(@RequestBody Account acc) throws Exception {
        System.out.println("Insert");
        System.out.println(acc.getAccountNumber());

        accService.save(acc);
    }

    @RequestMapping(value = "/account/{accId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    void updateAccount(@PathVariable("accId") Integer accId, @RequestBody Account acc) throws Exception {
        acc.setId(accId);
        accService.save(acc);
    }

    @RequestMapping(value = "/account/{accId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    void deleteAccount(@PathVariable("accId") int accId) throws Exception {
        accService.deleteById(accId);
    }
}
