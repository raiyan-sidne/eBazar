<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <td><fmt:message key="label.product.status"/></td>
        </tr>
        <c:forEach var="userProduct" items="${userProductList}">
            <tr>
                <c:url value="viewProduct" var="viewProductUrl">
                    <c:param name="id" value="${userProduct.id}"/>
                    <c:param name="status" value="${userProduct.status}"/>
                </c:url>
                <td><a href="${viewProductUrl}"> <c:out value="${userProduct.product.name}"/> </a></td>
                <td>
                    <c:choose>
                        <c:when test="${!userProduct.sold}">
                            <fmt:message key="label.stillAvailable"/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="${userProduct.status}"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>