package com.example.datn.repository;

import com.example.datn.Model.users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface usersRepository extends JpaRepository<users, String> {
    /** Lấy tất cả ADMIN + NHÂN_VIÊN (phân trang) */
    @Query("""
           SELECT u FROM users u
           WHERE u.role.roleName IN ('ADMIN', 'NHAN_VIEN')
           """)
    Page<users> findAllStaff(Pageable pageable);

    /** Tìm kiếm username hoặc email trong ADMIN + NHÂN_VIÊN (phân trang) */
    @Query("""
           SELECT u FROM users u
           WHERE u.role.roleName IN ('ADMIN', 'NHAN_VIEN')
             AND ( LOWER(u.username) LIKE LOWER(CONCAT('%', :kw, '%'))
                   OR LOWER(u.email)    LIKE LOWER(CONCAT('%', :kw, '%')) )
           """)
    Page<users> searchStaff(String kw, Pageable pageable);
}
