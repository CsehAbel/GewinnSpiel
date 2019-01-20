package newfile;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.Query;

import authentication.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Named
@Stateless
public class DbManager {

	@PersistenceContext(unitName="Szavazas")
	private EntityManager em;
	
	private String t;
	
	public List<csv> userek;
	
	private String b;
	
	private String d;
	
	@PostConstruct
	public void init(){
		d="";
	}
	
	public csv findUser(String dolgozokod){
		csv c=null;
		Query query;
		query=em.createQuery("FROM csv c WHERE c.cDolgozokod = :k");
		query.setParameter("k", dolgozokod);
		try{c=(csv) query.getSingleResult();
		}catch(NoResultException ex){
			
		}
		return c;
	}
	
	public List<csv> allUser(){
		Query query;
		query=em.createQuery("FROM csv AS c ORDER BY c.kapott");
		return (List<csv>) query.getResultList();
	}
	
	public void concd(int i){
		d=""+d+""+i;
		System.out.println("d:"+d);
		if(d.length()==5){
			Query query;
			query = em.createNamedQuery("find_user");
			query.setParameter("k", d);
			List<csv> list= (List<csv>) query.getResultList();
			userek=list;
			//System.out.println(userek.get(0).getcNev());
			d="";
		}
		
	}
	
	public void backsd(){
		String str="";
		char[] s=d.toCharArray();
		for(int i=0;i<s.length-1;i++){
			str+=s[i];
		}
		d=str;
	}
	
	public String valaszt(){
		if(t==null){
			t=getTeruletekNemSelectItem().get(0);
		}
		if(!getBeosztasokNemSelectItem().contains(b)){
			b=getBeosztasokNemSelectItem().get(0);
		}
		fM();
		return "submit()";
	}
	
	
	public void nullazas(){
		Query query;
		query = em.createQuery("UPDATE csv set szavazat = :szavazat, kapott = :kapott");
		query.setParameter("szavazat", 1);
		query.setParameter("kapott", 0);
		int db=query.executeUpdate();
		System.out.println("update-elt sorok:"+db+"db");
	}
	
	public void fM(){
		Query query;
		query = em.createQuery("FROM csv AS c where c.cTerulet = :t AND c.cBeosztas = :b");
		if(t==null){
			query.setParameter("t", t=getTeruletekNemSelectItem().get(0));
			query.setParameter("b", b=getBeosztasokNemSelectItem().get(0));
			System.out.println("t="+t);
			System.out.println("b="+b);
		} else{
			query.setParameter("t", t);
			query.setParameter("b", b);
			System.out.println("t="+t);
			System.out.println("b="+b);
		}
		List<csv> list= (List<csv>) query.getResultList();
		userek=list;
	}
	
	public List<SelectItem> getTeruletek(){
		
		Query query= em.createNativeQuery("SELECT DISTINCT k.cTerulet FROM csv k");
		List<String> list = (List<String>) query.getResultList();
		List<SelectItem> si=new ArrayList<SelectItem>();
		for(String s:list){
			si.add(new SelectItem(s,s));
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
		}
		return si;}
		else{ return null;}
	}
	
	public List<String> getBeosztasokNemSelectItem(){
		if(t!=null){
			Query query= em.createNativeQuery("SELECT DISTINCT k.cBeosztas FROM csv k where cTerulet = :pT");
			query.setParameter("pT",t);
			List<String> list = (List<String>) query.getResultList();
			return list;
		} else{
			return null;
		}
	}
	
	public List<String> getTeruletekNemSelectItem(){
			
			Query query= em.createNativeQuery("SELECT DISTINCT k.cTerulet FROM csv k");
			List<String> list = (List<String>) query.getResultList();
			return list;
		}

	public List<csv> getUserek() {
		return userek;
	}

	public void setUserek(List<csv> userek) {
		this.userek = userek;
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

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}
}
