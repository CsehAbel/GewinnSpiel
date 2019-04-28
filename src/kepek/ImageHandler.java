package kepek;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
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

@ManagedBean
@SessionScoped
public class ImageHandler {
		
		@EJB
		private ImageManager im;
		
		public Kep findKepekben(int id){
			Kep k = findKepDarab().stream()
					  .filter(x -> (id==x.getId()))
					  .findAny().orElse(null);
			return k;
					  
		}
		
		//kepek byte[] nélkül
		public List<Kep> findKepDarab(){
				List<Kep> kepek=new ArrayList<Kep>();
				for(Object[] s:im.findKepek()){
					Kep k=new Kep();
					k.setId(Integer.parseInt(s[0].toString()));
					k.setPont(Integer.parseInt(s[1].toString()));
					k.setLeiras(s[2].toString());
					kepek.add(k);
				}
				return kepek;
		}
		
		public int findKepDarabHalfSize() {
			int s=findKepId().size();
			int feleEgeszReszPluszEgy=(int)Math.ceil((double)s/2);
			return feleEgeszReszPluszEgy;
		}
		
		public List<Integer> findKepId(){
			return im.findKepId();
		}
		
		public Kep saveKep(Kep kep){
			return im.saveKep(kep);
		}
		
		public Kep deleteKep(int i){
			return im.deleteKep(i);
			
		}
}
