package Negocio.geraciondefactura;

public class Cajero {

	Factura factura = null;
	
	public String generarFactura() {
		factura = new Factura(null,null,null,null);
		return "Factura";
	}

	public Factura getFactura() {
		return factura;
	}

	public boolean aņadirpropina(int x) {
		//se le suma x al valor de la factura.
		return true;
	}
	
}
