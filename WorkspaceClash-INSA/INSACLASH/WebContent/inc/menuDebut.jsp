<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table id="mytable"> <tr>
<td> <a href="<c:url value="/connexion"/>" > <img src="<c:url value="/inc/connecter.png"/>" width="200" height="50" alt="Connexion" /> </a> </td>
<td> <a href="<c:url value="/inscription"/>" > <img src="<c:url value="/inc/inscrire.png"/>" width="200" height="50"alt="Inscription" /> </a> </td>
</tr></table>