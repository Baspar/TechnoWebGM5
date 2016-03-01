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
<h1> Affichage des adversaires </h1>

<form method="post" action="<c:url value="/gestionTousJoueurs" />">

<table id="mytable">
<tr> <th id="myth"> Login </th> <th id="myth"> Niveau Hotel de Ville</th> <th id="myth"> Quantite Or <img src="<c:url value="/inc/or.png"/>" width="20" height="20"alt="or" /> </th> <th id="myth"> Quantite Charbon <img src="<c:url value="/inc/charbon.png"/>" width="20" height="20"alt="or" /></th> </tr>
<c:forEach begin="0" end="${sessionScope.sessionListeJoueur.size()-1}" var="i">
	<tr>
	<c:forEach begin="0" end="${sessionScope.sessionListeJoueur.get(i).size()-1}" var="j">
		<td id="mytd">
		<c:if test="${j==0}">
			<input type="submit" value="${sessionScope.sessionListeJoueur.get(i).get(j)}" name="${sessionScope.sessionListeJoueur.get(i).get(j)}">
		</c:if>
		<c:if test="${j!=0}">
		   	<c:out value="${sessionScope.sessionListeJoueur.get(i).get(j)}"/>
		</c:if>
   		</td>
   	</c:forEach>
   	</tr>
</c:forEach>
</table>

</form>

</body>
</html>




