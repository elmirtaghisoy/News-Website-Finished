package kadr25.service.login.services;

import kadr25.dao.login.dao.LoginDao;
import kadr25.model.LoginUser;
import kadr25.service.login.impl.LoginService;

public class LoginServiceImpl implements LoginService {

    private LoginDao loginDao;

    public LoginServiceImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    @Override
    public LoginUser login(String username, String password) throws Exception {
        return loginDao.login(username, password);
    }

    @Override
    public LoginUser checkEmail(String email) throws Exception {
        return loginDao.checkEmail(email);
    }

    @Override
    public boolean updateToken(String token) throws Exception {
        return loginDao.updateToken(token);
    }

    @Override
    public boolean changePassword(String password, String token) throws Exception {
        return loginDao.changePassword(password, token);
    }

    @Override
    public boolean updateTokenById(String token, Integer id) throws Exception {
        return loginDao.updateTokenById(token, id);
    }
}
