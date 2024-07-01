package com.jobconnect.backend.service.impl;

import com.jobconnect.backend.domain.Application;
import com.jobconnect.backend.domain.JobPost;
import com.jobconnect.backend.domain.member.Member;
import com.jobconnect.backend.dto.ApplicationDTO;
import com.jobconnect.backend.reposiroty.ApplicationRepository;
import com.jobconnect.backend.reposiroty.JobPostRepository;
import com.jobconnect.backend.reposiroty.MemberRepository;
import com.jobconnect.backend.service.ApplicationService;
import com.jobconnect.backend.type.ApplicationStateType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationServiceImpl implements ApplicationService<ApplicationDTO, Application> {

    // 의존성 주입 방법 : 생성자 주입 (Constructor Injection)
    private final ApplicationRepository repository;
    private final MemberRepository memberRepository;
    private final JobPostRepository jobPostRepository;

    @Override
    public String save(ApplicationDTO applicationDTO) {

        Optional<Member> optionalMember = memberRepository.findById(applicationDTO.getMemberId());
        Optional<JobPost> optionalJobPost = jobPostRepository.findById(applicationDTO.getApplicationId());

        if(optionalMember.isPresent() && optionalJobPost.isPresent()) {
            Member member = optionalMember.get();
            JobPost jobPost = optionalJobPost.get();

            Application application = Application.builder()
                    .jobPostId(jobPost)
                    .memberId(member)
                    .stateType(ApplicationStateType.APPLY)
                    .build();

            repository.save(application);

            return "OK";
        } else {

            throw new IllegalArgumentException("공고가 없습니다.");
        }
    }

    @Override
    public Optional<ApplicationDTO> findById(Long id) {

        return repository.findById(id).map(this::convertToDTO);
    }

    @Override
    public List<ApplicationDTO> findByJobPostId(Long id) {

        Optional<JobPost> optionalJobPost = jobPostRepository.findById(id);

        return optionalJobPost.map(jobPost -> repository.findByJobPostId(jobPost).stream()
                .map(this::convertToDTO).collect(Collectors.toList())).orElse(null);
    }

    @Override
    public List<ApplicationDTO> findByMemberId(Long memberId) {

        Optional<Member> optionalMember = memberRepository.findById(memberId);

        // 예외처리 추가
        return optionalMember.map(member -> repository.findByMemberId(member).stream()
                .map(this::convertToDTO).collect(Collectors.toList())).orElse(null);
    }

    @Override
    public List<ApplicationDTO> findAll() {

        return repository.findAll().stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ApplicationDTO update(Long id, ApplicationDTO applicationDTO) {

        Optional<Application> optionalApplication = repository.findById(id);

        if (optionalApplication.isPresent()) {

            Application application = optionalApplication.get();
            application.updateState(applicationDTO);

            Application savedApplication = repository.save(application);

            return convertToDTO(savedApplication);
        } else {

            // 예외처리 추가
            return null;
        }
    }

    @Override
    public String delete(Long id) {

        repository.deleteById(id);

        return "OK";
    }

    @Override
    public ApplicationDTO convertToDTO(Application application) {

        return ApplicationDTO.builder()
                .memberId(application.getMemberId().getMemberId())
                .jobPostId(application.getJobPostId().getJobId())
                .stateType(String.valueOf(application.getStateType()))
                .applicationCreateAt(application.getApplicationCreatedAt())
                .applicationUpdateAt(application.getApplicationCreatedAt())
                .build();
    }
}
