package com.simplecoding.simlesuggestion.jpa.emp.service;


import com.simplecoding.simlesuggestion.common.ErrorMsg;
import com.simplecoding.simlesuggestion.common.MapStruct;
import com.simplecoding.simlesuggestion.jpa.emp.dto.EmpDto;
import com.simplecoding.simlesuggestion.jpa.emp.entity.Emp;
import com.simplecoding.simlesuggestion.jpa.emp.repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmpService {

    //    DB CRUD 클래스 받기 : JPA 제공 함수 사용 가능
    private final EmpRepository empRepository;
    private final MapStruct mapStruct;
    private final ErrorMsg errorMsg;

    public Page<EmpDto> selectEmpList(String searchKeyword, Pageable pageable) {
        Page<Emp>  page= empRepository.selectEmpList(searchKeyword, pageable);
        return page.map(emp -> mapStruct.toDto(emp));
    }

    //    저장/수정 : 1) 기본키가(부서번호) 없으면 저장(insert)
//               2) 기본키가(부서번호) 있으면 수정(update)
//           => JPA 내부적으로 if문 있음 : 알아서 실행됨
    public void save(EmpDto empDto) {
//        JPA 저장 함수 실행 : return 값 : 저장된 객체
        Emp emp=mapStruct.toEntity(empDto);
        empRepository.save(emp);
    }

    public EmpDto findById(long eno) {
//        JPA 상세조회 함수 실행
        Emp emp= empRepository.findById(eno)
                .orElseThrow(() -> new RuntimeException(errorMsg.getMessage("errors.not.found")));
        return mapStruct.toDto(emp);
    }


    @Transactional
    public void updateFromDto(EmpDto empDto) {
//        JPA 저장 함수 실행 : return 값 : 저장된 객체
        Emp emp=empRepository.findById(empDto.getEno())
                .orElseThrow(() -> new RuntimeException("errors.not.found"));

        mapStruct.updateFromDto(empDto, emp);
    }

    //    삭제 함수
    public void deleteById(long eno) {
        empRepository.deleteById(eno);
    }
}

