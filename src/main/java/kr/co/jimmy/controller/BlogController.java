package kr.co.jimmy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.jimmy.DAO.BlogPostDAO;
import kr.co.jimmy.VO.BlogAdminVO;
import kr.co.jimmy.VO.BlogPostVO;
import kr.co.jimmy.VO.BlogVO;
import kr.co.jimmy.service.BlogAdminService;
import kr.co.jimmy.service.BlogService;

@Controller
@RequestMapping(value="{id}")
public class BlogController {
	
	@Autowired
	private BlogService service;
	@Autowired
	private BlogAdminService a_service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String blogMain(@PathVariable("id") String id, Model model) {
		System.out.println("Blog-Home");
		BlogVO blogVo = service.selectMemberId(id);
		List<BlogAdminVO> list = a_service.selectList(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("list",list);
		return "blog/blog-main";
	}
	
	@RequestMapping(value="/cate", method=RequestMethod.POST)
	public String selectMainCategory(@PathVariable("id") String id,@RequestParam("cateNo") int cateNo, Model model){
		List<BlogPostVO> list = service.selectAll(cateNo);
		System.out.println(list.toString());
		model.addAttribute("cateList", list);
		return "blog/blog-main";
	}
	
}
