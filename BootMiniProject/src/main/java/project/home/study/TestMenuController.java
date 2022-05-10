package project.home.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestMenuController {
	
	@GetMapping("/")
	public String home() {
		
		return "/layout/main";
	}
	
	@GetMapping("/shop/form")
	public String home2() {
		
		return "/shop/shopform";
	}
	
	/*
	 * @GetMapping("/board/list") public String home3() {
	 * 
	 * return "/board/boardlist"; }
	 */
	
	@GetMapping("/load/map")
	public String map() {
		
		return "/sub/layout/map";
	}
	
	
}
