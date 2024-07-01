package com.jobconnect.backend.service;

import java.util.List;
import java.util.Optional;

public interface JobPostService<T, U> {
    String save(T t);
    Optional<T> findById(Long id);
    Optional<T> findByEnterpriseMemberId(Long enterpriseMemberId);
    List<T> findAll();
    T update(Long id, T t);
    String delete(Long id);
    T convertToDTO(U u);
}
