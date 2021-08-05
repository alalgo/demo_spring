package org.alalgo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alalgo.exception.NoLoginException;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("==LoginInterceptor");
		Object obj = request.getSession().getAttribute("user");
		log.debug("session user:"+obj);
		if(obj!=null){
			return true;
		}
		throw new NoLoginException();
	}
}
