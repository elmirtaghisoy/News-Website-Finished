<%@ page import="kadr25.model.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="javafx.geometry.Pos" %><%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 15.01.2020
  Time: 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Post> slidePosts = (List<Post>) request.getAttribute("slidePosts");%>
<section class="top-post-area pt-10">
    <div class="container no-padding">
        <div class="row small-gutters">

            <div class="col-lg-8 top-post-left">
                <div class="feature-image-thumb relative">
                    <div class="overlay overlay-bg"></div>
                    <img class="img-fluid" src="<%=slidePosts.get(0).getFiles().getFileName()%>" alt="">
                </div>
                <div class="top-post-details">
                    <ul class="tags">
                        <li><a href="javascript: getPostsByCatId(<%=slidePosts.get(0).getCategorie().getId()%>)"><%=slidePosts.get(0).getCategorie().getCategoryName()%></a></li>
                    </ul>
                    <a href="javascript: getPostByIdForClient(<%=slidePosts.get(0).getId()%>)">
                        <h3><%=slidePosts.get(0).getHeading()%></h3>
                    </a>
                    <ul class="meta">
                        <li><a href="#"><i class="fas fa-user-edit"></i> <%=slidePosts.get(0).getAuthor().getName()%> <%=slidePosts.get(0).getAuthor().getSurname()%></a></li>
                        <li><a href="javascript: getPostsByDate('<%=slidePosts.get(0).getCreated_at()%>')"><i class="far fa-calendar-alt"></i> <%=slidePosts.get(0).getCreated_at()%></a></li>
                        <li><a href="#"><i class="far fa-eye"></i> <%=slidePosts.get(0).getLikes()%></a></li>
                    </ul>
                </div>
            </div>

            <div class="col-lg-4 top-post-right">
                <div class="single-top-post">
                    <div class="feature-image-thumb relative">
                        <div class="overlay overlay-bg"></div>
                        <img class="img-fluid" src="<%=slidePosts.get(1).getFiles().getFileName()%>" alt="">
                    </div>
                    <div class="top-post-details">
                        <ul class="tags">
                            <li><a href="javascript: getPostsByCatId(<%=slidePosts.get(1).getId()%>)"><%=slidePosts.get(1).getCategorie().getCategoryName()%></a></li>
                        </ul>
                        <a href="javascript: getPostByIdForClient(<%=slidePosts.get(1).getId()%>)">
                            <h4><%=slidePosts.get(1).getHeading()%></h4>
                        </a>
                        <ul class="meta">
                            <li><a href="#"><i class="fas fa-user-edit"></i> <%=slidePosts.get(1).getAuthor().getName()%></a></li>
                            <li><a href="javascript: getPostsByDate('<%=slidePosts.get(1).getCreated_at()%>')"><i class="far fa-calendar-alt"></i> <%=slidePosts.get(1).getCreated_at()%></a></li>
                            <li><a href="#"><i class="far fa-eye"></i> <%=slidePosts.get(1).getLikes()%></a></li>
                        </ul>
                    </div>
                </div>
                <div class="single-top-post mt-10">
                    <div class="feature-image-thumb relative">
                        <div class="overlay overlay-bg"></div>
                        <img class="img-fluid" src="<%=slidePosts.get(2).getFiles().getFileName()%>" alt="">
                    </div>
                    <div class="top-post-details">
                        <ul class="tags">
                            <li><a href="javascript: getPostsByCatId(<%=slidePosts.get(2).getCategorie().getId()%>)"><%=slidePosts.get(2).getCategorie().getCategoryName()%></a></li>
                        </ul>
                        <a href="javascript: getPostByIdForClient(<%=slidePosts.get(2).getId()%>)">
                            <h4><%=slidePosts.get(2).getHeading()%></h4>
                        </a>
                        <ul class="meta">
                            <li><a href="#"><i class="fas fa-user-edit"></i> <%=slidePosts.get(2).getAuthor().getName()%></a></li>
                            <li><a href="javascript: getPostsByDate('<%=slidePosts.get(2).getCreated_at()%>')"><i class="far fa-calendar-alt"></i> <%=slidePosts.get(2).getCreated_at()%></a></li>
                            <li><a href="#"><i class="far fa-eye"></i> <%=slidePosts.get(2).getLikes()%></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="news-tracker-wrap">
                    <h6><span>Breaking News:</span> <a href="#">Astronomy Binoculars A Great Alternative</a></h6>
                </div>
            </div>
        </div>
    </div>
</section>