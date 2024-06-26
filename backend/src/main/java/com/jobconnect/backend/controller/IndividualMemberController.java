package com.jobconnect.backend.controller;

import com.jobconnect.backend.domain.member.IndividualMember;
import com.jobconnect.backend.dto.IndividualMemberDTO;
import com.jobconnect.backend.service.IndividualMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/individual")
public class IndividualMemberController {

    // 의존성 주입 방법 : 생성자 주입 (Constructor Injection)
    private final IndividualMemberService<IndividualMemberDTO, IndividualMember> service;

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody IndividualMemberDTO memberDTO) {
        String result = service.save(memberDTO);

        if(result.equals("OK")) {

            return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입 완료");
        } else {

            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping
    public ResponseEntity<List<IndividualMemberDTO>> individualMembers() {
        List<IndividualMemberDTO> resultList = service.findAll();

        return ResponseEntity.ok(resultList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndividualMemberDTO> individualMemberFindById(@PathVariable Long id) {
        Optional<IndividualMemberDTO> result = service.findById(id);

        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody IndividualMemberDTO memberDTO) {

        service.update(id, memberDTO);

        return ResponseEntity.ok("Update success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        String result = service.delete(id);
        if (result.equals("OK")) {

            return ResponseEntity.ok("Deleted individual member");
        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed");
        }
    }
}
