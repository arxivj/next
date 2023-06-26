package com.fintech.next.domain.member.api;

import com.fintech.next.domain.member.application.MemberSignInService;
import com.fintech.next.domain.member.application.MemberSignUpService;
import com.fintech.next.domain.member.dao.MemberFindDao;
import com.fintech.next.domain.member.domain.Member;
import com.fintech.next.domain.member.dto.MemberResponse;
import com.fintech.next.domain.member.dto.SignInRequest;
import com.fintech.next.domain.member.dto.SignUpRequest;
import com.fintech.next.global.util.JwtDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberApi {
    private final MemberSignUpService memberSignUpService;
    private final MemberSignInService memberSignInService;
    private final MemberFindDao memberFindDao;

    public MemberApi(MemberSignUpService memberSignUpService, MemberSignInService memberSignInService, MemberFindDao memberFindDao){
        this.memberSignUpService = memberSignUpService;
        this.memberSignInService = memberSignInService;
        this.memberFindDao = memberFindDao;
    }

    @PostMapping("/signin")
    public ResponseEntity<String> login(@RequestBody @Valid final SignInRequest dto, HttpServletResponse response){
        memberSignInService.doSignIn(dto, response);
        return new ResponseEntity<>("Sign in successfully!", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@RequestBody @Valid final SignUpRequest dto){
        return ResponseEntity.ok(memberSignUpService.doSignUp(dto));
    }

    @GetMapping("/{id}")
    public MemberResponse getMember(@PathVariable long id){
        return new MemberResponse(memberFindDao.findById(id));
    }



}
