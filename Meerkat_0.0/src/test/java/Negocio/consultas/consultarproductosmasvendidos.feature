Feature: Como administrador quiero consultar los productos m�s vendidos.

		Scenario: El administrador va a consultar los productos mas vendidos
		Given El administrador va a consultar los productos m�s vendidos
		When Existen ventas en la base de datos
		Then Mostrar el listado de productos

