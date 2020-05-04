package kadr25.dao.login.impl;

import kadr25.DbHelper;
import kadr25.dao.login.dao.LoginDao;
import kadr25.model.LoginUser;
import kadr25.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDaoImpl implements LoginDao {
    @Override
    public LoginUser login(String username, String password) throws Exception {
        LoginUser loginUser = new LoginUser();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID, USERNAME, PASSWORD FROM LOGIN WHERE USERNAME = ? AND PASSWORD = ?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    loginUser.setUsername(rs.getString("USERNAME"));
                    loginUser.setPassword(rs.getString("PASSWORD"));
                } else {
                    loginUser = null;
                }
            } else {
                System.out.println("Error in connection !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return loginUser;
    }

    @Override
    public LoginUser checkEmail(String email) throws Exception {
        LoginUser loginUser = new LoginUser();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID, USERNAME, PASSWORD , TOKEN FROM LOGIN WHERE USERNAME = ?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    loginUser.setId(rs.getInt("ID"));
                    loginUser.setToken(rs.getString("TOKEN"));
                } else {
                    loginUser = null;
                }
            } else {
                System.out.println("Error in connection !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return loginUser;
    }

    @Override
    public boolean updateTokenById(String token, Integer id) throws Exception {
        boolean isUpdated = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE LOGIN SET TOKEN = ? WHERE ID = ?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, token);
                ps.setInt(2, id);
                ps.execute();
//                c.commit();
                isUpdated = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, null, ps);
        }
        System.out.println("update token by id" + isUpdated);
        return isUpdated;
    }

    @Override
    public boolean changePassword(String password, String token) throws Exception {
        boolean isUpdated = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE LOGIN SET PASSWORD = ? WHERE TOKEN = ?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, password);
                ps.setString(2, token);
                ps.execute();
                //     c.commit();
                isUpdated = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, null, ps);
        }
        System.out.println("chanegePassswrod" + isUpdated);

        return isUpdated;
    }

    @Override
    public boolean updateToken(String token) throws Exception {
        boolean isUpdated = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE LOGIN SET TOKEN = ? WHERE TOKEN = ?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, null);
                ps.setString(2, token);
                ps.execute();
                //     c.commit();
                isUpdated = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, null, ps);
        }
        System.out.println("update token" + isUpdated);

        return isUpdated;
    }

}
