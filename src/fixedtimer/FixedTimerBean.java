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
	
	private boolean b=false;

	@Lock(LockType.READ)
	@Schedule(dayOfMonth="22",hour="11",minute="14",persistent= false)
	public void atSchedule() throws InterruptedException{
		if(b){
		szavazas.requestNyit();
		workerBean.doTimerWork();;
		}
		b=true;
		System.out.println(szavazas.getIState());
		
	}

}
