package regBB;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class regBB {
	private String Login;
	private String Haslo;
	private String Mail;
	private String Imie;
	private String Nazwisko;
	private String Telefon;
	
	@Inject
	FacesContext ctx;

	public String getLogin() {
		return Login;
	}
	public void settLogin(String login) {
		Login = login;
	}

	public String getHaslo() {
		return Haslo;
	}
	public void setHaslo(String haslo) {
		Haslo = haslo;
	}
	
	public String getMail() {
		return Mail;
	}
	public void setMail(String mail) {
		Mail= mail;
	}
	public String rejestracja() {
		return "rejestracja";
	}
	
	public void VerifyMail() {
		String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern p = Pattern.compile(emailPattern);
        Matcher m = p.matcher(Mail);

	}
	public String getImie() {
		return Imie;
	}
	public void setImie(String imie) {
		Imie = imie;
	}
	public String getNazwisko() {
		return Nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		Nazwisko = nazwisko;
	}
	public String getTelefon() {
		return Telefon;
	}
	public void setTelefon(String telefon) {
		this.Telefon = telefon;
	}
	
	public String powrot(){
		return "login";
	}
		
	

}