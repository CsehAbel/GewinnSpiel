package backend;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	public boolean szavaz(String veszit,String kap,String ki,String kire) {
		return false;
	}

}
