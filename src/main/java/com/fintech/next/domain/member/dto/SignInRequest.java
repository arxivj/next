package com.fintech.next.domain.member.dto;

import com.fintech.next.domain.model.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SignInRequest {


    @Valid
    private Email email;

    private String password;

    SignInRequest(@Valid Email email, String password){
        this.email = email;
        this.password = password;
    }


}
