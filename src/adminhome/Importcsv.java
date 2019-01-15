package adminhome;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@SessionScoped
public class Importcsv {

	private Part file; // +getter+setter

	public void save() {
		String t="";
	    try (InputStream input = file.getInputStream()) {
	            try(java.util.Scanner s = new java.util.Scanner(input)){
	            	t=s.useDelimiter("\\A").hasNext() ? s.next() : "";
	            }
	    }
	    catch (IOException e) {
	        // Show faces message?
	    }
	    String[] str=t.split("\n");
	    for(int i=0;i<str.length;i++){
	    	System.out.println(str[i]+"\n");
	    }
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
	
	
	
}
