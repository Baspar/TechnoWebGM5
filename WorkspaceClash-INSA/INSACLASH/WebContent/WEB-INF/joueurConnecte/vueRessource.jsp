<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="Model.TypeBatiment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="2, <c:url value="/gestionRessource" />">
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/form.css"/>" />
<title>Page de gestion des ressources</title>
</head>
<body>
<c:import url="/inc/menuConnecte.jsp" />
<h1> Gestion des mines d'or </h1>
<c:if test="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINEOR).size() < sessionScope.sessionJoueur.village.getHDV().quotaBatiments.get(TypeBatiment.MINEOR)}">
		<form method="post" action="<c:url value="/gestionRessource" />">
			<fieldset>
				<legend>Ajout d'une mine d'or</legend>
				<input border=0 src="<c:url value="/inc/MineOr.png" />" width="100" height="100" type=image value=submit name="ajouteror" align="middle" > <br>
				Cout de construction : <br> <img src="<c:url value="/inc/charbon.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.getVillage().getBatiments().getModeleBatiments().get(TypeBatiment.MINEOR).coutUpdate}"/>
			</fieldset>
		</form>
		
</c:if> 


<c:if test="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINEOR).size() !=0}">
	<form method="post" action="<c:url value="/gestionRessource"/> ">
		<fieldset>
		<legend>Gestion de vos mines d'or</legend>
		<table> 
			<tr>
			<c:forEach items="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINEOR)}" var="item" >
				  <td> Mine niveau <c:out value="${item.getNiveau()}"/> <br> 
				  <img src="<c:url value="/inc/MineOr.png"/>" width="100" height="100"alt="image de ma mine" /> </td> 
  		  </c:forEach>
  		  </tr>
   		 <tr> 
			<c:forEach items="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINEOR)}" var="item" >
				<td> <c:if test="${item.getNiveau()<sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.MINEOR)}">
				<input type="submit" value="Ameliorer" name="ameliorer${item.getId()}"> </c:if>  <br> 
				Cout de l'amélioration : <br> <img src="<c:url value="/inc/charbon.png"/>" width="20" height="20"alt="or" /> <c:out value="${item.coutUpdate}"/> <br>
				Quantite stockee : <br> <img src="<c:url value="/inc/or.png"/>" width="20" height="20"alt="or" /> <c:out value="${item.calculProduction()}"/><br>
				<input type="submit" value="Vider" name="vider${item.getId()}"> </td>      			
  		  </c:forEach>
  		  </tr>
   		 </table>
   		 </fieldset>
    </form>
</c:if>

<h1> Gestion des mines de charbon </h1>
<c:if test="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINECHARBON).size() < sessionScope.sessionJoueur.village.getHDV().quotaBatiments.get(TypeBatiment.MINECHARBON)}">
		<form method="post" action="<c:url value="/gestionRessource" />">
			<fieldset>
				<legend>Ajout d'une mine de charbon</legend>
				<input border=0 src="<c:url value="/inc/MineCharbon.png"/>" type=image value=submit name="ajoutercharbon" align="middle" > <br>
				Cout de construction : <br> <img src="<c:url value="/inc/or.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.getVillage().getBatiments().getModeleBatiments().get(TypeBatiment.MINECHARBON).coutUpdate}"/>
			</fieldset>
		</form>
		
</c:if> 


<c:if test="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINECHARBON).size() !=0}">
	<form method="post" action="<c:url value="/gestionRessource"/> ">
		<fieldset>
		<legend>Gestion de vos mines de charbon</legend>
		<table> 
			<tr>
			<c:forEach items="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINECHARBON)}" var="item" >
				  <td> Mine niveau <c:out value="${item.getNiveau()}"/> <br> 
				  <img src="<c:url value="/inc/MineCharbon.png"/>" width="100" height="100"alt="image de ma mine" /> </td> 
  		  </c:forEach>
  		  </tr>
   		 <tr> 
			<c:forEach items="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MINECHARBON)}" var="item" >
				<td> <c:if test="${item.getNiveau()<sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.MINECHARBON)}">
				<input type="submit" value="Ameliorer" name="ameliorer${item.getId()}"> </c:if>  <br> 
				Cout de l'amélioration : <br> <img src="<c:url value="/inc/or.png"/>" width="20" height="20"alt="or" /> <c:out value="${item.coutUpdate}"/> <br>
				Quantite stockee : <br> <img src="<c:url value="/inc/charbon.png"/>" width="20" height="20"alt="or" /> <c:out value="${item.calculProduction()}"/><br>
				<input type="submit" value="Vider" name="vider${item.getId()}"> </td>  
  		  </c:forEach>
  		  </tr>
   		 </table>
   		 </fieldset>
    </form>
</c:if>

</body>
</html>