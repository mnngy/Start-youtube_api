package com.example.youtube_tutorial.dto.youtube;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class YouTubeDto {

    private String title;
    private String thumbnailPath;
    private String videoId;

    @Builder(toBuilder = true)
    public YouTubeDto(String title, String thumbnailPath, String videoId) {
        this.title = title;
        this.thumbnailPath = thumbnailPath;
        this.videoId = videoId;
    }
}
