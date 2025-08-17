package com.simplecoding.simlesuggestion.jpa.faq.service;


import com.simplecoding.simlesuggestion.common.ErrorMsg;
import com.simplecoding.simlesuggestion.common.MapStruct;
import com.simplecoding.simlesuggestion.jpa.faq.dto.FaqDto;
import com.simplecoding.simlesuggestion.jpa.faq.entity.Faq;
import com.simplecoding.simlesuggestion.jpa.faq.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FaqService {

    //    DB CRUD 클래스 받기 : JPA 제공 함수 사용 가능
    private final FaqRepository faqRepository; // DI
    private final MapStruct mapStruct;
    private final ErrorMsg errorMsg;

    public Page<FaqDto> selectFaqList(String searchKeyword, Pageable pageable) {
        Page<Faq> page= faqRepository.selectFaqList(searchKeyword, pageable);
        return page.map(faq -> mapStruct.toDto(faq));
    }

    public FaqDto findById(long fno) {
//        JPA 상세조회 함수 실행
        Faq faq= faqRepository.findById(fno)
                .orElseThrow(() -> new RuntimeException(errorMsg.getMessage("errors.not.found")));
        return mapStruct.toDto(faq);
    }

    //    저장/수정 : 1) 기본키가(부서번호) 없으면 저장(insert)
//               2) 기본키가(부서번호) 있으면 수정(update)
//           => JPA 내부적으로 if문 있음 : 알아서 실행됨
    public void save(FaqDto faqDto) {
//        JPA 저장 함수 실행 : return 값 : 저장된 객체
        Faq faq= mapStruct.toEntity(faqDto);
        faqRepository.save(faq);
    }

    @Transactional
    public void updateFromDto(FaqDto faqDto) {
//        JPA 저장 함수 실행 : return 값 : 저장된 객체
        Faq faq=faqRepository.findById(faqDto.getFno())
                .orElseThrow(() -> new RuntimeException("errors.not.found"));

        mapStruct.updateFromDto(faqDto, faq);
    }

    //    삭제 함수
    public void deleteById(long fno) {
        faqRepository.deleteById(fno);
    }
}

