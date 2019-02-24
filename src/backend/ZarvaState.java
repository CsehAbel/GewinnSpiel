package backend;


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
	public boolean szavaz(String veszit,String kap,String ki,String kire) {
		// TODO Auto-generated method stub
		return false;
	}

}
