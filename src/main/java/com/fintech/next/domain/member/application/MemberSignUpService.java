package com.fintech.next.domain.member.application;

import com.fintech.next.domain.member.dao.MemberRepository;
import com.fintech.next.domain.member.domain.Member;
import com.fintech.next.domain.member.dto.SignUpRequest;
import com.fintech.next.domain.member.exception.EmailDuplicateException;
import com.fintech.next.global.util.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Duplication;
import org.hibernate.event.service.spi.DuplicationStrategy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class MemberSignUpService {

    private final MemberRepository memberRepository;

    public MemberSignUpService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member doSignUp(final SignUpRequest dto) {
        if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new EmailDuplicateException(dto.getEmail());
        }
        String salt = PasswordEncoder.generateSalt();
        String password = PasswordEncoder.hashPassword(dto.getPassword(), salt);
        Member member = Member.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(password)
                .salt(salt)
                .build();
        return memberRepository.save(member);
    }
    //TODO: 회원가입시 SMTP 이메일 인증
}
