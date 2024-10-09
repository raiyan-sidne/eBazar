<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> <fmt:message key="label.title"/> </title>
    <link rel="stylesheet" href="<c:url value='/css/profile.css'/>" />
</head>
<body>
<div class="split left" style="background-color: black">
    <c:set value="/images/" var="prefix"/>
    <c:url var="image" value="/images/product.jpg"/>
    <c:if test="${!userProduct.product.imageFiles.isEmpty()}">
        <c:url var="image" value="${prefix}${userProduct.product.imageFiles.get(0).path}"/>
    </c:if>
    <img src="${image}" width="90%" height="60%"/>

    <c:forEach var="imageFile" items="${userProduct.product.imageFiles}">
        <c:url var="currentPath" value="${prefix}${imageFile.path}"/>
        <img src="${currentPath}" width="30%" height="20%" style="padding-top: 10px"/>
    </c:forEach>
</div>

<div class="split right">
    <h2> ${userProduct.product.name} </h2>

    <h3> <fmt:message key="label.product.description"/> </h3>
    <p> ${userProduct.product.description} </p>

    <h3> <fmt:message key="label.product.status"/> </h3>
    <c:choose>
        <c:when test="${userProduct.product.isUsed}"> <fmt:message key="label.used"/> </c:when>
        <c:otherwise> <fmt:message key="label.new"/> </c:otherwise>
    </c:choose>

    <h3> <fmt:message key="label.product.price"/> </h3>
    <c:out value="${userProduct.product.price}"/>

    <h3> <fmt:message key="label.owner"/> </h3>
    <c:url value = "/viewSeller" var = "sellerUrl">
        <c:param name = "id" value = "${userProduct.user.id}"/>
    </c:url>
    <td> <a href="${sellerUrl}" style="font-size: 18px"> <c:out value="${userProduct.user.name}"/> </a> </td>
    <br><br>

    <c:if test="${not ownProduct}">
        <c:url value="/sendMessage" var="messageUrl">
            <c:param name="id" value="${userProduct.user.id}"/>
        </c:url>
        <button class="button buttonBlue" type="button" onclick="location.href='${messageUrl}'" >
            <fmt:message key="label.sendMessage"/>
        </button> <br><br>

        <c:if test="${bid.price != 0 and !user.admin}">
            <b><fmt:message key="label.currentOffer"/></b>
            <c:out value="${bid.price}"/><br><br>
        </c:if>

        <c:if test="${!user.admin && !userProduct.sold}">
            <form:form action="offer" modelAttribute="bid" method="post">
                <b><fmt:message key="label.offer"/></b>
                <form:input type="number" step="0.01" min="1" path="price"/>
                <form:errors path="price"/> <br><br>
                <form:hidden path="userProduct.id" value="${bid.userProduct.id}"/>
                <form:hidden path="user.id" value="${bid.user.id}"/>
                <form:hidden path="id" value="${bid.id}"/>
                <button class="button buttonGreen" type="submit">
                    <fmt:message key="label.submit"/>
                </button>
            </form:form>
        </c:if>
        <c:if test="${userProduct.sold}">
            <b style="color: red; font-size: 24px"><c:out value="Product Sold"/></b><br>
        </c:if>
    </c:if>

    <c:if test="${ownProduct}">

        <table border="2" cellpadding="2">
            <tr>
                <td> <b> <fmt:message key="label.buyer"/> </b> </td>
                <td> <b> <fmt:message key="label.offeredPrice"/> </b> </td>
                <c:if test="${userProduct.posted}">
                    <td> <b> <fmt:message key="label.action"/> </b> </td>
                </c:if>
            </tr>
            <c:forEach var="bid" items="${bidList}">
                <tr>
                    <c:url value = "/viewBuyer" var = "buyerUrl">
                        <c:param name = "id" value = "${bid.user.id}"/>
                    </c:url>
                    <td> <a href="${buyerUrl}"> <c:out value="${bid.user.name}"/> </a> </td>

                    <td> <c:out value="${bid.price}"/> </td>

                    <c:if test="${userProduct.posted}">
                        <c:url value = "/sellProduct" var = "sellingUrl">
                            <c:param name = "id" value = "${bid.id}"/>
                        </c:url>
                        <td> <a href="${sellingUrl}"> <fmt:message key="label.sell"/> </a> </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>