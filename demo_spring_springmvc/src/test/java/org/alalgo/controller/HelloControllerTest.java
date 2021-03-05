package org.alalgo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@RunWith(SpringJUnit4ClassRunner.class)  
public class HelloControllerTest extends AbstractTransactionalJUnit4SpringContextTests{
    @Autowired  
    private HelloController controller;  
    @Autowired  
    private RequestMappingHandlerAdapter handlerAdapter;  
  
    private final MockHttpServletRequest request = new MockHttpServletRequest();  
    private final MockHttpServletResponse response = new MockHttpServletResponse();  	
	@Test
	public void testGreeting() {
		
	}
}
