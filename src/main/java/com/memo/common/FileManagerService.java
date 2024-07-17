package com.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
	
	// 실제 업로득 된 이미지가 저장될 서버의 경로
	public static final String FILE_UPLOAD_PATH = "D:\\이준호\\6_spring_project\\memo\\memo_workspace\\images/"; // 학원컴 경로 (images 폴더 뒤에 /(슬래시) 붙이기)
	//public static final String FILE_UPLOAD_PATH = ""; // 집컴 경로
	
	// 메소드
	// input: MultipartFile, userLoginId
	// output: String(이미지의 경로)
	public String uploadFile(MultipartFile file, String loginId) {
		// 폴더(디렉토리) 생성
		// 예: aaaa_17348493489/sun.png
		String directoryName = loginId + "_" + System.currentTimeMillis();
		// D:\\이준호\\6_spring_project\\memo\\memo_workspace\\images/aaaa_17348493489/
		String filePath = FILE_UPLOAD_PATH + directoryName + "/";
		
		// 폴더 생성
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			// 폴더 생성 시  실패하면 경로를 null로 리턴
			return null;
		}
		
		// 파일 업로드
		try {
			byte[] bytes = file.getBytes();
			// ★★★한글명으로 된 이미지는 업로드가 불가하므로 나중에 영문자로 바꾸기★★★
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null; // 이미지 업로드 실패시 경로는 null
		}
		// 파일 업로드가 성공하면 이미지 url path를 리턴
		// 주소는 이렇게 될 것이다.(예언)
		// /images/aaaa_17348493489/sun.png
		return "/images/" + directoryName + "/" + file.getOriginalFilename();
	}
	
}
