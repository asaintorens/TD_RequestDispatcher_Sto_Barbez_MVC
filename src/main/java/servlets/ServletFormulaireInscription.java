package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Utilisateur;

@WebServlet("/InscriptionFormulaire")
public class ServletFormulaireInscription extends HttpServlet {


	private static final String CHEMIN_VUE = "/VUE/INSCRIPTION/SaisieFormulaire.jsp";

	private static final String CHEMIN_INSCRIPTION_VALIDE = "/Bienvenue"; 
	private static final String CHEMIN_INSCRIPTION_NON_VALIDE = "/InscriptionFormulaire"; 

	private static final String ID_UTILISATEUR = "leUtilisateur"; 
	private static final String ID_FORMULAIRE = "leFormulaireInscription";

    // MODIF MVC *************************************************************************************
    private static final String CHAMP_ERREUR_VALIDATION= "formulaireValide";

    private String loginUtilisateurSaisie;
    private String mailUtilisateurSaisie;
    private boolean formulaireValide;

    private Map<String, String> mapErreurs;

    //IMPORTANT !!!
    // quand dans la jsp on appelle leFormulaireInscription.mapErreurs[], il ne va pas
    //chercher le membre mais passe par la propriété qui se nomme EXACTEMENT de la meme facon avec get en amont !
    public Map<String, String> getMapErreurs() {
        return mapErreurs;
    }


    //ID CHAMPS FORMULAIRE /WEB-INF/SaisieFormulaire.jsp
    private static final String IDCHAMP_LOGIN   = "login";
    private static final String IDCHAMP_MAIL= "mail";
    private static final String IDCHAMP_CONTRAT= "contrat";
    private boolean contratUtilisateurSaisie;
    // FIN MODIF *************************************************************************************
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


                this.mapErreurs = new HashMap<String, String>();
                // .toString() d'une valeur nulle = lève exception
                this.loginUtilisateurSaisie = (String)req.getParameter(IDCHAMP_LOGIN);
                this.mailUtilisateurSaisie = (String)req.getParameter(IDCHAMP_MAIL);
                this.contratUtilisateurSaisie = req.getParameter(IDCHAMP_CONTRAT)!=null;
                System.out.println("see");
                System.out.println(contratUtilisateurSaisie);

                this.formulaireValide =false;                

		//recupere l'utilisateur qui sera passé en parametre pour récupérer les valeurs déjà saisites
		Utilisateur leUtilisateur = NouvelleInscriptionFormulaire();
		if (EstUneInscriptionValide())
		{
			req.setAttribute(ID_UTILISATEUR , leUtilisateur );
			this.getServletContext().getRequestDispatcher( CHEMIN_INSCRIPTION_VALIDE ).include( req, resp );
		}
		else
		{
//			req.setAttribute(ID_FORMULAIRE , leFormulaireInscription );
			req.setAttribute(ID_UTILISATEUR , leUtilisateur );
			this.getServletContext().getRequestDispatcher(CHEMIN_VUE ).forward( req, resp );
		}
	}

    public Utilisateur NouvelleInscriptionFormulaire()
    {
        Utilisateur leUtilisateur = new Utilisateur();
        leUtilisateur.setMail(this.mailUtilisateurSaisie);
        leUtilisateur.setLogin(this.loginUtilisateurSaisie);
        this.formulaireValide =true;
        try
        {

            controlerMailSaisie(this.mailUtilisateurSaisie);

        } catch ( Exception e ) {

            this.formulaireValide =false;
            this.ajouterUneErreur( IDCHAMP_MAIL, e.getMessage() );
            this.ajouterUneErreur( CHAMP_ERREUR_VALIDATION, "Formulaire Invalide" );
        }

        try
        {

            controlerLoginSaisie(this.loginUtilisateurSaisie);

        } catch ( Exception e ) {
            this.formulaireValide =false;
            this.ajouterUneErreur( IDCHAMP_LOGIN, e.getMessage() );
            this.ajouterUneErreur( CHAMP_ERREUR_VALIDATION, "Formulaire Invalide" );
        }
        try
        {
            controlerContratSaisie(this.contratUtilisateurSaisie);
        } catch ( Exception e ) {
            this.formulaireValide =false;
            this.ajouterUneErreur( IDCHAMP_CONTRAT, e.getMessage() );
            this.ajouterUneErreur( CHAMP_ERREUR_VALIDATION, "Formulaire Invalide" );
        }

        return leUtilisateur;
    }
    //----------------- Fonction de controle levant une exception avec un message d'erreur
    private void controlerContratSaisie(boolean leContratUtilisateurSaisie) throws Exception {
        if (leContratUtilisateurSaisie != true)
            throw new Exception("Il faut accepter le contrat");
    }
    private void controlerLoginSaisie(String leLoginUtilisateurSaisie) throws Exception {
        if (leLoginUtilisateurSaisie != null)
            if (leLoginUtilisateurSaisie.length()<3)
                throw new Exception("Le login doit comporter 3 caractères minimum");
    }
    private void controlerMailSaisie(String leMailUtilisateurSaisie) throws Exception {
        if ( leMailUtilisateurSaisie != null ) {
            if ( !leMailUtilisateurSaisie.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new Exception( "Le mail est invalide. il doit être au format : aa@aa.aa" );
            }
        } else {
            throw new Exception( "L'adresse mail est obligatoire." );
        }

    }

    private void ajouterUneErreur(String IdChamp, String messageErreur) {
        this.mapErreurs.put(IdChamp, messageErreur);

    }
    public boolean EstUneInscriptionValide()
    {
        return this.formulaireValide;
    }


}
