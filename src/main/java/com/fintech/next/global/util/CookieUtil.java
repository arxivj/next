package com.fintech.next.global.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.SET_COOKIE;

@Slf4j
@Component
@RequiredArgsConstructor
public class CookieUtil {

    public static void generateRefreshTokenCookie(HttpServletResponse response, String refreshToken){
        ResponseCookie cookie = ResponseCookie.from("refreshToken",refreshToken)
                .path("/")
                .httpOnly(true)
                .build();
        response.addHeader(SET_COOKIE,cookie.toString());
    }

}
