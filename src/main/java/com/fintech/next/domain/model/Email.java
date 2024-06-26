package com.fintech.next.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable //@Embedded와의 관계를 생각!
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"value"})
public class Email {

    @javax.validation.constraints.Email
    @Column(name = "email", length = 50)
    @NotEmpty
    private String value;
    private Email(final String value){
        this.value = value;
    }

    public static Email of(final String value){
        return new Email(value);
    }

    public String getHost(){
        final int index = value.indexOf("@");
        return index != -1 ? value.substring(index + 1) : null;
    }

    public String getId(){
        final int index = value.indexOf("@");
        return index != -1 ? value.substring(0, index) : null;
    }


}
