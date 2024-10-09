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

    <h1>${message}</h1>

    <form:form action="register" modelAttribute="user" method="post">
        <table>
            <tr>
                <th><fmt:message key="label.user.name"/></th>
                <td>
                    <form:input type="text" path="name"/>
                    <form:errors path="name"/>
                </td>
            </tr>
            <tr>
                <th><fmt:message key="label.user.email"/></th>
                <td>
                    <form:input type="text" path="email"/>
                    <form:errors path="email"/>
                </td>
            </tr>
            <tr>
                <th><fmt:message key="label.user.password"/></th>
                <td>
                    <form:input type="password" path="password"/>
                    <form:errors path="password"/>
                </td>
            </tr>
            <tr>
                <th><fmt:message key="label.user.phone"/></th>
                <td>
                    <form:input type="text" path="phone"/>
                    <form:errors path="phone"/>
                </td>
            </tr>
        </table>
        <br>
        <button class="button buttonGreen" type="submit"><fmt:message key="label.submit"/></button>
    </form:form>
</div>
</body>
</html>