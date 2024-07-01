package com.jobconnect.backend.dto;

import com.jobconnect.backend.type.MemberType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MemberDTO {
    private Long memberId;
    private String email;
    private String password;
    private String memberName;
    private String phone;
    private MemberType memberType;
    private LocalDateTime memberCreatedAt;
    private LocalDateTime memberUpdatedAt;
}
