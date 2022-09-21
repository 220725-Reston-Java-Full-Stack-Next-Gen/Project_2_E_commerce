package com.revature.services;

import com.revature.models.LoginLog;
import com.revature.repos.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepo loginRepo;


    @Override
    public int addLoginLog(LoginLog loginLog) {
        return loginRepo.save(loginLog).getLoginLogID();
    }

    @Override
    public int updateLoginLog(LoginLog loginLog) {
        return loginRepo.updateLoginLog(loginLog.getLogoutTime(), loginLog.getLoginLogID());
    }
}
