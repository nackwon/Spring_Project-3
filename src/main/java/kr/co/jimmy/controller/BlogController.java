package kr.co.jimmy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

import kr.co.jimmy.VO.BlogAdminVO;
import kr.co.jimmy.VO.BlogPostVO;
import kr.co.jimmy.VO.BlogVO;
import kr.co.jimmy.VO.CommentVO;
import kr.co.jimmy.service.BlogAdminService;
import kr.co.jimmy.service.BlogCommentService;
import kr.co.jimmy.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService service;
	@Autowired
	private BlogAdminService a_service;
	@Autowired
	private BlogCommentService c_service;
	
	@RequestMapping(value = "{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String blogMain(@PathVariable("id") String id, Model model) {
		BlogVO blogVo = service.selectMemberId(id);
		List<BlogAdminVO> list = a_service.selectList(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("list", list);
		return "blog/blog-main";
	}
	
	@ResponseBody
	@RequestMapping(value = "{id}/postList", method = RequestMethod.POST)
	public List<BlogPostVO> blogMainCate(@PathVariable("id") String id, @RequestParam(value="cateNo") int cateNo) {
		List<BlogPostVO> cateList = service.selectAll(cateNo);
		return cateList;
	}

	@ResponseBody
	@RequestMapping(value = "{id}/post", method = {RequestMethod.POST, RequestMethod.GET})
	public BlogPostVO blogMainPost(@PathVariable("id") String id,@RequestParam(value = "postNo",required=false,defaultValue = "-1") int postNo) {
		BlogPostVO blogpostVo = service.select(postNo);
		return blogpostVo;
	}
	
	@ResponseBody
	@RequestMapping(value="{id}/cmtinsert", method=RequestMethod.POST)
	public CommentVO blogMainComment(@PathVariable("id") String id, @ModelAttribute CommentVO cmtVo) {
		System.out.println(cmtVo.toString());
		return c_service.insertComment(cmtVo);
	}
	
	@ResponseBody
	@RequestMapping(value="{id}/commentList", method=RequestMethod.GET)
	public List<CommentVO> commentList(@PathVariable("id") String id, @RequestParam("postNo") int postNo){
		List<CommentVO> list = c_service.selectAll(postNo);
		return list;
	}
}
