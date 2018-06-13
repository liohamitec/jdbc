<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Skills</title>
</head>
<body>

<c:forEach items="${requestScope.skillCollection}" var="skill">
    <c:out value="${skill}" /> <br>
</c:forEach>


<br>
<form action="${pageContext.request.contextPath}/skills" method="POST">
    <tr>
        <td><input type="submit" value="Reload skills" style="height:25px; width:90px"></td>
    </tr>
</form>
<br>
<br>

<c:if test="${not empty requestScope.skillCollection}">
    <table>
        <form action="updateSkill" method="POST">
            <tr>
                <td></td>
                <td><label>id</label></td>
                <td><label>new name</label></td>
            </tr>
            <tr>
                <td><input type="submit" value="Update skill" style="height:25px; width:90px"></td>

                <td><input type="text" name="id" required style="height:25px; width:30px"></td>

                <td><input type="text" name="name" required style="height:25px"></td>
            </tr>
        </form>

        <form action="deleteSkill" method="POST">
            <tr>
                <td><input type="submit" value="Delete skill" style="height:25px; width:90px"></td>

                <td><input type="text" name="id" required style="height:25px; width:30px"></td>
            </tr>
        </form>
    </table>
</c:if>

<c:if test="${empty requestScope.skillCollection}">
    "Skill" collection is empty!<br>
</c:if>

</body>
</html>