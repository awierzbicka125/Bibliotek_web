package loginBB;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class loginBB {
	private String Haslo;
    private String login;
	@Inject
	FacesContext ctx;

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getHaslo() {
		return Haslo;
	}
	public void setHaslo(String haslo) {
		Haslo = haslo;
	}
	
	
	public String zaloguj() {	
			return "personList";		
	}
	
	public String zarejestruj() {		
		return "rejestracja";
	}

}