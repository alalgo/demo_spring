package org.alalgo.ViewResolverConf;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class MyViewResolver implements ViewResolver {

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		log.debug("viewName="+viewName);
		//return new MyView();
		return null;
	}

}
