package com.example.youtube_tutorial.dto.youtube;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDto {
    private Long memberIdx;
    private String memberId;
    private String memberPassword;
}
