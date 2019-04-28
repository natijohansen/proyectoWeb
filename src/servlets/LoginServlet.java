package servlets;

import modelo.User;
import controlador.UserLogic;
import excepciones.NegocioException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	   
	public LoginServlet() {
	   }
	   
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
        	Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
    	
    	//Debo validar que el usuario que se esta logueando exista
    	User user = null;
    	String mensajeError = null;
    	String usr = request.getParameter("inputUser");
    	String pass = request.getParameter("inputPassword");
    	
    	try {
    		user = UserLogic.getInstancia().identificarUser(usr,pass);
    	}catch(NegocioException ne){
            mensajeError = ne.getMessage();
        }
    	
    	HttpSession sesion = request.getSession();
        if (user!=null){
        	sesion.setAttribute("usuarioLogueado", user);
        	
        	//request.getRequestDispatcher("principal.jsp").forward(request, response);
        	response.sendRedirect("principal.jsp");
        }else{
            request.setAttribute("mensajeError", mensajeError);
            request.getRequestDispatcher("inicio.jsp").forward(request, response);
        }
    }

}
