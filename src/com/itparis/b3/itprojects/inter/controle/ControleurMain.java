package com.itparis.b3.itprojects.inter.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itparis.b3.project.bulletin.beans.Session;
import com.itparis.b3.project.bulletin.dao.SessionDAO;

/**
 * Servlet implementation class ServletPrincipale
 */
@WebServlet("/ControleurMain")
public class ControleurMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static ResourceBundle resource = ResourceBundle.getBundle("appli");

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControleurMain() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setAttribute("url", null);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String forward = "Accueil.jsp";
            //TraceManager.logInfo("Détermination de l'idaction");
            String idaction = request.getParameter("action");
            if (idaction.equals("auth")) {
                forward = authentifier(request);
            }
            else if (idaction.equals("getbulletin")) {
                forward = getbulletin(request);
            }
            else if (idaction.equals("detailmodule")) {
                forward = getdetailmodule(request);
            }
            
            //TraceManager.logInfo("Appel de la vue : " + forward);
            RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
            dispatcher.forward(request, response);

        } finally {
            out.close();
        }

    }
    private String getdetailmodule(HttpServletRequest request) {
		// TODO Auto-generated method stub
    	//récupérer paramètre de la requete
    	String id = request.getParameter("idmodule");
    	//requete
    	//recupération résultat
    	//envoi vers JSP
    	
		return null;
	}

	private String getbulletin(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	private String authentifier(HttpServletRequest request) {
        String forward;
        //récupération des paramètres
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        //création de la dao
        SessionDAO service = new SessionDAO();
        Session user = service.vesrifLoginPass(login, pass);
        //Traitement
        if (user != null) {
            forward = "Accueil.jsp";
/*            HttpSession session = request.getSession();
            if (user.isAdmin()) {
                session.setAttribute("role", "admin");
            } else {
                session.setAttribute("role", "user");
            }*/
        } else {
            forward = "Error.jsp";
            request.setAttribute("erreur", resource.getString("auth.erreur"));
        }
        return forward;
    }


}
