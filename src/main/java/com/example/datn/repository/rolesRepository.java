package com.example.datn.repository;

import com.example.datn.Model.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface rolesRepository extends JpaRepository<Roles, String> {
    /** Tìm kiếm role theo tên hoặc mã (có phân trang) */
    Page<Roles> findByRoleNameContainingIgnoreCaseOrRoleCodeContainingIgnoreCase(
            String name, String code, Pageable pageable);

    Optional<Roles> findByRoleCode(String roleCode);
}
