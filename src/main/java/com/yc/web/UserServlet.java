package com.yc.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.bean.UserBean;
import com.yc.dao.UserDao;
import com.yc.service.MailService;
import com.yc.service.ServiceException;
import com.yc.service.UserService;
import com.yc.util.CodeUtil;
import com.yc.util.Result;

@RestController
@RequestMapping("/user")
public class UserServlet {

	@Autowired
	private UserDao dao;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/reg")
	public Result reg(@Valid UserBean bean, Errors errors,String repwd,String code,HttpServletRequest request){
		System.out.println(bean);
		// 判断是否出现验证错误
		if (errors.hasFieldErrors("phone") || errors.hasFieldErrors("name") || errors.hasFieldErrors("account")
				|| errors.hasFieldErrors("pwd") || errors.hasFieldErrors("email")) {
			return Result.failure("字段验证错误！", errors.getAllErrors());
		}
		HttpSession s=request.getSession();
		String qCode=(String) s.getAttribute("qCode");
		System.out.println(qCode);
		System.out.println(code);
		if(qCode==null || !qCode.equalsIgnoreCase(code)){
			return Result.failure("验证码错误！", null);
		}
		try {
			int a=userService.register(bean,repwd);
			if(a==1){
				return Result.success("注册成功", null);
			}else{
				return Result.success("注册失败", null);
			}
		} catch (ServiceException e) {
			return Result.failure(e.getMessage(), null);
		}
	}
	
	@RequestMapping("/sendCode")
	public void sendMail(String email,HttpServletRequest request){
		String qCode=CodeUtil.genCode();
		HttpSession s=request.getSession();
		mailService.sendMail(email, "杰辰购物验证码", qCode);
		s.setAttribute("qCode", qCode);
	}
	
	@RequestMapping("/login")
	public String login(UserBean user,HttpServletRequest request){
		UserBean u;
		try {
			u = userService.login(user);
			if(u==null){
				return "用户名或密码错误";
			}else{
				HttpSession s=request.getSession();
				s.setAttribute("user", u);
				return "登录成功";
			}
		} catch (ServiceException e) {
			return e.getMessage();
		}
		
	}
	
	@RequestMapping("/checkLogin")
	public UserBean checkLogin(HttpServletRequest request){
		HttpSession s=request.getSession();
		UserBean u= (UserBean) s.getAttribute("user");
		System.out.println(u);
		return u;
	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request){
		HttpSession s=request.getSession();
		s.removeAttribute("user");
	}
	
	
}
