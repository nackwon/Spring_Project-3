package kr.co.jimmy.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.jimmy.DAO.BlogAdminDAO;
import kr.co.jimmy.VO.BlogAdminVO;
import kr.co.jimmy.VO.BlogVO;
import kr.co.jimmy.VO.FileVO;
import kr.co.jimmy.util.FileUpload;

@Service
public class BlogAdminService {

	@Autowired
	private BlogAdminDAO dao;

	// Thumnail 빠짐
	public int updateBlogProfile(BlogVO blogVo, MultipartFile file) {
		
		if (file == null) {
			String saveName = dao.select(blogVo.getId()).getLogoFile();
			blogVo.setLogoFile(saveName);
			System.out.println(blogVo.toString());
			return dao.update(blogVo);
		} else {
			FileVO fileVo = new FileVO();
			String saveDir = "D:\\dev\\fileupload";
			/*
			 * File f = new File(saveDir); 
			 * if(!f.exists()) { f.mkdirs();
			 * System.out.println(saveDir+" 위치에 폴더가 생성되었습니다."); }
			 */

			String orgName = file.getOriginalFilename();
			System.out.println("orgName : " + orgName);

			String exName = orgName.substring(file.getOriginalFilename().lastIndexOf("."));
			System.out.println("exName : " + exName);

			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString();
			System.out.println("saveName : " + saveName);

			String filePath = saveDir + "\\" + saveName;
			System.out.println("filePath : " + filePath);

			long fileSize = file.getSize();
			System.out.println("fileSize : " + fileSize);

			fileVo.setFilePath(filePath);
			fileVo.setOrgName(orgName);
			fileVo.setSaveName(saveName);
			fileVo.setFileSize(fileSize);
			blogVo.setLogoFile(saveName);
			// fileVo.setUser_id(user_id);
			try {
				byte[] fileData = file.getBytes();
				OutputStream out = new FileOutputStream(saveDir + "/" + saveName);
				BufferedOutputStream bout = new BufferedOutputStream(out);

				bout.write(fileData);

				if (bout != null)
					bout.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("file 있음"+blogVo.toString());
			return dao.update(blogVo);
		}
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
			flag = true; // 삭제 완료
		} else {
			flag = false; // 삭제 실패
		}
		return flag;
	}

}
