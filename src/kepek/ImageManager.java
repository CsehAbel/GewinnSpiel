package kepek;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import authentication.Dolgozo;

@Named
@ManagedBean(name="imagemanager")
@SessionScoped
public class ImageManager {

		@PersistenceContext(unitName="Szavazas")
		private EntityManager em;
		
		@Resource
		private UserTransaction userTransaction;
		
		//kepek byte[] nélkül
		public List<Kep> findKepDarab(){
				Query query;
				query=em.createNativeQuery("SELECT id,pont,leiras FROM Kep k");
				
				List<Kep> kepek=new ArrayList<Kep>();
				List<Object[]> l=(List<Object[]>) query.getResultList();
				for(Object[] s:l){
					Kep k=new Kep();
					k.setId(Integer.parseInt(s[0].toString()));
					k.setPont(Integer.parseInt(s[1].toString()));
					k.setLeiras(s[2].toString());
					kepek.add(k);
				}
				return kepek;
		}
		
		public Kep saveKep(Kep kep){
			try {
				userTransaction.begin();
				em.persist(kep);
				userTransaction.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return kep;
		}
	
}
