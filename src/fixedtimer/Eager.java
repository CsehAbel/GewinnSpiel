package fixedtimer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean(eager=true)
@ApplicationScoped
public class Eager{

	@Inject
	private FixedTimerBean fixedTimerBean;
	
    @PostConstruct
    public void init() {
        /*try {
			fixedTimerBean.atScheduleHonap();
			fixedTimerBean.atScheduleNap();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }

}
