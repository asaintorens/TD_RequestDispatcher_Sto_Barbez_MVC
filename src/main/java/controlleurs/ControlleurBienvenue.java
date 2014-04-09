package controlleurs;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import modele.Utilisateur;

public class ControlleurBienvenue {

	public Utilisateur utilisateur;
	public  ControlleurBienvenue(HttpServletRequest request) {
		this.utilisateur = (Utilisateur)request.getAttribute("leUtilisateur");
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


}
