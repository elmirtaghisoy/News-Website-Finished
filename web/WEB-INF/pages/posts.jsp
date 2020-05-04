<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elmir Taghyev
  Date: 21.11.2019
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    $(document).ready(function () {
        CKEDITOR.replace('editor1');
        CKEDITOR.replace('editor2');
        CKEDITOR.replace('editor3');
        CKEDITOR.replace('editor4');
    })
</script>
<div class="container-fluid noPadding">

<%--    <!--Page Button Start-->--%>
<%--    <div class="buttonsBox">--%>
<%--        <a href="javascript: getDataForSearch();">--%>
<%--            <button class="btnInfo" type="button" data-toggle="collapse"--%>
<%--                    data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">--%>
<%--                Axtarış <i style="margin-left: 5px;position: relative;top: 1px;" class="fas fa-caret-square-down"></i>--%>
<%--            </button>--%>
<%--        </a>--%>
<%--        <a href="javascript: getDataForNewPost();">--%>
<%--            <button type="button" data-toggle="modal" data-target="#addNewPost" class="btnInfo">--%>
<%--                Əlavə et <i style="margin-left: 5px;position: relative;top: 1px;" class="far fa-plus-square"></i>--%>
<%--            </button>--%>
<%--        </a>--%>
<%--    </div>--%>
<%--    <!--Page Button End-->--%>

<%--    <!--Search Box Start-->--%>
<%--    <div style="margin-top: 1rem;" class="collapse" id="collapseExample">--%>
<%--            <div id="searchBox" style="box-shadow: 0 0 5px 0px #6191ff;border-radius: 0 !important;" class="card card-body">--%>

<%--            </div>--%>
<%--        </div>--%>
<%--    <!--Search Box End-->--%>

    <!--Content Start-->
    <div class="card-group">
        <c:forEach var="ap" items="${allPosts}">
            <div class="card myCard">
                <img src="${ap.files.fileName}" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${ap.heading}</h5>
                    <p class="card-text">${ap.context}</p>
                </div>
                <div class="card-footer">
                    <div class="card-buttons">
                        <a href="javascript: idSelector(${ap.id})">
                            <button type="button" data-toggle="modal" data-id="1" data-target="#deletePostModal"
                                    class="btnDelete">
                                <i class="far fa-trash-alt"></i>
                            </button>
                        </a>
                        <a class="btnEdit"
                           href="<c:url value="pc?action=getPostById"><c:param name="id" value="${ap.id}"></c:param></c:url>">
                            <i class="far fa-edit"></i>
                        </a>
                    </div>
                    <small style="margin-left: 18%" class="text-muted">${ap.created_at}</small>
                </div>
            </div>
        </c:forEach>
    </div>
    <!--Content End-->

    <!--Add New News Start-->
    <div class="modal fade bd-example-modal-xl" id="addNewPost" tabindex="-1" role="dialog"
         aria-labelledby="addModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-xl" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Yeni Paylashim</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="uc" method="post" enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="editor1">Başlıq</label>
                                </div>
                                <textarea type="text" name="editor1" class="form-control" id="editor1"
                                          value=""></textarea>
                            </div>
                            <div class="form-group col-md-12">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="editor2">Mətn</label>
                                </div>
                                <textarea type="text" name="editor2" class="form-control" id="editor2"
                                          value=""></textarea>
                            </div>
                        </div>
                        <div id="addPostModal">

                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="submit" id="addPost" class="btnOk" value="Əlavə et">
                        <button type="button" class="btnNot" data-dismiss="modal">Ləğv et</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!--Add New News End-->

    <!--Delete News Start-->
    <div class="modal fade" id="deletePostModal" tabindex="-1" role="dialog" aria-labelledby="deletePostModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <h5 class="text-center" id="delName">Paylashim Silinsin ?</h5>
                    <div style="margin-top: 1em" id="#ads" class="text-center">
                        <form id="deleteQuerry">
                            <a href="javascript: deletePost()">
                                <button type="button" class="btnDelPerson">Beli</button>
                            </a>
                            <button type="button" class="btnNot" data-dismiss="modal">Xeyr</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--Delete News End-->

    <!--Update News Start-->
    <div class="modal fade bd-example-modal-xl" id="editModal" tabindex="-1" role="dialog"
         aria-labelledby="editModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-xl" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" data-name="editModalLabel" id="editModalLabel"></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="changePersonInfo">
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="header">Başlıq</label>
                                </div>
                                <textarea type="text" name="editor3" class="form-control" id="header"
                                          value=""></textarea>
                            </div>
                            <div class="form-group col-md-12">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="context">Mətn</label>
                                </div>
                                <textarea type="text" name="editor4" class="form-control" id="context"
                                          value=""></textarea>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputGroupSelect1">İstifadəçi</label>
                                </div>
                                <select class="custom-select" id="inputGroupSelect1">
                                    <option selected>Choose...</option>
                                    <option value="1">One</option>
                                    <option value="2">Two</option>
                                    <option value="3">Three</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <div class="input-group-prepend">
                                    <label class="input-group-text" for="inputGroupSelect2">Kateqoriya</label>
                                </div>
                                <select class="custom-select" id="inputGroupSelect2">
                                    <option selected>Choose...</option>
                                    <option value="1">One</option>
                                    <option value="2">Two</option>
                                    <option value="3">Three</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <label class="input-group-text" id="inputGroupFileAddon01">Əsas Şəkil-1</label>
                                    </div>
                                    <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="inputGroupFile01"
                                               aria-describedby="inputGroupFileAddon01">
                                        <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-4">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <label class="input-group-text" id="inputGroupFileAddon02">Əsas Şəkil-2</label>
                                    </div>
                                    <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="inputGroupFile02"
                                               aria-describedby="inputGroupFileAddon01">
                                        <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-4">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <label class="input-group-text" id="inputGroupFileAddon03">Əsas Şəkil-3</label>
                                    </div>
                                    <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="inputGroupFile03"
                                               aria-describedby="inputGroupFileAddon01">
                                        <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btnOk">Yadda saxla</button>
                    <button type="button" class="btnNot" data-dismiss="modal">Ləğv et</button>
                </div>
            </div>
        </div>
    </div>
    <!--Update News End-->

</div>
<!--Content End-->
