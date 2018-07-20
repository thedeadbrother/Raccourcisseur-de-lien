<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My dashboard</title>
</head>
<body>
<img src="images/Esgi-logo.jpg" alt="1"/>
<div style="display: flex; width : 100%">
	<form method="get" action="login" >
		<input type="submit" value="Connexion"/>
	</form>
</div>
<%= request.getAttribute("erreur") %>
<form method="post" action="shortener" >
	<input type="text" id="URL" name="URL" />
	<input type="submit" value="sousmettre"/>
</form>
</body>
</html>