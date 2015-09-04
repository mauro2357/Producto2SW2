package Controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Negocio.geraciondefactura.*;
import Negocio.tomaynotificacionpedidos.*;
import Presentacion.*;

@WebServlet("/mesero")
public class meseroControllador extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public meseroControllador() {
        super();
    }
    
    public static Factura factura=null; //
    public static ArrayList<Factura> facturas; //
    public static consultarproductosFacade productosFacade = new consultarproductosFacade();
    public static generaciondepedidoFacade pedidosFacade = new generaciondepedidoFacade();
    public static consultarmeserosFacade consultarmeserosFacade = new consultarmeserosFacade();
    public static consultarmesasFacade consultarmesasFacade = new consultarmesasFacade();
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ingreso al controlador de mesero");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession s = request.getSession();
        String Puerta = null;
        Puerta = request.getParameter("entrar");
        String pagina = null;
        if(Puerta.equalsIgnoreCase("Terminar"))
        {
        	s = request.getSession(false);
        	s.invalidate();
        	pagina = "index.jsp";
        }
        if(Puerta.equalsIgnoreCase("imprimirmeseros")){ //Lo ejecuta controller
        	try { //Try catch para intentar conectar a la BD
        		consultarmeserosFacade.main(); //Ejecuta el main del Facade, esto obtiene todos los meseros que est�n en la BD
			} catch (Exception e) {
				e.printStackTrace(); //Devuelve un error si no conect� correctamente a la BD
			}
        	pagina = "/consultarmeserosvista/listameseros.jsp"; //A esta p�gina jsp se enviar�n los atributos
        	s.setAttribute("todos-los-meseros", consultarmeserosFacade.listameseros); //Env�amos a la jsp anteriormente mencionada la lista de meseros con el nombre de "todos-los-meseros"
        }
        if(Puerta.equalsIgnoreCase("definirmesero")){ //Lo ejecuta mesero
        	String meseroi = request.getParameter("meseroi"); //Capturamos la id del mesero que est� ejecutando
        	//el bot�n que se presion� desde Index.
        	consultarmeserosFacade.definirmesero(meseroi); //Lo enviamos para definir el mesero en el servidor.
        	pagina = "/consultarproductosvista/consultarproductos.jsp"; //Rederigimos a consultarprodutos.
        }
        if(Puerta.equalsIgnoreCase("botones")){ //Lo ejecuta mesero y controller
        	try {
				facturas = pedidosFacade.main();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pagina = "/consultarproductosvista/consultarproductositems/botonproductos.jsp"; //Le vamos a mandar a esta jsp todos los productos.
        	s.setAttribute("todos-los-productos", consultarmeserosFacade.mesero.productos); //Le mandamos los productos a la jsp encargada de imprimirlos
        }
        if(Puerta.equalsIgnoreCase("ingresarproducto")){ //Lo ejecuta controller
        	String id = request.getParameter("idp"); //Este id lo recibimos del boton del producto que presionamos
        	//s.setAttribute("id-producto",id); //Lo colocamos como atributo 
        	try {
				consultarmeserosFacade.mesero.adicionarproducto(id); //Le decimos al mesero que busque cu�l es el producto (en memoria del servidor) y lo agregue al pedido actual
			} catch (Exception e) {
				e.printStackTrace();
			}
        	s.setAttribute("productos-pedido", consultarmeserosFacade.mesero.pedido_sin_asignar.getCuerpo()); //enviamos los productos que lleva el pedido actual
        	pagina = "/consultarproductosvista/consultarproductositems/tablapedidos.jsp";
        }
        if(Puerta.equalsIgnoreCase("listarpedidoactual")){ //Lo ejecuta controller
        	if(consultarmeserosFacade.mesero.pedido_sin_asignar != null) s.setAttribute("productos-pedido", consultarmeserosFacade.mesero.pedido_sin_asignar.getCuerpo()); //Enviamos los productos que lleva el pedido (inicio)
        	else s.setAttribute("productos-pedido",null);
        	pagina = "/consultarproductosvista/consultarproductositems/tablapedidos.jsp";	
        }
        if(Puerta.equalsIgnoreCase("Enviar pedido")){       //Listo
        	String cliente = request.getParameter("cliente");
        	String mesero = consultarmeserosFacade.mesero.getId();
        	String mesa = request.getParameter("mesa");
        	String cajero = request.getParameter("cajero");
        	Pedido pedido = consultarmeserosFacade.mesero.pedido_sin_asignar;
        	Calendar x = Calendar.getInstance();
        	String fecha = x.get(Calendar.YEAR)+"-"+Integer.toString(x.get(Calendar.MONTH)+1)+"-"+x.get(Calendar.DATE);
        	try {
				consultarmeserosFacade.mesero.finiquitarpedido(pedido,cliente,mesero,mesa,cajero,fecha);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	consultarmeserosFacade.mesero.pedido_sin_asignar = null;
        	pagina = "/index.jsp";
        }

        RequestDispatcher rd = request.getRequestDispatcher(pagina);
        rd.forward(request, response);
	}

}