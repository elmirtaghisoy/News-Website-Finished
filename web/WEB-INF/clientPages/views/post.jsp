<%@ page import="kadr25.model.Post" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 17.01.2020
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Post post = (Post) request.getAttribute("post");%>
<div class="single-post-wrap">
    <div class="content-wrap">
        <ul class="tags mt-10">
            <li>
                <a href="javascript: getPostsByCatId(<%=post.getCategorie().getId()%>)"><%=post.getCategorie().getCategoryName()%>
                </a></li>
        </ul>
        <a href="#">
            <h3><%=post.getHeading()%>
            </h3>
        </a>
        <ul class="meta pb-20">
            <li><a href="#"><i
                    class="fas fa-user-edit"></i> <%=post.getAuthor().getName()%> <%=post.getAuthor().getSurname()%>
            </a></li>
            <li><a href="javascript: getPostsByDate('<%=post.getCreated_at()%>')"><i
                    class="far fa-calendar-alt"></i> <%=post.getCreated_at()%>
            </a></li>
            <li><a href="#"><i class="far fa-eye"></i> <%=post.getViews()%>
            </a></li>
        </ul>
        <p>
            <%=post.getContext()%>
        </p>

        <div class="feature-img-thumb relative">
            <div id="postFiles"></div>
        </div>
        <div class="fb-comments" data-href="https://www.facebook.com/elmir.tagiyev.142"
             data-width=""
             data-numposts="5"></div>
        <div class="navigation-wrap justify-content-between d-flex">
            <a class="prev" href="javascript: getPostByIdForClient(<%=post.getId()%>-1)"><i
                    class="fas fa-arrow-left"></i> Əvvəlki Xəbər</a>
            <a class="next" href="javascript: getPostByIdForClient(<%=post.getId()%>+1)">Növbəti
                Xəbər <i
                        class="fas fa-arrow-right"></i></a>
        </div>
    </div>
</div>
