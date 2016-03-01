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


<h1> Amélioration de la caserne </h1>
<form method="post" action="<c:url value="/gestionCaserne" />"> 
<fieldset>
<legend> Bonus en ameliorant la caserne</legend>
<table>
<tr> <td> </td><td> Niveau actuel : <c:out value="${sessionScope.sessionJoueur.village.getCaserne().getNiveau()}"/></td> <td> Niveau suivant : <c:out value="${sessionScope.sessionJoueur.village.getCaserne().getNiveau()+1}"/></td></tr>
<tr> <td> niveau max Archer </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getCaserne().niveauMax.get(TypeSoldat.ARCHER)}"/></td>  <td> <c:out value="${sessionScope.sessionJoueur.village.getCaserne().niveauMax.get(TypeSoldat.ARCHER)+1}"/></td></tr>
<tr> <td> niveau max Trebuchet </td> <td> <c:out value="${sessionScope.sessionJoueur.village.getCaserne().niveauMax.get(TypeSoldat.TREBUCHET)}"/></td>  <td> <c:out value="${sessionScope.sessionJoueur.village.getCaserne().niveauMax.get(TypeSoldat.TREBUCHET)+1}"/></td></tr>
</table>
</fieldset>
<c:if test="${sessionScope.sessionJoueur.village.getCaserne().getNiveau()<sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.CASERNE)}">
<fieldset> 
<legend> Amelioration de la caserne </legend>
<img src="<c:url value="/inc/Caserne.png"/>" width="90" height="90" /> <br>
<input type="submit" value="Ameliorer" name="ameliorercaserne"><br>
Cout de l'amélioration : <br> <img src="<c:url value="/inc/charbon.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.getVillage().getCaserne().coutUpdate}"/>
</fieldset>
</c:if>
</form>

<h1> Gestion de l'armée </h1>
<form method="post" action="<c:url value="/gestionCaserne" />"> 
<fieldset>
<legend> Gestion de l'armée</legend>
<table>
<tr> <td> </td> <td> Archer <img src="<c:url value="/inc/Archer.png"/>" width="20" height="20"alt="or" /> </td> <td> Trebuchet <img src="<c:url value="/inc/trebuchet.png"/>" width="20" height="20"alt="or" /> </td> </tr>
<tr> <td> Niveau actuel </td> 
<td><c:out value="${sessionScope.sessionJoueur.village.getCaserne().niveauActuel.get(TypeSoldat.ARCHER)}"/> </td>
<td> <c:out value="${sessionScope.sessionJoueur.village.getCaserne().niveauActuel.get(TypeSoldat.TREBUCHET)}"/> </td> </tr> 
<tr> <td> Cout de l'amelioration </td>  
<td> <img src="<c:url value="/inc/charbon.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.getVillage().getCaserne().calculCoutUpgrade(TypeSoldat.ARCHER)}"/> <br> 
<c:if test="${sessionScope.sessionJoueur.village.getCaserne().niveauActuel.get(TypeSoldat.ARCHER)<sessionScope.sessionJoueur.village.getCaserne().niveauMax.get(TypeSoldat.ARCHER)}"> 
<input type="submit" value="Ameliorer" name="ameliorerarcher"> </c:if> </td> 
<td> <img src="<c:url value="/inc/charbon.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.getVillage().getCaserne().calculCoutUpgrade(TypeSoldat.TREBUCHET)}"/> <br> 
<c:if test="${sessionScope.sessionJoueur.village.getCaserne().niveauActuel.get(TypeSoldat.TREBUCHET)<sessionScope.sessionJoueur.village.getCaserne().niveauMax.get(TypeSoldat.TREBUCHET)}"> 
<input type="submit" value="Ameliorer" name="ameliorertrebuchet"> </c:if> </td> </tr>
<tr> <td> Nombre </td> 
<td> <c:out value="${sessionScope.sessionJoueur.village.getCaserne().getNombreArcher()}"/> </td>
<td> <c:out value="${sessionScope.sessionJoueur.village.getCaserne().getNombreTrebuchet()}"/> </td> </tr>
<tr> <td> Cout de formation </td>
<td> <img src="<c:url value="/inc/charbon.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.getVillage().getCaserne().calculCoutFormation(TypeSoldat.ARCHER)}"/> <br> 
<c:if test="${sessionScope.sessionJoueur.village.getCaserne().getArmee().calculNbSoldat()<sessionScope.sessionJoueur.village.getCaserne().getTailleTotaleArmee()}"> 
<input type="submit" value="Ajouter" name="ajouterarcher"> </c:if> <br> 
<c:if test="${sessionScope.sessionJoueur.village.getCaserne().getNombreArcher()>0}"> 
<input type="submit" value="Supprimer" name="supprimerarcher"> </c:if> </td> 
<td> <img src="<c:url value="/inc/charbon.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.getVillage().getCaserne().calculCoutFormation(TypeSoldat.TREBUCHET)}"/> <br> 
<c:if test="${sessionScope.sessionJoueur.village.getCaserne().getArmee().calculNbSoldat()+4<sessionScope.sessionJoueur.village.getCaserne().getTailleTotaleArmee()}"> 
<input type="submit" value="Ajouter" name="ajoutertrebuchet"> </c:if> <br> 
<c:if test="${sessionScope.sessionJoueur.village.getCaserne().getNombreTrebuchet()>0}"> 
<input type="submit" value="Supprimer" name="supprimertrebuchet"> </c:if> </td>  </tr>
</table>
</fieldset>
</form>

</body>
</html>