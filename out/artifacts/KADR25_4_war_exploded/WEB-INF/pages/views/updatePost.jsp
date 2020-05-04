<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="kadr25.model.Post" %>
<%@ page import="kadr25.model.Categorie" %>
<%@ page import="java.util.List" %>
<%@ page import="kadr25.model.Author" %>
<%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 05.01.2020
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Post post = (Post) request.getAttribute("post");
    List<Categorie> categorieList = (List<Categorie>) request.getAttribute("categories");
    List<Author> authorList = (List<Author>) request.getAttribute("authorList");
%>
<html>
<head>
    <title>Kadr25</title>

    <jsp:include page="/WEB-INF/pages/static/headerLinks.jsp"></jsp:include>

    <script>
        $(document).ready(function () {
            CKEDITOR.replace('editor1');
            CKEDITOR.replace('editor2');
            CKEDITOR.replace('editor3');
            CKEDITOR.replace('editor4');
        })
    </script>

</head>
<body>
<jsp:include page="/WEB-INF/pages/static/navbar.jsp"></jsp:include>
<form action="uc" method="post" enctype="multipart/form-data">
    <div class="container-fluid">
        <input type="hidden" name="update">
        <input type="hidden" name="postId" value="<%=post.getId()%>">
        <div class="form-row">
            <div class="form-group col-md-6">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="editor1">Başlıq</label>
                </div>
                <textarea type="text" name="editor1" class="form-control" id="editor1"
                          value=""><%=post.getHeading()%></textarea>
            </div>
            <div class="form-group col-md-6">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="editor2">Mətn</label>
                </div>
                <textarea type="text" name="editor2" class="form-control" id="editor2"
                          value=""><%=post.getContext()%></textarea>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="form-row">
            <div class="form-group col-md-6">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="addUser">İstifadəçi</label>
                </div>
                <select name="userName" class="custom-select" id="addUser">
                    <option name="" value="<%=post.getAuthor().getId()%>">
                        <%=post.getAuthor().getName()%> <%=post.getAuthor().getSurname()%>
                    </option>
                    <% for (Author author : authorList) {%>
                    <%
                        if (post.getAuthor().getId().equals(author.getId()))
                            continue;
                    %>
                    <option name="" value="<%=author.getId()%>">
                        <%=author.getName()%> <%=author.getSurname()%>
                    </option>
                    <%}%>
                </select>
            </div>
            <div class="form-group col-md-6">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="addCategory">Kateqoriya</label>
                </div>
                <select name="catName" class="custom-select" id="addCategory">
                    <option name="" value="<%=post.getCategorie().getId()%>"><%=post.getCategorie().getCategoryName()%>
                    </option>
                    <% for (Categorie categorie : categorieList) {%>
                    <%
                        if (post.getCategorie().getId().equals(categorie.getId()))
                            continue;
                    %>
                    <option name="" value="<%=categorie.getId()%>">
                        <%=categorie.getCategoryName()%>
                    </option>
                    <%}%>
                </select>
            </div>
        </div>


        <div class="accordion" id="accordionExample">
            <div class="card">
                <div class="card-header" id="headingOne">
                    <h2 class="mb-0">
                        <a href="javascript: getPostFilesById(<%=post.getId()%>)">
                            <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne"
                                    aria-expanded="true" aria-controls="collapseOne">
                                Fayllar
                            </button>
                        </a>
                    </h2>
                </div>

                <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
                    <div class="card-body">
                        <div id="postFiles">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div style="margin-top: 1rem" class="form-row">
            <div class="form-group col-md-12">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <label class="input-group-text" id="addPicLable1">Fayl elave et</label>
                    </div>
                    <div class="custom-file">
                        <input type="file" multiple=" " name="fileName[]" class="custom-file-input" id="addPic1"
                               aria-describedby="addPic1">
                        <label class="custom-file-label" for="addPic1">Choose file</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <input type="submit" id="addPost" class="btnOk" value="Əlavə et">
        </div>
    </div>
</form>
<!--Delete File Start-->
<div class="modal fade" id="deleteFileModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" data-name="deleteModalLabel" id="#deleteCategory1"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-group col-md-12">
                    <h6 style="text-align: center">Fayl silinsin ?</h6>
                </div>
            </div>
            <div class="modal-footer">
                <a href="javascript: deletePostFile()" class="btnOk">Beli</a>
                <button type="button" class="btnNot" data-dismiss="modal">Xeyir</button>
            </div>
        </div>
    </div>
</div>
<!--Delete File End-->

<!--SelectCover File Start-->
<div class="modal fade" id="selectCoverModal" tabindex="-1" role="dialog" aria-labelledby="selectCoverModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" data-name="selectCoverModalLabel"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="form-group col-md-12">
                    <h6 style="text-align: center">Postun coveri edilsin ?</h6>
                </div>
            </div>
            <div class="modal-footer">
                <a href="javascript: selectPostCover()" class="btnOk">Beli</a>
                <button type="button" class="btnNot" data-dismiss="modal">Xeyir</button>
            </div>
        </div>
    </div>
</div>
<!--SelectCover File End-->

<jsp:include page="/WEB-INF/pages/static/footer.jsp"></jsp:include>

<jsp:include page="/WEB-INF/pages/static/footerLinks.jsp"></jsp:include>
</body>
</html>
