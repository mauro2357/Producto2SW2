package Datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Negocio.factura.Factura;
import Negocio.pedido.Mesa;
import Negocio.pedido.Pedido;
import Negocio.pedido.Producto;

public class FacturaRepository {
	
	public void Despachar_pedido(int id) throws Exception{
		Connection con = new ConexionMySql().ObtenerConexion();
	    String query = "UPDATE venta SET `Ven_estado`='Despachado' WHERE `Ven_id`='"+ Integer.toString(id) +"';";
	    Statement st = con.createStatement();
	    st.executeUpdate(query);
	    st.close();
	}

	public void Cancelar_pedido(int parseInt) throws Exception {
		Connection con = new ConexionMySql().ObtenerConexion();
		Statement st = con.createStatement();
		String query = "Delete from detalles_venta where Ven_id =" + Integer.toString(parseInt) + ";";
		st.executeUpdate(query);
		query = "Delete from venta where Ven_id =" + Integer.toString(parseInt) + ";";
		st.executeUpdate(query);
		st.close();
	}
	
	public void Ingresar_pedido(Pedido x, String mesero) throws Exception {
		Connection con = new ConexionMySql().ObtenerConexion();
		String query = "INSERT INTO `future`.`venta` (`Ven_fecha`, `Ven_estado`, `Cli_id`, `Me_id`, `Mesa_id`) VALUES ('"+x.fecha+"', '"+x.estado+"', '"+x.cliente+"', '"+x.mesero.id+"', '"+x.mesa.id+"');";
	    Statement st = con.createStatement();
	    st.executeUpdate(query);
	    query = "Select * from venta order by ven_id desc limit 1";
	    st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    rs.first();
	    String ven_id = rs.getString("Ven_id");
		ArrayList<String> visitados = new ArrayList<String>();
		for(Producto producto: x.cuerpo){
			if(visitados.contains(producto.codigo)) continue;
			int aux=0;
			for(Producto auxproducto: x.cuerpo){
				if(producto.codigo.equalsIgnoreCase(auxproducto.codigo)){
					visitados.add(auxproducto.codigo);
					aux++;
				}
			}
			query = "INSERT INTO detalles_venta (`Pro_id`, `Ven_id`, `Dtv_cantidad`) VALUES ('"+producto.codigo+"','"+ven_id+"','"+aux+"');";
			st.executeUpdate(query);
		}
		query = "delete from pedidos_temporales where Me_id = "+mesero+";";
		st.executeUpdate(query);
	    st.close();
	}
	
	public ArrayList<Factura> Generar_factura(String aignorar) throws Exception {
		String[] porpartes = {""};
		if(aignorar!=null) porpartes = aignorar.split("/");
		Connection con = new ConexionMySql().ObtenerConexion();
	    String query = "select * from factura order by Ven_id, Pro_id, Dtv_cantidad ASC;";
	    Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    ArrayList<Factura> f = new ArrayList<Factura>();
	    ProductoRepository productoRepository = new ProductoRepository();
	    MesaRepository mesaRepository = new MesaRepository();
	    ArrayList<Producto> tproductos = productoRepository.Consultar_producto();
	    ArrayList<Producto> x = null;
	    int preciot=0;
	    Pedido y = null;
	    Map<Producto, Integer> z = null;
	    String auxid = null, auxestado=null;
	    String mesero=null, cajero=null, mesa=null, cliente=null,id=null, estado=null, fecha=null;
	    Mesa mesam = null;
	    while (rs.next()){
	    	id=rs.getString("Ven_id");
	    	estado = rs.getString("Ven_estado");
	    	if(porpartes.length>1){
	    		auxestado = estado; //Puede que en la web no sirva.
	    		if(estado.equalsIgnoreCase(porpartes[0]) || estado.equalsIgnoreCase(porpartes[1])) continue;
	    	}
	    	else if(estado.equalsIgnoreCase(aignorar) || estado.equalsIgnoreCase("Finalizado")) continue;
	    	if(auxid==null) auxid=id;
	    	if(!auxid.equalsIgnoreCase(id)){
	    		if(y==null){
		    		  y = new Pedido();
		    		  y.estado = auxestado;
		    		  y.cuerpo = x;
		    		  y.cantidades = z;
		    		  y.precio_total = preciot;
		    		  y.fecha = fecha;
		    		  mesam = mesaRepository.Buscar_Mesa(mesa);
		    		  Factura fi = new Factura(auxid,mesero,cajero,mesam,y,cliente);
				      f.add(fi);
		    	}
	    		preciot = 0;
		    	x = null;
		    	auxestado = null;
		    	z = null;
		    	y = null;
		    	auxid=id;
	    	}
	    	if(auxid.equalsIgnoreCase(id)){ 
		    	  if(x==null){ x = new ArrayList<Producto>(); preciot=0; z = new HashMap<Producto, Integer>(); }
	 	    	  mesero = rs.getString("Me_id"); 
	 		      cajero = rs.getString("Caj_id"); 
	 		      mesa = rs.getString("Mesa_id");
	 		      cliente = rs.getString("Cli_id");
	 		      fecha = rs.getString("Ven_fecha");
	 		      auxestado = estado;
	 		      for(Producto producto: tproductos){ 
	 		    	  if(producto.codigo.equalsIgnoreCase(rs.getString("Pro_id"))){ x.add(producto); preciot+=(producto.valor*Integer.parseInt(rs.getString("Dtv_cantidad"))); z.put(producto, Integer.parseInt(rs.getString("Dtv_cantidad"))); break;} 
	 		      }
	 		      auxid = id; 
	 		      continue; 
		     } 

	    }
	    if(porpartes.length>1){
    		if(auxestado.equalsIgnoreCase("Finalizado") || auxestado.equalsIgnoreCase(porpartes[0]) || auxestado.equalsIgnoreCase(porpartes[1])){
    			y = new Pedido();
    		    y.estado = estado;
    		    y.precio_total = preciot;
    		    y.cuerpo = x;
    		    y.cantidades = z;
    		    y.fecha = fecha;
    		    if(mesa == null) return f;
    		    mesam = mesaRepository.Buscar_Mesa(mesa);
    		    Factura fi = new Factura(auxid,mesero, cajero,mesam,y,cliente);
    			f.add(fi);
    		}
    	}
    	else if(auxestado!= null && !auxestado.equalsIgnoreCase(aignorar) && !auxestado.equalsIgnoreCase("Finalizado")){
    		y = new Pedido();
    	    y.estado = auxestado;
    	    y.precio_total = preciot;
    	    y.cuerpo = x;
    	    y.cantidades = z;
    	    y.fecha = fecha;
    	    if(mesa == null) return f;
    	    mesam = mesaRepository.Buscar_Mesa(mesa);
    	    Factura fi = new Factura(auxid,mesero, cajero,mesam,y,cliente);
    		f.add(fi);
    	}
	    st.close();
	    return f;
	}

