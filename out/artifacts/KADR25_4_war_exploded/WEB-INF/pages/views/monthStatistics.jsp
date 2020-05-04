<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 07.12.2019
  Time: 2:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Başlıq</th>
            <th scope="col">Like</th>
            <th scope="col">Baxış</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${statistics}" var="st">
        <tr>
            <th scope="row">${st.post.id}</th>
            <td><a>${st.post.heading}</a></td>
            <td>${st.post.likes}</td>
            <td>${st.post.views}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>

<%--    <table class="table table-hover">--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th scope="col">Paylaşım</th>--%>
<%--            <th scope="col">Like</th>--%>
<%--            <th scope="col">Baxış</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <tr>--%>
<%--            <th scope="row">3</th>--%>
<%--            <td>11</td>--%>
<%--            <td>50</td>--%>
<%--        </tr>--%>
<%--        </tbody>--%>
<%--    </table>--%>