package Presentacion;

import Datos.ProductoRepository;

public class consultarproductosFacade {
	public static void main(String []arg) throws Exception{
		ProductoRepository pedidorepository = new ProductoRepository();
		System.out.println(pedidorepository.ConsultarProducto());
	}
}