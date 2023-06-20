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

    @GetMapping("/api")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello");
    }
    public MemberApi(MemberSignUpService memberSignUpService, MemberSignInService memberSignInService, MemberFindDao memberFindDao){
        this.memberSignUpService = memberSignUpService;
        this.memberSignInService = memberSignInService;
        this.memberFindDao = memberFindDao;
    }

    @PostMapping
    public MemberResponse create(@RequestBody @Valid final SignUpRequest dto){
        final Member member = memberSignUpService.doSignUp(dto);
        return new MemberResponse(member);
    }

    @GetMapping("/{id}")
    public MemberResponse getMember(@PathVariable long id){
        return new MemberResponse(memberFindDao.findById(id));
    }

//    @PostMapping("/signup")
//    public MemberResponse login(@RequestBody @Valid final SignInRequest dto){
//        final Member member = memberSignInService
//    }

}
