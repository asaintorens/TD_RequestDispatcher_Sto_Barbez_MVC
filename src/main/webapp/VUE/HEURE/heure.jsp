<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <%@ page import="java.util.Date" %>
</head>
<body>

<h1>
 <%java.text.DateFormat date = new java.text.SimpleDateFormat("dd/MM/yyyy"); %>
   <%java.text.DateFormat heure = new java.text.SimpleDateFormat("hh:mm:ss"); %>
<h1>date : <%= date.format(new java.util.Date()) %> heure : <%= heure.format(new java.util.Date()) %> </h1>
</h1>
		

</body>
</html>