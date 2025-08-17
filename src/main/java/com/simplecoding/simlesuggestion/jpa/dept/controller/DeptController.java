/**
 * 
 */
package com.simplecoding.simlesuggestion.jpa.dept.controller;

import com.simplecoding.simlesuggestion.jpa.dept.dto.DeptDto;
import com.simplecoding.simlesuggestion.jpa.dept.service.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
public class DeptController {
//	서비스 가져오기
	private final DeptService deptService;
	
//	전체조회
	@GetMapping("/dept")
	public String selectDeptList( @RequestParam(defaultValue = "") String searchKeyword,
							 @PageableDefault(page = 0, size = 3) Pageable pageable,
					   Model model) {
//		1) Pageable : page(현재페이지), size(1페이지 당 화면에 보일개수)
//		Pageable pageable = PageRequest.of(page, size);
//		전체조회 서비스 메소드 실행
		Page<DeptDto> pages=deptService.selectDeptList(searchKeyword, pageable);
		log.info("테스트 : "+pages);
		model.addAttribute("depts", pages.getContent());
		model.addAttribute("pages", pages);
		
		return "dept/dept_all";
	}

	//	추가 페이지 열기
	@GetMapping("/dept/addition")
	public String createDeptView() {
		return "dept/add_dept";
	}

	//	insert : 저장 버튼 클릭시
	@PostMapping("/dept/add")
	public String insert(@ModelAttribute DeptDto deptDto) {
//		Dept 내용 확인
		log.info("테스트3 :"+deptDto);
//		서비스의 insert 실행
		deptService.save(deptDto);

		return "redirect:/dept";
	}

	//	수정페이지 열기(상세조회)
	@GetMapping("/dept/edition")
	public String updateDeptView(@RequestParam long dno, Model model) {
//		서비스의 상세조회
		DeptDto deptDto=deptService.findById(dno);
		model.addAttribute("dept", deptDto);
		return "dept/update_dept";
	}

	//	수정: 버튼 클릭시 실행
	@PostMapping("/dept/edit")
	public String update(@ModelAttribute DeptDto deptDto) {
//		서비스의 수정 실행
		deptService.save(deptDto);
		return "redirect:/dept";
	}

	//	삭제
	@PostMapping("/dept/delete")
	public String deleteById(@RequestParam long dno) {
//		서비스의 삭제 실행
		deptService.deleteById(dno);
		return "redirect:/dept";
	}
}






