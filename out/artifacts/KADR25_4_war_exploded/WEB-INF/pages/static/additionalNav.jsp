<%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 10.01.2020
  Time: 1:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container-fluid noPadding">
    <!--Page Button Start-->
    <div class="buttonsBox">
        <a href="javascript: getDataForSearch();">
            <button class="btnInfo" type="button" data-toggle="collapse"
                    data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                Axtarış <i style="margin-left: 5px;position: relative;top: 1px;" class="fas fa-caret-square-down"></i>
            </button>
        </a>
        <a href="javascript: getDataForNewPost();">
            <button type="button" data-toggle="modal" data-target="#addNewPost" class="btnInfo">
                Əlavə et <i style="margin-left: 5px;position: relative;top: 1px;" class="far fa-plus-square"></i>
            </button>
        </a>
    </div>
    <!--Page Button End-->

    <!--Search Box Start-->
    <div style="margin-top: 1rem;" class="collapse" id="collapseExample">
        <div id="searchBox" style="box-shadow: 0 0 5px 0px #6191ff;border-radius: 0 !important;" class="card card-body">

        </div>
    </div>
    <!--Search Box End-->
</div>
