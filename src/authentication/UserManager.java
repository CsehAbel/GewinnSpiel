package authentication;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserManager {
	
	@PersistenceContext(unitName="Szavazas")
	private EntityManager em;
	
	public User getUser(String dolgozokod){
		User u=null;
		try{
		u=em.createNamedQuery("select_user",User.class).setParameter("param",dolgozokod).getSingleResult();
		} catch(Exception ex){
			return null;
		}
		return u;
	}

}
