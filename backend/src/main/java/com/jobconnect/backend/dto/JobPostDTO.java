package com.jobconnect.backend.dto;

import com.jobconnect.backend.domain.member.EnterpriseMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobPostDTO {
    private Long jobId;
    private EnterpriseMember enterpriseMember;
    private String jobTitle;
    private String jobContent;
    private LocalDateTime jobCreateAt;
    private LocalDateTime jobUpdateAt;
}
