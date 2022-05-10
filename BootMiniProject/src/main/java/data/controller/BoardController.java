package data.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import data.dto.BoardDto;
import data.service.BoardService;
import util.FileUtil;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/list")
	public ModelAndView list() {
		
		ModelAndView mv = new ModelAndView();
		
		int totalCount; // 총 갯수
		
		totalCount = service.getTotalCount();
		mv.addObject("totalCount", totalCount);
		
		mv.setViewName("/board/boardlist");
		return mv;
	}
	
	@GetMapping("/form")
	public ModelAndView form(@RequestParam Map<String, String> map) {
		
		ModelAndView mv = new ModelAndView();
		
		/* 답글일 경우 읽어야할 5개의 값
		 * 새글일 경우는 값이 안넘어오므로 모두 null 이다.
		 */
		String currentPage = map.get("currentPage");
		String num = map.get("num");
		String reg = map.get("reg");
		String restep = map.get("restep");
		String relevel = map.get("relevel");
		
		mv.addObject("currentPage", currentPage==null? "1":currentPage);
		mv.addObject("num", num == null ? "0" : num);
		mv.addObject("reg", reg == null? "0": reg);
		mv.addObject("restep", restep == null ? "0" : restep);
		mv.addObject("relevel", relevel == null? "0" : relevel);
		
		mv.setViewName("/board/boardform");
		return mv;
	}
	
	@PostMapping("/insert")
	public String insert(
			@ModelAttribute BoardDto dto,
			@RequestParam String currentPage,
			@RequestParam ArrayList<MultipartFile> upload,
			HttpSession session,
			HttpServletRequest request) 
	{
		// 사진을 저장할 경우
		String path = request.getServletContext().getRealPath("/save");
		
		// 세션으로부터 로그인한 아이디 얻기
		String loginid = (String)session.getAttribute("loginid");
		dto.setId(loginid);
		
		// 사진을 업로드 안했을 경우 photos에 'no' 라고 저장
		if(upload.get(0).getOriginalFilename().equals("")) {
			dto.setPhotos("no");
		} else {
			FileUtil fileUtil = new FileUtil();
			String photos = "";
			for (MultipartFile f : upload) {
				String rename = fileUtil.changeFileName(f.getOriginalFilename());
				photos += rename + ",";
				File file = new File(path + "\\" + rename);
				try {
					f.transferTo(file); // save 폴더에 업로드 됨
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			// 마지막 컴마 제거
			photos = photos.substring(0, photos.length() - 1);
			System.out.println(photos);
			dto.setPhotos(photos);
		}
		 
		// db insert
		service.insertBoard(dto);
		
		return "redirect:list?currentPage=" + currentPage; // 해당 페이지로 이동
	}
}




































































