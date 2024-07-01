package com.jobconnect.backend.service.impl;

import com.jobconnect.backend.domain.member.IndividualMember;
import com.jobconnect.backend.dto.IndividualMemberDTO;
import com.jobconnect.backend.reposiroty.IndividualMemberRepository;
import com.jobconnect.backend.service.IndividualMemberService;
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
public class IndividualMemberServiceImpl implements IndividualMemberService<IndividualMemberDTO, IndividualMember> {

    // 의존성 주입 방법 : 생성자 주입 (Constructor Injection)
    private final IndividualMemberRepository repository;

    @Override
    public String save(IndividualMemberDTO memberDTO) {
        Optional<IndividualMember> optionalFindByEmailIndividualMember = repository.findByEmail(memberDTO.getEmail());
        Optional<IndividualMember> optionalFindByPhoneIndividualMember = repository.findByPhone(memberDTO.getPhone());

        if (optionalFindByEmailIndividualMember.isEmpty() && optionalFindByPhoneIndividualMember.isEmpty()) {
            IndividualMember member = IndividualMember.builder()
                    .email(memberDTO.getEmail())
                    .password(memberDTO.getPassword())
                    .memberName(memberDTO.getMemberName())
                    .phone(memberDTO.getPhone())
                    .memberBirth(memberDTO.getMemberBirth())
                    .memberGender(memberDTO.getMemberGender())
                    .memberType(MemberType.INDIVIDUAL)
                    .build();

            repository.save(member);

            return "OK";
        } else {

            throw new IllegalArgumentException("중복된 회원이 있습니다.");
        }
    }

    @Override
    public Optional<IndividualMemberDTO> findById(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }

    @Override
    public Optional<IndividualMemberDTO> findByEmail(String email) {
        return repository.findByEmail(email).map(this::convertToDTO);
    }

    @Override
    public Optional<IndividualMemberDTO> findByPhone(String phone) {
        return repository.findByPhone(phone).map(this::convertToDTO);
    }

    @Override
    public List<IndividualMemberDTO> findAll() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public IndividualMemberDTO update(Long id, IndividualMemberDTO memberDTO) {
        Optional<IndividualMember> optionalMember = repository.findById(id);

        if (optionalMember.isPresent()) {

            IndividualMember member = optionalMember.get();
            member.update(memberDTO);

            IndividualMember savedMember = repository.save(member);

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
    public IndividualMemberDTO convertToDTO(IndividualMember individualMember) {
        return IndividualMemberDTO.builder()
                .memberId(individualMember.getMemberId())
                .email(individualMember.getEmail())
                .password(individualMember.getPassword())
                .memberName(individualMember.getMemberName())
                .phone(individualMember.getPhone())
                .memberBirth(individualMember.getMemberBirth())
                .memberGender(individualMember.getMemberGender())
                .memberType(individualMember.getMemberType())
                .memberCreatedAt(individualMember.getMemberCreatedAt())
                .memberUpdatedAt(individualMember.getMemberUpdatedAt())
                .build();
    }
}
