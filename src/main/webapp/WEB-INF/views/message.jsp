<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> <fmt:message key="label.title"/> </title>
</head>
<body>
<div align="center" style="padding-top: 5%">
    <div style="height:50%; width: 60%; overflow-y: scroll;">
        <table border="2" cellpadding="2">
            <tr style="background: burlywood">
                <td style="padding: 5px"><fmt:message key="label.sender"/></td>
                <td style="padding: 5px"><b>
                    <fmt:message key="label.chatter"/>
                    <c:choose>
                        <c:when test="${userId == chat.chatPair.from.id}">
                            <c:out value=" ${chat.chatPair.to.name}"/>
                        </c:when>
                        <c:otherwise><c:out value=" ${chat.chatPair.from.name}"/></c:otherwise>
                    </c:choose>
                </b></td>
                <td style="padding: 5px">
                    <fmt:message key="label.date"/>
                </td>
            </tr>
            <c:forEach var="currentChat" items="${chatList}">
                    <c:choose>
                        <c:when test="${userId == currentChat.sender.id}">
                            <tr style="background: lightseagreen">
                        </c:when>
                        <c:otherwise>
                            <tr style="background: darkseagreen">
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${userId == currentChat.sender.id}">
                            <td><fmt:message key="label.me"/></td>
                        </c:when>
                        <c:otherwise>
                            <td><c:out value="${currentChat.sender.name}"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td style="overflow-wrap: anywhere"><c:out value="${currentChat.text}"/></td>
                    <td style="overflow-wrap: initial">
                        <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${currentChat.date}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div align="center">
        <form:form action="sendMessage" modelAttribute="chat" method="post" cssStyle="margin-top: 10px">
            <table>
                <td style="padding-left: 10px">
                    <form:textarea cssStyle="height: 100px; width: 300px;" path="text"/>
                    <form:errors path="text"/>
                    <form:hidden path="chatPair.id" value="${chat.chatPair.id}"/>
                    <form:hidden path="chatPair.from.id" value="${chat.chatPair.from.id}"/>
                    <form:hidden path="chatPair.to.id" value="${chat.chatPair.to.id}"/>
                    <br><br>
                </td>
            </table>
            <form:hidden path="chatPair" value="${chat.chatPair}"/>
            <button class="button buttonGreen" type="submit" style="margin-left: 10%">
                <fmt:message key="label.sendMessage"/>
            </button>
        </form:form>
    </div>
</div>
</body>
</html>
