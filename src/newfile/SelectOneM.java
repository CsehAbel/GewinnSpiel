package newfile;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named
@SessionScoped
public class SelectOneM {
	
	public List<SelectItem> getTerulet(){
		ArrayList<SelectItem> t=new ArrayList<SelectItem>();
		t.add(e);
	}

}
