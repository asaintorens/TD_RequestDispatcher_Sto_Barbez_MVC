<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <%@ page import="java.util.Date" %>
</head>
<body>

<h1>
    <jsp:useBean id="today" class="java.util.Date" scope="page" />
    <fmt:setLocale value="fr" />
    <fmt:formatDate value="${today}" type="both" />

 <%--<%java.text.DateFormat date = new java.text.SimpleDateFormat("dd/MM/yyyy"); %>--%>
   <%--<%java.text.DateFormat heure = new java.text.SimpleDateFormat("hh:mm:ss"); %>--%>
<%--<h1>date : <%= date.format(new java.util.Date()) %> heure : <%= heure.format(new java.util.Date()) %> </h1>--%>
</h1>
		

</body>
</html>