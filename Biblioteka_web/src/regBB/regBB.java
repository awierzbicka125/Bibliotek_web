package regBB;

import javax.inject.Inject;
import javax.inject.Named;



import biblioteka.dao.UzytkownikDAO;
import biblioteka_entities.Uzytkownik;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

@Named
@RequestScoped
//@SessionScoped
public class regBB {
	private static final String PAGE_PERSON_LIST = "";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String Login;
	private String Haslo;
	private String Mail;
	private String Imie;
	private String Nazwisko;
	private String Telefon;
	
	

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
	private Uzytkownik uzytkownik = new Uzytkownik();
	private Uzytkownik loaded = null;

	@EJB
	UzytkownikDAO UzytkownikDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Uzytkownik getUzytkownik() {
		return uzytkownik;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (Uzytkownik) flash.get("uzytkownik");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			uzytkownik = loaded;
			// session.removeAttribute("person");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "BÅ‚Ä™dne uÅ¼ycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
		}

	}
	
	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (uzytkownik.getID_uzytkownik() == null) {
				// new record
				UzytkownikDAO.create(uzytkownik);
			} else {
				// existing record
				UzytkownikDAO.merge(uzytkownik);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "blad zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_PERSON_LIST;
	}
}