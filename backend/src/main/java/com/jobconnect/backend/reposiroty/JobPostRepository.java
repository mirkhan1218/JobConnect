package com.jobconnect.backend.reposiroty;

import com.jobconnect.backend.domain.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {
}
