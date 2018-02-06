package com.class430.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.class430.interceptor.LoginInterceptor;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/picture/**").addResourceLocations("file:/root/class430/picture/");
		super.addResourceHandlers(registry);
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addRedirectViewController("/", "/toHome");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/writething").setViewName("writething");
		registry.addViewController("/say").setViewName("writesay");
		registry.addViewController("/register").setViewName("register");
		super.addViewControllers(registry);
	}

//	@Bean
//	public MultipartResolver multipartResolver(){
//		
//		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//		resolver.setMaxUploadSize(10*1024*1024);
//		
//		return resolver;
//	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new LoginInterceptor());
		
		super.addInterceptors(registry);
	}
	
	
}
