package kr.co.jimmy.DAO;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import kr.co.jimmy.VO.BlogAdminVO;
import kr.co.jimmy.VO.BlogVO;
import kr.co.jimmy.VO.FileVO;

@Repository
public class BlogAdminDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public int update(BlogVO blogVo) {
		return sqlSession.update("blog.updateByBlogSetting",blogVo);
	}
	
	public BlogVO select(String id) {
		return sqlSession.selectOne("blog.selectByMemberId",id);
	}
	
	public int insert(BlogAdminVO cateVo) {
		sqlSession.insert("blogcategory.insertByCategoryDefault", cateVo);
		return cateVo.getCateNo();
	}
	
	public BlogAdminVO select(int cateNo) {
		return sqlSession.selectOne("blogcategory.selectByCategoryDescription", cateNo);
	}
	
	public List<BlogAdminVO> selectAll(String id){
		return sqlSession.selectList("blogcategory.selectByCategory", id);
	}
	
	public int delete(int cateNo) {
		return sqlSession.delete("blogcategory.deleteByCategory",cateNo);
	}
}
