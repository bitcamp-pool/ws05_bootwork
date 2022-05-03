package board.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import board.data.BoardDao;
import board.data.BoardDto;

@Controller
public class BoardListController {
	
	@Autowired
	BoardDao dao;
	
	@GetMapping("/")
	public String home() {
		
		return "redirect:board/list";
	}
	
	@GetMapping("/board/list")
	public ModelAndView list() {
		
		ModelAndView mview = new ModelAndView();
		
		mview.setViewName("list");
		
		return mview;
	}
	
	@GetMapping("/board/writeform")
	public String form() {
		
		return "writeform";
	}
	
	@PostMapping("/board/insert")
	public String insert(
			@ModelAttribute BoardDto dto, 
			@RequestParam MultipartFile upload,
			HttpServletRequest request) 
	{
		// 사진을 올렸는지 여부 체크
//		if(!upload.isEmpty())
//			System.out.println("파일명: " + upload.getOriginalFilename());
		
		// 업로드될 실제 경로
		String realPath = request.getServletContext().getRealPath("/save");
		System.out.println(realPath);

		/* 사진을 실제 경로에 업로드
		   그 파일명을 dto의 photo에 저장
		   단, 사진을 안넣을을 경우 'no'라고 저장
		 */
		String uploadName = changeFileName(upload.getOriginalFilename());
		if(upload.isEmpty())
			dto.setPhoto("no");
		else {
			dto.setPhoto(uploadName);
			// 실제 업로드
			try {
				upload.transferTo(new File(realPath + "\\" + uploadName));
				
			}catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
        
		// db에 저장
		dao.insertBoard(dto);
		
		return "redirect:list";
	}
	
	// 파일명 변경하는 함수
	public String changeFileName(String fileName) {
		
		LocalDate date = LocalDate.now();
		int y = date.getYear();
		int m = date.getMonthValue();
		int d = date.getDayOfMonth();
		
		LocalTime time = LocalTime.now();
		int hh = time.getHour();
		int mm = time.getMinute();
		int ss = time.getSecond();
		
//		Date date = new Date();
//		int y = date.getYear() + 1900;
//		int m = date.getMonth() + 1;
//		int d = date.getDate();
//		int h = date.getHours();
//		int mm = date.getMinutes();
//		int ss = date.getSeconds();
		
		String s = "photo_" + y + m + d + hh + mm + ss + "_" + fileName;
		
		return s;
	}
}





























