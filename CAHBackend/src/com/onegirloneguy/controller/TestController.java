package com.onegirloneguy.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onegirloneguy.controller.vo.SignOnVO;
import com.onegirloneguy.service.UserService;
import com.oneguyonegirl.security.PasswordHandler;
import com.oneguyonegirl.util.CAHUtil;

@Controller
@RequestMapping("test")
public class TestController {

	
	@Inject
	private UserService service;
	
	@RequestMapping("login")
	@ResponseBody
	public SignOnVO signOn(final SignOnVO vo){
		vo.setUsername("Mikey");
		vo.setPassword("m@rr!25ss");
		return service.signOn(vo);
	}
	
	@RequestMapping("register")
	@ResponseBody
	public void registerUser(SignOnVO vo){
		
		vo = vo != null ? vo : new SignOnVO();
		vo.setUsername("Mikey");
		vo.setPassword("m@rr!25s");
		service.registerUser(vo);
	}
	
	@RequestMapping("register/rand")
	@ResponseBody
	public void randRegisterUser(SignOnVO vo){
		
		vo = vo != null ? vo : new SignOnVO();
		vo.setUsername("Mikey" + PasswordHandler.getInstance().generateHashSalt(2));
		vo.setPassword("m@rr!25s");
		service.registerUser(vo);
	}
}
