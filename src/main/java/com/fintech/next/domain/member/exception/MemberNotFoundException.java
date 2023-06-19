package com.fintech.next.domain.member.exception;

import javax.persistence.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {

    public MemberNotFoundException(Long target){
        super(target + " is not found");
    }

}
