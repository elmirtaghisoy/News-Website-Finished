<%@ page import="kadr25.model.Post" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 16.01.2020
  Time: 3:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Post> allCategories = (List<Post>) request.getAttribute("postList");%>


<%
    int i = 0;
    for (Post cat : allCategories) {
        i++;
        if (i == 1) {
%>
<div class="feature-img-wrap relative">
    <div class="feature-img relative">
        <div class="overlay overlay-bg"></div>
        <img class="img-fluid" src="<%=cat.getFiles().getFileName()%>" alt="">
    </div>
    <ul class="tags">
        <li><a href="javascript: getPostsByCatId(<%=cat.getCategorie().getId()%>)"><%=cat.getCategorie().getCategoryName()%></a></li>
    </ul>
</div>
<div class="details">
    <a href="javascript: getPostByIdForClient(<%=cat.getId()%>)">
        <h4 class="mt-20"><%=cat.getHeading()%></h4>
    </a>
    <ul class="meta">
        <li><a href="#"><i class="fas fa-user-edit"></i> <%=cat.getAuthor().getName()%> <%=cat.getAuthor().getSurname()%></a></li>
        <li><a href="javascript: getPostsByDate('<%=cat.getCreated_at()%>')"><i class="far fa-calendar-alt"></i> <%=cat.getCreated_at()%></a>
        </li>
        <li><a href="#"><i class="far fa-eye"></i> <%=cat.getLikes()%></a></li>
    </ul>
    <p class="excert">
        <%=cat.getContext()%>
    </p>
</div>
<%} else if (i == 2) {%>
<div class="post-lists">
    <div class="single-post d-flex flex-row">
        <div class="thumb">
            <img style="height: 100px;width: 150px;" src="<%=cat.getFiles().getFileName()%>" alt="">
        </div>
        <div class="detail">
            <a href="javascript: getPostByIdForClient(<%=cat.getId()%>)"><h6><%=cat.getHeading()%></h6></a>
            <ul class="meta">
                <li><a href="javascript: getPostsByDate('<%=cat.getCreated_at()%>')"><i class="far fa-calendar-alt"></i> <%=cat.getCreated_at()%></a></li>
                <li><a href="#"><i class="far fa-eye"></i> <%=cat.getLikes()%></a></li>
            </ul>
        </div>
    </div>
    <%} else if (i == 3) {%>
    <div class="single-post d-flex flex-row">
        <div class="thumb">
            <img style="height: 100px;width: 150px;" src="<%=cat.getFiles().getFileName()%>" alt="">
        </div>
        <div class="detail">
            <a href="javascript: getPostByIdForClient(<%=cat.getId()%>)"><h6><%=cat.getHeading()%></h6></a>
            <ul class="meta">
                <li><a href="javascript: getPostsByDate('<%=cat.getCreated_at()%>')"><i class="far fa-calendar-alt"></i> <%=cat.getCreated_at()%></a></li>
                <li><a href="#"><i class="far fa-eye"></i> <%=cat.getLikes()%></a></li>
            </ul>
        </div>
    </div>
    <%} else if (i == 4) {%>
    <div class="single-post d-flex flex-row">
        <div class="thumb">
            <img style="height: 100px;width: 150px;" src="<%=cat.getFiles().getFileName()%>" alt="">
        </div>
        <div class="detail">
            <a href="javascript: getPostByIdForClient(<%=cat.getId()%>)"><h6><%=cat.getHeading()%></h6></a>
            <ul class="meta">
                <li><a href="javascript: getPostsByDate('<%=cat.getCreated_at()%>')"><i class="far fa-calendar-alt"></i> <%=cat.getCreated_at()%></a></li>
                <li><a href="#"><i class="far fa-eye"></i> <%=cat.getLikes()%></a></li>
            </ul>
        </div>
    </div>
</div>
<%
        }
    }
%>