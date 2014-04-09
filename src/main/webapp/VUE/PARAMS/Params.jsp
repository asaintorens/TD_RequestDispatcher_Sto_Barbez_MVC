<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <%@ page import="java.util.Date" %>
</head>
<body>

<table>
<tr>
<td>
motto
</td>

<td>
<%= request.getParameter("motto") %>
</td>

</tr>

<tr>
<td>
language
</td>

<td>
<%=request.getParameter("language")%>
</td>

</tr>
<tr>
<td>
Version
</td>

<td>
<%=request.getParameter("version")%>
</td>

</tr>
</table>
		

</body>
</html>