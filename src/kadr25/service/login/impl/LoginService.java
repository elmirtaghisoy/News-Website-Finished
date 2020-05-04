package kadr25.service.login.impl;

import kadr25.model.LoginUser;

public interface LoginService {
    LoginUser login(String username, String password) throws Exception;

    LoginUser checkEmail(String email) throws Exception;

    boolean updateToken(String token) throws Exception;

    boolean changePassword(String password, String token) throws Exception;

    boolean updateTokenById(String token, Integer id) throws Exception  ;
}
