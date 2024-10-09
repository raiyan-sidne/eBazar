<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> <fmt:message key="label.title"/> </title>
</head>
<body>
<div align="center" style="padding-top: 60px">

    <h2><fmt:message key="label.product.history"/></h2> <br><br>

    <table border="2" cellpadding="2">
        <tr style="background: lightseagreen">
            <td><b> <fmt:message key="label.product"/> </b></td>
            <td><b> <fmt:message key="label.seller"/> </b></td>
            <td><b> <fmt:message key="label.action"/> </b></td>
            <td><b> <fmt:message key="label.admin"/> </b></td>
            <td><b> <fmt:message key="label.date"/> </b></td>
            <td><b> <fmt:message key="label.reason"/> </b></td>
        </tr>
        <c:forEach var="history" items="${historyList}">
            <tr style="background: lightcyan">
                <c:url value="/viewProduct" var="viewProductUrl">
                    <c:param name="id" value="${history.userProduct.id}"/>
                </c:url>
                <td><a href="${viewProductUrl}"> <c:out value="${history.userProduct.product.name}"/> </a></td>

                <c:url value="/viewSeller" var="sellerUrl">
                    <c:param name="id" value="${history.userProduct.user.id}"/>
                </c:url>
                <td><a href="${sellerUrl}"> <c:out value="${history.userProduct.user.name}"/> </a></td>

                <td><c:out value="${history.status}"/></td>

                <c:url value="/viewAdmin" var="adminUrl">
                    <c:param name="id" value="${history.user.id}"/>
                </c:url>
                <td><a href="${adminUrl}"> <c:out value="${history.user.name}"/> </a></td>

                <td>
                    <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${history.date}"/>
                </td>

                <td><c:out value="${history.text}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>