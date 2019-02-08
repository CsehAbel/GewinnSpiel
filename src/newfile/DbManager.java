package newfile;

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
	
	/*@Inject
	private Szavazas szavazas;*/
	
	private String t;
	
	private String b;
	
	private String ks;
	
	//private String betu;
	
	public List<Dolgozo> userek;
	
	private Dolgozo u;
	
	private String d;
	
	@PostConstruct
	public void init(){
		d="";
		ks="";
		userek=allDolgozo();
	}
	
	public Dolgozo findDolgozo(String dolgozokod){
		Dolgozo d=null;
		Query query;
		query=em.createQuery("FROM Dolgozo d WHERE d.torzsszam LIKE :k");
		query.setParameter("k", dolgozokod);
		try{d=(Dolgozo) query.getSingleResult();
		}catch(NoResultException ex){
			System.out.println("nincs dolgoz�k�d");
		}
		return d;
	}
	
	public Pontok findPontok(String adoszam){
		Pontok d=null;
		Query query;
		query=em.createQuery("FROM Pontok d WHERE d.pontok LIKE :k");
		query.setParameter("k", adoszam);
		try{d=(Pontok) query.getSingleResult();
		}catch(NoResultException ex){
			System.out.println("nincs xsw");
		}
		return d;
	}
	
	public Xsw findXsw(){
		Xsw c=null;
		Query query;
		query=em.createQuery("FROM Xsw x WHERE x.kartyaszam = :x");
		query.setParameter("x", Integer.parseInt(ks));
		try{c=(Xsw)query.getSingleResult();
		System.out.println("xsw:"+c.getDolgozokod());
		}catch(NoResultException ex){
			System.out.println(ks+" xsw nem tal�lt.");
		}
		return c;
	}
	
	public List<Dolgozo> allDolgozo(){
		Query query;
		query=em.createQuery("FROM Dolgozo AS d");
		return (List<Dolgozo>) query.getResultList();
	}
	
	public void updateKapott(String adoszam){
		Pontok kap=findPontok(adoszam);
		kap.setKapott(kap.getKapott()+1);
		/*FacesMessage msg=new FacesMessage(kap.getcNev()+","+kap.getcDolgozokod()+" pontot kapott.", "Kapott");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);*/
		em.merge(kap);
	}
	
	public void updateSzavazat(String adoszam){
		Pontok veszit=findPontok(adoszam);
		veszit.setSzavazat(veszit.getSzavazat()-1);
		/*FacesMessage msg=new FacesMessage(veszit.getcNev()+","+veszit.getcDolgozokod()+" pontot vesz�tett.", "Vesz�tett");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);*/
		em.merge(veszit);
	}
	
	public void concd(int i){
		d=""+d+""+i;
		System.out.println("d:"+d);
		if(d.length()==5){
			if(findDolgozo(d)!=null){
				userek=new ArrayList<>();
				userek.add(findDolgozo(d));
			} else{
				FacesMessage msg=new FacesMessage("Nincs ilyen k�d!");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			d="";
		}
	}
	
	public void concks(int i){
		ks=""+ks+""+i;
		System.out.println("ks:"+ks);
		if(ks.length()==5){
			Xsw xsw1=findXsw();
			if(xsw1!=null && Integer.parseInt(xsw1.getDolgozokod())==Integer.parseInt(loginBean.getDolgozokod()) ){
				
				Pontok veszit=findPontok(findDolgozo(loginBean.getDolgozokod()).getAdoszam());
				if(veszit.getSzavazat()>0){
					System.out.println("if(veszit.getSzavazat()>0)");
					updateKapott(u.getAdoszam());
					updateSzavazat(veszit.getAdoszam());
				} else{
					System.out.println("1} else{");
					FacesMessage msg=new FacesMessage("Nincs t�bb adhat� szavazat!", "Nincs pont");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			} else{
				System.out.println("2} else{");
				FacesMessage msg=new FacesMessage("Hib�s k�rtyasz�m!", "K�rtyasz�m");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			ks="";
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
	
	public void backsks(){
		String str="";
		ks=str;
	}
	
	public void valaszt(){
		System.out.println("valaszt");
		Query query;
		query = em.createQuery("FROM Dolgozo AS d where d.uzemegyseg = :t ORDER BY d.nev");
		List<Dolgozo> list=new ArrayList<>();
		if(t!=null){
			query.setParameter("t", t);
			list= (List<Dolgozo>) query.getResultList();	
		} else {
			query.setParameter("t", getTeruletekNemSelectItem().get(0));
			list= (List<Dolgozo>) query.getResultList();
		}
		userek=list;
		b=null;
	}
	
	public void bvalaszt(){
		if(b!=null){
		System.out.println("bvalaszt");
		Query query;
		query = em.createQuery("FROM Dolgozo AS d where d.uzemegyseg = :t AND d.munkakor = :b ORDER BY d.nev");
		if(t!=null){
			query.setParameter("t", t);
			query.setParameter("b", b);
			List<Dolgozo> list= (List<Dolgozo>) query.getResultList();
			userek=list;
		} else{
			userek=new ArrayList<>();
		}
		}
		//return "submit()";
	}
	
	
	/*public void nullazas(){
		Query query;
		query = em.createQuery("UPDATE csv set szavazat = :szavazat, kapott = :kapott");
		query.setParameter("szavazat", 1);
		query.setParameter("kapott", 0);
		int db=query.executeUpdate();
		System.out.println("update-elt sorok:"+db+"db");
	}*/
	
	public String csillagks(){
		
		String s="";
		
		for(int i=0;i<ks.length();i++){
			s+="*";
		}
		
		return s;
	}
		
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
		if(t!=null){
		Query query= em.createNativeQuery("SELECT DISTINCT d.munkakor FROM Dolgozo d where uzemegyseg = :pT");
		query.setParameter("pT",t);
		List<String> list = (List<String>) query.getResultList();
		List<SelectItem> si=new ArrayList<SelectItem>();
		for(String s:list){
			si.add(new SelectItem(s,s));
		}
		return si;}
		else{ return null;}
	}
	
	//k�l�nb�z� kezd�bet�ket �sszegy�jti
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

	public List<String> getBeosztasokNemSelectItem(){
		if(t!=null){
			Query query= em.createNativeQuery("SELECT DISTINCT d.munkakor FROM Dolgozo d where uzemegyseg = :pT");
			query.setParameter("pT",t);
			List<String> list = (List<String>) query.getResultList();
			return list;
		} else{
			return null;
		}
	}
	
	public List<String> getTeruletekNemSelectItem(){
			
			Query query= em.createNativeQuery("SELECT DISTINCT d.uzemegyseg FROM Dolgozo d");
			List<String> list = (List<String>) query.getResultList();
			return list;
		}

	/*public String getBetu() {
		return betu;
	}

	public void setBetu(String betu) {
		this.betu = betu;
	}*/

	public List<Dolgozo> getUserek() {
		return userek;
	}

	public void setUserek(List<Dolgozo> userek) {
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

	public Dolgozo getU() {
		return u;
	}

	public void setU(Dolgozo u) {
		this.u = u;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getKs() {
		return ks;
	}

	public void setKs(String ks) {
		this.ks = ks;
	}
}
