package org.alalgo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alalgo.model.BaseResponse;
import org.alalgo.model.SpringProject;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class CommonController {
	@GetMapping("/home")
	public String home(RedirectAttributes attributes) {
		return "index";
	}
	@GetMapping("/index")
	public @ResponseBody BaseResponse greeting(RedirectAttributes attributes,String type) {
		type = (type==null)?(String) attributes.getAttribute("type"):type;
		return BaseResponse.Success("index page " + type);
	}
	//通过RedirectAttributes传递参数
	@GetMapping("redirect")
    public String redirect(RedirectAttributes attributes,HttpServletResponse response) throws IOException {
		attributes.addAttribute("type", "redirect");
		response.sendRedirect("/index");
	    return null; 
    }	
	//通过拼接传递参数
	@GetMapping("redirect1")
    public String redirect1() {
        return "redirect:/index?type=redirect";
    }	
	
	@GetMapping("hi1")
    public String hi1(Model model) {
		SpringProject springProject = new SpringProject("spring boot", new Date(), "desc h1");
		model.addAttribute("SpringProject", springProject);
        return "hi1";
    }	
	@GetMapping("hi2")
    public String hi2(ModelMap modelmap) {
		SpringProject springProject = new SpringProject("spring boot", new Date(), "desc h2");
		modelmap.addAttribute("SpringProject", springProject);
        return "hi2";
    }
	@GetMapping("hi3")
    public ModelAndView hi3() {
		SpringProject springProject = new SpringProject("spring boot", new Date(), "desc h2");
		ModelAndView mav = new ModelAndView();
		mav.addObject("SpringProject", springProject);
		mav.setViewName("hi3");
        return mav;
    }	
	
	@GetMapping("forward")
    public String forward(HttpServletRequest request) {
		request.setAttribute("type", "forward");
        return "forward:/index";
    }	
    
	@PostMapping("/post")
	public String post() {
		return "post";
	}
	@DeleteMapping("/delete")
	public String delete() {
		return "delete";
	}
	@PatchMapping("/patch")
	public String patch() {
		return "patch";
	}
	@GetMapping("/methodArguments")
	public @ResponseBody BaseResponse methodArguments(HttpSession httpSession,HttpMethod httpMethod,Locale locale) {
		log.debug("session info:id-{},CreationTime-{}",httpSession.getId(),httpSession.getCreationTime());
		log.debug("httpmethod:{}",httpMethod.GET);
		log.debug("Locale:{}",Locale.getAvailableLocales());
		return BaseResponse.DefaultSuccess();
	}
}
