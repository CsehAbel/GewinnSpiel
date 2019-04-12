package backend;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import newfile.DbManager;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@ManagedBean
@javax.enterprise.context.ApplicationScoped
public class Szavazas implements Serializable {
	
	private LocalDate nyitas; 
	
	private LocalDate zaras;
	
	private IState iState;
	
	@PersistenceContext(unitName="Szavazas")
	private EntityManager em;

	public Szavazas(){
		this.iState=new NyitvaState(this);
		nyitas=LocalDate.now();
		zaras=nyitas.plusDays(10);
	}
	
	public void requestNyit(){
		this.iState.nyit();
		nyitas=LocalDate.now();
	}
	
	public void requestZar(){
		this.iState.zar();
		zaras=LocalDate.now();	
	}
	
	public boolean requestSzavaz(String veszit,String kap,String ki,String  kire){
		//System.out.println("iState: "+iState.getClass().getName());
		if(iState instanceof NyitvaState){
			return szavaz(veszit, kap,ki,kire);
		} else{
			return false;
		}
	}
	
	public boolean szavaz(String veszit,String kap,String ki,String kire) {
		
		Pontok v=null;
		Query query;
		System.out.println(em==null ? "null az em":"nem null az em");
		query=em.createQuery("FROM Pontok d WHERE d.adoszam LIKE :k");
		query.setParameter("k", veszit);
		try{v=(Pontok) query.getSingleResult();
		}catch(NoResultException ex){
			System.out.println("nincs ilyen adoszammal rekord a pontok táblában");
			return false;
		}
		
		Pontok k=null;
		query=em.createQuery("FROM Pontok d WHERE d.adoszam LIKE :k");
		query.setParameter("k", kap);
		try{k=(Pontok) query.getSingleResult();
		}catch(NoResultException ex){
			System.out.println("nincs ilyen adoszammal rekord a pontok táblában");
			return false;
		}
		if(v.getSzavazat()>0 && v.getAdoszam()!=k.getAdoszam()){
			v.setSzavazat(v.getSzavazat()-1);
			em.merge(v);
			k.setKapott(k.getKapott()+1);
			em.merge(k);
			
			Kikire kikire=new Kikire();
			kikire.setKi(ki);
			kikire.setKire(kire);
			em.merge(kikire);
	
			return true;
		}
		return false;
	}
	
	public void changeState(IState iState){
		this.iState=iState;
	}
	
	public String getIState(){
		return iState.toString();
	}

	public LocalDate getNyitas() {
		return nyitas;
	}

	public void setNyitas(LocalDate nyitas) {
		this.nyitas = nyitas;
	}

	public LocalDate getZaras() {
		return zaras;
	}

	public void setZaras(LocalDate zaras) {
		this.zaras = zaras;
	}

	
	
	/*public void clear(){
		dbManager.nullazas();
	}*/
}
