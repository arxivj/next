package com.fintech.next.domain.member.dao;

import com.fintech.next.domain.member.domain.Member;
import com.fintech.next.domain.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(Email email);
    boolean existsByEmail(Email email);

}
