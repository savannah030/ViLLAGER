package com.savannah030.ViLLAGER.repository;

import com.savannah030.ViLLAGER.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberName(String name);
    // 이미 가입한 사용자인지 확인할 때 이메일을 이용
    Optional<Member> findByEmail(String email);
}
