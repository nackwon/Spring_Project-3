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
import org.springframework.web.multipart.MultipartFile;

import kr.co.jimmy.VO.BlogAdminVO;
import kr.co.jimmy.VO.BlogPostVO;
import kr.co.jimmy.VO.BlogVO;
import kr.co.jimmy.VO.FileVO;
import kr.co.jimmy.service.BlogAdminService;
import kr.co.jimmy.service.BlogPostService;
import kr.co.jimmy.service.BlogService;

@Controller
@RequestMapping(value="{id}/admin")
public class BlogAdminController {
	
	@Autowired
	private BlogAdminService service;
	@Autowired
	private BlogService b_service;
	@Autowired
	private BlogPostService p_service;
	
	// blog profile form
	@RequestMapping(value="/basic", method=RequestMethod.GET)
	public String blogAdminForm(Model model, @PathVariable("id") String id) {
		System.out.println("Admin-Basic");
		BlogVO blogVo = b_service.selectMemberId(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/blog-admin-basic";
	}
	
	// blog profile setting
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String blogSetting(@RequestParam("file") MultipartFile file, @ModelAttribute BlogVO blogVo, @PathVariable("id") String id) {
		System.out.println("Admin-Setting");
		System.out.println(file.toString());
		System.out.println(blogVo.toString());
		if(file.isEmpty()) {
			System.out.println("file null");
			service.updateBlogProfile(blogVo, null);
		}else {
			System.out.println("file OK");
			service.updateBlogProfile(blogVo, file);
		}
		return "blog/admin/blog-admin-basic";
	}
	
	// blog category show
	@RequestMapping(value="/category", method=RequestMethod.GET)
	public String blogCategory(Model model, @PathVariable("id") String id) {
		System.out.println("Admin-cate");
		BlogVO blogVo = b_service.selectMemberId(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin/blog-admin-cate";
	}
	
	// blog category List show
	@ResponseBody
	@RequestMapping(value="/categoryList", method=RequestMethod.GET)
	public List<BlogAdminVO> blogCategoryList(@PathVariable("id") String id) {
		List<BlogAdminVO> list = service.selectList(id);
		return list;
	}
	
	// blog category insert
	@ResponseBody
	@RequestMapping(value="/cateinsert", method=RequestMethod.POST)
	public BlogAdminVO blogCategoryInsert(@ModelAttribute BlogAdminVO cateVo) {
		System.out.println("Cate-Insert");
		return service.insertBlogCategory(cateVo);
	}
	
	// blog category delete
	@ResponseBody
	@RequestMapping(value="/catedel", method=RequestMethod.GET)
	public boolean blogCategoryDelete(@RequestParam("cateNo") int cateNo) {
		return service.deleteCategory(cateNo);
	}
	
	// blog Post form
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String blogPostForm(Model model, @PathVariable("id") String id) {
		System.out.println("post-form");
		BlogVO blogVo = b_service.selectMemberId(id);
		List<BlogAdminVO> list = service.selectList(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("list",list);
		return "blog/admin/blog-admin-write";
	}
	
	// blog Post write
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String blogPostWrite(@PathVariable("id") String id,@ModelAttribute BlogPostVO blogpostVo) {
		System.out.println(blogpostVo.toString());
		p_service.insertPost(blogpostVo);
		return "redirect:/"+id+"/admin/write";
	}
}
