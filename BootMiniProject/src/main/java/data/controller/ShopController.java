package data.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.dto.ShopDto;
import data.mapper.ShopMapperInter;

@Controller
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private ShopMapperInter shopMapper;

	@GetMapping("/form")
	public String form() {

		return "/shop/shopform";
	}

	@PostMapping("/insert")
	public String insert(@ModelAttribute ShopDto dto){

		shopMapper.insertShop(dto);

		return "redirect:list";
	}

	@GetMapping("/list")
	public ModelAndView list(
			@RequestParam(defaultValue = "1") int currentPage
			) 
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
		totalCount = shopMapper.getTotalCount();

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
		Map<String, Integer> map = new HashMap<>();
		map.put("start", startNum);
		map.put("perpage", perPage);
		
		List<ShopDto> list = shopMapper.getShopList(map);
		
		String photo = "";
		for(ShopDto dto : list) {
			// Jsoup
			Document doc = null;
			doc=Jsoup.parse(dto.getContent());
			Elements myimg = doc.select("img"); // img 태그 요소값 얻기
			photo = myimg.attr("src");
			
//			System.out.println(photo);
			
			dto.setPhoto(photo);
		}
		

		mv.addObject("currentPage", currentPage);
		mv.addObject("totalCount", totalCount);
		mv.addObject("totalPage", totalPage);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("no", no);
		mv.addObject("list", list);

		mv.setViewName("/shop/shoplist");
		return mv;
	}

	@GetMapping("/detail")
	public ModelAndView detail(
			@RequestParam int num,
			@RequestParam int currentPage) 
	{
		// num에 해당하는 dto 얻기
		ShopDto dto = shopMapper.getSangpum(num);
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("dto", dto);
		mv.addObject("currentPage", currentPage);
		
		mv.setViewName("/shop/shopdetail");
		
		return mv;
	}

	@GetMapping("/delete")
	public String delete(
			@RequestParam int num,
			@RequestParam int currentPage) 
	{
		shopMapper.deleteShop(num);
		return "redirect:list?currentPage=" + currentPage;
	}
}








































