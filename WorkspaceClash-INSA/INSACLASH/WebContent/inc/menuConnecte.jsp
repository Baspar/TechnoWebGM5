<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="Model.TypeRessource"%>
<%@page import="Model.TypeBatiment"%>
<link type="text/css" rel="stylesheet" href="<c:url value="form.css"/>" />

<table id="mytable"> 
<tr> 
<td> <a href="<c:url value="/gestionRessource"/>" > <img src="<c:url value="/inc/ressource.png"/> " width="50" height="50"/> </a> </td>
<td> <a href="<c:url value="/gestionHDV"/>" >  <img src="<c:url value="/inc/HotelDeVille.png"/>" width="50" height="50"/> </a> </td>
<td> <a href="<c:url value="/gestionDefense"/>" > <img src="<c:url value="/inc/defense.png"/>" width="50" height="50"/> </a> </td>
<td> <a href="<c:url value="/gestionCaserne"/>" > <img src="<c:url value="/inc/Caserne.png"/>" width="50" height="50"/> </a> </td>
<td> <a href="<c:url value="/gestionVillage"/>" > <img src="<c:url value="/inc/Village.png"/>" width="50" height="50"/> </a> </td>
<td> <a href="<c:url value="/gestionTousJoueurs"/>" > <img src="<c:url value="/inc/Combat.png"/>" width="50" height="50"/> </a> </td>
<td> <a href="<c:url value="/deconnexion"/>" > <img src="<c:url value="/inc/deconnecter.png"/>" width="50" height="50"/> </a> </td>

</tr>
<tr> 
<td> GestionRessource </td>
<td> GestionHDV </td>
<td> GestionDefense </td>
<td> GestionCaserne </td>
<td> GestionVillage </td>
<td> RechercheAdversaire </td>
<td> Deconnexion </td>
</tr>
</table>

<br> <br>

<table id="mytable"><tr>
<td id="mytd"> <img src="<c:url value="/inc/HotelDeVille.png"/>" width="20" height="20"alt="or" /> Niveau <c:out value="${sessionScope.sessionJoueur.village.getHDV().getNiveau()}"/></td>
<td id="mytd"> <img src="<c:url value="/inc/or.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteActuelle().get(TypeRessource.OR)}"/>/<c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteMax().get(TypeRessource.OR)}"/></td>
<td id="mytd"> <img src="<c:url value="/inc/charbon.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteActuelle().get(TypeRessource.CHARBON)}"/>/<c:out value="${sessionScope.sessionJoueur.village.getHDV().getQuantiteMax().get(TypeRessource.CHARBON)}"/></td>
<td id="mytd"> <img src="<c:url value="/inc/Archer.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.village.getCaserne().getNombreArcher()}"/></td>
<td id="mytd"> <img src="<c:url value="/inc/trebuchet.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.village.getCaserne().getNombreTrebuchet()}"/></td>
<td id="mytd"> <img src="<c:url value="/inc/Caserne.png"/>" width="20" height="20"alt="or" /> <c:out value="${5*sessionScope.sessionJoueur.village.getCaserne().getNombreTrebuchet()+sessionScope.sessionJoueur.village.getCaserne().getNombreArcher()}"/> /<c:out value="${sessionScope.sessionJoueur.village.getCaserne().getTailleTotaleArmee()}"/></td>
</tr></table>

<c:if test="${sessionScope.manqueRessource!=null}">
<form>
<p class="erreur">Vous n'avez pas assez de ressource pour faire l'action demandée</p>
</form>
</c:if>
