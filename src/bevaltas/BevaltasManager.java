package bevaltas;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BevaltasManager {

	@PersistenceContext(unitName="Szavazas")
	private EntityManager em;
	
	public Nyer storeNyer(Nyer n) {
		try {
			em.persist(n);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
}
