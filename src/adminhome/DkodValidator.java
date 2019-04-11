package adminhome;

import java.util.regex.Pattern;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import newfile.DbManager;

@Named
@RequestScoped
@FacesValidator("adminhome.DkodValidator")
public class DkodValidator implements Validator {

	private Pattern p=Pattern.compile("[0-9]{5}");
	
	@Inject
	private DbManager dm;

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		//ha nincs vagy whitespaces
		if(arg2==null || arg2.toString().trim().length()==0){
			return;
		}
		if(!p.matcher(arg2.toString().trim()).matches() || dm.findDolgozo(arg2.toString().trim())==null){
			String label=(String) arg1.getAttributes().get("label");
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					(label==null || label.trim().length()==0 ? "Nincs ilyen dolgozókód!":"validator.IntValidator 21-es sor"),
					null));//)
		}
	}

}
