package bevaltas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.transaction.UserTransaction;

import authentication.LoginBean;
import kepek.Kep;

@ManagedBean
@SessionScoped
public class BevaltasHandler {
	
	@EJB
	private BevaltasManager bm;

	
	public Nyer storeNyer(String torzssz, int dpont,int nyeremenyid,int nyeremenypontja){
			Nyer nyer=new Nyer();
			nyer.setTorzsszam(torzssz);
			nyer.setDat(LocalDateTime.now());
			nyer.setPont(dpont);
			nyer.setNyid(nyeremenyid);
			nyer.setNypont(nyeremenypontja);
			
			return bm.storeNyer(nyer);
	}
	
	@Inject
	private LoginBean lb;
	
	public void bevaltas(int nyeremenyid,String nyeremenypontja,String leiras){
		System.out.println("Meghívva");
		String torzs=lb.getDolgozokod();
		String dnev=lb.getNev();
		int dpont=lb.getPont();
		int nypont=Integer.parseInt(nyeremenypontja);
		List<String> sent=new ArrayList<String>();
		if(dpont>=nypont){
			System.out.println("lel");
			String subj=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"))+" Nyeremény igény";
			String msg=dnev+"("+torzs+")"+": \r\n"+"\tNyeremenyid: "+nyeremenyid+"\r\n\t"+leiras;
			
			if(SendFromExchange.send(sent, true, "StromUV","Abcd123456","StromUV@ziehl-abegg.hu", "pflanczer.barbara@ziehl-abegg.hu", "kecse.abel@ziehl-abegg.hu", subj, msg)){
				this.storeNyer(torzs, dpont, nyeremenyid, nypont);
				FacesMessage hiba=new FacesMessage("Nyeremény igény elküldve a HR osztálynak!", "Email elküdlve");
				hiba.setSeverity(FacesMessage.SEVERITY_INFO);
				FacesContext.getCurrentInstance().addMessage(null, hiba);
			} else{
				FacesMessage hiba=new FacesMessage("Nyeremény igény továbbítási hiba!", "Email gescheitert");
				hiba.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, hiba);
			}
		} else{
			FacesMessage hiba=new FacesMessage("Nincs elég kapott pontod ehhez a nyereményhez!", "Nincs elég pont");
			hiba.setSeverity(FacesMessage.SEVERITY_INFO);
			FacesContext.getCurrentInstance().addMessage(null, hiba);
		}
	}
}
