package com.jobconnect.backend.reposiroty;

import com.jobconnect.backend.domain.member.IndividualMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IndividualMemberRepository extends JpaRepository<IndividualMember, Long> {
    Optional<IndividualMember> findByEmail(String email);
    Optional<IndividualMember> findByPhone(String phone);
}
