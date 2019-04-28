package megerosites;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import authentication.Dolgozo;
import authentication.LoginBean;
import backend.Szavazas;

@ManagedBean
@SessionScoped
public class MegerositesHandler {

	@PersistenceContext(unitName="Szavazas")
	private EntityManager em;
	
	@Inject
	private LoginBean loginBean;
	
	@Inject
	private Szavazas szavazas;
	
	private String js=null;
	
	private Dolgozo u;
	
	private String kkod;
	
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
	
	public Xsw findXsw(){
		Xsw c=null;
		Query query;
		query=em.createQuery("FROM Xsw x WHERE x.kartyaszam = :x");
		query.setParameter("x", Integer.parseInt(kkod));
		try{c=(Xsw)query.getSingleResult();
		System.out.println("xsw:"+c.getDolgozokod());
		}catch(NoResultException ex){
			System.out.println(kkod+" xsw nem talált.");
		}
		return c;
	}
	
	public void requestSzavaz(){
			js=null;
			System.out.println("requestSazvaz: "+kkod);
			Xsw xsw1=findXsw();
			if(xsw1!=null && Integer.parseInt(xsw1.getDolgozokod())==Integer.parseInt(loginBean.getDolgozokod()) ){
				
				String veszit=findDolgozo(loginBean.getDolgozokod()).getAdoszam();
				//u = A megerõsítés felületre jutáskor akire kattintott
				String kap=u.getAdoszam();
				if(szavazas.requestSzavaz(veszit,kap,loginBean.getDolgozokod(),u.getTorzsszam())){
						js="mehet";
						return;
				}
			} else{
				FacesMessage msg=new FacesMessage("Hibás kártyaszám!", "Kártyaszám");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		}

	public String getJs() {
		return js;
	}

	public void setJs(String js) {
		this.js = js;
	}

	public Dolgozo getU() {
		return u;
	}

	public void setU(Dolgozo u) {
		System.out.println("u: "+u.getNev());
		this.u = u;
	}

	public String getKkod() {
		return kkod;
	}

	public void setKkod(String kkod) {
		this.kkod = kkod;
	}
}