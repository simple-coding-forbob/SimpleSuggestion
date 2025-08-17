/**
 * 
 */
package com.simplecoding.simlesuggestion.jpa.emp.controller;


import com.simplecoding.simlesuggestion.jpa.emp.dto.EmpDto;
import com.simplecoding.simlesuggestion.jpa.emp.service.EmpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author user
 *
 */
@Log4j2
@Controller
@RequiredArgsConstructor
public class EmpController {
//	서비스 가져오기
	@Autowired
	private final EmpService empService;
	
//	전체조회
	@GetMapping("/emp")
	public String selectEmpList( @RequestParam(defaultValue = "") String searchKeyword,
							 @PageableDefault(page = 0, size = 3) Pageable pageable,
					   Model model) {
//		1) Pageable : page(현재페이지), size(1페이지 당 화면에 보일개수)
//		Pageable pageable = PageRequest.of(page, size);
//		전체조회 서비스 메소드 실행
		Page<?> pages=empService.selectEmpList(searchKeyword, pageable);
		log.info("테스트 : "+pages);
		model.addAttribute("emps", pages.getContent());
		model.addAttribute("pages", pages);
		
		return "emp/emp_all";
	}

	//	추가 페이지 열기
	@GetMapping("/emp/addition")
	public String createEmpView() {
		return "emp/add_emp";
	}

	//	insert : 저장 버튼 클릭시
	@PostMapping("/emp/add")
	public String insert(@ModelAttribute EmpDto empDto) {
//		Emp 내용 확인
		log.info("테스트3 :"+empDto);
//		서비스의 insert 실행
		empService.save(empDto);

		return "redirect:/emp";
	}

	//	수정페이지 열기(상세조회)
	@GetMapping("/emp/edition")
	public String updateEmpView(@RequestParam long eno, Model model) {
//		서비스의 상세조회
		EmpDto empDto=empService.findById(eno);
		model.addAttribute("emp", empDto);
		return "emp/update_emp";
	}

	//	수정: 버튼 클릭시 실행
	@PostMapping("/emp/edit")
	public String update(@ModelAttribute EmpDto empDto) {
//		서비스의 수정 실행
		empService.save(empDto);
		return "redirect:/emp";
	}

	//	삭제
	@PostMapping("/emp/delete")
	public String deleteById(@RequestParam long eno) {
//		서비스의 삭제 실행
		empService.deleteById(eno);
		return "redirect:/emp";
	}
}






