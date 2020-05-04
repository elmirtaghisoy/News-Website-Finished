<%@ page import="kadr25.model.Post" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 15.01.2020
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Post> mostViewedPosts = (List<Post>) request.getAttribute("mostViewedPosts");%>
<div class="popular-post-wrap">
    <h4 class="title">Ən çox oxunan</h4>
<div class="feature-post relative">
    <div class="feature-img relative">
        <div class="overlay overlay-bg"></div>
        <img class="img-fluid" src="<%=mostViewedPosts.get(0).getFiles().getFileName()%>" alt="">
    </div>
    <div class="details">
        <ul class="tags">
            <li><a href="javascript: getPostsByCatId(<%=mostViewedPosts.get(0).getCategorie().getId()%>)"><%=mostViewedPosts.get(0).getCategorie().getCategoryName()%>
            </a></li>
        </ul>
        <a href="javascript: getPostByIdForClient(<%=mostViewedPosts.get(0).getId()%>)">
            <h3><%=mostViewedPosts.get(0).getHeading()%>
            </h3>
        </a>
        <ul class="meta">
            <li><a href="#"><i class="fas fa-user-edit"></i> <%=mostViewedPosts.get(0).getAuthor().getName()%> <%=mostViewedPosts.get(0).getAuthor().getSurname()%>
            </a></li>
            <li><a href="javascript: getPostsByDate('<%=mostViewedPosts.get(0).getCreated_at()%>')"><i class="far fa-calendar-alt"></i> <%=mostViewedPosts.get(0).getCreated_at()%>
            </a></li>
            <li><a href="#"><i class="far fa-eye"></i> <%=mostViewedPosts.get(0).getLikes()%>
            </a></li>
        </ul>
    </div>
</div>
<%
    int i = 0;
    for (Post post : mostViewedPosts) {
        i++;
        if (i == 1) {
            continue;
        } else if (i == 2) {%>
<div class="row mt-20 medium-gutters">
    <div class="col-lg-6 single-popular-post">
        <div class="feature-img-wrap relative">
            <div class="feature-img relative">
                <div class="overlay overlay-bg"></div>
                <img class="img-fluid" src="<%=post.getFiles().getFileName()%>" alt="">
            </div>
            <ul class="tags">
                <li><a href="javascript: getPostsByCatId(<%=post.getCategorie().getId()%>)"><%=post.getCategorie().getCategoryName()%>
                </a></li>
            </ul>
        </div>
        <div class="details">
            <a href="javascript: getPostByIdForClient(<%=post.getId()%>)">
                <h4><%=post.getHeading()%>
                </h4>
            </a>
            <ul class="meta">
                <li><i class="fas fa-user-edit"></i> <%=post.getAuthor().getName()%> <%=post.getAuthor().getSurname()%>
                </a></li>
                <li><a href="javascript: getPostsByDate('<%=post.getCreated_at()%>')"><i class="far fa-calendar-alt"></i> <%=post.getCreated_at()%>
                </a>
                </li>
                <li><a href="#"><i class="far fa-eye"></i> <%=post.getLikes()%>
                </a></li>
            </ul>
            <p class="excert">
                <%=post.getContext()%>
            </p>
        </div>
    </div>
    <%} else if (i == 3) {%>
    <div class="col-lg-6 single-popular-post">
        <div class="feature-img-wrap relative">
            <div class="feature-img relative">
                <div class="overlay overlay-bg"></div>
                <img class="img-fluid" src="<%=post.getFiles().getFileName()%>" alt="">
            </div>
            <ul class="tags">
                <li><a href="javascript: getPostsByCatId(<%=post.getCategorie().getId()%>)"><%=post.getCategorie().getCategoryName()%>
                </a></li>
            </ul>
        </div>
        <div class="details">
            <a href="javascript: getPostByIdForClient(<%=post.getId()%>)">
                <h4><%=post.getHeading()%>
                </h4>
            </a>
            <ul class="meta">
                <li><i class="fas fa-user-edit"></i> <%=post.getAuthor().getName()%> <%=post.getAuthor().getSurname()%>
                </a></li>
                <li><a href="javascript: getPostsByDate('<%=post.getCreated_at()%>')"><i class="far fa-calendar-alt"></i> <%=post.getCreated_at()%>
                </a>
                </li>
                <li><a href="#"><i class="far fa-eye"></i> <%=post.getLikes()%>
                </a></li>
            </ul>
            <p class="excert">
                <%=post.getContext()%>
            </p>
        </div>
    </div>
</div>
<div class="row mt-20 medium-gutters">
    <%} else if (i == 4) {%>
    <div class="col-lg-6 single-popular-post">
        <div class="feature-img-wrap relative">
            <div class="feature-img relative">
                <div class="overlay overlay-bg"></div>
                <img class="img-fluid" src="<%=post.getFiles().getFileName()%>" alt="">
            </div>
            <ul class="tags">
                <li><a href="#"><%=post.getCategorie().getCategoryName()%>
                </a></li>
            </ul>
        </div>
        <div class="details">
            <a href="javascript: getPostByIdForClient(<%=post.getId()%>)">
                <h4><%=post.getHeading()%>
                </h4>
            </a>
            <ul class="meta">
                <li><i class="fas fa-user-edit"></i> </span><%=post.getAuthor().getName()%> <%=post.getAuthor().getSurname()%>
                </a></li>
                <li><a href="javascript: getPostsByDate('<%=post.getCreated_at()%>')"><i class="far fa-calendar-alt"></i> <%=post.getCreated_at()%>
                </a>
                </li>
                <li><a href="#"><i class="far fa-eye"></i> <%=post.getLikes()%>
                </a></li>
            </ul>
            <p class="excert">
                <%=post.getContext()%>
            </p>
        </div>
    </div>
    <%} else if (i == 5) {%>
    <div class="col-lg-6 single-popular-post">
        <div class="feature-img-wrap relative">
            <div class="feature-img relative">
                <div class="overlay overlay-bg"></div>
                <img class="img-fluid" src="<%=post.getFiles().getFileName()%>" alt="">
            </div>
            <ul class="tags">
                <li><a href="#"><%=post.getCategorie().getCategoryName()%>
                </a></li>
            </ul>
        </div>
        <div class="details">
            <a href="javascript: getPostByIdForClient(<%=post.getId()%>)">
                <h4><%=post.getHeading()%>
                </h4>
            </a>
            <ul class="meta">
                <li><i class="fas fa-user-edit"></i> <%=post.getAuthor().getName()%> <%=post.getAuthor().getSurname()%>
                </a></li>
                <li><a href="javascript: getPostsByDate('<%=post.getCreated_at()%>')"><i class="far fa-calendar-alt"></i> <%=post.getCreated_at()%>
                </a>
                </li>
                <li><a href="#"><i class="far fa-eye"></i> <%=post.getLikes()%>
                </a></li>
            </ul>
            <p class="excert">
                <%=post.getContext()%>
            </p>
        </div>
    </div>
</div>
<%
        }
    }
%>
</div>

