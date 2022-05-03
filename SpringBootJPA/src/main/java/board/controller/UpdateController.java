package board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.data.MyCarDao;
import board.data.MyCarDto;

@Controller
public class UpdateController {
	
	@Autowired
	MyCarDao dao;
	
	@GetMapping("/updateform")
	public String updateform(@RequestParam Long num, Model model) {
		
		// dao로부터  dto 가져오기
		MyCarDto dto = dao.getMyCar(num);
		
		// model에 dto 저장
		model.addAttribute("dto", dto);
		
		// 포워드
		return "updateform";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute MyCarDto dto) {
		
		// 수정
		dao.updateCar(dto);
		
		// 목록으로 이동
		return "redirect:/";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam Long num) {
		
		// 삭제 후 목록으로 이동
		dao.deleteCar(num);
		
		return "redirect:/";
	}
	
}






















