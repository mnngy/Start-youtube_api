package com.example.youtube_tutorial.controller.member;

import com.example.youtube_tutorial.dto.youtube.MemberDto;
import com.example.youtube_tutorial.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping("register")
    public void registerPost(MemberDto memberDto) {
        memberService.memberjoin(memberDto);
    }

    @PostMapping("memberIdCheckAction")
    public void memberIdCheckAction(HttpServletRequest request, HttpServletResponse response) {
        memberService.memberIdCheckAction(request, response);
    }

    @PostMapping("login")
    public void loginPost(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
        memberService.memberLogin(request, response, memberDto);
    }
}
