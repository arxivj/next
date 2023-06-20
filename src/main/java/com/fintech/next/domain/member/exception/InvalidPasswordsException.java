package com.fintech.next.domain.member.exception;

public class InvalidPasswordsException extends RuntimeException {
    public InvalidPasswordsException(){
        super("wrong password");
    }
}
