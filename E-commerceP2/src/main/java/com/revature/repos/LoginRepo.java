package com.revature.repos;

import com.revature.models.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
@Transactional
public interface LoginRepo extends JpaRepository<LoginLog, Integer> {
    @Modifying
    @Query(value = "UPDATE login_log SET logout_time = ?1 WHERE login_log_id = ?2", nativeQuery = true)
    int updateLoginLog(LocalDateTime logoutTime, int login_log_id);
}
