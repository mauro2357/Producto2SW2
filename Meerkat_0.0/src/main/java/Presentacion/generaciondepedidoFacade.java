package Presentacion;

import java.util.ArrayList;

import Datos.FacturaRepository;
import Negocio.geraciondefactura.Factura;
import Negocio.tomaynotificacionpedidos.Pedido;

public class generaciondepedidoFacade {
	public ArrayList<Factura> main() throws Exception{
		FacturaRepository facturarepository = new FacturaRepository();
		ArrayList<Factura> x = facturarepository.generarfactura();
		return x;
	}
	
	public int ultimopedidoid() throws Exception{
		FacturaRepository facturarepository = new FacturaRepository();
		ArrayList<Factura> x = facturarepository.generarfactura();
		return x.size();
	}
	
	public void enviar_pedido(Pedido x, String estado, String mesa, String mesero, String cliente, String caja, String fecha) throws Exception{
		FacturaRepository facturarepository = new FacturaRepository();
		facturarepository.ingresarPedido(x, estado, mesa, mesero, cliente, caja, fecha);
	}
}
