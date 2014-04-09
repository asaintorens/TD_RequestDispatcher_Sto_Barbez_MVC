package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlleurs.ControlleurBienvenue;
import controlleurs.ControlleurFormulaireInscription;
import modele.Utilisateur;

@WebServlet("/Bienvenue")
public class ServletBienvenue extends HttpServlet {
	
	private static final String CHEMIN_VUE = "/VUE/INSCRIPTION/Bienvenue.jsp"; 
	private static final String CHEMIN_INDEX = "/index.html";	
	private static final String ID_UTILISATEUR = "leUtilisateur";	
	private static final String ID_LOGIN = "leLogin";	
	
	//do get renvoie directement a l'index
	@Override
	public void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		System.out.println("doGetBienvenue");
		this.getServletContext().getRequestDispatcher( CHEMIN_INDEX ).forward( req, resp );
        
    }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		ControlleurBienvenue leControlleurBienvenue = new ControlleurBienvenue(req);
		Utilisateur leUtilisateur = leControlleurBienvenue.getUtilisateur();
		
		req.setAttribute(ID_UTILISATEUR , leUtilisateur );
		this.getServletContext().getRequestDispatcher(CHEMIN_VUE ).forward( req, resp );	
	}
}
