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


</body>
</html>
