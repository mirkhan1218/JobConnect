package com.jobconnect.backend.service;

import java.util.List;
import java.util.Optional;

public interface ApplicationService<T, U> {
    String save(T t);
    Optional<T> findById(Long id);
    List<T> findByMemberId(Long memberId);
    List<T> findByJobPostId(Long jobPostId);
    List<T> findAll();
    T update(Long id, T t);
    String delete(Long id);
    T convertToDTO(U u);
}
