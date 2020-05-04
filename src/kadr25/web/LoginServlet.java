package kadr25.web;

import kadr25.dao.login.dao.LoginDao;
import kadr25.dao.login.impl.LoginDaoImpl;
import kadr25.model.Author;
import kadr25.model.LoginUser;
import kadr25.model.Statistics;
import kadr25.service.login.impl.LoginService;
import kadr25.service.login.services.LoginServiceImpl;
import kadr25.util.Utility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "LoginServlet", urlPatterns = "/lc")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        proccesRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        proccesRequest(request, response);
    }


    private void proccesRequest(HttpServletRequest request, HttpServletResponse response) {
        LoginDao loginDao = new LoginDaoImpl();
        LoginService loginService = new LoginServiceImpl(loginDao);

        String address = null;
        String action = null;
        try {
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }

            if (action.equalsIgnoreCase("login")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                    LoginUser loginUser = loginService.login(username, password);
                    if (loginUser != null) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("login", loginUser);
                        address = "index.jsp";
                    } else {
                        request.setAttribute("invalid", "ISTIFADECI ADI ve ya SHIFRE yalnishdir !!!");
                        address = "login.jsp";
                    }
                } else {
                    request.setAttribute("invalid", "ISTIFADECI ADI ve ya SHIFRE xanalari doldurulmayib !!!");
                    address = "login.jsp";
                }
            } else if (action.equalsIgnoreCase("forgotPassword")) {
                address = "/WEB-INF/pages/views/forgotPassword.jsp";
            } else if (action.equalsIgnoreCase("forgot")) {
                String email = request.getParameter("email");
                String token = UUID.randomUUID().toString();
                if (email != null && !email.isEmpty()) {
                    LoginUser loginUser = loginService.checkEmail(email);
                    if (loginUser != null) {
                        boolean isUpdate = loginService.updateTokenById(token, loginUser.getId());
                        if (isUpdate) {
                            String text = "Şifrəni dəyişmək üçün bu linkə klik edin !!! http://localhost:8081/kadr25/lc?action=changePassword&token=" + token;
                            boolean isSend = Utility.sendMail(email, "Forgot Password", text);
                            if (isSend) {
                                request.setAttribute("ok", "Mail göndərildi ");
                            } else {
                                request.setAttribute("err", "Mail gonderilmedi !");
                            }
                        } else {
                            request.setAttribute("err", "Token xetasi !");
                        }
                    } else {
                        request.setAttribute("err", "Mail tapilmadi !");
                    }
                } else {
                    request.setAttribute("err", "Mail duzgun daxil edilmeyib !");
                }
                address = "/WEB-INF/pages/views/forgotPassword.jsp";
            } else if (action.equalsIgnoreCase("changePassword")) {
                String token = request.getParameter("token");
                request.setAttribute("token", token);
                address = "/WEB-INF/pages/views/changePassword.jsp";
            } else if (action.equalsIgnoreCase("change")) {
                String token = request.getParameter("token");
                String password = request.getParameter("password");
                String Rpassword = request.getParameter("Rpassword");
                if (password.equalsIgnoreCase(Rpassword)) {
                    boolean isChanged = loginService.changePassword(password, token);
                    if (isChanged) {
                        boolean isChangedToken = loginService.updateToken(token);
                        if (isChangedToken) {
                            request.setAttribute("ok", "Token deyishdirildi");
                            address = "login.jsp";
                        } else {
                            request.setAttribute("err", "Token deyishdirilmedi");
                        }

                    } else {
                        request.setAttribute("err", "Shifre deyishdirilmedi");
                    }
                } else {
                    request.setAttribute("err", "Daxil edilmish sihfrenin duzgunluyu tesdiq edilmedi");
                }
                address = "/WEB-INF/pages/views/changePassword.jsp";
            }
            if (address != null) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(address);
                requestDispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

