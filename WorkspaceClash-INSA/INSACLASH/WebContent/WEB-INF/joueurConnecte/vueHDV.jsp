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
<table id="mytable">
<tr> <td> </td><th id="myth"> Niveau actuel : <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveau()}"/></th> <th id="myth"> Niveau suivant : <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveau()+1}"/></th></tr>
<tr> <th id="myth"> quota max Mine Or </th> <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MINEOR)}"/></td>  <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MINEOR)*2}"/></td></tr>
<tr> <th id="myth"> quota max Mine Charbon </th> <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MINECHARBON)}"/></td>  <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MINECHARBON)*2}"/></td></tr>
<tr> <th id="myth"> quota max Canon </th> <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.CANON)}"/></td>  <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.CANON)*2}"/></td></tr>
<tr> <th id="myth"> quota max Mortier </th> <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MORTIER)}"/></td>  <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuotaBatiment(TypeBatiment.MORTIER)*2}"/></td></tr>
<tr> <th id="myth"> niveau max Mine Or </th> <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.MINEOR)}"/></td>  <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.MINEOR)+1}"/></td></tr>
<tr> <th id="myth"> niveau max Mine Charbon </th> <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.MINECHARBON)}"/></td>  <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.MINECHARBON)+1}"/></td></tr>
<tr> <th id="myth"> niveau max Canon </th> <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.CANON)}"/></td>  <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.CANON)+1}"/></td></tr>
<tr> <th id="myth"> niveau max Mortier </th> <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.MORTIER)}"/></td>  <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.MORTIER)+1}"/></td></tr>
<tr> <th id="myth"> niveau max HDV </th> <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.HDV)}"/></td>  <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.HDV)+1}"/></td></tr>
<tr> <th id="myth"> niveau max Caserne </th> <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.CASERNE)}"/></td>  <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.CASERNE)+1}"/></td></tr>
<tr> <th id="myth"> quantite max Or </th> <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteMax().get(TypeRessource.OR)}"/> </td> <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteMax().get(TypeRessource.OR)*2}"/> </td></tr>
<tr> <th id="myth"> quantite max Charbon </th> <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteMax().get(TypeRessource.CHARBON)}"/> </td> <td id="mytd"> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteMax().get(TypeRessource.OR)*2}"/> </td> </tr>
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