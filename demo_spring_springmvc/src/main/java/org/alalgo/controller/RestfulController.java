package org.alalgo.controller;

import java.util.Date;

import org.alalgo.model.BaseResponse;
import org.alalgo.model.SpringProject;
import org.alalgo.service.RestfulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/restful")
public class RestfulController {
	@Autowired
	private RestfulService restful;
	
	@RequestMapping(value="/project/{projectname}",method=RequestMethod.GET)
	public SpringProject getProject(@PathVariable String projectname) {
		return new SpringProject(projectname, new Date(), "good project");
	}
	@GetMapping(value="/projectShortcut/{projectname}")
	public SpringProject getProjectShortcut (@PathVariable String projectname) {
		return new SpringProject(projectname, new Date(), "good project");
	}
	
	@RequestMapping(value="/project",method=RequestMethod.GET)
	public SpringProject getProjectInfo(
			 @RequestParam(value = "projectname",required=false) String projectname,
			 @RequestParam String desc1,
			 @RequestParam String desc2) {
		String desc = restful.search() + desc1 + desc2;
		return new SpringProject(projectname, new Date(),desc);
	}	
	@GetMapping("/requestInfo")
	public BaseResponse handle(
	        @RequestHeader("Accept-Encoding") String encoding, 
	        @RequestHeader(required=false,value="Keep-Alive") Long keepAlive) { 
		
		return BaseResponse.Success("Accept-Encoding:"+encoding+";Keep-Alive:"+keepAlive);
	}	
	
	@GetMapping("/urlpattern/t/?project")//一个
	public SpringProject urlpattern(@PathVariable String variable) {
		return new SpringProject(variable, null, "no");
		//return "[] " + variable;
	}
	@GetMapping("/urlpattern/{name:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{ext:\\.[a-z]+}")
	public String urlpattern1(@PathVariable String name, @PathVariable String version, @PathVariable String ext) {
		return "[{name:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{ext:\\.[a-z]+}] " + name + "." + version + "." + ext;
	}
	@GetMapping("/urlpattern/*projec") //零或多个
	public String urlpattern2() {
		return "[*projec]";
	}	
	@GetMapping("/urlpattern/multi/**") //搭配多个路径段
	public String urlpattern3(@PathVariable String variable) {
		return "[] " + variable;
	}	
}
