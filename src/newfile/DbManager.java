package newfile;

import java.io.IOException;
import java.io.OutputStream;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;

import authentication.Dolgozo;
import authentication.LoginBean;
import backend.Pontok;
import backend.Szavazas;
import megerosites.Xsw;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Named
@Stateless
public class DbManager {

	@PersistenceContext(unitName="Szavazas")
	private EntityManager em;
	
	@Inject
	private LoginBean loginBean;
	
	@Inject
	private Szavazas szavazas;
	
	public List<Dolgozo> userek;
	
	@PostConstruct
	public void init(){
		userek=allDolgozo();
	}
	
	public Dolgozo findDolgozo(String dolgozokod){
		Dolgozo d=null;
		Query query;
		query=em.createQuery("FROM Dolgozo d WHERE d.torzsszam LIKE :k");
		query.setParameter("k", dolgozokod);
		try{d=(Dolgozo) query.getSingleResult();
		}catch(NoResultException ex){
			System.out.println("nincs dolgozókód");
		}
		return d;
	}
	
	public Pontok findPontok(String adoszam){
		Pontok d=null;
		Query query;
		query=em.createQuery("FROM Pontok d WHERE d.adoszam LIKE :k");
		query.setParameter("k", adoszam);
		try{d=(Pontok) query.getSingleResult();
		}catch(NoResultException ex){
			System.out.println("nincs xsw");
		}
		return d;
	}
	
	public List<Dolgozo> allDolgozo(){
		Query query;
		query=em.createQuery("FROM Dolgozo AS d");
		return (List<Dolgozo>) query.getResultList();
	}
	
	/*public void nullazas(){
		Query query;
		query = em.createQuery("UPDATE csv set szavazat = :szavazat, kapott = :kapott");
		query.setParameter("szavazat", 1);
		query.setParameter("kapott", 0);
		int db=query.executeUpdate();
		System.out.println("update-elt sorok:"+db+"db");
	}*/
		
	public List<SelectItem> getTeruletek(){
		
		Query query= em.createNativeQuery("SELECT DISTINCT d.uzemegyseg FROM Dolgozo d");
		List<String> list = (List<String>) query.getResultList();
		List<SelectItem> si=new ArrayList<SelectItem>();
		for(String s:list){
			si.add(new SelectItem(s,s));
		}
		return si;
	}
	
	public List<SelectItem> getBeosztasok(){
		
		Query query= em.createNativeQuery("SELECT DISTINCT d.munkakor FROM Dolgozo d");
		List<String> list = (List<String>) query.getResultList();
		List<SelectItem> si=new ArrayList<SelectItem>();
		for(String s:list){
			si.add(new SelectItem(s,s));
		}
		return si;
	}
	
	//különbözõ kezdõbetûket összegyûjti
	public List<String> getAbc() {
		Set<String> hashSet=new HashSet<>();
		for(Dolgozo u:userek){
			hashSet.add(u.getNev().substring(0,1));
		}
		List<String> abc=new ArrayList<>(hashSet);
		
		Collator huCollator = Collator.getInstance(new Locale("hu", "HU")); //Your locale here
		huCollator.setStrength(Collator.PRIMARY); //desired strength
		Collections.sort(abc, huCollator);
		return abc;
	}

	public List<Dolgozo> getUserek() {
		return userek;
	}

	public void setUserek(List<Dolgozo> userek) {
		this.userek = userek;
	}
}
