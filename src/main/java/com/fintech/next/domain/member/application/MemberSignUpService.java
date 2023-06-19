package com.fintech.next.domain.member.application;

import com.fintech.next.domain.member.dao.MemberRepository;
import com.fintech.next.domain.member.domain.Member;
import com.fintech.next.domain.member.dto.SignUpRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class MemberSignUpService {

    private final MemberRepository memberRepository;

    public MemberSignUpService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member doSignUp(final SignUpRequest dto) {
        if(memberRepository.existsByEmail(dto.getEmail()));
        return memberRepository.save(dto.toEntity());
    }


}
