<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Developers</title>
</head>
<body>

<c:forEach items="${requestScope.devCollection}" var="developer">
    <c:out value="${developer}" /> <br>
</c:forEach>


<br>
<form action="${pageContext.request.contextPath}/developers" method="POST">
    <tr>
        <td><input type="submit" value="Reload developers" style="height:25px; width:90px"></td>
    </tr>
</form>
<br>
<br>

<c:if test="${not empty requestScope.devCollection}">
    <table>
        <form action="updateDeveloper" method="POST">
            <tr>
                <td></td>
                <td><label>id</label></td>
                <td><label>new name</label></td>
                <td><label>new age</label></td>
                <td><label>new salary</label></td>
            </tr>
            <tr>
                <td><input type="submit" value="Update developer" style="height:25px; width:90px"></td>

                <td><input type="text" name="id" required style="height:25px; width:30px"></td>

                <td><input type="text" name="name" required style="height:25px"></td>

                <td><input type="text" name="age" required style="height:25px"></td>

                <td><input type="text" name="salary" required style="height:25px"></td>
            </tr>
        </form>

        <form action="deleteDeveloper" method="POST">
            <tr>
                <td><input type="submit" value="Delete developer" style="height:25px; width:90px"></td>

                <td><input type="text" name="id" required style="height:25px; width:30px"></td>
            </tr>
        </form>
    </table>
</c:if>

<c:if test="${empty requestScope.devCollection}">
    "Developer" collection is empty!<br>
</c:if>

</body>
</html>