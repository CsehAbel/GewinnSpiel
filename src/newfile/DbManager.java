package newfile;

import java.text.Collator;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;

import authentication.LoginBean;
import authentication.User;
import backend.Szavazas;
import megerosites.xsw;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.primefaces.component.datatable.DataTable;

@Named
@Stateless
public class DbManager {

	@PersistenceContext(unitName="Szavazas")
	private EntityManager em;
	
	@Inject
	private LoginBean loginBean;
	
	@Inject
	private Szavazas szavazas;
	
	private String t;
	
	private String b;
	
	private String ks;
	
	//private String betu;
	
	public List<csv> userek;
	public List<csv> userekNotFiltered;
	
	private csv u;
	
	private String d;
	
	@PostConstruct
	public void init(){
		d="";
		ks="";
		userek=allUser();
		userekNotFiltered=userek;
	}
	
	public void szures(String betu){
		userek=userekNotFiltered;
		List<csv> uj=new ArrayList<>();
		for(csv c:userek){
			if(c.getcNev().substring(0, 1).equals(betu)){
				uj.add(c);
			}
		}
		userekNotFiltered=userek;
		userek=uj;
	}
	
	/*public boolean filterABC(Object value, Object filter, Locale locale) {
		System.out.println("filterABC");
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
        return value.toString().matches("^"+betu+".*");
    }*/
	
	public csv findUser(String dolgozokod){
		csv c=null;
		Query query;
		query=em.createQuery("FROM csv c WHERE c.cDolgozokod = :k");
		query.setParameter("k", dolgozokod);
		try{c=(csv) query.getSingleResult();
		}catch(NoResultException ex){
			System.out.println("nincs dolgozókód");
		}
		return c;
	}
	public xsw findXsw(){
		xsw c=null;
		Query query;
		query=em.createQuery("FROM xsw x WHERE x.xKartyaszam = :x");
		query.setParameter("x", Integer.parseInt(ks));
		try{c=(xsw)query.getSingleResult();
		System.out.println("xsw:"+c.getxDolgozokod());
		}catch(NoResultException ex){
			System.out.println(ks+" xsw nem talált.");
		}
		return c;
	}
	
	public List<csv> allUser(){
		Query query;
		query=em.createQuery("FROM csv AS c");
		return (List<csv>) query.getResultList();
	}
	
	public void updateKapott(int kapId){
		csv kap=em.find(csv.class,kapId);
		kap.setKapott(kap.getKapott()+1);
		FacesMessage msg=new FacesMessage(kap.getcNev()+","+kap.getcDolgozokod()+" pontot kapott.", "Kapott");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		em.merge(kap);
	}
	
	public void updateSzavazat(int veszitId){
		csv veszit=em.find(csv.class, veszitId);
		veszit.setSzavazat(veszit.getSzavazat()-1);
		FacesMessage msg=new FacesMessage(veszit.getcNev()+","+veszit.getcDolgozokod()+" pontot veszített.", "Veszített");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		em.merge(veszit);
	}
	
	public void concd(int i){
		d=""+d+""+i;
		System.out.println("d:"+d);
		if(d.length()==5){
			if(findUser(d)!=null){
				userek=new ArrayList<>();
				userek.add(findUser(d));
			} else{
				FacesMessage msg=new FacesMessage("Nincs ilyen kód!");
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
			xsw xsw1=findXsw();
			if(xsw1!=null && xsw1.getxDolgozokod()==Integer.parseInt(loginBean.getDolgozokod()) ){
				System.out.println("if(xsw1!=null && xsw1.getxDolgozokod()==Integer.parseInt(loginBean.getDolgozokod()) ){");
				csv veszit=findUser(loginBean.getDolgozokod());
				if(veszit.getSzavazat()>0){
					System.out.println("if(veszit.getSzavazat()>0)");
					updateKapott(u.getcId());
					updateSzavazat(veszit.getcId());
				} else{
					System.out.println("1} else{");
					FacesMessage msg=new FacesMessage("Nincs több adható szavazat!", "Nincs pont");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					FacesContext.getCurrentInstance().addMessage(null, msg);
				}
			} else{
				System.out.println("2} else{");
				FacesMessage msg=new FacesMessage("Hibás kártyaszám!", "Kártyaszám");
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
		query = em.createQuery("FROM csv AS c where c.cTerulet = :t ORDER BY c.cNev");
		List<csv> list=new ArrayList<>();
		if(t!=null){
			query.setParameter("t", t);
			list= (List<csv>) query.getResultList();	
		} else {
			query.setParameter("t", getTeruletekNemSelectItem().get(0));
			list= (List<csv>) query.getResultList();
		}
		userek=list;
		userekNotFiltered=list;
		b=null;
	}
	
	public void bvalaszt(){
		if(b!=null){
		System.out.println("bvalaszt");
		Query query;
		query = em.createQuery("FROM csv AS c where c.cTerulet = :t AND c.cBeosztas = :b ORDER BY c.cNev");
		if(t!=null){
			query.setParameter("t", t);
			query.setParameter("b", b);
			List<csv> list= (List<csv>) query.getResultList();
			userek=list;
			userekNotFiltered=list;
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
	
	//különbözõ kezdõbetûket összegyûjti
	public List<String> getAbc() {
		Set<String> hashSet=new HashSet<>();
		for(csv u:userek){
			hashSet.add(u.getcNev().substring(0,1));
		}
		List<String> abc=new ArrayList<>(hashSet);
		
		Collator huCollator = Collator.getInstance(new Locale("hu", "HU")); //Your locale here
		huCollator.setStrength(Collator.PRIMARY); //desired strength
		Collections.sort(abc, huCollator);
		return abc;
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

	public List<csv> getUserekNotFiltered() {
		return userekNotFiltered;
	}

	public void setUserekNotFiltered(List<csv> userekNotFiltered) {
		this.userekNotFiltered = userekNotFiltered;
	}

	/*public String getBetu() {
		return betu;
	}

	public void setBetu(String betu) {
		this.betu = betu;
	}*/

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

	public csv getU() {
		return u;
	}

	public void setU(csv u) {
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
