package com.fintech.next.global.util;

public interface JwtProperties {

    String SECRET = "werwkjrkdfkajsdfjwqerjsdjflsafjlaksrfjwepqrjqwjkfsdkfjasdf";

    int DAY = 24 * 60 * 60 * 1000;
    int EXPIRATION_TIME = 30 * 60 * 1000;
    int EXPIRATION_TIME_REFRESH = 14 * 24 * 60 * 60 * 1000;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";

}
