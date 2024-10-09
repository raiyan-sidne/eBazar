<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> <fmt:message key="label.title"/> </title>
</head>
<body>
<div align="center" style="padding-top: 60px">

    <h2>${message}</h2>

    <c:choose>
        <c:when test="${user.admin}">
            <c:url value="/history" var="historyUrl"/>
            <button class="button buttonGreen" type="button" onclick="location.href='${historyUrl}'">
                <fmt:message key="label.product.history"/>
            </button>
            <br>
        </c:when>
        <c:otherwise>
            <c:url value="/product" var="viewProductUrl"/>
            <button class="button buttonGreen" type="button" onclick="location.href='${viewProductUrl}'">
                <fmt:message key="label.product.post"/>
            </button>
            <br>
        </c:otherwise>
    </c:choose>

    <c:url value="/pending" var="pendingUrl"/>
    <button class="button buttonGreen" type="button" onclick="location.href='${pendingUrl}'">
        <fmt:message key="label.product.pending"/>
    </button>
    <br>

    <h2 style="padding-top: 50px"><fmt:message key="label.product.list"/></h2>
    <table border="2" cellpadding="2">
        <tr>
            <td><b> <fmt:message key="label.product"/> </b></td>
            <td><b> <fmt:message key="label.seller"/> </b></td>
            <c:if test="${!user.admin}">
                <td><b> <fmt:message key="label.action"/> </b></td>
            </c:if>
        </tr>
        <c:forEach var="userProduct" items="${userProductList}">
            <tr>
                <c:url value="/viewProduct" var="productUrl">
                    <c:param name="id" value="${userProduct.id}"/>
                </c:url>
                <td><a href="${productUrl}"> <c:out value="${userProduct.product.name}"/> </a></td>

                <c:url value="/viewSeller" var="sellerUrl">
                    <c:param name="id" value="${userProduct.user.id}"/>
                </c:url>
                <td><a href="${sellerUrl}"> <c:out value="${userProduct.user.name}"/> </a></td>

                <c:if test="${userProduct.user.id != userId && !user.admin}">
                    <td><a href="${productUrl}"> <fmt:message key="label.offer"/> </a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>