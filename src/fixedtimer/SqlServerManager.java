package fixedtimer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Stateless
public class SqlServerManager {
	
	@PersistenceContext(name="SzavazasSQLSERVER")
	private EntityManager em;
	
	public void sP(String storedProcedureName){
		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery(storedProcedureName);
		storedProcedure.execute();
	}

}
