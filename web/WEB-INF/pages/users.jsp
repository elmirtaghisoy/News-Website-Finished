<%@ page import="java.util.List" %>
<%@ page import="kadr25.model.Author" %>
<%@ page import="kadr25.model.Statistics" %><%--
  Created by IntelliJ IDEA.
  User: Elmir Taghyev
  Date: 21.11.2019
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Statistics> authorsStatisticsList = (List<Statistics>) request.getAttribute("authorsStatisticsList");%>

<script>
    new Chart(document.getElementById("bar-chart-grouped"), {
        type: 'bar',
        data: {
            labels:['Yanvar', 'Fevral', 'Mart', 'Aprel', 'May', 'Iyun', 'Iyul', 'Avqust', 'Sentyabr','Oktyabr', 'Noyabr', 'Dekabr'],
            datasets: [
                {
                    label: "Elmir Taghisoy",
                    backgroundColor: "rgba(255, 99, 132, 0.6)",
                    data: [13,430,234]
                },
                {
                    label: "Ferhad Musayev",
                    backgroundColor: "rgba(255, 206, 86, 0.6)",
                    data: [21,200,123]
                }
            ]
        },
        options: {
            title: {
                display: true,
                text: 'Istifadecilerin Statistikasi'
            }
        }
    });
</script>

