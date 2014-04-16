<%@page pageEncoding="UTF-8" contentType="text/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ page import="modele.*"%>
</head>
<body>
	<form method="post" action="InscriptionFormulaire">
		<label>Login :</label> 
		<input type="text" name="login" value="<c:out value="${leUtilisateur.login}"/>" />
		<p style="color: red;">
			${leFormulaireInscription.mapErreurs['login']}
		</p>
		
		<label>Mail :</label> 
		<input type="text" name="mail" value="<c:out value="${leUtilisateur.mail}"/> " />	
		<p style="color: red;">
			${leFormulaireInscription.mapErreurs['mail']}
		</p>
		
		<label> condition :</label> 
		<input type="checkbox" name="contrat"
			value="true" /> 
		
			J'ai lu et approuvé les conditions générales de ce site
	
			<p style="color: red;">
			${leFormulaireInscription.mapErreurs['contrat']}
		</p>
		
		
		<button type="submit">Suivant</button>	
		<p style="color: red;">
			${leFormulaireInscription.mapErreurs['formulaireValide']}
		</p>
		<br />
	</form>

</body>
</html>