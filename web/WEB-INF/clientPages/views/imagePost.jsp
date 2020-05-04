<%@ page import="java.util.List" %>
<%@ page import="kadr25.model.MyFiles" %><%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 17.01.2020
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% List<MyFiles> files = (List<MyFiles>) request.getAttribute("files");
    for (MyFiles file : files) {
        if (file.getFileName().endsWith(".mp4")) {
%>
<div class="embed-responsive embed-responsive-16by9">
    <video controls="true" class="embed-responsive-item">
        <source src="<%=file.getFileName()%>" type="video/mp4" />
    </video>
</div>
<%
        }
    }
%>
<div class="active-gallery-carusel">
    <%
        for (MyFiles mf : files) {
            if (mf.getFileName().endsWith(".png") || mf.getFileName().endsWith(".jpeg")
                    || mf.getFileName().endsWith(".jpg") || mf.getFileName().endsWith(".img")) {
    %>
    <div class="single-img relative">
        <div class="overlay overlay-bg"></div>
        <img class="img-fluid item" src="<%=mf.getFileName()%>" alt="">
    </div>
    <%
            }
        }
    %>
</div>