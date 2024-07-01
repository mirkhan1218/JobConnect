package com.jobconnect.backend.dto;

import com.jobconnect.backend.type.EnterpriseStateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class EnterpriseMemberDTO extends MemberDTO {
    private String businessNumber;
    private String enterpriseName;
    private EnterpriseStateType enterpriseState;
}
