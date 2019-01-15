
public class NyitvaState implements IState {
	
	private Szavazas szavazas;
	
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
	public void szavaz(User u) {
		// TODO Auto-generated method stub
		if(u.getSzavazatok()>0){
		u.setSzavazatok(u.getSzavazatok()-1);
		int gabor=szavazas.getUserek().indexOf(new User("gábor",1));
		szavazas.getUserek().get(gabor).setKapott(szavazas.getUserek().get(gabor).getKapott()+1);;
		}
	}
}
