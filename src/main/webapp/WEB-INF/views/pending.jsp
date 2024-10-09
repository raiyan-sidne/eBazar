<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> <fmt:message key="label.title"/> </title>
</head>
<body>
<div align="center" style="padding-top: 100px">
    <table border="2" cellpadding="2">
        <tr>
            <td><b> <fmt:message key="label.product.list"/> </b></td>
            <c:if test="${user.admin}">
                <td><b> <fmt:message key="label.action"/> </b></td>
                <td><b> <fmt:message key="label.action"/> </b></td>
            </c:if>
        </tr>
        <c:forEach var="userProduct" items="${userProductList}">
            <tr>
                <c:url value="viewProduct" var="viewProductUrl">
                    <c:param name="id" value="${userProduct.id}"/>
                </c:url>
                <td><a href="${viewProductUrl}"> <c:out value="${userProduct.product.name}"/> </a></td>

                <c:if test="${user.admin}">
                    <c:url value="approvePost" var="approveUrl">
                        <c:param name="id" value="${userProduct.id}"/>
                    </c:url>
                    <td><a href="${approveUrl}"> <fmt:message key="label.approve"/> </a></td>

                    <c:url value="rejectPost" var="rejectUrl">
                        <c:param name="id" value="${userProduct.id}"/>
                    </c:url>
                    <td><a href="${rejectUrl}"> <fmt:message key="label.reject"/> </a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>