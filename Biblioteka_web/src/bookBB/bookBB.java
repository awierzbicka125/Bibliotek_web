package bookBB;

import javax.inject.Inject;
import javax.inject.Named;


import biblioteka.dao.KsiazkaDAO;
import biblioteka_entities.Ksiazka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

@Named
@RequestScoped
//@SessionScoped
public class bookBB {
	

	private static final String PAGE_BOOK_EDIT = "personEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String tytul;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	KsiazkaDAO ksiazkaDAO;
		
	public String getSurname() {
		return tytul;
	}

	public void setSurname(String tytul) {
		this.tytul = tytul;
	}

	public List<Ksiazka> getFullList(){
		return ksiazkaDAO.getFullList();
	}

	public List<Ksiazka> getList(){
		List<Ksiazka> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (tytul != null && tytul.length() > 0){
			searchParams.put("tytul", tytul);
		}
		
		//2. Get list
		list =ksiazkaDAO.getList(searchParams);
		
		return list;
	}

	public String newKsiazka(){
		Ksiazka ksiazka = new Ksiazka();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("ksiazka", ksiazka);
		
		return PAGE_BOOK_EDIT;
	}

	public String editPerson(Ksiazka ksiazka){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("ksiazka", ksiazka);
		
		return PAGE_BOOK_EDIT;
	}

	public String deletePerson(Ksiazka ksiazka){
		ksiazkaDAO.remove(ksiazka);
		return PAGE_STAY_AT_THE_SAME;
	}
		
	public String powrot(){
		return "login";
	}

}