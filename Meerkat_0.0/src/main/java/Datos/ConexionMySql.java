package Datos;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMySql {
	  public  Connection ObtenerConexion() throws Exception {
		    String driver = "com.mysql.jdbc.Driver";
		    String connection = "jdbc:mysql://localhost:3306/future";
		    String user = "root";
		    String password = "root";
		    Class.forName(driver);
		    Connection con = DriverManager.getConnection(connection, user, password);
		    System.out.println("conect�");
		   return con;
		  
}
}