package Datos;

import java.sql.Connection;
import java.sql.Statement;

import Negocio.tomaynotificacionpedidos.Despachador;


public class nominaempleadosRepository {
	
	public void registrarusuario (Despachador d) throws Exception {
		Connection con = new ConexionMySql().ObtenerConexion();
	    String query = "INSERT INTO `future`.`despachador` (`Des_id`, `Des_nombre`, `Des_apellido`, `Des_telefono`) VALUES ('"+d.id+"', '"+d.nombre+"', '"+d.apellido+"', '"+d.telefono+"');";
	    Statement st = con.createStatement();
	    st.executeUpdate(query);
	    st.close();
	}
}