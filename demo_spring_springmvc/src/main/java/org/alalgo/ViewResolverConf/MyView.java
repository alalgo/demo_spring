package org.alalgo.ViewResolverConf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

public class MyView implements View {

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
       for(Map.Entry<String, ?> entry : model.entrySet()) {
            response.getWriter().write(entry.getKey() + " " + entry.getValue());
        }
	}

}
