<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<div id="logo" align="center">
		<a href="http://localhost:8080/Meerkat_0.0/index.jsp"><img src="../logo.png" style="max-width: 100%; height: auto;"></a>
</div>
<h1>Ingresar nuevo usuario</h1>
<form name="nuevo_usuario" method= "post" action="http://localhost:8080/Meerkat_0.0/mesero">
	<input type="hidden" name="entrar" value="datos_usuario">
	<input type="text" name="id"><br>
	<input type="text" name="nombre"><br>
	<input type="text" name="apellido"><br>
	<input type="text" name="sexo"><br>
	<input type="text" name="puntos"><br>
	<input type="text" name="musica"><br>
	<input type="text" name="email"><br>
	<input type="text" name="telefono"><br>
	<input type="submit" name="registrar" value="Registrar Usuario">
</form>
</body>
</html>