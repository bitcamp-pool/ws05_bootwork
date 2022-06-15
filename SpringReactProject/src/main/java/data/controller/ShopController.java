package data.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import data.service.ShopService;
import util.FileUtil;

@RestController // 무조건 ResponseBody
@CrossOrigin
@RequestMapping("/shop")
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	String photoName; // 리액트에서 업로드한 이미지명(변경된 이미지명일 수도...)
	
	// 리액트에서 이미지 업로드시 save 폴더에 저장 후 이미지명 반환
	@PostMapping("/upload")
	public String fileUpload(@RequestParam MultipartFile uploadFile,
			HttpServletRequest request) {
		// 파일명
		String fileName=uploadFile.getOriginalFilename();
		
		// 업로드할 폴더 위치
		String path = request.getServletContext().getRealPath("/save");
		
		// 직전에 업로드한 이미지 삭제하기
		File file = new File(path + "/" + photoName);
		// 만약 존재할 경우 삭제
		if(file.exists())
			file.delete();
		
		// 파일명 변경
		FileUtil fileUtil = new FileUtil();
		photoName = fileUtil.changeFileName(fileName);
		System.out.println("fileName=" + fileName + "=>" + photoName);
		
		// save 폴더에 업로드
		try {
			uploadFile.transferTo(new File(path + "/" + photoName));
		} catch(IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return photoName;
	}
}
