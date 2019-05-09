package authentication;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import backend.Pontok;
import newfile.DbManager;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	
		@EJB
		private DbManager dm;

		private String dolgozokod;
		
		private String nev;
		
		private int pont;
		private int szavazat;
		
		private boolean loggedIn;
		
		private boolean admin;
		
		@Inject
		private NavigationBean nav;
		
		public String doLogin(){
			if(dolgozokod.matches("[zZ][0-9]{5}")){
				dolgozokod=dolgozokod.substring(1);
			}
			Dolgozo dolgozo=dm.findDolgozo(dolgozokod);
			if(dolgozo!=null){ //&&this.Pass.equals(pass)){
					nev=dolgozo.getNev();
					loggedIn=true;
					Pontok p=dm.findPontok(dolgozo.getAdoszam());
					if(p!=null){
					pont=p.getKapott();
					szavazat=p.getSzavazat();
					}
					if(dolgozo.getAdmin()==1){
						this.admin=true;
						return nav.redirectToAdmin();
					} 
					else{
						this.admin=false;
						return nav.redirectToNewFile();
					}
			}
			FacesMessage msg=new FacesMessage("Hibás kód!", "Bejelentkezési_Hiba");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return nav.toLogin();
		}
		
		public String doAtugor(){
			FacesMessage msg=new FacesMessage("Bejelentkezés átugrása", "Bejelentkezési_kihagyva");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return nav.toLogin();
		}
		
		public void doLogout() throws IOException{
			loggedIn = false;
			FacesMessage msg=new FacesMessage("Sikeres kijelentkezés");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			dolgozokod="";
			nav.toBDE();
		}

		public String getNev() {
			return nev;
		}

		public void setNev(String nev) {
			this.nev = nev;
		}

		public boolean isLoggedIn() {
			return loggedIn;
		}

		public void setLoggedIn(boolean loggedIn) {
			this.loggedIn = loggedIn;
		}

		public String getDolgozokod() {
			return dolgozokod;
		}

		public void setDolgozokod(String dolgozokod) {
			this.dolgozokod = dolgozokod;
		}

		public boolean isAdmin() {
			return admin;
		}

		public int getPont() {
			return pont;
		}

		public void setPont(int pont) {
			this.pont = pont;
		}

		public int getSzavazat() {
			return szavazat;
		}

		public void setSzavazat(int szavazat) {
			this.szavazat = szavazat;
		}
}
