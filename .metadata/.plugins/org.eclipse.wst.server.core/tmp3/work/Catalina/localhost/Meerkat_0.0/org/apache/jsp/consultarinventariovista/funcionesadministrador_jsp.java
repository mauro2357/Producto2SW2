/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.24
 * Generated at: 2015-09-02 21:21:54 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.consultarinventariovista;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class funcionesadministrador_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<input type=\"button\" value=\"Genera una tabla\" onclick=\"genera_tabla()\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\t\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("\t// aquí debe ir un Javascript con la accion consultar inventerio.\r\n");
      out.write("\t//No sé si actualizarlo en un ajax o crear otra jsp con la lista del inventario.\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function genera_tabla() {\r\n");
      out.write("  // Obtener la referencia del elemento body\r\n");
      out.write("  var body = document.getElementsByTagName(\"body\")[0];\r\n");
      out.write(" \r\n");
      out.write("  // Crea un elemento <table> y un elemento <tbody>\r\n");
      out.write("  var tabla   = document.createElement(\"table\");\r\n");
      out.write("  var tblBody = document.createElement(\"tbody\");\r\n");
      out.write(" \r\n");
      out.write("  // Crea las celdas\r\n");
      out.write("  for (var i = 0; i < 2; i++) {\r\n");
      out.write("    // Crea las hileras de la tabla\r\n");
      out.write("    var hilera = document.createElement(\"tr\");\r\n");
      out.write(" \r\n");
      out.write("    for (var j = 0; j < 2; j++) {\r\n");
      out.write("      // Crea un elemento <td> y un nodo de texto, haz que el nodo de\r\n");
      out.write("      // texto sea el contenido de <td>, ubica el elemento <td> al final\r\n");
      out.write("      // de la hilera de la tabla\r\n");
      out.write("      var celda = document.createElement(\"td\");\r\n");
      out.write("      var textoCelda = document.createTextNode(\"celda en la hilera \"+i+\", columna \"+j);\r\n");
      out.write("      celda.appendChild(textoCelda);\r\n");
      out.write("      hilera.appendChild(celda);\r\n");
      out.write("    }\r\n");
      out.write(" \r\n");
      out.write("    // agrega la hilera al final de la tabla (al final del elemento tblbody)\r\n");
      out.write("    tblBody.appendChild(hilera);\r\n");
      out.write("  }\r\n");
      out.write(" \r\n");
      out.write("  // posiciona el <tbody> debajo del elemento <table>\r\n");
      out.write("  tabla.appendChild(tblBody);\r\n");
      out.write("  // appends <table> into <body>\r\n");
      out.write("  body.appendChild(tabla);\r\n");
      out.write("  // modifica el atributo \"border\" de la tabla y lo fija a \"2\";\r\n");
      out.write("  tabla.setAttribute(\"border\", \"2\");\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
