<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="Model.TypeRessource"%>
<%@page import="Model.TypeBatiment"%>
<link type="text/css" rel="stylesheet" href="<c:url value="form.css"/>" />

<div id="menu">
<a href="<c:url value="/gestionRessource"/>" > GestionRessource </a> 
<a href="<c:url value="/gestionHDV"/>" > GestionHDV </a> 
<a href="<c:url value="/gestionDefense"/>" > GestionDefense </a> 
<a href="<c:url value="/gestionCaserne"/>" > GestionCaserne </a> 
<a href="<c:url value="/gestionVillage"/>" > GestionVillage </a> 

<a href="<c:url value="/deconnexion"/>" > <img src="<c:url value="/inc/deconnecter.png"/>" width="200" height="50" alt="Deconnexion" /> </a> 
<br>
<table><tr>
<td> <img src="<c:url value="/inc/HotelDeVille.png"/>" width="20" height="20"alt="or" /> Niveau <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveau()}"/></td>
<td> <img src="<c:url value="/inc/or.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteActuelle().get(TypeRessource.OR)}"/>/<c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteMax().get(TypeRessource.OR)}"/></td>
<td> <img src="<c:url value="/inc/charbon.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteActuelle().get(TypeRessource.CHARBON)}"/>/<c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteMax().get(TypeRessource.CHARBON)}"/></td>
<td> <img src="<c:url value="/inc/Archer.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.village.getCaserne().getNombreArcher()}"/></td>
<td> <img src="<c:url value="/inc/trebuchet.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.village.getCaserne().getNombreTrebuchet()}"/></td>
<td> <img src="<c:url value="/inc/Caserne.png"/>" width="20" height="20"alt="or" /> <c:out value="${5*sessionScope.sessionJoueur.village.getCaserne().getNombreTrebuchet()+sessionScope.sessionJoueur.village.getCaserne().getNombreArcher()}"/> /<c:out value="${sessionScope.sessionJoueur.village.getCaserne().getTailleTotaleArmee()}"/></td>
</tr></table>

<c:if test="${sessionScope.manqueRessource!=null}">
<form>
<p class="erreur">Vous n'avez pas assez de ressource pour faire l'action demandée</p>
</form>
</c:if>
</div> 