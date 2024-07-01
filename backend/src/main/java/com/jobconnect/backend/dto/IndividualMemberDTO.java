package com.jobconnect.backend.dto;

import com.jobconnect.backend.type.GenderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class IndividualMemberDTO extends MemberDTO {
    private Date memberBirth;
    private GenderType memberGender;
}
