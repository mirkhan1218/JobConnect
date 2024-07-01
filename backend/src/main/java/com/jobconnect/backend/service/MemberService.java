package com.jobconnect.backend.service;

import java.util.List;
import java.util.Optional;

public interface MemberService<T, U> {
    String save(T t);
    Optional<T> findById(Long id);
    Optional<T> findByEmail(String email);
    Optional<T> findByPhone(String phone);
    List<T> findAll();
    T update(Long id, T t);
    String delete(Long id);
    T convertToDTO(U u);
}
