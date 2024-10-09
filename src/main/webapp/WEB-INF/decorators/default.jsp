<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>"/>
    <title><sitemesh:write property='title'/></title>
    <sitemesh:write property='head'/>
</head>

<body>
<div class="topnav">
    <c:url value="/" var="homeUrl"/>
    <a style="font-size: 24px" href="${homeUrl}"> <fmt:message key="label.title"/> </a>

    <c:if test="${not empty role}">
        <c:url value="/logout" var="logoutUrl"/>
        <a style="float:right;" href="${logoutUrl}"> <fmt:message key="label.logout"/> </a>

        <c:url value="/profile" var="profileUrl"/>
        <a style="float:right;" href="${profileUrl}"> <fmt:message key="label.profile"/> </a>

        <c:url value="/inbox" var="inboxUrl"/>
        <a style="float:right;" href="${inboxUrl}"> <fmt:message key="label.inbox"/> </a>
    </c:if>
</div>
<sitemesh:write property='body'/>
</body>
</html>