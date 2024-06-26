package com.jobconnect.backend.domain.member;

import com.jobconnect.backend.dto.EnterpriseMemberDTO;
import com.jobconnect.backend.type.EnterpriseStateType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "enterprise_member")
public class EnterpriseMember extends Member {

    @Column(name = "business_number", unique = true, nullable = false)
    private String businessNumber;

    @Column(name = "enterprise_name", nullable = false)
    private String enterpriseName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "enterprise_state", nullable = false)
    private EnterpriseStateType enterpriseState;

    public void update(EnterpriseMemberDTO memberDTO) {
        super.update(memberDTO);
        this.businessNumber = memberDTO.getBusinessNumber();
        this.enterpriseName = memberDTO.getEnterpriseName();
        this.enterpriseState = memberDTO.getEnterpriseState();
    }
}
