<%--
  Created by IntelliJ IDEA.
  User: Elmir
  Date: 14.01.2020
  Time: 0:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kadr25 | Forgot Password</title>
    <jsp:include page="/WEB-INF/pages/static/headerLinks.jsp"></jsp:include>
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
</head>
<body>
<div class="container">
    <div class="col-4 inputs">
        <form action="lc?action=change" method="post">
            <input type="hidden" name="token" value="${token}">
            <div class="form-row">
                <label style="margin: auto;" for="validationServerUsername">YENİ ŞİFRƏNİ DAXİL EDİN</label>
                <div class="input-group">
                    <input name="password" type="password" class="form-control" id="validationServerUsername"
                           aria-describedby="inputGroupPrepend3" placeholder="Yeni şifrə" required>
                    <div class="invalid-feedback">
                        Please choose a username.
                    </div>
                </div>
            </div>
            <div class="form-row">
                <label style="margin: auto;" for="validationServerUsername">ŞİFRƏNİNIN DOĞRULUĞUNU TƏSDİQ EDİN</label>
                <div class="input-group">
                    <input name="Rpassword" type="password" class="form-control" id="validationServerUsernamSe"
                           aria-describedby="inputGroupPrepend3" placeholder="Yeni şifrə" required>
                    <div class="invalid-feedback">
                        Please choose a username.
                    </div>
                </div>
            </div>
            <button class="btn btn-secondary" type="submit">Yadda Saxla</button>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/static/footerLinks.jsp"></jsp:include>
</body>
</html>