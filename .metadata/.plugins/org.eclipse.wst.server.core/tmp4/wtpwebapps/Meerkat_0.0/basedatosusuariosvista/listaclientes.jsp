<%@ page import="java.util.ArrayList"%>
<%@ page import="Negocio.actualizarinventario.Administrador"%>
<%@ page import="Negocio.tomaynotificacionpedidos.Producto"%>

<%@ page import="Negocio.actualizarinventario.Administrador"%>
<%@ page import="Negocio.basedatosclientes.Cliente"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		Clientes: <br>
		<% 		
		ArrayList<Cliente> y1 = (ArrayList<Cliente>) session.getAttribute("todos-los-clientes");
		for(Cliente cliente: y1){
			out.println(cliente.getNombre() + " " + cliente.apellido + ".  Telfono: " + cliente.getTelefono());
			out.println("<br />");
		}%>



</body>
</html>