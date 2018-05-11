package kr.co.jimmy.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.jimmy.VO.BlogPostVO;

@Repository
public class BlogPostDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(BlogPostVO blogpostVo) {
		return sqlSession.insert("post.insertByPost", blogpostVo);
	}
}
