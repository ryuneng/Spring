package com.sample.service; // 20240229 Day8

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	// @Value : 설정파일(application.properties)의 설정된 설정 값을 자동주입해준다.
	@Value("${upload.save.directory}")
	private String saveDirectory;
	
	public String upload(MultipartFile uploadFile) {
		// 1. 첨부파일이 비어있으면 파일저장과정 없이 default.png를 반환한다.
		if (uploadFile.isEmpty()) {
			return "default.png";
		}
		
		// 2. 첨부파일이 비어있지 않으면 파일을 저장하고, 해당 파일명을 반환한다.
		String filename = uploadFile.getOriginalFilename();
		File file = new File(saveDirectory, filename);
		
		try {
			FileCopyUtils.copy(uploadFile.getBytes(), file);
		} catch (Exception ex) {
			throw new RuntimeException("첨부파일을 저장할 수 없습니다.", ex);
		}
		
		return filename;
	}
}
