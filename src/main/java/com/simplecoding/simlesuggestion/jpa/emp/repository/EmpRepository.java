package com.simplecoding.simlesuggestion.jpa.emp.repository;


import com.simplecoding.simlesuggestion.jpa.emp.entity.Emp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepository extends JpaRepository<Emp,Long> {
    @EntityGraph(attributePaths = {"dept"})
    @Query(value = "select e from Emp e\n" +
            "where e.ename like %:searchKeyword%")
    Page<Emp> selectEmpList(
            @Param("searchKeyword") String searchKeyword,
            Pageable pageable
    );
}
