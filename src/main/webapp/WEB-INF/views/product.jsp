<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> <fmt:message key="label.title"/> </title>
</head>
<body>
<div align="center" style="padding-top: 100px">

    <h1>${message}</h1>

    <form:form action="product" modelAttribute="product" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <th><fmt:message key="label.product.name"/></th>
                <td>
                    <form:input type="text" path="name"/>
                    <form:errors path="name"/>
                    <br><br>
                </td>
            </tr>
            <tr>
                <th><fmt:message key="label.product.price"/></th>
                <td>
                    <form:input type="number" step="0.01" min="0" path="price"/>
                    <form:errors path="price"/>
                    <br>
                </td>
            </tr>
            <tr>
                <th><fmt:message key="label.product.status"/></th>
                <td>
                    <br><br>
                    <fmt:message key="label.new"/> <form:radiobutton path="isUsed" value="false"/><br>
                    <fmt:message key="label.used"/> <form:radiobutton path="isUsed" value="true"/>
                    <br><br>
                </td>
            </tr>
            <tr>
                <th style="padding-bottom: 90px"><fmt:message key="label.product.description"/></th>
                <td>
                    <form:textarea cssStyle="height: 100px" path="description"/>
                    <form:errors path="description"/>
                    <br><br>
                </td>
            </tr>
            <tr>
                <th style="margin-top: 10px;"><fmt:message key="label.product.image"/></th>
                <td>
                    <input type="file" name="files" multiple="true">
                </td>
            </tr>
        </table>
        <br>
        <button class="button buttonGreen" type="submit"><fmt:message key="label.submit"/></button>
    </form:form>
</div>
</body>
</html>
