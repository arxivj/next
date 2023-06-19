package com.fintech.next.domain.member.api;

import com.fintech.next.domain.member.application.MemberSignUpService;
import com.fintech.next.domain.member.dao.MemberFindDao;
import com.fintech.next.domain.member.domain.Member;
import com.fintech.next.domain.member.dto.MemberResponse;
import com.fintech.next.domain.member.dto.SignUpRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberApi {

        private final MemberSignUpService memberSignUpService;

        private final MemberFindDao memberFindDao;

        public MemberApi(MemberSignUpService memberSignUpService, MemberFindDao memberFindDao){
            this.memberSignUpService = memberSignUpService;
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


}
