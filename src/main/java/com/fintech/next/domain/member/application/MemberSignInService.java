package com.fintech.next.domain.member.application;

import com.fintech.next.domain.member.dao.MemberFindDao;
import com.fintech.next.domain.member.domain.Member;
import com.fintech.next.domain.member.dto.SignInRequest;
import com.fintech.next.domain.member.exception.InvalidPasswordsException;
import com.fintech.next.global.util.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class MemberSignInService {

//    public void tokenCheck(Email email, HttpServ)
    private final MemberFindDao memberFindDao;
    public MemberSignInService(MemberFindDao memberFindDao) {
        this.memberFindDao = memberFindDao;
    }


    public Member doSignIn(final SignInRequest dto){
        Member member = memberFindDao.findByEmail(dto.getEmail());
        String enteredPassword = dto.getPassword();
        String salt = member.getSalt();
        String hashPassword = PasswordEncoder.hashPassword(enteredPassword,salt);
        String password = member.getPassword();
        if(!password.equals(hashPassword)){
            throw new InvalidPasswordsException();
        }
        //TODO: 로그인시 JWT 토큰 발급
        return member;
    }

}
