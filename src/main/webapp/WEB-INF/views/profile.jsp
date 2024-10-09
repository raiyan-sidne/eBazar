<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> <fmt:message key="label.title"/> </title>
    <link rel="stylesheet" href="<c:url value='/css/profile.css'/>"/>
</head>
<body>
<h2>${message}</h2>
<div>
    <div class="split left" style="width: 35%; padding: 15px">
        <c:set value="/images/" var="prefix"/>
        <c:url var="image" value="${prefix}${user.image.path}"/>
        <c:if test="${empty user.image}">
            <c:url var="image" value="/images/avatar.png"/>
        </c:if>
        <img src="${image}" width="60%" height="35%"/> <br><br>

        <c:choose>
            <c:when test="${ownProfile}">
                <c:url value="/updateProfile" var="updateUrl"/>
                <button class="button buttonBlue" type="button" onclick="location.href='${updateUrl}'">
                    <fmt:message key="label.user.updateProfile"/>
                </button>
            </c:when>
            <c:otherwise>
                <c:url value="/sendMessage" var="messageUrl">
                    <c:param name="id" value="${user.id}"/>
                </c:url>
                <button class="button buttonBlue" type="button" onclick="location.href='${messageUrl}'">
                    <fmt:message key="label.sendMessage"/>
                </button>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="split right">

        <h3><fmt:message key="label.name"/></h3>
        <p> ${user.name} </p> <br>

        <h3><fmt:message key="label.user.email"/></h3>
        <p> ${user.email} </p> <br>

        <h3><fmt:message key="label.user.phone"/></h3>
        <p> ${user.phone} </p> <br>

        <c:if test="${viewOwnListsEligibility}">
            <c:url value="/postedProduct" var="postedUrl"/>
            <button class="button buttonGreen" type="button" style="width: 260px"
                    onclick="location.href='${postedUrl}'">
                <fmt:message key="label.user.posted"/>
            </button>
            <br>

            <c:url value="/soldProduct" var="soldUrl"/>
            <button class="button buttonGreen" type="button" style="width: 260px"
                    onclick="location.href='${soldUrl}'">
                <fmt:message key="label.user.sold"/>
            </button>
            <br>

            <c:url value="/offeredProduct" var="offeredUrl"/>
            <button class="button buttonGreen" type="button" style="width: 260px"
                    onclick="location.href='${offeredUrl}'">
                <fmt:message key="label.user.offered"/>
            </button>
            <br>

            <c:url value="/boughtProduct" var="boughtUrl"/>
            <button class="button buttonGreen" type="button" style="width: 260px"
                    onclick="location.href='${boughtUrl}'">
                <fmt:message key="label.user.bought"/>
            </button>
            <br>
        </c:if>
    </div>
</div>
</body>
</html>