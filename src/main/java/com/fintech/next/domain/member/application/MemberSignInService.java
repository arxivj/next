package com.fintech.next.domain.member.application;

import com.fintech.next.domain.member.dao.MemberFindDao;
import com.fintech.next.domain.member.domain.Member;
import com.fintech.next.domain.member.dto.SignInRequest;
import com.fintech.next.domain.member.exception.InvalidPasswordsException;
import com.fintech.next.domain.model.Email;
import com.fintech.next.global.util.JwtDto;
import com.fintech.next.global.util.JwtProperties;
import com.fintech.next.global.util.JwtUtil;
import com.fintech.next.global.util.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class MemberSignInService {
    private final MemberFindDao memberFindDao;

    public MemberSignInService(MemberFindDao memberFindDao) {
        this.memberFindDao = memberFindDao;
    }


    public HttpHeaders doSignIn(final SignInRequest dto) {
        Email email = dto.getEmail();
        Member member = memberFindDao.findByEmail(email);
        String enteredPassword = dto.getPassword();
        String salt = member.getSalt();
        String hashPassword = PasswordEncoder.hashPassword(enteredPassword, salt);
        String password = member.getPassword();
        if (!password.equals(hashPassword)) {
            throw new InvalidPasswordsException();
        }
        //TODO: 로그인시 JWT 토큰을 헤더가 아닌 쿠키에 담아 전송하는 방식으로 변경
        String token = JwtUtil.createRefreshToken(member, email);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+token);
        return httpHeaders;
    }

}
