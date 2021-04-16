package com.yc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yc.web.LoginInterceptor;

@SpringBootApplication
@MapperScan("com.yc.dao")
@EnableTransactionManagement
public class StartApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}
	
	/**
	 * 	注册拦截器
	 * 	拦截资源的配置
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns(
				// 配置要拦截的资源
				"/checkLogin",
				"/cart/findCart",
				"/cart/addCart",
				"/Member*");
	}
}
