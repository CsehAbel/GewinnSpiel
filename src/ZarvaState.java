
public class ZarvaState implements IState {
	
	private Szavazas szavazas;
	
	public ZarvaState(Szavazas szavazas){
		this.szavazas=szavazas;
	}

	@Override
	public void nyit() {
		// TODO Auto-generated method stub
		this.szavazas.changeState(new NyitvaState(this.szavazas));
		this.szavazas.clear();
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
	public void szavaz(User u) {
		// TODO Auto-generated method stub
	}

}
