<%--
  Created by IntelliJ IDEA.
  User: zh
  Date: 2020/6/28
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <script>
        window.onload = function () {
            document.getElementById("img").onclick=function () {

                this.src="${pageContext.request.contextPath}/CheckCodeServlet?time="+new Date().getTime();;
            }
        }
    </script>
</head>
<body>
<form action="/Login/loginServlet" method="post">
    <table align="center">
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>验证码</td>
            <td><input type="text" name="checkCode"></td>
        </tr>
        <tr>
            <td colspan="2"><img id="img" src="/Login/CheckCodeServlet"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="登陆"></td>

        </tr>
    </table>

</form>
<div align="center">
<%
    if(session.getAttribute("cc_error")!=null)
    out.println(session.getAttribute("cc_error"));
%>
</div>
</body>
</html>
