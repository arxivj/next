package com.fintech.next.domain.member.api;

import com.fintech.next.domain.member.application.MemberSignInService;
import com.fintech.next.domain.member.application.MemberSignUpService;
import com.fintech.next.domain.member.dao.MemberFindDao;
import com.fintech.next.domain.member.domain.Member;
import com.fintech.next.domain.member.dto.MemberResponse;
import com.fintech.next.domain.member.dto.SignInRequest;
import com.fintech.next.domain.member.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Member> login(@RequestBody @Valid final SignInRequest dto){
        return ResponseEntity.ok(memberSignInService.doSignIn(dto));
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
//        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
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
