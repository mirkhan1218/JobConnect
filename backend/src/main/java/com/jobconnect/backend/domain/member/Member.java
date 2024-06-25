package com.jobconnect.backend.domain.member;

import com.jobconnect.backend.dto.MemberDTO;
import com.jobconnect.backend.type.MemberType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자에 접근 제어자 설정
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@SuperBuilder
@Table(name = "common_member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_email", length = 30, nullable = false, unique = true)
    private String email;

    @Column(name = "member_password", length = 15, nullable = false)
    private String password;

    @Column(name = "member_name", length = 20, nullable = false)
    private String memberName;

    @Column(name = "member_phone", length = 13, nullable = false, unique = true)
    private String phone;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "member_type", length = 15, nullable = false)
    private MemberType memberType;

    @CreatedDate
    @Column(name = "member_create_at", nullable = false)
    private LocalDateTime memberCreatedAt;

    @LastModifiedDate
    @Column(name = "member_update_at", nullable = false)
    private LocalDateTime memberUpdatedAt;

    // 계정 생성시 날짜와 시간 생성
    @PrePersist // Entity가 DB에 Insert되기 전에 호출
    protected void onCreate() {
        memberCreatedAt = LocalDateTime.now();
        memberUpdatedAt = LocalDateTime.now();
    }

    // 계정 수정시 날짜와 시간 생성
    @PreUpdate // Entity가 DB에 Update되기 전에 호출
    protected void onUpdate() {
        memberUpdatedAt = LocalDateTime.now();
    }

    public void update(MemberDTO memberDTO) {
        this.password = memberDTO.getPassword();
        this.memberName = memberDTO.getMemberName();
        this.phone = memberDTO.getPhone();
    }
}
