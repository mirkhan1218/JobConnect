package com.jobconnect.backend.domain;

import com.jobconnect.backend.domain.member.EnterpriseMember;
import com.jobconnect.backend.dto.JobPostDTO;
import com.jobconnect.backend.dto.MemberDTO;
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
@Table(name = "job_post")
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @ManyToOne
    @JoinColumn(name = "enterprise_member_id", nullable = false)
    private EnterpriseMember enterpriseMember;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String jobContent;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime jobCreateAt;

    @LastModifiedDate
    private LocalDateTime jobUpdateAt;

    @PrePersist
    protected void onCreate() {
        jobCreateAt = LocalDateTime.now();
        jobUpdateAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        jobUpdateAt = LocalDateTime.now();
    }

    public void update(JobPostDTO jobPostDTO) {
        this.jobTitle = jobPostDTO.getJobTitle();
        this.jobContent = jobPostDTO.getJobContent();
    }
}
