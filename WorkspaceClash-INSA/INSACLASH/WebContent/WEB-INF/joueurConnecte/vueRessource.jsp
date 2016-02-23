<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page de gestion des ressources</title>
</head>
<body>
<c:import url="/inc/menuConnecte.jsp" />

<c:forEach begin="0" end="${joueur.getVillage().getBatiments().getModeleBatiments().get(MineOr).size()}" var="i">
<c:out value="joueur.getVillage().getBatiments().getModeleBatiments().get(MineOr).size()"/><br>
</c:forEach>

</body>
</html>