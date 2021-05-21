package com.example.youtube_tutorial.controller.youtube;

import com.example.youtube_tutorial.dto.youtube.YouTubeDto;
import com.example.youtube_tutorial.service.youtube.spec.YouTubeProvider;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
