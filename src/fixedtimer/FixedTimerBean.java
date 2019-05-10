package fixedtimer;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import backend.Szavazas;

@Singleton
@Startup
public class FixedTimerBean {
	
	@EJB 
	private WorkerBean workerBean;
	
	@Inject
	private Szavazas szavazas;
	
	private boolean a=false;
	private boolean b=false;

	/*@Lock(LockType.READ)
	@Schedule(dayOfMonth="10",hour="18",minute="30",persistent= false)
	public void atScheduleHonap() throws InterruptedException{
		if(a){
		workerBean.doTimerWork("pontokSzavazatEgyre");
		}
		a=true;
	}
	
	@Lock(LockType.READ)
	@Schedule(hour="18",minute="31",persistent= false)
	public void atScheduleNap() throws InterruptedException{
		if(b){
		workerBean.doTimerWorkSqlServer("dolgozoSzavazas");
		}
		b=true;
	}*/

}
