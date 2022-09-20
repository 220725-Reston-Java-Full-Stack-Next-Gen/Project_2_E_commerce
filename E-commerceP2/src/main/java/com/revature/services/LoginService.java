package com.revature.services;

import com.revature.models.LoginLog;

public interface LoginService {
    int addLoginLog(LoginLog loginLog);
    boolean updateLoginLog(LoginLog loginLog);
}
