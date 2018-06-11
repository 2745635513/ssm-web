package com.example.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Login;
import com.example.demo.entity.MD5;
import com.example.demo.service.LoginService;

@RestController
@RequestMapping(value = "/manager")
public class ManagerLogin {

	@Resource
	private LoginService loginService;
	private Login login;
	private static final Log logger = (Log) LogFactory.getLog(ManagerLogin.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		logger.info("login GET方法被调用。。。");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(String loginname, String passwd, Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Map<String, Object>> result = this.loginService.getLoginById();

		logger.info("登录名：" + loginname + "密码：" + passwd);
		String passwdMD5 = MD5.getResult(passwd);
		Map<String, Object> map = result.get(0);
		String email = (String) map.get("email");
		System.out.println(email);
		String passWord = (String) map.get("passWord");
		System.out.println(passWord);		
		if (email.equals(loginname) && passWord.equals(passwdMD5)) {
			model.addAttribute("login", login);
			response.sendRedirect("/pages/managerIndex.html");
		} else {
			logger.info("重新登录");
			response.sendRedirect("/pages/fail.html");
		}

	}

	// 检测数据是否存在
	@ResponseBody
	@RequestMapping(value = "/login2", method = RequestMethod.GET)
	public List<Map<String, Object>> ajaxshowAdd(HttpServletRequest request, Model model) {
		List<Map<String, Object>> login = this.loginService.getLoginById();
		return login;
	}

}
