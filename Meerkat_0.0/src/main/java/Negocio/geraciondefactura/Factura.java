package Negocio.geraciondefactura;

import java.util.ArrayList;

import Negocio.tomaynotificacionpedidos.Producto;

public class Factura {
	
	public String meser;
	public String cajer;
	public String mesa;
	public String Productos;

	
	
public Factura(String x,String y,String z,String w){	
	this.meser=x;
	this.cajer=y;
	this.mesa=z;
	this.Productos=w;
}
}
