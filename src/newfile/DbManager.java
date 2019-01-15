package newfile;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless
public class DbManager {

	@PersistenceContext(unitName="Szavazas")
	private EntityManager em;
	
	private String t;
	
	private String b;
	
	public List<csv> getUserek(){
		
		Query query = em.createNativeQuery("FROM csv AS c where c.cTerulet = :t AND c.cBeosztas = :b");
		query.setParameter("t", t);
		query.setParameter("b", b);
		List<csv> list= (List<csv>) query.getResultList();
		return list;
	}
	
	public List<SelectItem> getTeruletek(){
		
		Query query= em.createNativeQuery("SELECT DISTINCT k.cTerulet FROM csv k");
		List<String> list = (List<String>) query.getResultList();
		List<SelectItem> si=new ArrayList<SelectItem>();
		for(String s:list){
			si.add(new SelectItem(s,s));
			System.out.println(s);
		}
		return si;
	}
	
	public List<SelectItem> getBeosztasok(){
		if(t!=null){
		Query query= em.createNativeQuery("SELECT DISTINCT k.cBeosztas FROM csv k where cTerulet = :pT");
		query.setParameter("pT",t);
		List<String> list = (List<String>) query.getResultList();
		List<SelectItem> si=new ArrayList<SelectItem>();
		for(String s:list){
			si.add(new SelectItem(s,s));
			System.out.println(s);
		}
		return si;}
		else{ return null;}
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}
}
