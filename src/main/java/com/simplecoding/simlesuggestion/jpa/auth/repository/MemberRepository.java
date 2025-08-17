package com.simplecoding.simlesuggestion.jpa.auth.repository;



import com.simplecoding.simlesuggestion.jpa.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
}
