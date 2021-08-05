package org.alalgo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.alalgo.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class Login {
	@Autowired(required = false)
	private HttpSession session;
	
	@GetMapping("/login")
	public BaseResponse Login() {
		SimpleDateFormat sf = new SimpleDateFormat("hh:mm:ss");
		session.setAttribute("user",sf.format(new Date()));
		return new BaseResponse(200, "login sucess", null);
	}
	@GetMapping("/logout")
	public BaseResponse logout() {
		SimpleDateFormat sf = new SimpleDateFormat("hh:mm:ss");
		session.removeAttribute("user");
		return new BaseResponse(200, "logout sucess", null);
	}	
}
