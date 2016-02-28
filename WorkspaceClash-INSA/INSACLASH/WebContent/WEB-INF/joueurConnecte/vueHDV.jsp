<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="Model.TypeRessource"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/form.css"/>" />
<title>Page de gestion de l'hotel de ville</title>
</head>
<body>
<c:import url="/inc/menuConnecte.jsp" />

<h1> Descriptif de l'hotel de ville </h1>
Quantite d'or stockee : <br> <img src="<c:url value="/inc/or.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteActuelle().get(TypeRessource.OR)}"/><br>
Quantite de charbon stockee : <br> <img src="<c:url value="/inc/charbon.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteActuelle().get(TypeRessource.CHARBON)}"/><br>


<h1> Amélioration de l'hotel de ville </h1>



</body>
</html>