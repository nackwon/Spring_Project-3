package kr.co.jimmy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jimmy.DAO.BlogCommentDAO;
import kr.co.jimmy.VO.CommentVO;

@Service
public class BlogCommentService {
	
	@Autowired
	private BlogCommentDAO dao;
	
	public CommentVO insertComment(CommentVO cmtVo) {
		dao.insert(cmtVo);
		return dao.selectOne(cmtVo.getCmtNo());
	}
	
	public List<CommentVO> selectAll(int postNo){
		return dao.selectAll(postNo);
	}
	
}
