<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <%@ page import="java.util.*" %>
 <%@ page import="java.*" %>
 <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy-hh-mm-ss"); %>
 <%java.text.DateFormat date = new java.text.SimpleDateFormat("dd/MM/yyyy"); %>
 <%java.text.DateFormat heure = new java.text.SimpleDateFormat("hh:mm:ss"); %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head> 
<body>
<p>
Bienvenue <p style="color:red;" >${leUtilisateur.login}</p> votre inscription a bien été prise en compte le
<%--<%= date.format(new java.util.Date()) %> à <%= heure.format(new java.util.Date()) %> .--%>
<jsp:useBean id="today" class="java.util.Date" scope="page" />
<fmt:setLocale value="fr" />
<fmt:formatDate value="${today}" type="both" />

</p>
</body>
</html>