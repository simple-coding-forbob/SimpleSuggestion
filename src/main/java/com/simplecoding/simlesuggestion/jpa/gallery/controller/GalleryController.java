/**
 * 
 */
package com.simplecoding.simlesuggestion.jpa.gallery.controller;


import com.simplecoding.simlesuggestion.jpa.gallery.dto.GalleryDto;
import com.simplecoding.simlesuggestion.jpa.gallery.entity.Gallery;
import com.simplecoding.simlesuggestion.jpa.gallery.service.GalleryService;
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
public class GalleryController {
//	서비스 가져오기
	private final GalleryService galleryService;
	
//	전체조회
	@GetMapping("/gallery")
	public String selectGalleryList(@RequestParam(defaultValue = "") String searchKeyword,
								   @PageableDefault(page = 0, size = 3) Pageable pageable,
								   Model model) {
		Page<GalleryDto> pages=galleryService.selectGalleryList(searchKeyword, pageable);
		log.info("테스트 : "+pages);
		model.addAttribute("gallerys", pages.getContent());
		model.addAttribute("pages", pages);
		
		return "gallery/gallery_all";
	}
	
//	추가 페이지 열기
	@GetMapping("/gallery/addition")
	public String createGalleryView() {
		return "gallery/add_gallery";
	}
	
//	insert: 업로드
	@PostMapping("/gallery/add")
	public String insert(@RequestParam(defaultValue = "") String galleryTitle,
			 @RequestParam(required = false) MultipartFile image) throws Exception {
		GalleryDto galleryDto=new GalleryDto(galleryTitle);
		galleryService.save(galleryDto,image.getBytes());
		return "redirect:/gallery";
	}
	
//	다운로드 메소드: 사용자가 다운로드URL을 웹브라우저에서 실행하면 이 메소드가 첨부파일을 전달해줌
	@GetMapping("/gallery/download")
	@ResponseBody
	public ResponseEntity<byte[]> fileDownload(@RequestParam(defaultValue = "") String uuid) {
		Gallery gallery=galleryService.findById(uuid);

		HttpHeaders headers=new HttpHeaders();
		headers.setContentDispositionFormData("attachment", gallery.getUuid());
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		return new ResponseEntity<byte[]>(gallery.getGalleryData(),
											headers, HttpStatus.OK);
	}
	
//	삭제
	@PostMapping("/gallery/delete")
	public String deleteById(@RequestParam(defaultValue = "") String uuid) {
		galleryService.deleteById(uuid);
		return "redirect:/gallery";
	}
}







