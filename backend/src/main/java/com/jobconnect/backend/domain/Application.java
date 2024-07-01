package com.jobconnect.backend.domain;

import com.jobconnect.backend.domain.member.Member;
import com.jobconnect.backend.dto.ApplicationDTO;
import com.jobconnect.backend.type.ApplicationStateType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member memberId;

    @ManyToOne
    @JoinColumn(name = "joId", nullable = false)
    private JobPost jobPostId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStateType stateType;

    @CreatedDate
    @Column(name = "create_at", nullable = false)
    private LocalDateTime applicationCreatedAt;

    @LastModifiedDate
    @Column(name = "update_at", nullable = false)
    private LocalDateTime applicationUpdatedAt;

    @PrePersist
    protected void onCreate() {
        applicationCreatedAt = LocalDateTime.now();
        applicationUpdatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        applicationUpdatedAt = LocalDateTime.now();
    }

    public void updateState(ApplicationDTO applicationDTO) {
        this.stateType = ApplicationStateType.valueOf(applicationDTO.getStateType());
    }
}
