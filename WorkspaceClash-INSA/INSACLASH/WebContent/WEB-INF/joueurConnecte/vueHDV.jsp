<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="Model.TypeRessource"%>
<%@page import="Model.TypeBatiment"%>
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
<table><tr>
<td> <img src="<c:url value="/inc/HotelDeVille.png"/>" width="20" height="20"alt="or" /> Niveau <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveau()}"/></td>
<td> <img src="<c:url value="/inc/or.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteActuelle().get(TypeRessource.OR)}"/>/<c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteMax().get(TypeRessource.OR)}"/></td>
<td> <img src="<c:url value="/inc/charbon.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteActuelle().get(TypeRessource.CHARBON)}"/>/<c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteMax().get(TypeRessource.CHARBON)}"/></td>
<td> <img src="<c:url value="/inc/Archer.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.village.getCaserne().getNombreArcher()}"/></td>
<td> <img src="<c:url value="/inc/trebuchet.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.village.getCaserne().getNombreTrebuchet()}"/></td>
<td> <img src="<c:url value="/inc/Caserne.png"/>" width="20" height="20"alt="or" /> <c:out value="${5*sessionScope.sessionJoueur.village.getCaserne().getNombreTrebuchet()+sessionScope.sessionJoueur.village.getCaserne().getNombreArcher()}"/> /<c:out value="${sessionScope.sessionJoueur.village.getCaserne().getTailleTotaleArmee()}"/></td>

</tr></table>

<h1> Amélioration de l'hotel de ville </h1>

<table>
<tr> <td> </td><td> Niveau actuel : <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveau()}"/></td> <td> Niveau suivant : <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveau()+1}"/></td></tr>
<tr> <td> quota max Mine Or </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MINEOR)}"/></td>  <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MINEOR)*2}"/></td></tr>
<tr> <td> quota max Mine Charbon </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MINECHARBON)}"/></td>  <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MINECHARBON)*2}"/></td></tr>
<tr> <td> quota max Canon </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.CANON)}"/></td>  <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.CANON)*2}"/></td></tr>
<tr> <td> quota max Mortier </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MORTIER)}"/></td>  <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MORTIER)*2}"/></td></tr>
</table>


</body>
</html>