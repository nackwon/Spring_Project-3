package kr.co.jimmy.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import kr.co.jimmy.VO.FileVO;

public class FileUpload {
	
	public FileVO restore(MultipartFile file, String user_id) {
		FileVO fileVo = new FileVO();
		String saveDir = "D:\\dev\\fileupload";
		File f = new File(saveDir);
		if(!f.exists()) {
			f.mkdirs();
			System.out.println(saveDir+" 위치에 폴더가 생성되었습니다.");
		}
		
		String orgName = file.getOriginalFilename();
		System.out.println("orgName : " + orgName);
		
		String exName = orgName.substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName : "+ exName);
		
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString();
		System.out.println("saveName : "+ saveName);
		
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath : "+ filePath);
		
		long fileSize = file.getSize();
		System.out.println("fileSize : "+ fileSize);
		
		fileVo.setFilePath(filePath);
		fileVo.setOrgName(orgName);
		fileVo.setSaveName(saveName);
		fileVo.setFileSize(fileSize);
		fileVo.setUser_id(user_id);
		
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(saveDir + "/" + saveName);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			
			if(bout != null)
				bout.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileVo;
	}
}
