package com.jobconnect.backend.controller;

import com.jobconnect.backend.domain.JobPost;
import com.jobconnect.backend.dto.JobPostDTO;
import com.jobconnect.backend.service.JobPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job")
public class JobPostController {

    // 의존성 주입 방법 : 생성자 주입 (Constructor Injection)
    private final JobPostService<JobPostDTO, JobPost> service;

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody JobPostDTO jobPostDTO) {

        String result = service.save(jobPostDTO);

        if(result.equals("OK")) {

            return ResponseEntity.status(HttpStatus.CREATED).body("채용 공고 등록 완료");
        } else {

            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPostDTO> jobPostById(@PathVariable Long id) {

        Optional<JobPostDTO> result = service.findById(id);

        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<JobPostDTO>> jobPosts() {

        List<JobPostDTO> resultList = service.findAll();

        return ResponseEntity.ok(resultList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modify(@PathVariable Long id, @RequestBody JobPostDTO jobPostDTO) {

        service.update(id, jobPostDTO);

        return ResponseEntity.ok("Update success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        String result = service.delete(id);
        if (result.equals("OK")) {

            return ResponseEntity.ok("Deleted Job Post");
        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed");
        }
    }
}
