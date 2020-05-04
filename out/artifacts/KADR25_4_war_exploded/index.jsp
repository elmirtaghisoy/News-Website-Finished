<%@ page import="kadr25.model.LoginUser" %><%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 05.12.2019
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kadr25</title>
    <jsp:include page="WEB-INF/pages/static/headerLinks.jsp"></jsp:include>
    <script>
        history.pushState(null.null,'login.jsp');
        window.addEventListener('popstate',function (event) {
            history.pushState(null,null,'login.jsp')
        })
    </script>
</head>
<body>
<%
    LoginUser loginUser = (LoginUser) request.getSession(false).getAttribute("login");
    if (loginUser == null) {
        response.sendRedirect("login.jsp");
    }
%>

<jsp:include page="WEB-INF/pages/static/navbar.jsp"></jsp:include>
<div style="display: none;" id="additionalNav">
    <jsp:include page="WEB-INF/pages/static/additionalNav.jsp"></jsp:include>
</div>

<div id="content"></div>

<jsp:include page="WEB-INF/pages/static/footer.jsp"></jsp:include>

<jsp:include page="WEB-INF/pages/static/footerLinks.jsp"></jsp:include>
</body>
</html>