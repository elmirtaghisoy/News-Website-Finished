<%@ page import="kadr25.model.Post" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 18.01.2020
  Time: 6:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Post> postsList = (List<Post>) request.getAttribute("postsList");%>
<div class="latest-post-wrap">
    <h4 class="cat-title"><%=postsList.get(0).getCategorie().getCategoryName()%></h4>

    <%for (Post post : postsList) {%>
    <div class="single-latest-post row align-items-center">
        <div class="col-lg-5 post-left">
            <div class="feature-img relative">
                <div class="overlay overlay-bg"></div>
                <img class="img-fluid" src="<%=post.getFiles().getFileName()%>" alt="">
            </div>
            <ul class="tags">
                <li>
                    <a href="javascript: getPostsByCatId(<%=post.getCategorie().getId()%>)"><%=post.getCategorie().getCategoryName()%>
                    </a></li>
            </ul>
        </div>
        <div class="col-lg-7 post-right">
            <a href="javascript: getPostByIdForClient(<%=post.getId()%>)">
                <h4><%=post.getHeading()%>
                </h4>
            </a>
            <ul class="meta">
                <li><i class="fas fa-user-edit"></i> <%=post.getAuthor().getName()%> <%=post.getAuthor().getSurname()%>
                    </a></li>
                <li><a href="javascript: getPostsByDate('<%=post.getCreated_at()%>')"><i
                        class="far fa-calendar-alt"></i> <%=post.getCreated_at()%>
                </a></li>
                <li><a href="#"><i class="far fa-eye"></i> <%=post.getLikes()%>s</a></li>
            </ul>
            <p class="excert">
                <%=post.getContext()%>
            </p>
        </div>
    </div>
    <%}%>
    <div class="load-more">
        <a href="#" class="primary-btn">Load More Posts</a>
    </div>
</div>