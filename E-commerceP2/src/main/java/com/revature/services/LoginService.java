package com.revature.services;

import com.revature.models.LoginInfo;

public interface LoginService {
    LoginInfo addLoginLog(LoginInfo loginInfo);
    boolean updateLoginLog(LoginInfo loginInfo);
}
