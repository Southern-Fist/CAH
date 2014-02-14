package com.onegirloneguy.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onegirloneguy.controller.vo.SignOnVO;
import com.onegirloneguy.service.UserService;

@Controller
@RequestMapping("user")
public class UserManagementController {

	@Inject
	UserService service;
	
	@RequestMapping("login")
	@ResponseBody
	public SignOnVO signOn(SignOnVO vo){
		
		return service.signOn(vo);
	}
	
	@RequestMapping("register")
	@ResponseBody
	public void registerUser(SignOnVO vo){
		
		vo = vo != null ? vo : new SignOnVO();
		
		service.registerUser(vo);
	}
}
