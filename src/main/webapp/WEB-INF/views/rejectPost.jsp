<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> <fmt:message key="label.title"/> </title>
</head>
<body>
<div align="center" style="height: 100px">
    <form:form action="rejectPost" modelAttribute="history" method="post" cssStyle="margin-top: 10%">
        <table>
            <th style="padding-bottom: 90px"><fmt:message key="label.post.rejectionReason"/></th>
            <td style="padding-left: 10px">
                <form:textarea cssStyle="height: 100px" path="text"/>
                <form:errors path="text"/>
                <form:hidden path="userProduct.id" value="${history.userProduct.id}"/>
                <br><br>
            </td>
        </table>
        <button class="button buttonGreen" type="submit" style="margin-left: 10%">
            <fmt:message key="label.submit"/>
        </button>
    </form:form>
</div>
</body>
</html>