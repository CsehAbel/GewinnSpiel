package adminhome;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import authentication.Dolgozo;
import authentication.LoginBean;
import backend.Pontok;
import newfile.DbManager;

@ManagedBean
@SessionScoped
public class AdminHandler {
	
	@Inject
	private Levon lev;
	
	@Inject
	private LoginBean lb;

	@EJB
	private AdminManager am;
	
	@EJB
	private DbManager dm;
	
	public void exportPontok() {
		try {
	        String filename = "pontok.csv";

	        FacesContext fc = FacesContext.getCurrentInstance();
	        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

	        response.reset();
	        response.setContentType("text/comma-separated-values");
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

	        OutputStream output = response.getOutputStream();

	        // writing just sample data
	        List<String> strings = new ArrayList<String>();
	        
	        
			for(Object[] o:am.getPontok()){
	        	strings.add(o[0].toString()+";"+o[1]+";"+o[2]+"\n");
	        }
	        
	        for (String s : strings) {
	            output.write(s.getBytes());
	        }

	        output.flush();
	        output.close();

	        fc.responseComplete();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void exportKikire() {
		try {
	        String filename = "kikire.csv";

	        FacesContext fc = FacesContext.getCurrentInstance();
	        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

	        response.reset();
	        response.setContentType("text/comma-separated-values");
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

	        OutputStream output = response.getOutputStream();

	        // writing just sample data
	        List<String> strings = new ArrayList<String>();
	        
			for(Object[] o:am.getKikire()){
	        	strings.add(o[0].toString()+";"+o[1]+"\n");
	        }
	        
	        for (String s : strings) {
	            output.write(s.getBytes());
	        }

	        output.flush();
	        output.close();

	        fc.responseComplete();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void levonpont(String i1,String i2){
		//torzsszamhoz meg kell kapni az adoszamot majd a Pontok oszt�lyt m�dos�tani �s em.mergel elni, ut�na FacesMessage-ben sikeress�gr�l visszaigazol�st
		System.out.println("AdminHandler:"+lev.getKitol());
		Dolgozo d=dm.findDolgozo(i1);//lev.getKitol());
		if(d==null){
			FacesMessage hiba=new FacesMessage("Megadott dolgoz�k�d nincs az adatb�zisban.", "Rossz dkod");
			hiba.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, hiba);
			return;
		}
		Pontok p=dm.findPontok(d.getAdoszam());
		if(!i2.matches("^[-\\+]?[0-9]{1,4}$")){
			FacesMessage hiba=new FacesMessage("Pontok form�tuma rossz.", "Rossz pont");
			hiba.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, hiba);
			return;
		}
		int levontpont=Integer.parseInt(i2);
		p.setKapott(((p.getKapott()-levontpont)>=0) ? (p.getKapott()-levontpont) : 0 );
		if(p.getKapott()<levontpont){
			FacesMessage hiba=new FacesMessage("Levont pontok t�bb mint a munkav�llal��", "Rossz pontlevonas");
			hiba.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, hiba);
		}
		dm.mergePontok(p);
		lev.setFtorzsszam(lb.getDolgozokod());
		am.saveLevon(lev.cloneLevon());
		
		FacesMessage hiba=new FacesMessage("V�ltozott: "+d.getNev()+" ("+d.getTorzsszam()+") "+p.getKapott()+"pont", "Jo pontlevonas");
		hiba.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, hiba);	
	}
}
