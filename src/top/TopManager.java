package top;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TopManager {
	
	@PersistenceContext(name="szavazas")
	private EntityManager em;
	
	public List<Object[]> getTop15(){
		Query q=em.createNativeQuery("SELECT d.nev,d.torzsszam,d.uzemegyseg,d.munkakor,p.kapott FROM dolgozo d JOIN pontok p ON d.adoszam=p.adoszam ORDER BY p.kapott DESC LIMIT 15");
		return q.getResultList();
	}

}
