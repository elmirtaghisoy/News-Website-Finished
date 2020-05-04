package kadr25.web;

import kadr25.DbHelper;
import kadr25.dao.statistics.dao.StatisticsDao;
import kadr25.dao.statistics.impl.StatisticsDaoImpl;
import kadr25.model.Statistics;
import kadr25.service.statistics.impl.StatisticsService;
import kadr25.service.statistics.service.StatisticsServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "StatisticsServlet", urlPatterns = "/sc")
public class StatisticsServlet extends HttpServlet {

    StatisticsDao statisticsDao = new StatisticsDaoImpl();
    StatisticsService statisticsService = new StatisticsServiceImpl(statisticsDao);

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
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }
            if (action.equalsIgnoreCase("users")) {
                    List<Statistics> authorsStatisticsList = statisticsService.getGeneralAuthorsStatistics();
                    request.setAttribute("authorsStatisticsList", authorsStatisticsList);
                    address = "/WEB-INF/pages/users.jsp";
            } else if (action.equalsIgnoreCase("getStatisticsByMonth")) {
                Integer authorId = Integer.parseInt(request.getParameter("authorId"));
                Integer month = Integer.parseInt(request.getParameter("month"));
                List<Statistics> statisticsListByMonth = statisticsService.getStatisticsByMonth(month, authorId);
                request.setAttribute("statistics", statisticsListByMonth);
                address = "/WEB-INF/pages/views/monthStatistics.jsp";
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
