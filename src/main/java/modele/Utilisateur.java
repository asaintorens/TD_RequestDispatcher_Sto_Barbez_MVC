package modele;
import java.lang.reflect.Constructor;

import javax.swing.text.StyledEditorKit.BoldAction;


public class Utilisateur {

	private String login;
	private String mail;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	private String nom;
	private int age;
	private String rue;
	private int CP;
	private String ville;
	private boolean etape2;
	public Utilisateur() {
		this.etape2=false;
	}
	public boolean isEtape2() {
		return etape2;
	}
	public void setEtape2(boolean etape2) {
		this.etape2 = etape2;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public int getCP() {
		return CP;
	}
	public void setCP(int cP) {
		CP = cP;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}


	
}
