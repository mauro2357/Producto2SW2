package tomaynotificacionpedidos;

import java.util.ArrayList;

public class Mesero {

	public void consultarproductos() {
	}
	
	public String getMensaje() {
		return "No hay productos";
	}
	
	Producto productox = new Producto("Aguardiente",30000);

	public ArrayList<Producto> getProductos() {
		ArrayList<Producto> x = new ArrayList<Producto>();
		x.add(productox);
		return x;
	}

}
