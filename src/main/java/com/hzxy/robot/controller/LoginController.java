package com.hzxy.robot.controller;

import com.hzxy.robot.annotation.Log;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception{
		System.out.println("HomeController.login()");
		// 登录失败从request中获取shiro处理的异常信息。
		// shiroLoginFailure:就是shiro异常类的全类名.
		String exception = (String) request.getAttribute("shiroLoginFailure");
		System.out.println("exception=" + exception);
		String msg = "";
		if (exception != null) {
			if (AuthenticationException.class.getName().equals(exception)) {
				System.out.println("UnknownAccountException -- > 账号不存在：");
				msg = "账号不存在";
			} else if (IncorrectCredentialsException.class.getName().equals(exception)) {
				System.out.println("IncorrectCredentialsException -- > 密码不正确：");
				msg = "密码不正确";
			} else if ("kaptchaValidateFailed".equals(exception)) {
				System.out.println("kaptchaValidateFailed -- > 验证码错误");
				msg = "kaptchaValidateFailed -- > 验证码错误";
			} else {
				msg = "账号或密码不正确";
				System.out.println("else -- >" + exception);
			}
		}
		map.put("msg", msg);
        return "login";
    }

	@RequestMapping("/index")
	public String index(){
		return "index";
	}

	@RequestMapping("/index2")
	public String index2(){
		return "index2";
	}
	@Log(type = "错误界面")
	@RequestMapping("/403")
	public String error(){
		return "403";
	}



}