<div class="container-fluid">
    <!--Table Start-->
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Ad</th>
            <th scope="col">Soyad</th>
            <th scope="col">İxtisas</th>
            <th scope="col">Email</th>
            <th scope="col">Əlaqə Nömrəsi</th>
            <th scope="col">Paylaşım</th>
            <th scope="col">Baxış</th>
            <th scope="col">Like</th>
            <th scope="col" colspan="2" class="text-center">
                <button type="button" data-toggle="modal" data-target="#addModal" class="add"><i
                        class="far fa-plus-square"></i></button>
            </th>
        </tr>
        </thead>
        <tbody>
        <% for (Statistics statistics : authorsStatisticsList) {%>
        <tr>
            <th scope="row"><%=statistics.getAuthor().getId()%>
            </th>
            <td><%=statistics.getAuthor().getName()%>
            </td>
            <td><%=statistics.getAuthor().getSurname()%>
            </td>
            <td><%=statistics.getAuthor().getProfession()%>
            </td>
            <td><%=statistics.getAuthor().getEmail()%>
            </td>
            <td><%=statistics.getAuthor().getPhone()%>
            </td>
            <td><%=statistics.getPostCounts()%>
            </td>
            <td><%=statistics.getPost().getViews()%>
            </td>
            <td><%=statistics.getPost().getLikes()%>
            </td>
            <td class="buttonList text-center">
                <a href="javascript: getAuthorById('<%=statistics.getAuthor().getId()%>','del')">
                    <button type="button" data-toggle="modal" data-id="1" data-target="#deleteModal" class="btnDelete">
                        <i class="far fa-trash-alt"></i>
                    </button>
                </a>
                <a href="javascript: getAuthorById('<%=statistics.getAuthor().getId()%>','upd')">
                    <button type="button" data-toggle="modal" data-id="1" data-target="#editModal" class="btnEdit"><i
                            class="far fa-edit"></i>
                    </button>
                </a>
                <a href="javascript: idSelector('<%=statistics.getAuthor().getId()%>')">
                    <button type="button" data-toggle="modal" data-id="1" data-target="#infoModal" class="btnInfo"><i
                            class="far fa-file-alt"></i></button>
                </a>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
    <!--Table End-->

    <!--Add New AuthorDao Start-->
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
                                <input type="text" class="form-control" id="authorEmail" placeholder="Email">
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
    <!--Add New AuthorDao End-->
    <!--Delete AuthorDao Start-->
    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div id="delModalSec"></div>
                </div>
                <div class="modal-footer">
                    <a href="javascript: deleteAuthor();" class="btnDelPerson">Silinsin</a>
                    <button type="button" class="btnNot" data-dismiss="modal">Xeyr</button>
                </div>
            </div>
        </div>
    </div>
    <!--Delete AuthorDao End-->
    <!--Update AuthorDao Start-->
    <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" data-name="editModalLabel" id="editModalLabel"></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div id="updModalSec">

                </div>
                <div class="modal-footer">
                    <a href="javascript: updateAuthor();" class="btnOk">Yadda saxla</a>
                    <button type="button" class="btnNot" data-dismiss="modal">Ləğv et</button>
                </div>
            </div>
        </div>
    </div>
    <!--Update AuthorDao End-->
    <!--Info AuthorDao Start-->
    <div class="modal fade bd-example-modal-xl" id="infoModal" tabindex="-1" role="dialog"
         aria-labelledby="infoModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-xl" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" data-name="editModalLabel" id="infoModalLabel">İstifadəçinin aylıq
                        statistikası</h5>
                    <button type="button" data-dismiss="modal" aria-label="Close"
                            class="btn btn-link collapsed close" data-toggle="collapse"
                            data-target="#collapse01" aria-expanded="true" aria-controls="collapse">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="accordion" id="accordionExample">
                        <div class="card">
                            <div class="card-header" id="heading01">
                                <h2 class="mb-0">
                                    <a href="javascript: getStatisticsByMonth('1')">
                                        <button class="btn btn-link collapsed" type="button" data-toggle="collapse"
                                                data-target="#collapse01" aria-expanded="true"
                                                aria-controls="collapse01">
                                            Aylıq Statistika
                                        </button>
                                    </a>
                                </h2>
                            </div>
                            <div id="collapse01" class="collapse" aria-labelledby="heading01"
                                 data-parent="#accordionExample">
                                <div style="padding: 0; padding-left: 0.3rem;background: #79c800;" class="card-body">
                                    <div class="accordion" id="accordionExample1">
                                        <div class="card">
                                            <div class="card-header" id="heading1">
                                                <h2 class="mb-0">
                                                    <a href="javascript: getStatisticsByMonth('1')">
                                                        <button class="btn btn-link collapsed" type="button"
                                                                data-toggle="collapse"
                                                                data-target="#collapse1" aria-expanded="true"
                                                                aria-controls="collapse1">
                                                            Yanvar
                                                        </button>
                                                    </a>
                                                </h2>
                                            </div>
                                            <div id="collapse1" class="collapse" aria-labelledby="heading1"
                                                 data-parent="#accordionExample1">
                                                <div class="card-body">
                                                    <div id="1Stat"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card">
                                            <div class="card-header" id="heading2">
                                                <h2 class="mb-0">
                                                    <a href="javascript: getStatisticsByMonth('2')">
                                                        <button class="btn btn-link collapsed" type="button"
                                                                data-toggle="collapse"
                                                                data-target="#collapse2" aria-expanded="false"
                                                                aria-controls="collapse2">
                                                            Fevral
                                                        </button>
                                                    </a>
                                                </h2>
                                            </div>
                                            <div id="collapse2" class="collapse" aria-labelledby="heading2"
                                                 data-parent="#accordionExample1">
                                                <div class="card-body">
                                                    <div id="2Stat"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card">
                                            <div class="card-header" id="heading3">
                                                <h2 class="mb-0">
                                                    <a href="javascript: getStatisticsByMonth('3')">
                                                        <button class="btn btn-link collapsed" type="button"
                                                                data-toggle="collapse"
                                                                data-target="#collapse3" aria-expanded="false"
                                                                aria-controls="collapse3">
                                                            Mart
                                                        </button>
                                                    </a>
                                                </h2>
                                            </div>
                                            <div id="collapse3" class="collapse" aria-labelledby="heading3"
                                                 data-parent="#accordionExample1">
                                                <div class="card-body">
                                                    <div id="3Stat"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card">
                                            <div class="card-header" id="heading4">
                                                <h2 class="mb-0">
                                                    <a href="javascript: getStatisticsByMonth('4')">
                                                        <button class="btn btn-link collapsed" type="button"
                                                                data-toggle="collapse"
                                                                data-target="#collapse4" aria-expanded="false"
                                                                aria-controls="collapse4">
                                                            Aprel
                                                        </button>
                                                    </a>
                                                </h2>
                                            </div>
                                            <div id="collapse4" class="collapse" aria-labelledby="heading4"
                                                 data-parent="#accordionExample1">
                                                <div class="card-body">
                                                    <div id="4Stat"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card">
                                            <div class="card-header" id="heading5">
                                                <h2 class="mb-0">
                                                    <a href="javascript: getStatisticsByMonth('5')">
                                                        <button class="btn btn-link collapsed" type="button"
                                                                data-toggle="collapse"
                                                                data-target="#collapse5" aria-expanded="false"
                                                                aria-controls="collapse5">
                                                            May
                                                        </button>
                                                    </a>
                                                </h2>
                                            </div>
                                            <div id="collapse5" class="collapse" aria-labelledby="heading5"
                                                 data-parent="#accordionExample1">
                                                <div class="card-body">
                                                    <div id="5Stat"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card">
                                            <div class="card-header" id="heading6">
                                                <h2 class="mb-0">
                                                    <a href="javascript: getStatisticsByMonth('6')">
                                                        <button class="btn btn-link collapsed" type="button"
                                                                data-toggle="collapse"
                                                                data-target="#collapse6" aria-expanded="false"
                                                                aria-controls="collapse6">
                                                            İyun
                                                        </button>
                                                    </a>
                                                </h2>
                                            </div>
                                            <div id="collapse6" class="collapse" aria-labelledby="heading6"
                                                 data-parent="#accordionExample1">
                                                <div class="card-body">
                                                    <div id="6Stat"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card">
                                            <div class="card-header" id="heading7">
                                                <h2 class="mb-0">
                                                    <a href="javascript: getStatisticsByMonth('7')">
                                                        <button class="btn btn-link collapsed" type="button"
                                                                data-toggle="collapse"
                                                                data-target="#collapse7" aria-expanded="false"
                                                                aria-controls="collapse7">
                                                            İyul
                                                        </button>
                                                    </a>
                                                </h2>
                                            </div>
                                            <div id="collapse7" class="collapse" aria-labelledby="heading7"
                                                 data-parent="#accordionExample1">
                                                <div class="card-body">
                                                    <div id="7Stat"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card">
                                            <div class="card-header" id="heading8">
                                                <h2 class="mb-0">
                                                    <a href="javascript: getStatisticsByMonth('8')">
                                                        <button class="btn btn-link collapsed" type="button"
                                                                data-toggle="collapse"
                                                                data-target="#collapse8" aria-expanded="false"
                                                                aria-controls="collapse8">
                                                            Avqust
                                                        </button>
                                                    </a>
                                                </h2>
                                            </div>
                                            <div id="collapse8" class="collapse" aria-labelledby="heading8"
                                                 data-parent="#accordionExample1">
                                                <div class="card-body">
                                                    <div id="8Stat"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card">
                                            <div class="card-header" id="heading9">
                                                <h2 class="mb-0">
                                                    <a href="javascript: getStatisticsByMonth('9')">
                                                        <button class="btn btn-link collapsed" type="button"
                                                                data-toggle="collapse"
                                                                data-target="#collapse9" aria-expanded="false"
                                                                aria-controls="collapse9">
                                                            Sentyabr
                                                        </button>
                                                    </a>
                                                </h2>
                                            </div>
                                            <div id="collapse9" class="collapse" aria-labelledby="heading9"
                                                 data-parent="#accordionExample1">
                                                <div class="card-body">
                                                    <div id="9Stat"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card">
                                            <div class="card-header" id="heading10">
                                                <h2 class="mb-0">
                                                    <a href="javascript: getStatisticsByMonth('10')">
                                                        <button class="btn btn-link collapsed" type="button"
                                                                data-toggle="collapse"
                                                                data-target="#collapse10" aria-expanded="false"
                                                                aria-controls="collapse10">
                                                            Oktyabr
                                                        </button>
                                                    </a>
                                                </h2>
                                            </div>
                                            <div id="collapse10" class="collapse" aria-labelledby="heading10"
                                                 data-parent="#accordionExample1">
                                                <div class="card-body">
                                                    <div id="10Stat"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card">
                                            <div class="card-header" id="heading11">
                                                <h2 class="mb-0">
                                                    <a href="javascript: getStatisticsByMonth('11')">
                                                        <button class="btn btn-link collapsed" type="button"
                                                                data-toggle="collapse"
                                                                data-target="#collapse11" aria-expanded="false"
                                                                aria-controls="collapse11">
                                                            Noyabr
                                                        </button>
                                                    </a>
                                                </h2>
                                            </div>
                                            <div id="collapse11" class="collapse" aria-labelledby="heading11"
                                                 data-parent="#accordionExample1">
                                                <div class="card-body">
                                                    <div id="11Stat"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card">
                                            <div class="card-header" id="heading12">
                                                <h2 class="mb-0">
                                                    <a href="javascript: getStatisticsByMonth('12')">
                                                        <button class="btn btn-link collapsed" type="button"
                                                                data-toggle="collapse"
                                                                data-target="#collapse12" aria-expanded="false"
                                                                aria-controls="collapse12">
                                                            Dekabr
                                                        </button>
                                                    </a>
                                                </h2>
                                            </div>
                                            <div id="collapse12" class="collapse" aria-labelledby="heading12"
                                                 data-parent="#accordionExample1">
                                                <div class="card-body">
                                                    <div id="12Stat"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btnNot" data-dismiss="modal">Bağla</button>
                </div>
            </div>
        </div>
    </div>
    <!--Info AuthorDao End-->
</div>
<div class="container">
    <canvas id="bar-chart-grouped" width="800" height="450"></canvas>
</div>
