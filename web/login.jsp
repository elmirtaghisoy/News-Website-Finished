<%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 12.01.2020
  Time: 1:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kadr25 | Admin</title>
    <jsp:include page="WEB-INF/pages/static/headerLinks.jsp"></jsp:include>
    <style>
        .inputs {
            margin-left: auto;
            margin-right: auto;
            margin-top: 15%;
        }

        .form-row {
            margin-top: 10px;
        }

        button {
            margin-top: 20px;
        }

        form {
            text-align: center;
            padding: 3rem;
            border: solid;
            border-radius: 5%;
            border-color: #007bff;
        }
    </style>
    <script>
        history.pushState(null.null, 'index.jsp');
        window.addEventListener('popstate', function (event) {
            history.pushState(null, null, 'index.jsp')
        })
    </script>
</head>
<body>
<div class="container">
    <div class="col-4 inputs text-center">
        <form action="lc?action=login" method="post">
            <div class="form-row">
                <label for="validationServerUsername">İSTİFADƏÇİ</label>
                <div class="input-group">
                    <input name="username" type="text" class="form-control" id="validationServerUsername"
                           aria-describedby="inputGroupPrepend3" required>
                    <div class="invalid-feedback">
                        Please choose a username.
                    </div>
                </div>
            </div>
            <div class="form-row">
                <label for="validationServer01">ŞİFRƏ</label>
                <input name="password" type="password" class="form-control" id="validationServer01" value="" required>
                <div class="invalid-feedback">
                    Looks good!
                </div>
            </div>
            <button class="btn btn-primary" type="submit">DAXIL OL</button>
        </form>
        <a class="btn btn-secondary" href="lc?action=forgotPassword">
            ŞİFRƏNİ YENİLƏ
        </a>
    </div>
</div>
<jsp:include page="WEB-INF/pages/static/footerLinks.jsp"></jsp:include>
</body>
</html>