<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Presentacion.*" %>
<%@ page import="Negocio.tomaynotificacionpedidos.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<br>
		<% 
		ArrayList<Mesero> y1 = (ArrayList<Mesero>) session.getAttribute("todos-los-meseros");
		for(Mesero mesero: y1){
			%>
			<center>
			<h3><font color ="white"><%out.print(mesero.getNombre());%></h2></font>
			<input size="15" maxlength="20" name="nombre_boton" type="image" src="mes.png" onclick="definirmesero(<%out.print(mesero.getId());%>);">
			</center>
			<%
		}%>
</body>
</html>