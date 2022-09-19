package com.revature.repos;

import com.revature.models.LoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LoginRepo extends JpaRepository<LoginInfo, Integer> {
    boolean updateLoginLog(LoginInfo loginInfo);
}
