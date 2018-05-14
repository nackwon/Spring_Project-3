package kr.co.jimmy.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.jimmy.VO.CommentVO;

@Repository
public class BlogCommentDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(CommentVO cmtVo) {
		return sqlSession.insert("blog.insertByComment",cmtVo);
	}
	
	public CommentVO selectOne(int cmtNo) {
		return sqlSession.selectOne("blog.selectByComment",cmtNo);
	}
	
	public List<CommentVO> selectAll(int postNo){
		return sqlSession.selectList("blog.selectByCommentList",postNo);
	}
}
