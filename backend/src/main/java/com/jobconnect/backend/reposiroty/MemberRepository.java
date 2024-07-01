package com.jobconnect.backend.reposiroty;

import com.jobconnect.backend.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
