package kr.co.jimmy.DAO;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.jimmy.VO.MemberVO;

@Repository
public class LoginDAO {

	@Autowired
	private SqlSession sqlSession;

	// login DAO
	public MemberVO select(String id, String password) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("password", password);
		return sqlSession.selectOne("member.selectByMember", map);
	}
}
