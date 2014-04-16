<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1" frame="void">
    <c:forEach var="par" items="${paramValues}">

    <tr>
        <td>
            <c:out value="${par.key}"/>
        </td>
        <td>
            <c:forEach var="maValeur" items="${par.value}" >
                <c:out value="${maValeur}," escapeXml="true" />
            </c:forEach>
        </td>
    </tr>
    </c:forEach>
</table>

</body>
</html>