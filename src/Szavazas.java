import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class Szavazas implements Serializable {
	
	private Date date1; 
	
	private IState iState;

	public Szavazas(){
		this.iState=new ZarvaState(this);
		userek=new ArrayList<User>();
		userek.add(new User("gábor",1));
		userek.add(new User("andi",1));
		userek.add(new User("bandi",1));
		userek.add(new User("nándor",1));
		userek.add(new User("szandi",1));
		userek.add(new User("gáborka",1));
		userek.add(new User("ubul",1));
	}
	
	public void requestNyit(){
		this.iState.nyit();
	}
	
	public void requestZar(){
		this.iState.zar();
	}
	
	public void requestSzavaz(User u){
		this.iState.szavaz(u);
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
		userek=new ArrayList<User>();
		userek.add(new User("gábor",1));
		userek.add(new User("andi",1));
		userek.add(new User("bandi",1));
		userek.add(new User("nándor",1));
		userek.add(new User("szandi",1));
		userek.add(new User("gáborka",1));
		userek.add(new User("ubul",1));
	}
}
