package com.fintech.next.domain.member.dto;

import com.fintech.next.domain.member.domain.Member;
import com.fintech.next.domain.model.Email;
import com.fintech.next.domain.model.Name;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {

    private Email email;

    private Name name;

    public MemberResponse(final Member member){
        this.email = member.getEmail();
        this.name = member.getName();
    }

}
