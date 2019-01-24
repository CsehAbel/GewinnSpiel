package backend;
import newfile.csv;

public class ZarvaState implements IState {
	
	private Szavazas szavazas;
	
	public ZarvaState(Szavazas szavazas){
		this.szavazas=szavazas;
	}

	@Override
	public void nyit() {
		// TODO Auto-generated method stub
		this.szavazas.changeState(new NyitvaState(this.szavazas));
	}

	@Override
	public void zar() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public String toString() {
		return "Zárva";
	}

	@Override
	public void szavaz(csv t) {
		// TODO Auto-generated method stub
	}

}
