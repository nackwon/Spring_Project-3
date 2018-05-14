package kr.co.jimmy.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.jimmy.VO.BlogAdminVO;
import kr.co.jimmy.VO.BlogVO;
import kr.co.jimmy.VO.MemberVO;

@Repository
public class JoinDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	// join DAO
	public int insert(MemberVO memberVo) {
		return sqlSession.insert("member.insertByMember",memberVo);
	}
	
	// join Id Check DAO
	public MemberVO select(String id) {
		return sqlSession.selectOne("member.selectByMemberIdCheck",id);
	}
	
	// blog Create
	public int insertBlog(MemberVO memberVo) {
		String id = memberVo.getId();
		String blogTitle = memberVo.getUserName()+" 님의 블로그 입니다.";
		String logoFile = "1525402072222f17868c9-934d-4569-ac3c-aff09c703512";
		BlogVO blogVo = new BlogVO();
		blogVo.setId(id);
		blogVo.setBlogTitle(blogTitle);
		blogVo.setLogoFile(logoFile);
		return sqlSession.insert("blog.insertByBlogDefault",blogVo);
	}
	
	// category Create
	public int insertCategory(MemberVO memberVo) {
		String id = memberVo.getId();
		String cateName = "미분류";
		String description = "";
		BlogAdminVO blogCategoryVo = new BlogAdminVO();
		blogCategoryVo.setId(id);
		blogCategoryVo.setCateName(cateName);
		blogCategoryVo.setDescription(description);
		return sqlSession.insert("blogcategory.insertByCategoryDefault", blogCategoryVo);
	}
}
