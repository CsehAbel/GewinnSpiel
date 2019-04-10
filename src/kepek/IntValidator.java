package kepek;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("kepek.IntValidator")
public class IntValidator implements Validator{
	
	private Pattern p=Pattern.compile("[0-9]{1,4}");

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		//ha nincs vagy whitespaces
		if(arg2==null || arg2.toString().trim().length()==0){
			return;
		}
		if(!p.matcher(arg2.toString()).matches() || !(Integer.parseInt(arg2.toString().trim())>=1)){
			String label=(String) arg1.getAttributes().get("label");
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					(label==null || label.trim().length()==0 ? "nem >=1 egész szám":"validator.IntValidator 21-es sor"),
					null));//)
		}
	}

}
