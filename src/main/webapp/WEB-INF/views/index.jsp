<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> <fmt:message key="label.title"/> </title>
</head>
<body>
<div align="center" style="padding-top: 100px">

    <h3 style="color: red"> ${message} </h3>

    <form:form action="login" modelAttribute="userLogin" method="post">
        <table>
            <tr>
                <th><fmt:message key="label.user.email"/></th>
                <td>
                    <form:input type="text" path="email" placeholder="Enter Email"/>
                    <form:errors path="email"/>
                </td>
            </tr>
            <tr>
                <th><fmt:message key="label.user.password"/></th>
                <td>
                    <form:input type="password" path="password" placeholder="Existing account's pass: 123"/>
                    <form:errors path="password"/>
                </td>
            </tr>
        </table>
        <br>
        <button type="submit" class="button buttonBlue"><fmt:message key="label.login"/></button>
    </form:form>

    <fmt:message key="label.noAccount"/>
    <c:url value="/register" var="registerUrl"/>
    <a href="${registerUrl}"> <fmt:message key="label.register"/> </a> <br><br>
</div>

<div style="padding: 50px">
    <h1><fmt:message key="label.aboutUs"/></h1>
    <p><fmt:message key="label.aboutUs.description"/></p>
</div>
</body>
</html>