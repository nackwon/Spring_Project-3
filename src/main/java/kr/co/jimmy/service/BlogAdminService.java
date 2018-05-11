package kr.co.jimmy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jimmy.DAO.BlogAdminDAO;
import kr.co.jimmy.VO.BlogAdminVO;

@Service
public class BlogAdminService {

	@Autowired
	private BlogAdminDAO dao;

	// Thumnail 빠짐
	public int updateBlogProfile(String blogTitle, String id) {
		return dao.update(blogTitle, id);
	}

	public List<BlogAdminVO> selectList(String id) {
		return dao.selectAll(id);
	}

	public BlogAdminVO insertBlogCategory(BlogAdminVO cateVo) {
		int cateNo = dao.insert(cateVo);
		return dao.select(cateNo);
	}

	public boolean deleteCategory(int cateNo) {
		boolean flag = false;
		int number = dao.delete(cateNo);

		if (number == 1) {
			flag = true; //삭제 완료
		} else {
			flag = false; //삭제 실패
		}
		return flag;
	}

}
