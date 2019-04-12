package top;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class TopHandler {
	
	@EJB
	private TopManager tm;
	
	//"SELECT d.nev,d.torzsszam,d.uzemegyseg,d.munkakor,p.kapott FROM dolgozo d JOIN pontok p ON d.adoszam=p.adoszam ORDER BY p.kapott,d.nev; "
	
	public List<List<String>> top15(){
		List<List<String>> l=new ArrayList<List<String>>();
		for(Object[] o:tm.getTop15()){
			List<String> belso=new ArrayList<String>();
			belso.add(o[0].toString());
			belso.add(o[1].toString());
			belso.add(o[2].toString());
			belso.add(o[3].toString());
			belso.add(o[4].toString());
			l.add(belso);
		}
		return l;
	}

}
