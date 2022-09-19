package com.revature.services;

import com.revature.models.LoginInfo;
import com.revature.repos.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepo loginRepo;


    @Override
    public LoginInfo addLoginLog(LoginInfo loginInfo) {
        return loginRepo.save(loginInfo);
    }

    @Override
    public boolean updateLoginLog(LoginInfo loginInfo) {
        return loginRepo.updateLoginLog(loginInfo);
    }
}
