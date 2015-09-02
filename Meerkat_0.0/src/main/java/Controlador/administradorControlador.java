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


import Negocio.tomaynotificacionpedidos.*;
import Presentacion.*;

@WebServlet("/administrador")
public class administradorControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public administradorControlador() {
        super();
    }
    
    public static ArrayList<Producto> productos;
    public static consultarinventarioFacade inventarioFacade= new consultarinventarioFacade();
    public static ArrayList<Producto> productosactual=null;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        RequestDispatcher rd = request.getRequestDispatcher(pagina);
        rd.forward(request, response);
	}

}
