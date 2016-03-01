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
<title>Page de gestion du village</title>
</head>
<body>

<h1> Plan du village adverse : <c:out value="${sessionScope.sessionAdversaire.getLogin()}"/></h1>
<form method="post" action="<c:url value="/gestionCombat" />">
<input type="submit" value="Retour a la liste des joueurs " name="retour"> <br> 
Selectionner les directions ou placer les soldats au debut de l'attaque <br>
<INPUT type="checkbox" name="choix1" value="1"> EST
<INPUT type="checkbox" name="choix2" value="2"> OUEST
<INPUT type="checkbox" name="choix3" value="3"> NORD
<INPUT type="checkbox" name="choix4" value="4"> SUD
<input type="submit" value="Attaquer" name="attaquer">
</form>
<c:forEach begin="0" end="${sessionScope.sessionAdversaire.getVillage().getCarte().size()-1}" var="i">
	<c:forEach begin="0" end="${sessionScope.sessionAdversaire.getVillage().getCarte().get(i).size()-1}" var="j">
   		<c:choose>
    		<c:when test="${sessionScope.sessionAdversaire.getVillage().getCarte().get(i).get(j)==null}">
    			<img src="<c:url value="/inc/carreHerbe.png" />" width="30" height="30"> 
    		</c:when>
    		<c:when test="${sessionScope.sessionAdversaire.getVillage().getCarte().get(i).get(j).getTypeBatiment()==TypeBatiment.HDV}">
    		    <img src="<c:url value="/inc/HotelDeVille.png" />" width="30" height="30">     		
    		</c:when>
    		<c:when test="${sessionScope.sessionAdversaire.getVillage().getCarte().get(i).get(j).getTypeBatiment()==TypeBatiment.CASERNE}">
    		    <img src="<c:url value="/inc/Caserne.png" />" width="30" height="30">     		
    		</c:when>
    		<c:when test="${sessionScope.sessionAdversaire.getVillage().getCarte().get(i).get(j).getTypeBatiment()==TypeBatiment.MINEOR}">
    		    <img src="<c:url value="/inc/MineOr.png" />" width="30" height="30"> 
       		</c:when>
    		<c:when test="${sessionScope.sessionAdversaire.getVillage().getCarte().get(i).get(j).getTypeBatiment()==TypeBatiment.MINECHARBON}">
    		    <img src="<c:url value="/inc/MineCharbon.png" />" width="30" height="30" >     		   
    		</c:when>
    		<c:when test="${sessionScope.sessionAdversaire.getVillage().getCarte().get(i).get(j).getTypeBatiment()==TypeBatiment.CANON}">
    			<img src="<c:url value="/inc/Canon.png" />" width="30" height="30"> 
    		</c:when>
    		<c:when test="${sessionScope.sessionAdversaire.getVillage().getCarte().get(i).get(j).getTypeBatiment()==TypeBatiment.MORTIER}">
     			<img src="<c:url value="/inc/Mortier.png" />" width="30" height="30">     		    
    		</c:when>
		</c:choose>
	</c:forEach>
	<br>
</c:forEach>

</body>
</html>