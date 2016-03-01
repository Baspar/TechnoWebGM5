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
<c:import url="/inc/menuConnecte.jsp" />
<h1> Plan de votre village </h1>

<form method="post" action="<c:url value="/gestionVillage" />">
<c:forEach begin="0" end="${sessionScope.sessionJoueur.getVillage().getCarte().size()-1}" var="i">
	<c:forEach begin="0" end="${sessionScope.sessionJoueur.getVillage().getCarte().get(i).size()-1}" var="j">
   		<c:choose>
    		<c:when test="${sessionScope.sessionJoueur.getVillage().getCarte().get(i).get(j)==null}">
    			<input src="<c:url value="/inc/carreHerbe.png" />" width="30" height="30" type=image value=submit name="case${j*sessionScope.sessionJoueur.getVillage().getTailleVillage()+i}"> 
    		</c:when>
    		<c:when test="${sessionScope.sessionJoueur.getVillage().getCarte().get(i).get(j).getTypeBatiment()==TypeBatiment.HDV}">
    		    <input src="<c:url value="/inc/HotelDeVille.png" />" width="30" height="30" type=image value=submit name="case${j*sessionScope.sessionJoueur.getVillage().getTailleVillage()+i}">     		
    		</c:when>
    		<c:when test="${sessionScope.sessionJoueur.getVillage().getCarte().get(i).get(j).getTypeBatiment()==TypeBatiment.CASERNE}">
    		    <input src="<c:url value="/inc/Caserne.png" />" width="30" height="30" type=image value=submit name="case${j*sessionScope.sessionJoueur.getVillage().getTailleVillage()+i}">     		
    		</c:when>
    		<c:when test="${sessionScope.sessionJoueur.getVillage().getCarte().get(i).get(j).getTypeBatiment()==TypeBatiment.MINEOR}">
    		    <input src="<c:url value="/inc/MineOr.png" />" width="30" height="30" type=image value=submit name="case${j*sessionScope.sessionJoueur.getVillage().getTailleVillage()+i}"> 
       		</c:when>
    		<c:when test="${sessionScope.sessionJoueur.getVillage().getCarte().get(i).get(j).getTypeBatiment()==TypeBatiment.MINECHARBON}">
    		    <input src="<c:url value="/inc/MineCharbon.png" />" width="30" height="30" type=image value=submit name="case${j*sessionScope.sessionJoueur.getVillage().getTailleVillage()+i}">     		   
    		</c:when>
    		<c:when test="${sessionScope.sessionJoueur.getVillage().getCarte().get(i).get(j).getTypeBatiment()==TypeBatiment.CANON}">
    			<input src="<c:url value="/inc/Canon.png" />" width="30" height="30" type=image value=submit name="case${j*sessionScope.sessionJoueur.getVillage().getTailleVillage()+i}"> 
    		</c:when>
    		<c:when test="${sessionScope.sessionJoueur.getVillage().getCarte().get(i).get(j).getTypeBatiment()==TypeBatiment.MORTIER}">
     			<input src="<c:url value="/inc/Mortier.png" />" width="30" height="30" type=image value=submit name="case${j*sessionScope.sessionJoueur.getVillage().getTailleVillage()+i}">     		    
    		</c:when>
		</c:choose>
	</c:forEach>
	<br>
</c:forEach>
</form>

<h1> Batiments non place </h1>
<table>
<tr> <td> Hotel de Ville </td> 
<td> <c:forEach items="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.HDV)}" var="item2">
		<c:if test="${item2.getX()==-1 || item2.getY()==-1}">
		    <input src="<c:url value="/inc/HotelDeVille.png" />" width="30" height="30" type=image value=submit name="hdv"> 		
		</c:if>
</c:forEach> </td> </tr>
<tr> <td> Caserne </td> 
<td> <c:forEach items="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.CASERNE)}" var="item2">
		<c:if test="${item2.getX()==-1 || item2.getY()==-1}">
		    <input src="<c:url value="/inc/Caserne.png" />" width="30" height="30" type=image value=submit name="caserne"> 		
		</c:if>
</c:forEach> </td> </tr>
<tr> <td> Mine Or </td> 
<td> <c:forEach items="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINEOR)}" var="item2">
		<c:if test="${item2.getX()==-1 || item2.getY()==-1}">
   			<input src="<c:url value="/inc/MineOr.png" />" width="30" height="30" type=image value=submit name="mineor${item2.getId()}"> 
		</c:if>
</c:forEach> </td> </tr>
<tr> <td> Mine Charbon </td> 
<td> <c:forEach items="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINECHARBON)}" var="item2">
		<c:if test="${item2.getX()==-1 || item2.getY()==-1}">
   			<input src="<c:url value="/inc/MineCharbon.png" />" width="30" height="30" type=image value=submit name="minecharbon${item2.getId()}"> 		
		</c:if>
</c:forEach> </td> </tr>
<tr> <td> Canon </td> 
<td> <c:forEach items="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.CANON)}" var="item2">
		<c:if test="${item2.getX()==-1 || item2.getY()==-1}">
   			<input src="<c:url value="/inc/Canon.png" />" width="30" height="30" type=image value=submit name="canon${item2.getId()}"> 
		</c:if>
</c:forEach> </td> </tr>
<tr> <td> Mortier </td> 
<td> <c:forEach items="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MORTIER)}" var="item2">
		<c:if test="${item2.getX()==-1 || item2.getY()==-1}">
   			<input src="<c:url value="/inc/Mortier.png" />" width="30" height="30" type=image value=submit name="mortier${item2.getId()}"> 
		</c:if>
</c:forEach> </td> </tr>
</table>

</body>
</html>