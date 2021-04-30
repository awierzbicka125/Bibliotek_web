package bookBB;

import java.io.IOException;
import java.io.Serializable;


import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



import biblioteka.dao.KsiazkaDAO;
import biblioteka_entities.Ksiazka;
@Named
@ViewScoped
public class bookEditBB  implements Serializable {
	
	 
		private static final long serialVersionUID = 1L;

		private static final String PAGE_PERSON_LIST = "personList";
		private static final String PAGE_STAY_AT_THE_SAME = null;

		private Ksiazka ksiazka = new Ksiazka();
		private Ksiazka loaded = null;

		@Inject
		FacesContext context;
		
		@Inject
		Flash flash;
		
		@EJB
		KsiazkaDAO ksiazkaDAO;

		public Ksiazka getKsiazka() {
			return ksiazka;
		}

		public void onLoad() throws IOException {
			if (!context.isPostback()) {
				if (!context.isValidationFailed() && ksiazka.getID_ksiazka() != null) {
					loaded = ksiazkaDAO.find(ksiazka.getID_ksiazka());
				}
				if (loaded != null) {
					ksiazka = loaded;
				} else {
					context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
					// if (!context.isPostback()) { // possible redirect
					// context.getExternalContext().redirect("personList.xhtml");
					// context.responseComplete();
					// }
				}
			}

		}

		public String saveData() {
			// no Person object passed
			if (loaded == null) {
				return PAGE_STAY_AT_THE_SAME;
			}

			try {
				if (ksiazka.getID_ksiazka() == null) {
					// new record
					ksiazkaDAO.create(ksiazka);
				} else {
					// existing record
					ksiazkaDAO.merge(ksiazka);
				}
			} catch (Exception e) {
				e.printStackTrace();
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
				return PAGE_STAY_AT_THE_SAME;
			}

			return PAGE_PERSON_LIST;
		}
	
	
	
}
