package com.jobconnect.backend.domain.member;

import com.jobconnect.backend.dto.IndividualMemberDTO;
import com.jobconnect.backend.type.GenderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "individual_member")
public class IndividualMember extends Member {

    @Temporal(TemporalType.DATE)
    @Column(name = "member_birth", nullable = false)
    private Date memberBirth;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "member_gender", nullable = false)
    private GenderType memberGender;

    public void update(IndividualMemberDTO memberDTO) {
        super.update(memberDTO);
        this.memberBirth = memberDTO.getMemberBirth();
    }
}
