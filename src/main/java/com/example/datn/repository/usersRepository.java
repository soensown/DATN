package com.example.datn.repository;

import com.example.datn.Model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface usersRepository extends JpaRepository<Users, String> {
    /** Lấy tất cả ADMIN + NHÂN_VIÊN (phân trang) */
    @Query("""
           SELECT u FROM Users u
           WHERE u.role.roleName IN ('ADMIN', 'NHAN_VIEN')
           """)
    Page<Users> findAllStaff(Pageable pageable);

    /** Tìm kiếm username hoặc email trong ADMIN + NHÂN_VIÊN (phân trang) */
    @Query("""
           SELECT u FROM Users u
           WHERE u.role.roleName IN ('ADMIN', 'NHAN_VIEN')
             AND ( LOWER(u.username) LIKE LOWER(CONCAT('%', :kw, '%'))
                   OR LOWER(u.email)    LIKE LOWER(CONCAT('%', :kw, '%')) )
           """)
    Page<Users> searchStaff(String kw, Pageable pageable);

    Optional<Users> findByUsername(String username);
}
