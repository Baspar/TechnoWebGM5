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