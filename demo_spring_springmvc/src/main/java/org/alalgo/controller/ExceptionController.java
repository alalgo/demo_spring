package org.alalgo.controller;

import org.alalgo.exception.BusinessException;
import org.alalgo.model.BaseResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ExceptionController {
    @GetMapping("/exception")
    public String handle(Model model) {
    	log.debug("ExceptionController:" + model.getAttribute("globalConf").toString());
    	if(true) throw new BusinessException("业务异常123d");
        return "common1";
    }
    @GetMapping("/error")
    public BaseResponse handle() {
        return BaseResponse.fail(500, "系统未知错误");
    }    
}
