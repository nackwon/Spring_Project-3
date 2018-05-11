package kr.co.jimmy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jimmy.DAO.BlogPostDAO;
import kr.co.jimmy.VO.BlogPostVO;

@Service
public class BlogPostService {
	
	@Autowired
	private BlogPostDAO dao;
	
	public int insertPost(BlogPostVO blogpostVo) {
		return dao.insert(blogpostVo);
	}
}
