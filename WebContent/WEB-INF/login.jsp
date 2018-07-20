<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<img src="images/Esgi-logo.jpg" alt="1"/>

<%= request.getAttribute("erreurLogin") %>
<form method="post" action="login" >
	<label for="login">Name</label><br/>
	<input type="text" id="login" name="login" /><br/>
	<label for="login">Password</label><br/>
	<input type="password" id="pwd" name="pwd"/><br/>
	<input type="submit" value="login/sign up"/>
</form>
</body>
</html>