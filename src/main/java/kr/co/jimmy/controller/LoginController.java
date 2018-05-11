package kr.co.jimmy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jimmy.VO.MemberVO;
import kr.co.jimmy.service.LoginService;

@Controller
@RequestMapping(value = "/user")
public class LoginController {

	@Autowired
	private LoginService service;
	String path = "/jblog/";

	// login form
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		System.out.println("Login-Form");
		return "user/loginForm";
	}

	// login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("id") String id, @RequestParam("password") String password, HttpSession session) {
		System.out.println("Login");
		MemberVO memberVo = service.selectMember(id, password);

		if (memberVo != null) {
			session.setAttribute("authUser", memberVo);
			return "redirect: " + path;
		} else {
			return "redirect: " + path + "user/login?result=fail";
		}
	}

	// logout
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		if (session != null) {
			session.invalidate();
			return "redirect: " + path;
		}
		return "redirect: " + path;
	}
}
