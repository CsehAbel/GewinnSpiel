package fixedtimer;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.ejb.EJB;
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
	
	@EJB
	private SqlServerManager sqlServerManager;
	
	@EJB
	private MySqlManager mySqlManager;
	
	private AtomicBoolean busy = new AtomicBoolean(false);
	
	private AtomicBoolean busysqlserver = new AtomicBoolean(false);
	
	//pontokSzavazas, dolgozoSzavazas, xswSzavazas
	public void doTimerWork(String storedProcedureName) throws InterruptedException{
		if(!busy.compareAndSet(false, true)){
			return;
		} try {
			mySqlManager.sP(storedProcedureName);
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		} finally{
			busy.set(false);
		}
	}
	
	//pontokSzavazas, dolgozoSzavazas, xswSzavazas
		public void doTimerWorkSqlServer(String storedProcedureName) throws InterruptedException{
			if(!busysqlserver.compareAndSet(false, true)){
				return;
			} try {
				sqlServerManager.sP(storedProcedureName);
			} catch(Exception ex){
				System.out.println(ex.getMessage());
			} finally{
				busysqlserver.set(false);
			}
		}
}
