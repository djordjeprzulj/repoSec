package com.example.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.security.model.TUser;
import com.example.security.repository.TUserRepository;

@Controller
@RequestMapping("/users")
public class UserListController {

	@Autowired
	private TUserRepository tUserRepository;		

	@GetMapping
	public ModelAndView getAllUsersView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("userslist");
		mav.addObject("users", this.tUserRepository.findAll());
		return mav;
	}

	@GetMapping("create-user")
	public ModelAndView createUserView() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("add-user");
		mav.addObject("user", new TUser());
		return mav;
	}

	@PostMapping("create-user")
	public ModelAndView createUser(@Valid TUser user, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName("add-user");
			mav.addObject("user", user);
			return mav;
		}		
		tUserRepository.save(user);
		mav.addObject("allUsers", tUserRepository.findAll());
		mav.setViewName("user-info");	    
		return mav;
	}

}
