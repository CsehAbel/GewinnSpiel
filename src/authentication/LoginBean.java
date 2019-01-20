package authentication;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import newfile.DbManager;
import newfile.csv;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	
		@EJB
		private DbManager dm;

		private String dolgozokod;
		private String nev;
		
		private boolean loggedIn;
		
		private boolean admin;
		
		@Inject
		private NavigationBean nav;
		
		public String doLogin(){
			csv user=dm.findUser(dolgozokod);
			if(user!=null){ //&&this.Pass.equals(pass)){
					nev=user.getcNev();
					loggedIn=true;
					if(user.getAdminn()==1){
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
		
		public String doLogout(){
			loggedIn = false;
			FacesMessage msg=new FacesMessage("Sikeres kijelentkezés");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return nav.toLogin();
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
		
}
