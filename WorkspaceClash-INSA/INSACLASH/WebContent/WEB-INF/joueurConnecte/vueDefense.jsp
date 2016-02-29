<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="Model.TypeBatiment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/form.css"/>" />
<title>Page de gestion des defenses</title>
</head>

<body>
<c:import url="/inc/menuConnecte.jsp" />
<h1> Gestion des canons </h1>
<c:if test="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.CANON).size() < sessionScope.sessionJoueur.village.getHDV().quotaBatiments.get(TypeBatiment.CANON)}">
		<form method="post" action="<c:url value="/gestionDefense" />">
			<fieldset>
				<legend>Ajout d'un canon</legend>
				<input border=0 src="<c:url value="/inc/Canon.png" />" width="80" height="80" type=image value=submit name="ajoutercanon" align="middle" > <br>
				Cout de construction : <br> <img src="<c:url value="/inc/or.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.getVillage().getBatiments().getModeleBatiments().get(TypeBatiment.CANON).coutUpdate}"/>
			</fieldset>
		</form>
</c:if> 


<c:if test="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.CANON).size() !=0}">
	<form method="post" action="<c:url value="/gestionDefense"/> ">
		<fieldset>
		<legend>Gestion de vos canons</legend>
		<table> 
			<tr>
			<c:forEach items="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.CANON)}" var="item" >
				  <td> Canon niveau <c:out value="${item.getNiveau()}"/> <br> 
				  <img src="<c:url value="/inc/Canon.png"/>" width="80" height="80"alt="image de mon canon" /> </td> 
  		  </c:forEach>
  		  </tr>
   		 <tr> 
			<c:forEach items="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.CANON)}" var="item" >
				<td> <c:if test="${item.getNiveau()<sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.CANON)}">
				<input type="submit" value="Ameliorer" name="ameliorer${item.getId()}"> </c:if>  <br> 
				Cout de l'amélioration : <br> <img src="<c:url value="/inc/or.png"/>" width="20" height="20"alt="or" /> <c:out value="${item.coutUpdate}"/> <br>  			
  		  </c:forEach>
  		  </tr>
   		 </table>
   		 </fieldset>
    </form>
</c:if>

<h1> Gestion des mortiers</h1>
<c:if test="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MORTIER).size() < sessionScope.sessionJoueur.village.getHDV().quotaBatiments.get(TypeBatiment.MORTIER)}">
		<form method="post" action="<c:url value="/gestionDefense" />">
			<fieldset>
				<legend>Ajout d'un mortier</legend>
				<input border=0 src="<c:url value="/inc/Mortier.png"/>" width="90" height="90" type=image value=submit name="ajoutermortier" align="middle" > <br>
				Cout de construction : <br> <img src="<c:url value="/inc/or.png"/>" width="20" height="20"alt="or" /> <c:out value="${sessionScope.sessionJoueur.getVillage().getBatiments().getModeleBatiments().get(TypeBatiment.MORTIER).coutUpdate}"/>
			</fieldset>
		</form>
</c:if> 


<c:if test="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MORTIER).size() !=0}">
	<form method="post" action="<c:url value="/gestionDefense"/> ">
		<fieldset>
		<legend>Gestion de vos mortiers</legend>
		<table> 
			<tr>
			<c:forEach items="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MORTIER)}" var="item" >
				  <td> Mortier niveau <c:out value="${item.getNiveau()}"/> <br> 
				  <img src="<c:url value="/inc/Mortier.png"/>" width="90" height="90"alt="image de mon mortier" /> </td> 
  		  </c:forEach>
  		  </tr>
   		 <tr> 
			<c:forEach items="${sessionScope.sessionJoueur.getVillage().getBatiments().getBatiments(TypeBatiment.MORTIER)}" var="item" >
				<td> <c:if test="${item.getNiveau()<sessionScope.sessionJoueur.village.getHDV().getNiveauMaxBatiment(TypeBatiment.MORTIER)}">
				<input type="submit" value="Ameliorer" name="ameliorer${item.getId()}"> </c:if>  <br> 
				Cout de l'amélioration : <br> <img src="<c:url value="/inc/or.png"/>" width="20" height="20"alt="or" /> <c:out value="${item.coutUpdate}"/> <br> 
  		  </c:forEach>
  		  </tr>
   		 </table>
   		 </fieldset>
    </form>
</c:if>

</body>
</html>