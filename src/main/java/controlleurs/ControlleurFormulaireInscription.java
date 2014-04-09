package controlleurs;

import java.net.HttpRetryException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import modele.Utilisateur;

//controle l'intégralité des données saisie dans le formulaire /WEB-INF/SaisieFormulaire.jsp
public class ControlleurFormulaireInscription {
	
	//ID CHAMPS FORMULAIRE /WEB-INF/SaisieFormulaire.jsp
	private static final String IDCHAMP_LOGIN   = "login";
    private static final String IDCHAMP_MAIL= "mail"; 
    private static final String IDCHAMP_CONTRAT= "contrat"; 
    
    
    private static final String CHAMP_ERREUR_VALIDATION= "formulaireValide"; 
    
    private String loginUtilisateurSaisie;
    private String mailUtilisateurSaisie;
    private boolean contratUtilisateurSaisie;
    
    private boolean formulaireValide;
    
     private Map<String, String> mapErreurs;

    //IMPORTANT !!!
    // quand dans la jsp on appelle leFormulaireInscription.mapErreurs[], il ne va pas 
    //chercher le membre mais passe par la propriété qui se nomme EXACTEMENT de la meme facon avec get en amont ! 
    public Map<String, String> getMapErreurs() {
        return mapErreurs;
    }
    
    public ControlleurFormulaireInscription(HttpServletRequest request)
    {
    	this.mapErreurs = new HashMap<String, String>();
    	// .toString() d'une valeur nulle = lève exception
    	this.loginUtilisateurSaisie = (String)request.getParameter(IDCHAMP_LOGIN);
    	this.mailUtilisateurSaisie = (String)request.getParameter(IDCHAMP_MAIL);
    	this.contratUtilisateurSaisie = request.getParameter(IDCHAMP_CONTRAT)!=null;
    	System.out.println("see");
    	System.out.println(contratUtilisateurSaisie);
    	
    	this.formulaireValide =false;
    }
    
    public boolean EstUneInscriptionValide()
    {
    	return this.formulaireValide;
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
//-------------------------------------------------------------------------------------
	
	private void ajouterUneErreur(String IdChamp, String messageErreur) {
		this.mapErreurs.put(IdChamp, messageErreur);
		
	}
}
