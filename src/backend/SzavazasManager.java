package backend;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

@Stateless
public class SzavazasManager {

	@PersistenceContext(unitName="Szavazas")
	private EntityManager em;
	
	public Pontok getPontok(String veszit) {
		
		Pontok p=null;
		Query query;
		query=em.createQuery("FROM Pontok d WHERE d.adoszam LIKE :k");
		query.setParameter("k", veszit);
		try{p=(Pontok) query.getSingleResult();
		}
		catch(NoResultException ex){
			ex.printStackTrace();
		}
		return p;
	}
	
	public void decrKapott(Pontok v,Pontok k) {
		try {
			em.merge(v);
			em.merge(k);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void storeKikire(Kikire kikire){
		try {
			
			em.merge(kikire);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
