<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 10.12.2019
  Time: 2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container-fluid">
    <!--Table Start-->
    <div class="row">
        <div class="col-6">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Kateqoriyalar</th>
                    <th scope="col" colspan="2" class="text-center">
                        <button type="button" data-toggle="modal" data-target="#addCategory" class="add"><i
                                class="far fa-plus-square"></i></button>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${allCategories}" var="ac">
                    <tr>
                        <th scope="row">${ac.categoryName}</th>
                        <td class="buttonList text-center">
                            <a href="javascript: idSelector(${ac.id})">
                                <button type="button" data-toggle="modal" data-id="1" data-target="#addToMenu"
                                        class="btnInfo">
                                    <i class="far fa-plus-square"></i>
                                </button>
                            </a>
                            <a href="javascript: idSelector(${ac.id})">
                                <button type="button" data-toggle="modal" data-id="1" data-target="#deleteCategory"
                                        class="btnDelete">
                                    <i class="far fa-trash-alt"></i>
                                </button>
                            </a>
                            <a href="javascript: getCategoriesByParentId(${ac.id})">
                                <button type="button" data-toggle="modal" data-id="1" data-target="#updateCategory"
                                        class="btnEdit">
                                    <i class="far fa-edit"></i>
                                </button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col-6">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th colspan="2" scope="col">Menu</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${mainCategories}" var="mc">
                    <tr>
                        <th scope="row">${mc.categoryName}</th>
                        <td class="buttonList text-center">
                            <a href="javascript: getCategoriesByParentId(${mc.id})">
                                <button type="button" data-toggle="modal" data-id="1" data-target="#updateCategory"
                                        class="btnEdit">
                                    <i class="far fa-edit"></i>
                                </button>
                            </a>
                            <a href="javascript: idSelector(${mc.id})">
                            <button type="button" data-toggle="modal" data-id="1" data-target="#deleteFromMainMenu"
                                    class="btnDelete"><i
                                    class="far fa-trash-alt"></i></button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div id="modals"></div>
    <!--Table End-->
    <!--Add New Category Start-->
    <div class="modal fade" id="addCategory" tabindex="-1" role="dialog" aria-labelledby="addModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addModalLabel">Yeni kateqoriya elave et</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="addPersonFrom">
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <input type="text" class="form-control" id="addCategoryName" name="newCatName"
                                       placeholder="Yeni Kateqoriya">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <a href="javascript: addCategorie()" class="btnOk">Əlavə et</a>
                    <button type="button" class="btnNot" data-dismiss="modal">Ləğv et</button>
                </div>
            </div>
        </div>
    </div>
    <!--Add New Category End-->

    <!--Add To Menu Start-->
    <div class="modal fade" id="addToMenu" tabindex="-1" role="dialog" aria-labelledby="editModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" data-name="editModalLabel" id="#addToMenu"></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group col-md-12">
                            <h6 style="text-align: center">Kateqoriyası əsas menuya əlavə edilsin ?</h6>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <a href="javascript: addCatToMainMenu()" class="btnOk">Bəli</a>
                    <button type="button" class="btnNot" data-dismiss="modal">Xeyir</button>
                </div>
            </div>
        </div>
    </div>
    <!--Add To Menu End-->

    <!--Delete From Menu Start-->
    <div class="modal fade" id="deleteFromMainMenu" tabindex="-1" role="dialog" aria-labelledby="deleteFromMainMenuModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" data-name="deleteFromMainMenuModalLabel" id="#deleteFromMainMenu"></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group col-md-12">
                            <h6 style="text-align: center">Kateqoriyası əsas menuya əlavə edilsin ?</h6>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <a href="javascript: deleteCatFromMainMenu()" class="btnOk">Bəli</a>
                    <button type="button" class="btnNot" data-dismiss="modal">Xeyir</button>
                </div>
            </div>
        </div>
    </div>
    <!--Delete From Menu Start-->

    <!--Update Category Start-->
    <div class="modal fade" id="updateCategory" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="updateModalLabel">Yeni istifadəçi</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div id="updateCatModal">

                </div>
                <div class="modal-footer">
                    <a href="javascript: updateCategory()" class="btnOk">Yadda Saxla</a>
                    <button type="button" class="btnNot" data-dismiss="modal">Ləğv et</button>
                </div>

            </div>
        </div>
    </div>
    <!--Update Category End-->

    <!--Update Menu Start-->
    <div class="modal fade" id="updateToMenu" tabindex="-1" role="dialog" aria-labelledby="editModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" data-name="editModalLabel" id="editModalLabel"></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="changePersonInfo">
                        <div class="form-group col-md-12">
                            <h6 style="text-align: center">Alt Kateqoriya</h6>
                            <ul style="list-style: none;padding-left: .3rem;">
                                <li>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="inlineCheckbox01"
                                               value="option1">
                                        <label class="form-check-label" for="inlineCheckbox1">İdman</label>
                                    </div>
                                </li>
                                <li>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="inlineCheckbox02"
                                               value="option2">
                                        <label class="form-check-label" for="inlineCheckbox2">Dünya</label>
                                    </div>
                                </li>
                            </ul>
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
    <!--Update Menu End-->

    <!--Delete Category Start-->
    <div class="modal fade" id="deleteCategory" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel"
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
                        <h6 style="text-align: center">Kateqoriya silinsin ?</h6>
                    </div>
                </div>
                <div class="modal-footer">
                    <a href="javascript: deleteCategory()" class="btnOk">Beli</a>
                    <button type="button" class="btnNot" data-dismiss="modal">Xeyir</button>
                </div>
            </div>
        </div>
    </div>
    <!--Delete Category End-->

    <!--Add New SubCategory Start-->
    <div class="modal fade" id="addSubCategory" tabindex="-1" role="dialog" aria-labelledby="addSubModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addSubModalLabel">Yeni kateqoriya elave et</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div id="unusedcats">

                </div>
                <div class="modal-footer">
                    <a href="javascript: addSubCategorie()" class="btnOk">Sec</a>
                    <button type="button" class="btnNot" data-dismiss="modal">Ləğv et</button>
                </div>
            </div>
        </div>
    </div>
    <!--Add New SubCategory End-->
</div>