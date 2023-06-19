package com.fintech.next.domain.member.dto;


import com.fintech.next.domain.member.domain.Member;
import com.fintech.next.domain.model.Email;
import com.fintech.next.domain.model.Name;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SignUpRequest {

    @Valid
    private Email email;

    @Valid
    private Name name;

    SignUpRequest(@Valid Email email, @Valid Name name){
        this.email = email;
        this.name = name;
    }
    public Member toEntity(){
        return Member.builder()
                .name(name)
                .email(email)
                .build();
    }

}
