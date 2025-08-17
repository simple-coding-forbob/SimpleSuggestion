package com.simplecoding.simlesuggestion.jpa.dept.repository;



import com.simplecoding.simlesuggestion.jpa.dept.entity.Dept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<Dept,Long> {
    @Query(value = "select d from Dept d\n" +
            "where d.dname like %:searchKeyword%")
    Page<Dept> selectDeptList(
            @Param("searchKeyword") String searchKeyword,
            Pageable pageable
    );
}
