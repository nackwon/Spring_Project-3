package kr.co.jimmy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jimmy.DAO.LoginDAO;
import kr.co.jimmy.VO.MemberVO;

@Service
public class LoginService {

	@Autowired
	private LoginDAO dao;

	// login service
	public MemberVO selectMember(String id, String password) {
		return dao.select(id, password);
	}
}
