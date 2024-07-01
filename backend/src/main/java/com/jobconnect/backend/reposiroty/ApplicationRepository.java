package com.jobconnect.backend.reposiroty;

import com.jobconnect.backend.domain.Application;
import com.jobconnect.backend.domain.JobPost;
import com.jobconnect.backend.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByMemberId(Member memberId);
    List<Application> findByJobPostId(JobPost jobPostId);
}
