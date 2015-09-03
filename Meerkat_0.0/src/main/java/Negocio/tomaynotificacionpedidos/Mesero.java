package Negocio.tomaynotificacionpedidos;

import java.util.ArrayList;
import java.util.Map;

import Presentacion.*;

public class Mesero {
	
	public String id;
	public String nombre;
	public String apellido;
	public String telefono;
	public ArrayList<Producto> productos;
	public Pedido pedido_sin_asignar;
	public Map<String,String> coladepedidos;
	
	public Mesero(String id, String nombre, String apellido, String telefono) throws Exception {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.productos = consultarproductos();
	}

	public Mesero() {
	}

	public void setId(String id) {this.id = id;}
	
	public String getId() {return id;}
	
	public String getNombre() {return nombre;}
	
	public String getApellido() {return apellido;}
	
	public String getTelefono() {return telefono;}
	
	public String getMensaje() throws Exception {
		ArrayList<Producto> x = consultarproductos();
		if(!(x.size()==0)) return "No hay productos";
		return "Hay productos";
	}
	
	public ArrayList<Producto> consultarproductos() throws Exception {
		consultarproductosFacade y = new consultarproductosFacade();
		ArrayList<Producto> x = new ArrayList<Producto>();
		x = y.main();
		return x;
	}
	
	//c�digo obsoleto
	public void realizar_pedido(ArrayList<Producto> lista, String mesa, String cliente, String meser, String caja, String fecha, String estado, String id) throws Exception{
		this.productos = lista;
		consultarpedidosFacade r = new consultarpedidosFacade();
		//r.main(lista,mesa,cliente,meser,caja,fecha,estado,id);
		r.main(Integer.parseInt(id));
	}
	//fin c�digo obsoleto
	
	public String enviar_pedido(Pedido pedido, String estado, String mesa, String mesero, String cliente, String cajero, String fecha) throws Exception{
		if(pedido.getCuerpo().size()==0) return "No hay productos.";
		generaciondepedidoFacade pedidosFacade = new generaciondepedidoFacade(); 
		pedidosFacade.enviar_pedido(pedido, estado, mesa, mesero, cliente, cajero, fecha);
		return "Pedido enviado";
	}
	
	public Producto consultarproductoinvididual(String idu) throws Exception{
		for(Producto producto: productos){ //Revisamos todos los productos
			if(producto.getCodigo().equalsIgnoreCase(idu)) return producto; //Cuando encontremos el producto en memoria que consida con la id que estamos buscando, retorna el producto
		}
		return null;
	}

	public void adicionarproducto(String id2) throws Exception {
		if(pedido_sin_asignar==null) pedido_sin_asignar = new Pedido(); //Si no hay pedido, se crea
		if(pedido_sin_asignar.getCuerpo() == null) pedido_sin_asignar.cuerpo = new ArrayList<Producto>(); //Si el pedido no tiene productos, se crea el vector
		Producto encontrado = consultarproductoinvididual(id2); //Le decimos al mesero que nos busque la ubicacion en memoria del producto
		pedido_sin_asignar.adicionarproducto(encontrado); //Ya encontrado el producto, lo adicionamos al pedido
	}
	

}
