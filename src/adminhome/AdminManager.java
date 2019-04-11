package adminhome;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;

import backend.Kikire;
import backend.Pontok;

@Named
@ManagedBean
@SessionScoped
public class AdminManager {

	@PersistenceContext(unitName="Szavazas")
	private EntityManager em;
	
	private int pontlevonas;
	
	private String dkod;
	
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
	        
	        Query query;
			query=em.createNativeQuery("SELECT d.nev,d.torzsszam,SUM(p.kapott) as kap FROM pontok p JOIN dolgozo d ON p.adoszam=d.adoszam group by p.adoszam ORDER BY kap DESC");
	        
			for(Object[] o:(List<Object[]>) query.getResultList()){
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
	        
	        Query query;
			query=em.createNativeQuery("SELECT ki,kire FROM kikire ORDER BY ki ASC;");
	        
			for(Object[] o:(List<Object[]>) query.getResultList()){
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
	
	public void levonpont(int p,String torzsszam){
		//torzsszamhoz meg kell kapni az adoszamot majd a Pontok osztályt módosítani és em.mergel elni, utána FacesMessage-ben sikerességrõl visszaigazolást
		
			em.merge(k);
			
			
	}

	public int getPontlevonas() {
		return pontlevonas;
	}

	public void setPontlevonas(int pontlevonas) {
		this.pontlevonas = pontlevonas;
	}

	public String getDkod() {
		return dkod;
	}

	public void setDkod(String dkod) {
		this.dkod = dkod;
	}
}
