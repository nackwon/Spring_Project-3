package kr.co.jimmy.DAO;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.jimmy.VO.BlogAdminVO;

@Repository
public class BlogAdminDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public int update(String blogTitle, String id) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("blogTitle", blogTitle);
		map.put("id", id);
		return sqlSession.update("blog.updateByBlogSetting",map);
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
