<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="Model.TypeBatiment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page de gestion des ressources</title>
</head>
<body>
<c:import url="/inc/menuConnecte.jsp" />
<c:if test="${!empty sessionScope.sessionJoueur}">
		<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
		<p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionJoueur.login}</p>
	    <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionJoueur.village.getHDV().quotaBatiments}</p>
	    <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.HDV)}</p>

</c:if> 


</body>
</html>