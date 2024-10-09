<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> <fmt:message key="label.title"/> </title>
</head>
<body>
<div align="center" style="padding-top: 15%">
    <h1 style="font-size: 36px"><fmt:message key="label.nothingFound"/></h1> <br>
    <button class="button buttonBlue" type="button" onclick="history.back()">
        <fmt:message key="label.back"/>
    </button>
</div>

</body>
</html>
