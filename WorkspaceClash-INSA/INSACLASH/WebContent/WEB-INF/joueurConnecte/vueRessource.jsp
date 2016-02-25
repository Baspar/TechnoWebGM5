<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="Model.TypeBatiment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page de gestion des ressources</title>
</head>
<body>
<c:import url="/inc/menuConnecte.jsp" />
<h1> Gestion des mines d'or </h1>
<c:if test="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINEOR).size() < sessionScope.sessionJoueur.village.getHDV().quotaBatiments.get(TypeBatiment.MINEOR)}">
		<form method="post" action="<c:url value="/AjoutMine" />">
			<fieldset>
				<legend>Ajout d'une mine d'or</legend>
				<input border=0 src="<c:url value="/inc/MineOr.png"/>" type=image value=submit align="middle" > 
			</fieldset>
		</form>
		
</c:if> 


<c:if test="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINEOR).size() !=0}">
	Vos mines sont les suivantes 
	<table> 
	<c:forEach items="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINEOR)}" var="item" >
		<tr> 
	  <td> Mine niveau <c:out value="${item.getNiveau()}"/> <br> <img src="<c:url value="/inc/MineOr.png"/>" width="100" height="100"alt="image de ma mine" /> </td> 
	  <td>Améliorer  <br> Vider <br> <img src="<c:url value="/inc/or.png"/>" width="20" height="20"alt="or" /> <c:out value="${item.calculProduction()}"/></td> 
      </tr>
    </c:forEach>
    </table>
</c:if>
</body>
</html>