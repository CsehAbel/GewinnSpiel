package fixedtimer;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;

@Singleton
@ApplicationScoped
public class FixedTimerBean {
	
	@EJB 
	private WorkerBean workerBean;

	@Lock(LockType.READ)
	@Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
	public void atSchedule() throws InterruptedException{
		
		workerBean.doTimerWork();;
		
	}

}
