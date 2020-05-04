<%@ page import="javafx.geometry.Pos" %>
<%@ page import="kadr25.model.Post" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 15.01.2020
  Time: 7:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="latest-post-wrap">
    <h4 class="cat-title">Son Xəbərlər</h4>
    <% List<Post> lastPosts = (List<Post>) request.getAttribute("lastPosts");
        int i = 0;
        for (Post lp : lastPosts) {
            i++;
            if (i <= 3)
                continue;
            else if (i == 8) {
                break;
            } else {
    %>
    <div class="single-latest-post row align-items-center">
        <div class="col-lg-5 post-left">
            <div class="feature-img relative">
                <div class="overlay overlay-bg"></div>
                <img class="img-fluid" src="<%=lp.getFiles().getFileName()%>" alt="">
            </div>
            <ul class="tags">
                <li>
                    <a href="javascript: getPostsByCatId(<%=lp.getCategorie().getId()%>)"><%=lp.getCategorie().getCategoryName()%>
                    </a></li>
            </ul>
        </div>
        <div class="col-lg-7 post-right">
            <a href="javascript: getPostByIdForClient(<%=lp.getId()%>)">
                <h4><%=lp.getHeading()%>
                </h4>
            </a>
            <ul class="meta">
                <li><a href="#"><i
                        class="fas fa-user-edit"></i> <%=lp.getAuthor().getName()%> <%=lp.getAuthor().getSurname()%>
                </a></li>
                <li><a href="javascript: getPostsByDate('<%=lp.getCreated_at()%>')"><i
                        class="far fa-calendar-alt"></i> <%=lp.getCreated_at()%>
                </a></li>
                <li><a href="#"><i class="far fa-eye"></i> <%=lp.getLikes()%>
                </a></li>
            </ul>
            <p class="excert">
                <%=lp.getContext()%>
            </p>
        </div>
    </div>
    <%
            }
        }
    %>
</div>