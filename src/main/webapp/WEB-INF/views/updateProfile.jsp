<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> <fmt:message key="label.title"/> </title>
</head>
<body>
<div align="center" style="padding-top: 10%">
    <form:form action="updateProfile" method="post" enctype="multipart/form-data">
        <fmt:message key="label.user.image"/>
        <input type="file" name="profilePic">
        <p>${message}</p>
        <br>
        <button class="button buttonGreen" type="submit"><fmt:message key="label.submit"/></button>
    </form:form>
</div>
</body>
</html>