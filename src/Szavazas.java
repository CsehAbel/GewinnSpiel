import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import newfile.DbManager;
import newfile.csv;

import java.io.Serializable;
import java.util.Date;

@Named
@SessionScoped
public class Szavazas implements Serializable {
	
	private Date date1; 
	
	private IState iState;
	
	@EJB
	private DbManager dbManager;

	public Szavazas(){
		this.iState=new ZarvaState(this);
	}
	
	public void requestNyit(){
		this.iState.nyit();
	}
	
	public void requestZar(){
		this.iState.zar();
	}
	
	public void requestSzavaz(csv t){
		//System.out.println("iState: "+iState.getClass().getName());
		iState.szavaz(t);
		
	}
	
	public void changeState(IState iState){
		this.iState=iState;
	}
	
	public String getIState(){
		return iState.toString();
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	
	public void clear(){
		dbManager.nullazas();
	}
}
