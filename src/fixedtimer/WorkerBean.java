package fixedtimer;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Singleton
public class WorkerBean {
	
	@PersistenceContext(name="Szavazas")
	private EntityManager em;
	
	private AtomicBoolean busy = new AtomicBoolean(false);
	
	@Lock(LockType.READ)
	public void doTimerWork() throws InterruptedException{
		if(!busy.compareAndSet(false, true)){
			return;
		} try {
			StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("mukodik");
			storedProcedure.execute();
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		} finally{
			busy.set(false);
		}
	}
	
}
