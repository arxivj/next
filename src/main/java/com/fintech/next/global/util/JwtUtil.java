package com.fintech.next.global.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fintech.next.domain.member.domain.Member;
import com.fintech.next.domain.model.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class JwtUtil {
    public static String createAccessToken(Member member, Email email){
        log.info("# Check access to 'createAccessToken' logic ");
        return JWT.create()
                .withSubject(member.getName().getFullName())
                .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
                .withClaim("email",email.getValue())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }

    public static String createRefreshToken(Member member, Email email){
        log.info("# Check access to 'createRefreshToken' logic ");
        return JWT.create()
                .withSubject(member.getName().getFullName())
                .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
                .withClaim("email",email.getValue())
                .withClaim(JwtProperties.HEADER_STRING,JwtProperties.TOKEN_PREFIX+JwtUtil.createAccessToken(member, email))
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
    }

}
