<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> <fmt:message key="label.title"/> </title>
</head>
<body>
<div align="center" style="padding-top: 100px">
    <table border="2" cellpadding="2">
        <tr>
            <td><b> <fmt:message key="label.chatList"/> </b></td>
        </tr>
        <c:forEach var="chatPair" items="${chatPairList}">
            <tr>
                <c:choose>
                    <c:when test="${chatPair.from.id == userId}">
                        <c:url value="/sendMessage" var="messageUrl">
                            <c:param name="id" value="${chatPair.to.id}"/>
                        </c:url>
                        <td><a href="${messageUrl}"> <c:out value="${chatPair.to.name}"/> </a></td>
                    </c:when>
                    <c:otherwise>
                        <c:url value="/sendMessage" var="messageUrl">
                            <c:param name="id" value="${chatPair.from.id}"/>
                        </c:url>
                        <td><a href="${messageUrl}"> <c:out value="${chatPair.from.name}"/> </a></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>