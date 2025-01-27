package Datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Negocio.pedido.Mesa;

public class MesaRepository {
	
	public Mesa Buscar_Mesa(String mesa) throws Exception{
		Mesa mesae = null;
		for(Mesa mesam: Consultar_mesas(null)){
			if(mesa.equalsIgnoreCase(mesam.id)){
				mesae=mesam;
				break;
			}
		}
		return mesae;
	}
	
	public ArrayList<Mesa> Consultar_mesas(String aignorar) throws Exception { 
		Connection con = new ConexionMySql().ObtenerConexion();
	    String query = "SELECT * FROM mesa";
	    Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    ArrayList<Mesa> a = new ArrayList<Mesa>();
	    while (rs.next()){
	      String id = rs.getString("Mesa_id");
	      String estado = rs.getString("Mesa_estado");
	      if(estado.equalsIgnoreCase(aignorar)) continue;
	      Mesa p = new Mesa(id, estado);
	      a.add(p);     
	    }
	    st.close();
	    return a; 
	}

	public void Ocupar_Desocupar_Mesa(String id,String estado) throws Exception {
		Connection con = new ConexionMySql().ObtenerConexion();
		String query = "UPDATE mesa SET Mesa_estado='"+estado+"' WHERE Mesa_id='"+id+"';";
		Statement st = con.createStatement();
		st.executeUpdate(query);
		st.close();
	}
	
}
