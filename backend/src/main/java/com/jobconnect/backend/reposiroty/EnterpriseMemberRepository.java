package com.jobconnect.backend.reposiroty;

import com.jobconnect.backend.domain.member.EnterpriseMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnterpriseMemberRepository extends JpaRepository<EnterpriseMember, Long> {
    Optional<EnterpriseMember> findByEmail(String email);
    Optional<EnterpriseMember> findByPhone(String phone);
}
