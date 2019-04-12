package authentication;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class NavigationBean implements Serializable {
	
	public String toLogin(){
		return "/Login.jsf";
	}
	
	public String redirectToNewFile(){
		return "/secure/NewFile.jsf?faces-redirect=true";
	}

	public String redirectToAdmin() {
		// TODO Auto-generated method stub
		return "/secure/AdminHome.jsf?faces-redirect=true";
	}
	
	
	public String redirectToKepek() {
		// TODO Auto-generated method stub
		return "/secure/Kepek.jsf?faces-redirect=true";
	}
	
	public String redirectToBevaltas() {
		// TODO Auto-generated method stub
		return "/secure/Bevaltas2.jsf?faces-redirect=true";
	}
	
	public String redirectToTop(){
		return "/secure/Top.jsf?faces-redirect=true";
	}
	
	public String redirectToMegerosites(){
		return "/secure/Megerosites.jsf?faces-redirect=true";
	}
	
	public String redirectToFilterABC(){
		return "/FilterABC.jsf";
	}
}