package com.example.datn.repository;

import com.example.datn.Model.roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface rolesRepository extends JpaRepository<roles, String> {
    /** Tìm kiếm role theo tên hoặc mã (có phân trang) */
    Page<roles> findByRoleNameContainingIgnoreCaseOrRoleCodeContainingIgnoreCase(
            String name, String code, Pageable pageable);
}
