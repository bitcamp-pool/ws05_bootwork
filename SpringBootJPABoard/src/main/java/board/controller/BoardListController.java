package board.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
		List<BoardDto> list = dao.getAllDatas();
		
		mview.addObject("list", list);
		mview.addObject("count", list.size());
		
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
		
	// detail
	@GetMapping("/board/detail")
	public ModelAndView detail(@RequestParam Long num) {
		
		ModelAndView mv = new ModelAndView();
		
		// dao로부터 dto얻기
		BoardDto dto = dao.getData(num);
		String content = dto.getContent().replace("\n", "<br>");
		dto.setContent(content);
		
		// model에 저장
		mv.addObject("dto", dto);
		
		mv.setViewName("detail");
		return mv;
	}
	
	@GetMapping("/board/updateform")
	public ModelAndView updateform(@RequestParam Long num) {
		
		ModelAndView mv = new ModelAndView();
		BoardDto dto = dao.getData(num);
		
		mv.addObject("dto", dto);
		
		mv.setViewName("updateform");
		return mv;
				
	}
	
	@PostMapping("/board/update")
	public String update(
			@ModelAttribute BoardDto dto,
			@RequestParam MultipartFile upload,
			HttpServletRequest request) 
	{
		// 톰캣에 있는 업로드할 폴더 실제 경로
		String path = request.getServletContext().getRealPath("/save");
		
		// 기존 사진 파일명
		String oldFileName = dao.getData(dto.getNum()).getPhoto();
		
		// 이미지가 없는 항목(no image)에 이미지 업로드를 안했을 경우 
		if(upload.isEmpty()) {
			// 사진을 업로드 안했을 경우 기존 이름으로 수정
			dto.setPhoto(oldFileName);
		} else {
			// 새로 저장할 파일명
			String newFileName = changeFileName(upload.getOriginalFilename());
			
			// dto에 저장
			dto.setPhoto(newFileName);
			
			// 기존 사진 삭제
			deleteFile(path, oldFileName);
			
			// 사진 업로드
			try {
				upload.transferTo(new File(path + "\\" + newFileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		// db update
		dao.updateBoard(dto);
		
		// 디테일 페이지로 이동
		return "redirect:detail?num=" + dto.getNum();
		
	}
	
	@GetMapping("/board/delete")
	public String delete(
			@RequestParam Long num, 
			HttpServletRequest request)
	{
		String path = request.getServletContext().getRealPath("/save");
		String fileName = dao.getData(num).getPhoto();
		
		// 사진 삭제
		deleteFile(path, fileName);
		
		// db에서도 삭제
		dao.deleteBoard(num);
		
		// 목록으로 이동
		return "redirect:list";
		
	}
	
	// 파일 삭제하는 메서드
	public void deleteFile(String path, String oldFileName) {
		
		File file = new File(path + "\\" + oldFileName);
		
		// 해당 경로에 파일이 있을 경우 true
		if(file.exists()) {
			
			file.delete();
			System.out.println("파일 삭제 성공");
		}
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









































