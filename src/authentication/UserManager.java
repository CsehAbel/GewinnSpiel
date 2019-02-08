package authentication;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserManager {
	
	@PersistenceContext(unitName="Szavazas")
	private EntityManager em;
	
	public Dolgozo getDolgozo(String dolgozokod){
		Dolgozo d=null;
		try{
		d=em.createNamedQuery("select_dolgozo",Dolgozo.class).setParameter("param",dolgozokod).getSingleResult();
		} catch(Exception ex){
			return null;
		}
		return d;
	}

}
