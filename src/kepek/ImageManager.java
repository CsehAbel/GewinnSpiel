package kepek;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ImageManager {
	
	@PersistenceContext(unitName="Szavazas")
	private EntityManager em;
	
	public List<Object[]> findKepek(){
		Query query;
		query=em.createNativeQuery("SELECT id,pont,leiras FROM Kep k");
		List<Kep> kepek=new ArrayList<Kep>();
		List<Object[]> l=(List<Object[]>) query.getResultList();
		return l;
	}
	
	public List<Integer> findKepId(){
		Query query;
		query=em.createNativeQuery("SELECT id FROM Kep k");
		List<Integer> l=(List<Integer>) query.getResultList();
		return l;
	}

	public Kep saveKep(Kep kep){
		em.persist(kep);
		return kep;
	}
	
	public Kep deleteKep(int i){
			Kep k=em.find(Kep.class, i);
			em.remove(k);
			return k;
	}
}
