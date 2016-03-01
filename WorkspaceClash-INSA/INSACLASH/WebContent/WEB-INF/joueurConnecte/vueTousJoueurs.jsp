<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="Model.TypeRessource"%>
<%@page import="Model.TypeBatiment"%>
<%@page import="Model.TypeSoldat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/form.css"/>" />
<title>Page de gestion de la caserne</title>
</head>
<body>
<c:import url="/inc/menuConnecte.jsp" />

<table>
<tr> <td> Login </td> <td> Niveau Hotel de Ville</td> <td> Quantite Or <img src="<c:url value="/inc/or.png"/>" width="20" height="20"alt="or" /> </td> <td> Quantite Charbon <img src="<c:url value="/inc/charbon.png"/>" width="20" height="20"alt="or" /></td> </tr>
<c:forEach begin="0" end="${sessionScope.sessionListeJoueur.size()-1}" var="i">
	<tr>
	<c:forEach begin="0" end="${sessionScope.sessionListeJoueur.get(i).size()-1}" var="j">
		<td>
   		<c:out value="${sessionScope.sessionListeJoueur.get(i).get(j)}"/>
   		</td>
   	</c:forEach>
   	</tr>
</c:forEach>
</table>


</body>
</html>




