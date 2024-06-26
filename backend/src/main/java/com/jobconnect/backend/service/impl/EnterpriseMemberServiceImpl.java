package com.jobconnect.backend.service.impl;

import com.jobconnect.backend.domain.member.EnterpriseMember;
import com.jobconnect.backend.dto.EnterpriseMemberDTO;
import com.jobconnect.backend.reposiroty.EnterpriseMemberRepository;
import com.jobconnect.backend.service.EnterpriseMemberService;
import com.jobconnect.backend.type.MemberType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EnterpriseMemberServiceImpl implements EnterpriseMemberService<EnterpriseMemberDTO, EnterpriseMember> {

    // 의존성 주입 방법 : 생성자 주입 (Constructor Injection)
    private final EnterpriseMemberRepository repository;

    @Override
    public String save(EnterpriseMemberDTO memberDTO) {
        Optional<EnterpriseMember> optionalFindByEmailEnterpriseMember = repository.findByEmail(memberDTO.getEmail());
        Optional<EnterpriseMember> optionalFindByPhoneEnterpriseMember = repository.findByPhone(memberDTO.getPhone());

        if (optionalFindByEmailEnterpriseMember.isEmpty() && optionalFindByPhoneEnterpriseMember.isEmpty()) {
            EnterpriseMember member = EnterpriseMember.builder()
                    .email(memberDTO.getEmail())
                    .password(memberDTO.getPassword())
                    .enterpriseName(memberDTO.getEnterpriseName())
                    .phone(memberDTO.getPhone())
                    .memberType(MemberType.INDIVIDUAL)
                    .businessNumber(memberDTO.getBusinessNumber())
                    .enterpriseState(memberDTO.getEnterpriseState())
                    .build();

            repository.save(member);

            return "OK";
        } else {

            throw new IllegalArgumentException("중복된 회원이 있습니다.");
        }
    }

    @Override
    public Optional<EnterpriseMemberDTO> findById(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }

    @Override
    public Optional<EnterpriseMemberDTO> findByEmail(String email) {
        return repository.findByEmail(email).map(this::convertToDTO);
    }

    @Override
    public Optional<EnterpriseMemberDTO> findByPhone(String phone) {
        return repository.findByPhone(phone).map(this::convertToDTO);
    }

    @Override
    public List<EnterpriseMemberDTO> findAll() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EnterpriseMemberDTO update(Long id, EnterpriseMemberDTO memberDTO) {
        Optional<EnterpriseMember> optionalMember = repository.findById(id);

        if (optionalMember.isPresent()) {

            EnterpriseMember member = optionalMember.get();

            member.update(memberDTO);
            EnterpriseMember savedMember = repository.save(member);

            return convertToDTO(savedMember);
        } else {

            throw new IllegalArgumentException("유저 없음");
        }
    }

    @Override
    public String delete(Long id) {
        repository.deleteById(id);
        return "OK";
    }

    @Override
    public EnterpriseMemberDTO convertToDTO(EnterpriseMember member) {
        return EnterpriseMemberDTO.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .password(member.getPassword())
                .memberName(member.getMemberName())
                .phone(member.getPhone())
                .memberType(member.getMemberType())
                .businessNumber(member.getBusinessNumber())
                .enterpriseState(member.getEnterpriseState())
                .build();
    }
}
