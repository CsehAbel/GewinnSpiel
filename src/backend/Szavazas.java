package backend;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

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
	
	@EJB
	private SzavazasManager sm;

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
		v=sm.getPontok(veszit);
		if(v==null) {
			FacesMessage msg=new FacesMessage("Nincs a szavazatot adó adoszáma az adatbázisban!", "veszit nincs pontok ban");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return false;
		}
		
		Pontok k=null;
		k=sm.getPontok(kap);
		if(k==null) {
			FacesMessage msg=new FacesMessage("Nincs a szavazatot kapó adoszáma az adatbázisban!", "kap nincs a pontok");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return false;
		}
		if(v.getSzavazat()>0 && !v.getAdoszam().equals(k.getAdoszam())){
			v.setSzavazat(v.getSzavazat()-1);
			k.setKapott(k.getKapott()+1);
			sm.decrKapott(v,k);
			
			
			Kikire kikire=new Kikire();
			kikire.setKi(ki);
			kikire.setKire(kire);
			sm.storeKikire(kikire);
	
			return true;
		} else {
			if(!(0<v.getSzavazat())){
				FacesMessage msg=new FacesMessage("Nincs leadható szavazat!", "veszít getSzavazat<=0");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return false;
			} 
			if(v.getAdoszam().equals(k.getAdoszam())) {
				FacesMessage msg=new FacesMessage("Nem szavazhatsz magadra!", "v getAdoszam = k getAdoszam");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return false;
			}
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
