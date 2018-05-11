package kr.co.jimmy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.jimmy.DAO.JoinDAO;
import kr.co.jimmy.VO.MemberVO;

@Service
public class JoinService {
	
	@Autowired
	private JoinDAO dao;
	
	// join service
	@Transactional
	public int insertMember(MemberVO memberVo) {
		int result = dao.insert(memberVo);
		dao.insertCategory(memberVo);
		System.out.println("inserCategory");
		dao.insertBlog(memberVo);
		System.out.println("insertBlog");
		return result;
	}
	
	// join id check service
	public boolean selectMemberId(String id) {
		boolean flag = false;
		MemberVO memberVo = dao.select(id);
		
		if(memberVo == null) {
			flag = true; // 가입 가능
		} else {
			flag = false; // 가입 불가능
		}
		return flag;
	}
}
