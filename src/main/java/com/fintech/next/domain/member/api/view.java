package com.fintech.next.domain.member.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class view {

    @GetMapping("/signin")
    public String signIn(){
        return "/member/signin";
    }

    @GetMapping("/signup")
    public String signUp(){
        return "/member/signup";
    }

    @GetMapping("/members/signin")
    public String create(){
        return "/member/signin";
    }

}
