package com.example.youtube_tutorial.controller.youtube;

import com.example.youtube_tutorial.dto.youtube.YouTubeDto;
import com.example.youtube_tutorial.service.youtube.spec.YouTubeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class YouTubeController {

    private final YouTubeProvider youTubeProvider;

    @Autowired
    public YouTubeController(YouTubeProvider youTubeProvider) {
        this.youTubeProvider = youTubeProvider;
    }

    @GetMapping("youtube")
    public YouTubeDto index() {
        return youTubeProvider.get();
    }

    @GetMapping("main")
    public String moveMain(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("sessionMemberId") != null) {
            return "main";
        } else {
            return "login";
        }
    }

    @GetMapping("search-title")
    public String moveSearchQuery(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("sessionMemberId") != null) {
            return "search-title";
        } else {
            return "login";
        }
    }

    @GetMapping("search-title-detail")
    public String moveSearchTitleDetail(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("sessionMemberId") != null) {
            return "search-title-detail";
        } else {
            return "login";
        }
    }

    @GetMapping("search-channel")
    public String moveSearchChannel(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("sessionMemberId") != null) {
            return "search-channel";
        } else {
            return "login";
        }
    }
}
