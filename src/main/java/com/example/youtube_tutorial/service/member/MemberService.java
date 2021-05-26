package com.example.youtube_tutorial.service.member;

import com.example.youtube_tutorial.dto.youtube.MemberDto;
import com.example.youtube_tutorial.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 아이디 중복 확인을 해주는 서비스
     */
    public void memberIdCheckAction(HttpServletRequest request, HttpServletResponse response) {
        String memberId = request.getParameter("memberId");
        int result = memberRepository.memberSelectCountById(memberId);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;

        try {
            out = response.getWriter();
            if (result == 0) {
                out.println("0");
                out.close();
            } else {
                out.println("1");
                out.close();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 회원가입 서비스
     */
    public void memberjoin(MemberDto memberDto) {
        memberRepository.memberSave(memberDto);
    }

    /**
     * 로그인 서비스
     */
    public void memberLogin(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
        try {
            response.setContentType("text/html;charset=utf-8");

            int result = memberRepository.memberSelectPasswordById(memberDto);

            if (result == 1) {
                HttpSession session = request.getSession();
                session.setAttribute("sessionMemberId", memberDto.getMemberId());

                PrintWriter out = response.getWriter();
                out.println("<script>" +
                        "alert('로그인을 성공했습니다!');" +
                        "</script>");
                out.close();
            } else if (result == 0) {
                PrintWriter out = response.getWriter();
                out.println("<script>" +
                        "alert('비밀번호가 일치하지 않습니다.');" +
                        "</script>");
                out.close();
            } else {
                PrintWriter out = response.getWriter();
                out.println("<script>" +
                        "alert('아이디가 일치하지 않습니다.');" +
                        "</script>");
                out.close();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
