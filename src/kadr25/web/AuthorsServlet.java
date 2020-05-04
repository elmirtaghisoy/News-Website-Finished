package kadr25.web;

import kadr25.DbHelper;
import kadr25.dao.author.dao.AuthorDao;
import kadr25.dao.author.impl.AuthorDaoImpl;
import kadr25.model.Author;
import kadr25.service.author.impl.AuthorService;
import kadr25.service.author.service.AuthorServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "AuthorsServlet", urlPatterns = "/ac")
public class AuthorsServlet extends HttpServlet {

    AuthorDao authorDao = new AuthorDaoImpl();
    AuthorService authorService = new AuthorServiceImpl(authorDao);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter pw = response.getWriter();
            String action = null;
            String address = null;
            boolean isExistAdress = false;
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }

            if (action.equalsIgnoreCase("addAuthor")) {
                    String name = request.getParameter("name");
                    String surname = request.getParameter("surname");
                    String profession = request.getParameter("profession");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");

                    Author author = new Author();
                    author.setName(name);
                    author.setSurname(surname);
                    author.setProfession(profession);
                    author.setEmail(email);
                    author.setPhone(phone);

                    boolean isAdded = authorService.addAuthor(author);

                    if (isAdded) {
                        pw.write("success");
                    } else {
                        pw.write("fail");
                    }
            } else if (action.equalsIgnoreCase("getAuthor")) {
                isExistAdress = true;
                Integer authorId = Integer.parseInt(request.getParameter("authorId"));
                String opp = request.getParameter("opp");
                Author author = authorService.getAuthorById(authorId);
                request.setAttribute("author", author);
                if (opp.equals("del"))
                    address = "/WEB-INF/pages/views/deleteAuthor.jsp";
                else if (opp.equals("upd"))
                    address = "/WEB-INF/pages/views/editAuthor.jsp";

            } else if (action.equalsIgnoreCase("updateAuthor")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String profession = request.getParameter("profession");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");

                Author author = new Author();
                author.setId(id);
                author.setName(name);
                author.setSurname(surname);
                author.setProfession(profession);
                author.setEmail(email);
                author.setPhone(phone);

                boolean isUpdated = authorService.updateAuthor(author);
                response.setContentType("text/html");
                if (isUpdated) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("deleteAuthor")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                Author author = new Author();
                author.setId(id);

                boolean isDeleted = authorService.deleteAuthor(author);
                if (isDeleted) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            if (isExistAdress) {
                dispatcher.forward(request, response);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
