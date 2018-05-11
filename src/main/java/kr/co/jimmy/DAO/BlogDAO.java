package kr.co.jimmy.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.jimmy.VO.BlogAdminVO;
import kr.co.jimmy.VO.BlogPostVO;
import kr.co.jimmy.VO.BlogVO;

@Repository
public class BlogDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public BlogVO select(String id) {
		return sqlSession.selectOne("blog.selectByMemberId", id);
	}
	
	public List<BlogPostVO> selectAll(int cateNo){
		return sqlSession.selectList("blog.selectByMainCategory", cateNo);
	}
}
