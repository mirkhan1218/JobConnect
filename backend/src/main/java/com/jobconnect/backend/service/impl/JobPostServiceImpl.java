package com.jobconnect.backend.service.impl;

import com.jobconnect.backend.domain.JobPost;
import com.jobconnect.backend.domain.member.EnterpriseMember;
import com.jobconnect.backend.dto.EnterpriseMemberDTO;
import com.jobconnect.backend.dto.JobPostDTO;
import com.jobconnect.backend.reposiroty.EnterpriseMemberRepository;
import com.jobconnect.backend.reposiroty.JobPostRepository;
import com.jobconnect.backend.service.JobPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class JobPostServiceImpl implements JobPostService<JobPostDTO, JobPost> {

    // 의존성 주입 방법 : 생성자 주입 (Constructor Injection)
    private final JobPostRepository repository;
    private final EnterpriseMemberRepository enterpriseMemberRepository;

    @Override
    public String save(JobPostDTO jobPostDTO) {

        Optional<EnterpriseMember> optionalMember = enterpriseMemberRepository.findById(jobPostDTO.getEnterpriseMember().getMemberId());

        if(optionalMember.isPresent()) {
            EnterpriseMember member = optionalMember.get();

            JobPost jobpost = JobPost.builder()
                    .enterpriseMember(member)
                    .jobTitle(jobPostDTO.getJobTitle())
                    .jobContent(jobPostDTO.getJobContent())
                    .build();

            repository.save(jobpost);
            return "OK";
        } else {

            throw new IllegalArgumentException("회원이 없습니다.");
        }
    }

    @Override
    public Optional<JobPostDTO> findById(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }

    @Override
    public Optional<JobPostDTO> findByEnterpriseMemberId(Long enterpriseMemberId) {
        Optional<EnterpriseMember> enterpriseMember = enterpriseMemberRepository.findById(enterpriseMemberId);

        if(enterpriseMember.isPresent()) {

            return repository.findById(enterpriseMember.get().getMemberId()).map(this::convertToDTO);
        } else {

            return Optional.empty();
        }
    }

    @Override
    public List<JobPostDTO> findAll() {

        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JobPostDTO update(Long id, JobPostDTO jobPostDTO) {

        Optional<JobPost> optionalJob = repository.findById(id);

        if (optionalJob.isPresent()) {

            JobPost jobPost = optionalJob.get();

            jobPost.update(jobPostDTO);
            JobPost savedJobPost = repository.save(jobPost);

            return convertToDTO(savedJobPost);
        } else {

            throw new IllegalArgumentException("채용 공고 없음");
        }
    }

    @Override
    public String delete(Long id) {
        repository.deleteById(id);
        return "OK";
    }

    @Override
    public JobPostDTO convertToDTO(JobPost jobPost) {
        return JobPostDTO.builder()
                .jobId(jobPost.getJobId())
                .enterpriseMember(jobPost.getEnterpriseMember())
                .jobTitle(jobPost.getJobTitle())
                .jobContent(jobPost.getJobContent())
                .jobCreateAt(jobPost.getJobCreateAt())
                .jobUpdateAt(jobPost.getJobUpdateAt())
                .build();
    }
}
