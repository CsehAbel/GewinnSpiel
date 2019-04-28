package kepek;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.Part;
import javax.transaction.UserTransaction;

import backend.Pontok;

@ManagedBean
@RequestScoped
public class ImageStoreBean {

	@Inject
	private ImageManager im;
	//nem kell storeImage()-be siker=null mert requestscoped
   private String siker=null;

   private Part file;
   
   //táblába felvenni, jsf be inputtextet beírni
   private int pont;
   
   
   //html-nél maxlength
   private String leiras;
   
   public void storeImage() {
	try (InputStream input = file.getInputStream()) {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[1024];
		while ((nRead = input.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}

		buffer.flush();
		byte[] byteArray = buffer.toByteArray();
		if(byteArray!=null && byteArray.length<2000000){
			
			Kep kep=new Kep();
			kep.setKep(byteArray);
			kep.setLeiras(leiras);
			kep.setPont(pont);
		
			im.saveKep(kep);
		
			siker="nem ures";
		}
	   //EMAILBEN elküldhetem magamnak ha a kepfeltoltes nem sikerul
       } catch (Exception e) {
           e.printStackTrace();
       }
        
   }

	public Part getFile() {
		return file;
	}
	
	public void setFile(Part file) {
		this.file = file;
	}

	public String getSiker() {
		return siker;
	}

	public void setSiker(String siker) {
		this.siker = siker;
	}

	public int getPont() {
		return pont;
	}

	public void setPont(int pont) {
		this.pont = pont;
	}

	public String getLeiras() {
		return leiras;
	}

	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}
}