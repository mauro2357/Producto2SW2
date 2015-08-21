package Datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Negocio.geraciondefactura.Factura;
import Negocio.tomaynotificacionpedidos.Producto;
public class FacturaRepository {
	public Factura generarfactura() throws Exception {
		Connection con = new ConexionMySql().ObtenerConexion();
	    String query = "SELECT * FROM venta";
	    Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    Factura f = null;
	    while (rs.next()){
	      String meser = rs.getString("Me_nombre");
	      String cajer = rs.getString("Caj_nombre");
	      String mesa = rs.getString("Mesa_id");
	      String producto=rs.getString("Pro_id"+""+"Pro_nombre"+"Pro_valor");
	      f = new Factura(meser, cajer,mesa,producto);
	    }
	    st.close();
	    return f;
	}
}