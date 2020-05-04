<%--
  Created by IntelliJ IDEA.
  User: Elmir Taghyev
  Date: 26.11.2019
  Time: 2:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addModalLabel">Yeni Müəllif</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="addAuthorFrom">
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <input type="text" class="form-control" id="authorName" placeholder="Ad">
                        </div>
                        <div class="form-group col-md-12">
                            <input type="text" class="form-control" id="authorSurname" placeholder="Soyad">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <input type="text" class="form-control" id="authorProfession" placeholder="İş">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <input type="text" class="form-control" id="authorEmail"  placeholder="Email">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <input type="text" class="form-control" id="authorPhone" placeholder="Mobil nömrə">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <a href="javascript: addAuthor();" id="addAuthorBtnId" class="btnOk">Əlavə et</a>
                <button type="button" class="btnNot" data-dismiss="modal">Ləğv et</button>
            </div>
        </div>
    </div>
</div>