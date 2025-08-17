/**
 * 
 */
package com.simplecoding.simlesuggestion.jpa.filedb.controller;


import com.simplecoding.simlesuggestion.jpa.filedb.dto.FileDbDto;
import com.simplecoding.simlesuggestion.jpa.filedb.entity.FileDb;
import com.simplecoding.simlesuggestion.jpa.filedb.service.FileDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author user
 *
 */
@Log4j2
@Controller
@RequiredArgsConstructor
public class FileDbController {
//	서비스 가져오기
	private final FileDbService fileDbService;
	
//	전체조회
	@GetMapping("/fileDb")
	public String selectFileDbList(@RequestParam(defaultValue = "") String searchKeyword,
								   @PageableDefault(page = 0, size = 3) Pageable pageable,
								   Model model) {
		Page<FileDbDto> pages=fileDbService.selectFileDbList(searchKeyword, pageable);
		log.info("테스트 : "+pages);
		model.addAttribute("fileDbs", pages.getContent());
		model.addAttribute("pages", pages);
		
		return "fileDb/fileDb_all";
	}	

//	추가 페이지 열기
	@GetMapping("/fileDb/addition")
	public String createFileDbView() {
		return "fileDb/add_fileDb";
	}

//	insert: 업로드
//	@RequestParam(required = false) : 첨부파일 없어도 에러 발생 안하게 하는 옵션
//	첨부파일 다루기: (필수) 예외처리 필수
//	image.getBytes() : byte[] 배열로 변경
	@PostMapping("/fileDb/add")
	public String insert(@RequestParam(defaultValue = "") String fileTitle,
			 @RequestParam(defaultValue = "") String fileContent,
			 @RequestParam(required = false) MultipartFile image) throws Exception {
		FileDbDto fileDbDto=new FileDbDto(fileTitle,fileContent);
//		서비스의 insert 메소드 실행
		fileDbService.save(fileDbDto, image.getBytes());
		return "redirect:/fileDb";
	}

//	다운로드 메소드: 사용자가 다운로드URL을 웹브라우저에서 실행하면 이 메소드가 첨부파일을 전달해줌
//	@ResponseBody: JSON으로(JS 객체) 데이터를 JSP로 전달해줌
//	JSON : 예) [{속성:값},{속성2:값2}...]
	@GetMapping("/fileDb/download")
	@ResponseBody
	public ResponseEntity<byte[]> fileDownload(@RequestParam(defaultValue = "") String uuid) {
//		1) 상세조회: 첨부파일을 가져오려구
		FileDb fileDb=fileDbService.findById(uuid);
//		2) 헤더: 첨부파일을 보낼때(통신규칙) (1)첨부파일 보내요! (2)첨부파일 문서형식도 지정
//		=> HTML 문서: 헤더(문서형식,암호화등) + 바디(실제 데이터)
		HttpHeaders headers=new HttpHeaders();
//		첨부파일 보낸다는 의미: 1)attachment(첨부파일) 2)fileDb.getUuid()(첨부파일명)
		headers.setContentDispositionFormData("attachment", fileDb.getUuid());
//		첨부파일 문서형식(이진파일)
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

//		ResponseEntity: 데이터와 함께 신호도 같이 전송가능합니다.
//		신호 예) HttpStatus.OK(200번), HttpStatus.NOT_FOUND(404번) 등
//		사용법: new ResponseEntity<byte[]>(데이터, 헤더(생략가능),신호);
		return new ResponseEntity<byte[]>(fileDb.getFileData(),
											headers, HttpStatus.OK);
	}

//	삭제
	@PostMapping("/fileDb/delete")
	public String deleteById(@RequestParam(defaultValue = "") String uuid) {
//		서비스의 삭제 메소드 실행
		fileDbService.deleteById(uuid);
		return "redirect:/fileDb";
	}
}







