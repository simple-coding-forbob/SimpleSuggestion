package com.simplecoding.simlesuggestion.common;


import com.simplecoding.simlesuggestion.es.filedbsuggested.dto.FileDbSuggestedDto;
import com.simplecoding.simlesuggestion.es.filedbsuggested.entity.FileDbSuggested;
import com.simplecoding.simlesuggestion.es.lookup.dto.LookupAllDto;
import com.simplecoding.simlesuggestion.es.lookup.entity.LookupAll;
import com.simplecoding.simlesuggestion.es.search.dto.SearchAllDto;
import com.simplecoding.simlesuggestion.es.search.entity.SearchAll;
import com.simplecoding.simlesuggestion.jpa.auth.dto.MemberDto;
import com.simplecoding.simlesuggestion.jpa.auth.entity.Member;
import com.simplecoding.simlesuggestion.jpa.dept.dto.DeptDto;
import com.simplecoding.simlesuggestion.jpa.dept.entity.Dept;
import com.simplecoding.simlesuggestion.jpa.emp.dto.EmpDto;
import com.simplecoding.simlesuggestion.jpa.emp.entity.Emp;
import com.simplecoding.simlesuggestion.jpa.faq.dto.FaqDto;
import com.simplecoding.simlesuggestion.jpa.faq.entity.Faq;
import com.simplecoding.simlesuggestion.jpa.filedb.dto.FileDbDto;
import com.simplecoding.simlesuggestion.jpa.filedb.entity.FileDb;
import com.simplecoding.simlesuggestion.jpa.filedblikes.dto.FileDbLikesDto;
import com.simplecoding.simlesuggestion.jpa.filedblikes.entity.FileDbLikes;
import com.simplecoding.simlesuggestion.jpa.gallery.dto.GalleryDto;
import com.simplecoding.simlesuggestion.jpa.gallery.entity.Gallery;
import com.simplecoding.simlesuggestion.jpa.gallerylikes.dto.GalleryLikesDto;
import com.simplecoding.simlesuggestion.jpa.gallerylikes.entity.GalleryLikes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE  // null 제외 기능(update 시 사용)
)
public interface MapStruct {

    // TODO: 1) Dept <-> DeptDto
    DeptDto toDto(Dept dept);
    Dept toEntity(DeptDto deptDto);
    // TODO: 수정 시 사용: dirty checking 기능(save() 없이 수정 가능)
    void updateFromDto(DeptDto deptDto, @MappingTarget Dept dept);

    // TODO: 2) Emp <-> EmpDto
    @Mapping(source = "dept.dno", target = "dno")
    EmpDto toDto(Emp emp);
    @Mapping(source = "dno", target = "dept.dno")
    Emp toEntity(EmpDto empDto);
    // TODO: 수정 시 사용: dirty checking 기능(save() 없이 수정 가능)
//      경고 무시하고 싶으면 아래 추가하세요
    @Mapping(target = "dept", ignore = true)
    void updateFromDto(EmpDto empDto, @MappingTarget Emp emp);

    // TODO: 3) Dept <-> DeptDto
    FaqDto toDto(Faq faq);
    Faq toEntity(FaqDto faqDto);
    // TODO: 수정 시 사용: dirty checking 기능(save() 없이 수정 가능)
    void updateFromDto(FaqDto faqDto, @MappingTarget Faq faq);

    //    TODO: 4) fileDb <-> fileDto
    FileDbDto toDto(FileDb fileDb);
    @Mapping(target = "fileData", ignore = true)
    FileDb toEntity(FileDbDto fileDbDto);

    //    TODO: 5) gallery <-> galleryDto
    GalleryDto toDto(Gallery gallery);
    @Mapping(target = "galleryData", ignore = true)
    Gallery toEntity(GalleryDto galleryDto);

    //    TODO: 6) member <-> memberDto
    MemberDto toDto(Member member);
    Member toEntity(MemberDto memberDto);


    //    TODO: 7) fileDbLikes <-> fileDbLikesDto
    @Mapping(source = "member.email", target = "email")
    @Mapping(source = "fileDb.uuid", target = "uuid")
    FileDbLikesDto toDto(FileDbLikes fileDbLikes);
    @Mapping(source = "email", target = "member.email")
    @Mapping(source = "uuid", target = "fileDb.uuid")
    FileDbLikes toEntity(FileDbLikesDto fileDbLikesDto);

    //    TODO: 8) galleryLikes <-> galleryLikesDto
    @Mapping(source = "member.email", target = "email")
    @Mapping(source = "gallery.uuid", target = "uuid")
    GalleryLikesDto toDto(GalleryLikes galleryLikes);
    @Mapping(source = "email", target = "member.email")
    @Mapping(source = "uuid", target = "gallery.uuid")
    GalleryLikes toEntity(GalleryLikesDto galleryLikesDto);

//    TODO: 9) FileDbSuggested <-> FileDbSuggestedDto
    FileDbSuggestedDto toDto(FileDbSuggested fileDbSuggested);
    FileDbSuggested toEntity(FileDbSuggestedDto fileDbSuggestedDto);

//    TODO: 엘라스틱서치 용
//    TODO: 10) SearchAll <-> SearchAllDto
    SearchAllDto toDto(SearchAll search);
    SearchAll toEntity(SearchAllDto searchDto);

//    TODO: 11) SearchAll <-> SearchAllDto
    LookupAllDto toDto(LookupAll lookupAll);
    LookupAll toEntity(LookupAllDto lookupAllDto);
}
