

	function pagar_mesa(x,y){
		var numberString = $('#Costo').text();
		var Costo = Number(numberString.replace(/[^0-9\.]+/g,""));
		var respuesta = (Costo-$('#Dinero').val());
		if(respuesta<=0){
			alert("Entra la compra");
			var Entrar = "pagar_mesa";
			$.post("/Meerkat_0.0/caja", {
				entrar : Entrar,
				id : x,
				mesa : y
			}, function(responseText){
				$('#cuerpotres').html(responseText)});
			if( ($("#cuerpo").is(':visible')) ){
				$("#cuerpo").toggle();
			}
			if( ($("#cuerpodos").is(':visible')) ){
				$("#cuerpodos").toggle();
			}
		}else{
			alert("Atención! Falta dinero para completar la compra.")
		}
	}
	
	function devolverprecio_mesa(x, y){
		if((!$("#cuerpodos").is(':visible')) ){
			$("#cuerpodos").toggle();
		}
		var Entrar = "devolver_precio_mesa";
		$.post("/Meerkat_0.0/caja", {
			entrar : Entrar,
			id : x,
			mesa : y
		}, function(responseText){
			$('#cuerpodos').html(responseText)});
	}

	function abrircerrarcuerpodos(){
		if( !($("#cuerpodos").is(':visible')) ){
			$("#cuerpodos").toggle();
		}
	}	

	function definirmesero(x){
		var Entrar = "definirmesero";
		$.post("/Meerkat_0.0/mesero", {
			entrar : Entrar,
			meseroi : x
		}, function(responseText){
			$('#cuerpo').html(responseText)});
		$('#titulo').text("Productos");
		$('#menu').hide();
	}
	
	function despacharpedido(x){
		var Entrar = "despachar_pedido";
		$.post("/Meerkat_0.0/despachador", {
			entrar : Entrar,
			pedido : x
		}, function(responseText){
			$('#cuerpo').html(responseText)});
	}
	
	function ir_administrador(){
		$('#cuerpodos').hide();
		var Entrar = "ir_administrador";
		var Pass = $('#pass').val();
		$.post("/Meerkat_0.0/administrador", {
			entrar : Entrar,
			pass : Pass 
		},
		function(responseText) {
				if(responseText!=""){
				$('#cuerpo').html(responseText);}
				else{alert("contrasena incorrecta");}
			});
		$('#titulo').text("Administrador")
		$('#menu').hide();
	}
	
	function ir_despachador(){
		var Entrar = "ir_despachador";
		$.post("/Meerkat_0.0/despachador", {
			entrar : Entrar,
		}, function(responseText){
			$('#cuerpo').html(responseText)});
	}
	
	function crear_usuario(){
		var Entrar = "crear_usuario";
		$.post("/Meerkat_0.0/mesero", {
			entrar : Entrar,
		}, function(responseText){
			$('#cuerpo').html(responseText)
		});
		$('#titulo').text("Crear Usuario")
	}
	
	function ingresarproducto(x){
		var Entrar = "ingresarproducto";
		y = document.getElementById(x);
		x = y.name;
		$.post("/Meerkat_0.0/mesero", {
			entrar : Entrar,
			idp : x
		}, function(responseText) {
			$('#table1').html(responseText);
		});
	}
	
	function ejecutar(){
		alert("Se ha recibido un pedido");
	}
	
	function consultar_inventario(){
		abrircerrarcuerpodos();
		var Entrar = "consultar_inventario";
		$.post("/Meerkat_0.0/administrador", {
			entrar : Entrar,			
			}, function(responseText) {
				$('#cuerpodos').html(responseText);
			});	
	}
	
	function crear_despachador(){
		abrircerrarcuerpodos();
		var Entrar = "crear_despachador";
		$.post("/Meerkat_0.0/administrador", {
			entrar : Entrar,			
			}, function(responseText) {
				$('#cuerpodos').html(responseText);
			});
	}

	function crear_mesero(){
		abrircerrarcuerpodos();
		var Entrar = "crear_mesero";
		$.post("/Meerkat_0.0/administrador", {
			entrar : Entrar,			
			}, function(responseText) {
				$('#cuerpodos').html(responseText);
			});
	}
	
	function consultar_clientes(){
		abrircerrarcuerpodos();
		var Entrar = "consultar_clientes";
		$.post("/Meerkat_0.0/administrador", {
			entrar : Entrar,
			}, function(responseText) {
				$('#cuerpodos').html(responseText);
			});	
	}
	
	function consultar_totalVentas(){
		abrircerrarcuerpodos();
		var Entrar = "consultar_totalVentas";
		$.post("/Meerkat_0.0/administrador", {
			entrar : Entrar,
			}, function(responseText) {
				$('#cuerpodos').html(responseText);
			});	
	}
	
	function productos_masvendidos(){
		abrircerrarcuerpodos();
		var Entrar = "productos_masvendidos";
		$.post("/Meerkat_0.0/administrador", {
			entrar : Entrar,
			}, function(responseText) {
				$('#cuerpodos').html(responseText);
			});	
	}