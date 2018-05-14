package kr.co.jimmy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jimmy.DAO.BlogDAO;
import kr.co.jimmy.VO.BlogMainDefaultVO;
import kr.co.jimmy.VO.BlogPostVO;
import kr.co.jimmy.VO.BlogVO;

@Service
public class BlogService {

	@Autowired
	private BlogDAO dao;

	public BlogVO selectMemberId(String id) {
		return dao.select(id);
	}

	public List<BlogPostVO> selectAll(int cateNo) {
		return dao.selectAll(cateNo);
	}

	public BlogPostVO select(int postNo) {
		return dao.select(postNo);
	}
	
	/*public List<BlogMainDefaultVO> selectCateNumber(int cateNo){
		return dao.selectOne(cateNo);
	}
	
	public List<BlogMainDefaultVO> selectPostNumber(String id){
		return dao.selectOne(id);
	}*/
}
