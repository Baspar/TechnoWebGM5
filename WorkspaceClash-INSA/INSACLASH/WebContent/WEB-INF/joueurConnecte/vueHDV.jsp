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


<h1> Amélioration de l'hotel de ville </h1>
<form method="post" action="<c:url value="/gestionHDV" />"> 
<fieldset>
<legend> Bonus en ameliorant l'hdv</legend>
<table>
<tr> <td> </td><td> Niveau actuel : <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveau()}"/></td> <td> Niveau suivant : <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveau()+1}"/></td></tr>
<tr> <td> quota max Mine Or </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MINEOR)}"/></td>  <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MINEOR)*2}"/></td></tr>
<tr> <td> quota max Mine Charbon </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MINECHARBON)}"/></td>  <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MINECHARBON)*2}"/></td></tr>
<tr> <td> quota max Canon </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.CANON)}"/></td>  <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.CANON)*2}"/></td></tr>
<tr> <td> quota max Mortier </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MORTIER)}"/></td>  <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MORTIER)*2}"/></td></tr>
<tr> <td> niveau max Mine Or </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.MINEOR)}"/></td>  <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.MINEOR)+1}"/></td></tr>
<tr> <td> niveau max Mine Charbon </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.MINECHARBON)}"/></td>  <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.MINECHARBON)+1}"/></td></tr>
<tr> <td> niveau max Canon </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.CANON)}"/></td>  <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.CANON)+1}"/></td></tr>
<tr> <td> niveau max Mortier </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.MORTIER)}"/></td>  <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.MORTIER)+1}"/></td></tr>
<tr> <td> niveau max HDV </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.HDV)}"/></td>  <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.HDV)+1}"/></td></tr>
<tr> <td> niveau max Caserne </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.CASERNE)}"/></td>  <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.CASERNE)+1}"/></td></tr>
<tr> <td> quantite max Or </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteMax().get(TypeRessource.OR)}"/> </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteMax().get(TypeRessource.OR)*2}"/> </td></tr>
<tr> <td> quantite max Charbon </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteMax().get(TypeRessource.CHARBON)}"/> </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteMax().get(TypeRessource.OR)*2}"/> </td> </tr>
</table>
</fieldset>
<fieldset> 
<legend> Amelioration de l'hdv </legend>
<img src="<c:url value="/inc/HotelDeVille.png"/>" width="90" height="90" /> <br>
<input type="submit" value="Ameliorer" name="ameliorer"><br>
Cout de l'amélioration : <br> <img src="<c:url value="/inc/or.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.getVillage().getHDV().coutUpdate}"/>
</fieldset>
</form>
</body>
</html>