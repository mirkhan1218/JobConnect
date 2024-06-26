package com.jobconnect.backend.controller;

import com.jobconnect.backend.domain.member.EnterpriseMember;
import com.jobconnect.backend.dto.EnterpriseMemberDTO;
import com.jobconnect.backend.dto.IndividualMemberDTO;
import com.jobconnect.backend.service.EnterpriseMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enterprise")
public class EnterpriseMemberController {

    // 의존성 주입 방법 : 생성자 주입 (Constructor Injection)
    private final EnterpriseMemberService<EnterpriseMemberDTO, EnterpriseMember> service;

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody EnterpriseMemberDTO memberDTO) {
        String result = service.save(memberDTO);

        if(result.equals("OK")) {

            return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입 완료");
        } else {

            return ResponseEntity.badRequest().body(result);
        }
    }

    @GetMapping
    public ResponseEntity<List<EnterpriseMemberDTO>> enterpriseMembers() {
        List<EnterpriseMemberDTO> resultList = service.findAll();

        return ResponseEntity.ok(resultList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnterpriseMemberDTO> enterpriseMemberFindById(@PathVariable Long id) {
        Optional<EnterpriseMemberDTO> result = service.findById(id);

        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody EnterpriseMemberDTO memberDTO) {

        service.update(id, memberDTO);

        return ResponseEntity.ok("Update success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        String result = service.delete(id);
        if (result.equals("OK")) {

            return ResponseEntity.ok("Deleted enterprise member");
        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed");
        }
    }
}
