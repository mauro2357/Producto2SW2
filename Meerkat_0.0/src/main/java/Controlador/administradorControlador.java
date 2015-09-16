package Controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Negocio.cliente.Cliente;
import Negocio.pedido.*;
import Presentacion.*;

@WebServlet("/administrador")
public class AdministradorControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdministradorControlador() {
        super();
    }
    
    
    public static String clave = "meerkat";
    public  static ArrayList<Producto> insumos;
    public  static ArrayList<Cliente> clientes;
    public  static InventarioFacade inventarioFacade= new InventarioFacade();
    public static ClientesFacade clientesFacade= new ClientesFacade();
    public static GeneralesFacade totalventas = new GeneralesFacade();
    public static NominaFacade creardespachador = new NominaFacade();
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Ingreso al servlet");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession s = request.getSession();
        String Puerta = null;
        Puerta = request.getParameter("entrar");
        String pagina = null;
        System.out.println(Puerta);
        if(Puerta.equalsIgnoreCase("Terminar"))
        {
        	s = request.getSession(false);
        	s.invalidate();
        	pagina = "index.jsp";
        }
        
        if(Puerta.equalsIgnoreCase("consultar_inventario")){ 
        	System.out.println("Ingreso a buscar el inventario: ");
        	try { //Try catch para intentar conectar a la BD
        		inventarioFacade.Consultar_insumos(); //Ejecuta el main del Facade, esto obtiene todos los insumos que est�n en la BD
			} catch (Exception e) {
				e.printStackTrace(); //Devuelve un error si no conect� correctamente a la BD
			}
        	pagina = "/consultarinventariovista/listainventario.jsp"; //A esta p�gina jsp se enviar�n los atributos
        	s.setAttribute("todos-los-insumos", inventarioFacade.listainsumos);
        }
        
        if(Puerta.equalsIgnoreCase("consultar_clientes")){ 
        	System.out.println("Ingreso a consultar clientes: ");
        	try { //Try catch para intentar conectar a la BD
        		clientesFacade.Consultar_clientes(); //Ejecuta el main del Facade, esto obtiene todos los insumos que est�n en la BD
			} catch (Exception e) {
				e.printStackTrace(); //Devuelve un error si no conect� correctamente a la BD
			}
        	pagina = "/basedatosusuariosvista/listaclientes.jsp"; //A esta p�gina jsp se enviar�n los atributos
        	s.setAttribute("todos-los-clientes", clientesFacade.listaclientes);
        }
        
        if(Puerta.equalsIgnoreCase("consultar_totalVentas")){ 
        	System.out.println("Ingreso a consultar totalventas: ");
        	try { //Try catch para intentar conectar a la BD
        		totalventas.Consultar_totalventas(); 
			} catch (Exception e) {
				e.printStackTrace(); //Devuelve un error si no conect� correctamente a la BD
			}
        	pagina = "/consultasgeneralesenlaBDvista/totalventas.jsp"; //A esta p�gina jsp se enviar�n los atributos
        	s.setAttribute("todas-ventas", totalventas.x);
        }
        
        if(Puerta.equalsIgnoreCase("ir_administrador")){ //cuando en el index.jsp damos click al boton administrador.      	
        	String claveaux = request.getParameter("pass");
        	System.out.println("clave " + claveaux);
        	if(!clave.equalsIgnoreCase(claveaux)) return;
        	pagina = "/consultarinventariovista/funcionesadministrador.jsp";	
        }
        
        if(Puerta.equalsIgnoreCase("crear_despachador")){ //cuando en el index.jsp damos click al boton administrador.
        	System.out.println("Ingreso al controlador, crear despachador");
        	pagina = "creardespachador.jsp"; //Rederigimos a la jsp de las funciones del administrador.  	
        }
        
        if(Puerta.equalsIgnoreCase("datos_despachador")){ //cuando en el index.jsp damos click al boton administrador.
        	String id = request.getParameter("id");
        	String nombre = request.getParameter("nombre");
        	String apellido = request.getParameter("apellido");
        	String telefono = request.getParameter("telefono");
        	try {
				creardespachador.Registar_Despachador(id, nombre, apellido, telefono);
			} catch (Exception e) {
				e.printStackTrace();
			}
 	
        	System.out.println("Ingreso al controlador, datos despachador");
        	pagina = "/consultarinventariovista/funcionesadministrador.jsp"; //Rederigimos a la jsp de las funciones del administrador.  	
        }
        
        if(Puerta.equalsIgnoreCase("productos_masvendidos")){ 
        	System.out.println("Ingreso a consultar productos mas vendidos: ");
        	try { //Try catch para intentar conectar a la BD
        		totalventas.Consultar_productosmasvendidos();
			} catch (Exception e) {
				e.printStackTrace(); //Devuelve un error si no conect� correctamente a la BD
			}
        	pagina = "/consultasgeneralesenlaBDvista/productosmasvendidos.jsp"; //A esta p�gina jsp se enviar�n los atributos
        	s.setAttribute("productos-masvendidos", totalventas.listaproductos);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(pagina);
        rd.forward(request, response);
	}

}



