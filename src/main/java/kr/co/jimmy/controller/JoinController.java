package kr.co.jimmy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.jimmy.VO.MemberVO;
import kr.co.jimmy.service.JoinService;

@Controller
@RequestMapping(value="/user")
public class JoinController {
	
	@Autowired
	private JoinService service;
	String path = "/jblog/";
	
	// join form
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String joinForm() {
		System.out.println("Join-Form");
		return "user/joinForm";
	}
	
	// join and blog create
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute MemberVO memberVo) {
		System.out.println("Join-Success");
		System.out.println(memberVo.toString());
		service.insertMember(memberVo);
		return "user/joinSuccess";
	}
	
	// join id check
	@ResponseBody
	@RequestMapping(value="/idcheck", method=RequestMethod.GET)
	public boolean joinIdCheck(@RequestParam("id") String id) {
		return service.selectMemberId(id);
	}

}
