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
		
		//id szerint rendezve(tal�n) elt�rolja a findKepDarab met�dusban
		private List<Kep> kepek;
		
		private List<Integer> kepid;
		
		public Kep findKepekben(List<Kep> l,int id){
			Kep k = l.stream()
					  .filter(x -> (id==x.getId()))
					  .findAny().orElse(null);
			return k;
					  
		}
		
		//kepek byte[] n�lk�l
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
				this.kepek=kepek;
				return kepek;
		}
		
		public List<Integer> findKepid(){
			Query query;
			query=em.createNativeQuery("SELECT id FROM Kep k");
			
			List<Kep> kepek=new ArrayList<Kep>();
			kepid=(List<Integer>) query.getResultList();
			return kepid;
		}
		
		public Kep saveKep(Kep kep){
			try {
				userTransaction.begin();
				em.persist(kep);
				userTransaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return kep;
		}
		
		public void deleteRecept(int i){
			try {
				userTransaction.begin();
				Kep k=em.find(Kep.class, i);
				em.remove(k);
				userTransaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		public List<Kep> getKepek() {
			return kepek;
		}

		public void setKepek(List<Kep> kepek) {
			this.kepek = kepek;
		}

		public List<Integer> getKepid() {
			System.out.println("getKepid():"+kepid.size());
			return kepid;
		}

		public void setKepid(List<Integer> kepid) {
			this.kepid = kepid;
		}		
}