	public void Cobrar(Factura factura) throws Exception {
		Connection con = new ConexionMySql().ObtenerConexion();
		String query = "UPDATE `future`.`venta` SET `Ven_estado`='Finalizado' WHERE `Ven_id`='"+factura.id+"';";
		MesaRepository mesaRepository = new MesaRepository();
		mesaRepository.Ocupar_Desocupar_Mesa(factura.mesa.id, "disponible");
		if(factura.cliente != null) ingresar_puntos(factura.cliente, factura.pedido.precio_total);
		Statement st = con.createStatement();
		st.executeUpdate(query);
		st.close();
	}
	
	public Pedido Pedido_temporal(String mesero) throws Exception{
		Connection con = new ConexionMySql().ObtenerConexion();
		String query = "select * from pedidos_temporales where Me_id="+mesero+" order by Pt_id, Pro_id ASC;";
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    Map<Producto,Integer> cantidades = new HashMap<Producto,Integer>();
	    while(rs.next()){
	    	String Pro_id = rs.getString("Pro_id");
	    	ProductoRepository pR = new ProductoRepository();
	    	Producto productoen = null;
	    	for(Producto producto: pR.Consultar_producto()){
	    		if(producto.codigo.equalsIgnoreCase(Pro_id)){
	    			productoen = producto;
	    			break;
	    		}
	    	}
	    	if(!cantidades.containsKey(Pro_id)) cantidades.put(productoen, 1);
	    	else cantidades.replace(productoen, cantidades.get(Pro_id)+1);
	    }
		Pedido pedido_temporal = new Pedido();
		pedido_temporal.cantidades = cantidades;
		ArrayList<Producto> cuerpo = new ArrayList<Producto>();
		List<Producto> ordenado = new ArrayList<Producto>(cantidades.keySet());
	    Collections.sort(ordenado, new Comparator<Producto>() {
	        public int compare(Producto o1, Producto o2) {
	            return o1.valor - o2.valor;
	        }
	    });
		cuerpo.addAll(ordenado);
		pedido_temporal.cuerpo = cuerpo;
		st.close();
		return pedido_temporal;
	}

	public void Adicionar_Producto_a_Pedido_Temporal(String producto, String mesero) throws Exception {
		Connection con = new ConexionMySql().ObtenerConexion();
		String query = "INSERT INTO `future`.`pedidos_temporales` (`Me_id`, `Pro_id`) VALUES ('"+mesero+"', '"+producto+"');";
		Statement st = con.createStatement();
		st.executeUpdate(query);
		st.close();
	}

	public void Quitar_Producto_a_Pedido_Temporal(String producto, String mesero, Pedido pedido_sin_asignar) throws Exception {
		Connection con = new ConexionMySql().ObtenerConexion();
    	String query = "select * from pedidos_temporales where Me_id="+mesero+" and Pro_id = '"+producto+"';";
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    while(rs.next()){
	    	query = "delete from pedidos_temporales where Pt_id = "+rs.getString("Pt_id") +";";
			st.executeUpdate(query);
			st.close();
	    	break;
	    }
	}

	public void Limpiar_pedido_temporal(String id) throws Exception {
		Connection con = new ConexionMySql().ObtenerConexion();
		String query = "delete from pedidos_temporales where Me_id=" +id+";";
		Statement st = con.createStatement();
		st.executeUpdate(query);
		st.close();
	}
	
	public void ingresar_puntos(String cli_id, int pro_valor ) throws Exception{
		int puntos = pro_valor/1000;
		int puntos_actual = 0;
		Connection con = new ConexionMySql().ObtenerConexion();
		String query_cliente = "select cli_puntos from cliente where cli_id = '"+cli_id+"'";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query_cliente);
		rs.first();
		puntos_actual = Integer.parseInt(rs.getString("cli_puntos"));
		puntos += puntos_actual;
		String query = "UPDATE `future`.`cliente` SET `Cli_puntos`='"+puntos+"' WHERE `Cli_id`='"+cli_id+"';";
		st.executeUpdate(query);
		st.close();
	}
}
