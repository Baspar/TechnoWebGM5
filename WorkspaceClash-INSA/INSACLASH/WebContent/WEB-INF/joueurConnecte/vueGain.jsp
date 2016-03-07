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
<title>Page d'affichage du gain</title>
</head>
<body>

<h1> Bonus de guerre </h1>
<table id="mytable">
<tr> <th id="myth"> Or <img src="<c:url value="/inc/or.png"/>" width="20" height="20"alt="or" /></th> <th id="mytd"> Charbon <img src="<c:url value="/inc/charbon.png"/>" width="20" height="20"alt="or" /></th> </tr>
<tr> <td> <c:out value="${sessionScope.gain.get(TypeRessource.OR)}"/> </td> <td> <c:out value="${sessionScope.gain.get(TypeRessource.CHARBON)}"/> </td> </tr>
</table>

<form method="post" action="<c:url value="/gestionGain" />"> 
<fieldset> 
<legend> Suite des événements ... </legend>
<table>
<tr>
<td> <img src="<c:url value="/inc/Caserne.png"/>" width="50" height="50"alt="or" /></td>
<td> <img src="<c:url value="/inc/Combat.png"/>" width="50" height="50"alt="or" /></td>
</tr>
<tr>
<td><input type="submit" value="Revenir a la caserne" name="caserne"></td>
<td><input type="submit" value="Visionner l'attaque" name="attaque"></td>
</tr>
</table>
</fieldset>
</form>
</body>
</html>