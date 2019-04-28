package bevaltas;
import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.*;
import javax.mail.internet.*;

public class SendFromExchange {

	public static boolean send(List<String> sent,boolean auth,String user,String pass,String from,String jozsi,String en,String subj,String msg)
	{

	    Properties props = new Properties();
	    props.put("mail.smtp.auth", auth);
	    props.put("mail.debug", "true");

	    props.put("mail.smtp.host", "ziehlmail");
	    props.put("mail.smtp.port", "25");
	    props.put("mail.smtp.auth.mechanisms","NTLM");

	    // *** CHANGED ***
	    props.put("mail.smtp.auth.ntlm.domain","ziehlabegg2"); // Domain you log into Windows with


	    Session session = Session.getInstance(props,new MyAuthenticator(user,pass));

	    try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        
	        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(jozsi+","+en));
	        message.setSubject(subj);
	        message.setText(msg);

	        Transport.send(message);
	        
	        return true;
	    } catch (MessagingException mex) {
	    	mex.printStackTrace();
	    	return false;
	    }
	}
}
