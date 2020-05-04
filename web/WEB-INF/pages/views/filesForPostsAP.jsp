<%@ page import="java.util.List" %>
<%@ page import="kadr25.model.MyFiles" %><%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 06.01.2020
  Time: 3:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<MyFiles> files = (List<MyFiles>) request.getAttribute("file");
%>
<div class="row">
    <%
        for (MyFiles file : files) {
    %>
    <div class="col-3">
        <div class="overlayContainer">
            <img height="200" width="200" src="<%=file.getFileName()%>" alt="..."
                 class="img-thumbnail overlayImage">
            <div class="myOverlay">
                <div class="overlayText">
                    <p><%=file.getId()%></p>
                    <a href="javascript: fileIdSelector(<%=file.getId()%>)">
                        <button type="button" data-toggle="modal" data-id="1"
                                data-target="#selectCoverModal"
                                class="btnOk">
                            <i class="far fa-edit"></i>
                        </button>
                    </a>
                    <a href="javascript: fileIdSelector(<%=file.getId()%>)">
                        <button type="button" data-toggle="modal" data-id="1"
                                data-target="#deleteFileModal"
                                class="btnDelete">
                            <i class="far fa-trash-alt"></i>
                        </button>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <%
        }
    %>
</div>