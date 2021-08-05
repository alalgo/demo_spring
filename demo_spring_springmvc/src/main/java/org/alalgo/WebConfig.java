package org.alalgo;

import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;

import org.alalgo.ViewResolverConf.MyViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Configuration
@EnableWebMvc
public class WebConfig  implements WebMvcConfigurer{
	
	//----------- 拦截器 ---------------
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new LocaleChangeInterceptor());
		//registry.addInterceptor(new ThemeChangeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");
		registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/error/**","/login");
	}
	
	
	//----------- 过滤器 ---------------
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
			.allowedOrigins("http://localhost:8081")
			.maxAge(300L);
	}
	 
    @Autowired
    private MyFilter myFilter;
    @Bean
    public FilterRegistrationBean registerMyFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(myFilter);
        registration.addUrlPatterns("/restful/project/*");
        registration.setName("myFilter");
        registration.setOrder(1); 
        return registration;
    }
    
	@Bean
	public FilterRegistrationBean<Filter> myCharaterEncoding(){
		FilterRegistrationBean<Filter> myFilter = new FilterRegistrationBean<>();
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setForceEncoding(true);
		myFilter.setFilter(encodingFilter);
		myFilter.addUrlPatterns("/*");
		return myFilter;
	}

	//----------- HttpMessageConverter ---------------
	/**
	 * 自定义HttpMessageConverter,用albaba的fastjson替代内置的jackonjson
	 */	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fjc = new FastJsonHttpMessageConverter();
        FastJsonConfig fj = new FastJsonConfig();
        fj.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        fjc.setFastJsonConfig(fj);
        fjc.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        converters.add(fjc);
	}
	
	//----------- ViewController ---------------
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}

	//----------- ViewResolver ---------------
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(new MyViewResolver());
        //InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        //internalResourceViewResolver.setPrefix("/templates/");
        //internalResourceViewResolver.setSuffix(".html");
        //registry.viewResolver(internalResourceViewResolver);
	}  
   	
	
}
