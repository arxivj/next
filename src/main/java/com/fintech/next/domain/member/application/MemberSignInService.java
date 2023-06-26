package com.fintech.next.domain.member.application;

import com.fintech.next.domain.member.dao.MemberFindDao;
import com.fintech.next.domain.member.domain.Member;
import com.fintech.next.domain.member.dto.SignInRequest;
import com.fintech.next.domain.member.exception.InvalidPasswordsException;
import com.fintech.next.domain.model.Email;
import com.fintech.next.global.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class MemberSignInService {
    private final MemberFindDao memberFindDao;

    public MemberSignInService(MemberFindDao memberFindDao) {
        this.memberFindDao = memberFindDao;
    }


    public void doSignIn(final SignInRequest dto,HttpServletResponse response) {
        Email email = dto.getEmail();
        Member member = memberFindDao.findByEmail(email);
        String enteredPassword = dto.getPassword();
        String salt = member.getSalt();
        String hashPassword = PasswordEncoder.hashPassword(enteredPassword, salt);
        String password = member.getPassword();
        if (!password.equals(hashPassword)) {
            throw new InvalidPasswordsException();
        }
        //TODO: HandlerInterceptor에 리턴타입 boolean으로 검증
        //TODO: + redis에 저장된 refreshToken은 Client IP or Payload의 문자열 등을 통해 검증
//        JwtUtil.validateAccessToken(response.getHeader(JwtProperties.HEADER_STRING));

        String accessToken = JwtUtil.createAccessToken(member, email);
        JwtUtil.accessTokenSetHeader(response, accessToken);
        String refreshToken = JwtUtil.createRefreshToken(member, email);
        CookieUtil.generateRefreshTokenCookie(response, refreshToken);
    }


    public void getCookie(Email email, HttpServletResponse response){
        Member member = memberFindDao.findByEmail(email);


    }




}
