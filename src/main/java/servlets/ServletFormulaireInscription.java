package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlleurs.ControlleurFormulaireInscription;
import modele.Utilisateur;

@WebServlet("/InscriptionFormulaire")
public class ServletFormulaireInscription extends HttpServlet {

	
	private static final String CHEMIN_VUE = "/VUE/INSCRIPTION/SaisieFormulaire.jsp"; 

	private static final String CHEMIN_INSCRIPTION_VALIDE = "/Bienvenue"; 
	private static final String CHEMIN_INSCRIPTION_NON_VALIDE = "/InscriptionFormulaire"; 

	private static final String ID_UTILISATEUR = "leUtilisateur"; 
	private static final String ID_FORMULAIRE = "leFormulaireInscription"; 


	//Do get se déclanche lors de l'appel de la servlet par la balise <a> de l'index
	@Override
	public void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher( CHEMIN_VUE ).forward( req, resp );
	}

	//DoPost se déclanche lors de l'action submit du formulaire inscription
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
			{
		ControlleurFormulaireInscription leFormulaireInscription = new ControlleurFormulaireInscription(req);
		//recupere l'utilisateur qui sera passé en parametre pour récupérer les valeurs déjà saisites
		Utilisateur leUtilisateur = leFormulaireInscription.NouvelleInscriptionFormulaire();
		if (leFormulaireInscription.EstUneInscriptionValide())
		{
			req.setAttribute(ID_UTILISATEUR , leUtilisateur );
			this.getServletContext().getRequestDispatcher( CHEMIN_INSCRIPTION_VALIDE ).include( req, resp );
		}
		else
		{
			req.setAttribute(ID_FORMULAIRE , leFormulaireInscription );
			req.setAttribute(ID_UTILISATEUR , leUtilisateur );
			this.getServletContext().getRequestDispatcher(CHEMIN_VUE ).forward( req, resp );
		}
	}

}
