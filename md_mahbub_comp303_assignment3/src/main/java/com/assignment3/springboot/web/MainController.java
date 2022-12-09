package com.assignment3.springboot.web;

import com.assignment3.springboot.model.Account;
import com.assignment3.springboot.model.AccountType;
import com.assignment3.springboot.model.User;
import com.assignment3.springboot.repository.AccountRepository;
import com.assignment3.springboot.repository.AccountTypesRepository;
import com.assignment3.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
	private Integer userId = Integer.parseInt("1");

	@Autowired
	private AccountTypesRepository accTypesRepository;
	@Autowired
	private AccountRepository accRepository;
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("accounts", accRepository.findAll());
		return "index";
	}

	@GetMapping("/new")
	public String showme(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object id = auth.getDetails();

		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

		List<String> options = new ArrayList<String>();

		List<AccountType> accTypes = accTypesRepository.findAll();

		for (AccountType element : accTypes) {
			options.add(element.getAccountTypeName());
		}

		model.addAttribute("options", options);
		model.addAttribute("user", user);

		return "new";
	}

	@GetMapping("/updateprofile")
	public String showprofile(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object id = auth.getDetails();
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		model.addAttribute("user", user);
		return "updateprofile";
	}

	@ModelAttribute("accc")
	public Account accountx() {
		return new Account();
	}

	@ModelAttribute("updateuser")
	public User userx() {

		return new User();
	}


	@PostMapping("/new")
	public @ResponseBody
	String add(
			@RequestParam("accountNumber") String accountNumber,
			@RequestParam("accountTypeCode") String accountTypeCode,
			@RequestParam("balance") Double balance,
			@RequestParam("overDraftLimit") double overDraftLimit)
	{

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object id = auth.getDetails();
		User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		Integer myCustomerId = user.id;

		Account account=new Account(accountNumber,accountTypeCode, myCustomerId ,balance,overDraftLimit);
		accRepository.save(account);
		return "An account has been added";
	}


	@PostMapping("/updateprofile")
	public
	String edit(
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,
			@RequestParam("address") String address,
			@RequestParam("city") String city,
			@RequestParam("postalcode") String postalcode,
			@RequestParam("phone") String phone,
			@RequestParam("Id") Integer id)
	{

		User user=new User(firstName,lastName,email,address,city, postalcode, phone, id);
		userRepository.save(user);
		return "redirect:/updateprofile?success";
	}


}
