Feature:Como administrador quiero tener una clave de acceso

	Scenario: El administrador ingresa su usuario y contraseņa
	Given El administrador ingresa su usuario y contraseņa
	When el usuario y la clave son incorrectas
	Then notificar que el usuario o clave son incorrectos

	Scenario: El administrador ingresa su usuario y contraseņa
	Given El administrador ingresa su usuario y contraseņa
	When el usuario y la clave son correctas
	Then acceder a las funciones de administrador
	