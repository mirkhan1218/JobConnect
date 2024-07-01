package com.jobconnect.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDTO {

    private Long applicationId;
    private Long memberId;
    private Long jobPostId;
    private String stateType;
    private LocalDateTime applicationCreateAt;
    private LocalDateTime applicationUpdateAt;
}
