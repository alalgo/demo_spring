package org.alalgo;

import java.util.HashMap;
import java.util.Map;

import org.alalgo.exception.BusinessException;
import org.alalgo.exception.NoLoginException;
import org.alalgo.model.BaseResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyControllerAdvice {
	//全局异常处理
	@ExceptionHandler(NoLoginException.class)
	public @ResponseBody BaseResponse nologin() {
		return BaseResponse.Success("no login");
	}
	
	@ExceptionHandler(BusinessException.class)
	public @ResponseBody BaseResponse businessException(Exception e) {
		return BaseResponse.fail(500,e.getMessage());
	}		
	
	//全局数据绑定,我们可以将一些公共的数据定义在添加了 @ControllerAdvice 注解的类中,这样，在每一个 Controller 的接口中，就都能够访问导致这些数据。
    @ModelAttribute(name = "globalConf")
    public Map<String,Object> mydata() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("version", "1.23.1");
        map.put("port", 234);
        return map;
    }	
}
