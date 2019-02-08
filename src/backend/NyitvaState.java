package backend;
import javax.ejb.EJB;
import javax.inject.Inject;

import authentication.LoginBean;
import newfile.DbManager;

public class NyitvaState implements IState {
	
	private Szavazas szavazas;
	
	@EJB
	private DbManager dbManager;
	
	@Inject
	private LoginBean loginBean;
	
	public NyitvaState(Szavazas szavazas){
		this.szavazas=szavazas;
	}

	@Override
	public void nyit() {
		// TODO Auto-generated method stub
	}

	@Override
	public void zar() {
		// TODO Auto-generated method stub
		szavazas.changeState(new ZarvaState(szavazas));
	}

	@Override
	public String toString() {
		return "Nyitva";
	}


	@Override
	public void szavaz(csv t) {
		csv c=dbManager.findUser(loginBean.getDolgozokod());
		if(c.getSzavazat()>0){
		c.setSzavazat(c.getSzavazat()-1);
		//SET oszlophoz hozzáadás
		System.out.println("setkapott");
		t.setKapott(t.getKapott()+1);
		}
	}
}
