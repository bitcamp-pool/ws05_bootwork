package data.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import data.dto.BoardDto;

import data.mapper.MemberMapperInter;
import data.service.BoardService;
import util.FileUtil;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@Autowired
	private MemberMapperInter memberMapper;
	
	@GetMapping("/list")
	public ModelAndView list(
			@RequestParam(defaultValue = "1") int currentPage) 
	{
		
		ModelAndView mv = new ModelAndView();
		
		// 페이징처리
		int totalCount;   	// 총 갯수
		int perPage  = 5;  	// 한 페이지당 보여질 글의 개수
		int perBlock = 5; 	// 한 블럭당 보여질 페이지 수
		int totalPage;		// 총 페이지 수
		int startNum;		// 한 페이지에서 보여질 시작 글번호
		int startPage;		// 한 블럭에서 보여질 시작 페이지 번호
		int endPage; 		// 한 블럭에서 보여질 끝 페이지 번호
		int no; 			// 각 페이지당 보여질 시작 번호
		
		// 총 글의 개수를 구한다
		totalCount = service.getTotalCount();
		
		// 총 페이지 수를 구한다
		   totalPage = totalCount/perPage + (totalCount % perPage == 0 ? 0 : 1);
		// totalPage = (int)Math.ceil((double)totalCount/perPage);
		
		// 각 블럭의 시작페이지(한 블럭당 5개일 경우)
		// 1, 6, 11 ...(currentPage가 1~5 이면 1, 6~10 이면 6)
		startPage = (currentPage-1)/perBlock * perBlock + 1;
				
	    // 5, 10, 15 ..(currentPage가 1~5 이면 5, 6~10 이면 10)
	    endPage = startPage + perBlock -1;
	    
	    // 마지막 블럭에서는 마지막 페이지(총 페이지수)까지만 나오게
	    if(endPage > totalPage)
	    	endPage = totalPage;
	    
	    // 각 페이지에서 보여질 글의 시작번호(mysql은 0 부터)
	    // 예) 한 페이지당 3개일 경우 1페이지:0, 2페이지:3, 3페이지:6 ...
	    startNum = (currentPage - 1) * perPage;
	    
	    // 각 페이지당 보여질 시작번호
	    no = totalCount - (currentPage - 1) * perPage;
	    
	    // 데이터 가져오기
	    List<BoardDto> list = service.getList(startNum, perPage);
	    
	    // 각 데이터에 id를 이용해서 이름 넣어주기
	    for (BoardDto dto : list) {
	    	String id = dto.getId();
	    	String name = memberMapper.getSearchName(id);
	    	dto.setName(name);
	    }
	    
	    mv.addObject("currentPage", currentPage);
		mv.addObject("totalCount", totalCount);
		mv.addObject("totalPage", totalPage);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("no", no);
		mv.addObject("list", list);
		
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
	
	@GetMapping("/content")
	public ModelAndView content(
			@RequestParam int num,
			@RequestParam String currentPage) 
	{
		
		ModelAndView mv = new ModelAndView();
		
		// 조회수 1 증가
		service.updateReadCount(num);
		
		// num에 해당하는 dto
		BoardDto dto = service.getData(num);
		
		// 이름 넣어주기
		String name = memberMapper.getSearchName(dto.getId());
		dto.setName(name);
		
		mv.addObject("dto", dto);
		mv.addObject("currentPage", currentPage);
		
		mv.setViewName("/board/content");
		return mv;
	}
	
	@GetMapping("/chu")
	@ResponseBody
	public Map<String, Integer> updateChu(
			@RequestParam int num,
			@RequestParam int chu)
	{
		// db chu, totalchu update
		service.updateChu(chu, num);
		
		// 변경된 totalchu 받아오기
		int totalchu = service.getData(num).getTotalchu();
		
		Map<String, Integer> map = new HashMap<>();
		map.put("totalchu", totalchu);
		
		return map;
	}
	
	@GetMapping("/updateform")
	public ModelAndView updateForm(
			@RequestParam int num,
			@RequestParam int currentPage) 
	{
		ModelAndView mv = new ModelAndView();
		
		// num에 해당하는 dto
		BoardDto dto = service.getData(num);
		
		// model에 저장
		mv.addObject("dto", dto);
		mv.addObject("currentPage", currentPage);
		
		mv.setViewName("/sub2/board/updateform");
		
		return mv;
	}
	
	
	@PostMapping("/update")
	public String update(
			@ModelAttribute BoardDto dto,
			@RequestParam String currentPage,
			@RequestParam ArrayList<MultipartFile> upload,
			HttpServletRequest request) 
	{
		System.out.println("dto=" + dto.toString());
		// 사진을 저장할 경우
		String path = request.getServletContext().getRealPath("/save");
		
		// 사진을 업로드 안했을 경우 photos에 'null' 라고 저장
		if(upload.get(0).getOriginalFilename().equals("")) {
			dto.setPhotos(null);
		} else { // 사진을 넣으면
			/* save폴더에 기존 리소스 삭제 후 업데이트
			 */
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
			// System.out.println(photos);
			dto.setPhotos(photos);
		}
		 
		// db update
		service.updateBoard(dto);
		
		// 수정한 다음에 내용보기로 이동
		return "redirect:content?currentPage=" + currentPage + "&num=" + dto.getNum();
	}
	
	@GetMapping("/delete")
	public String delete(
			@RequestParam int num,
			@RequestParam int currentPage,
			HttpServletRequest request) 
	{
		// save 폴더의 위치 구하기
		String path=request.getServletContext().getRealPath("/save");
		
		// save 폴더의 파일 삭제
		String photos = service.getData(num).getPhotos();
		if(!photos.equals("no")) {
			String[] fileName = photos.split(",");
			for(String f : fileName) {
				File file = new File(path + "\\" + f);
				if(file.exists())
					file.delete();
			}
		}
		
		// db에서 데이터 삭제
		service.deleteBoard(num);
		
		// 보던 페이지로 이동
		return "redirect:list?currentPage=" + currentPage;
	}
	
}




































































