package com.fintech.next.domain.member.exception;

import com.fintech.next.domain.model.Email;

public class EmailDuplicateException extends RuntimeException{
    public EmailDuplicateException(final Email email){
        super(email.getValue()+" is already exists");
    }

}
