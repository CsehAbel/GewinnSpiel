package backend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="pontok")
public class Pontok {
	
	@Id
	@Column(name="adoszam")
	private String adoszam;
	
	@Column(name="kapott")
	private int kapott;
	
	@Column(name="szavazat")
	private int szavazat;
	
	public Pontok(){
		
	}

	public String getAdoszam() {
		return adoszam;
	}

	public void setAdoszam(String adoszam) {
		this.adoszam = adoszam;
	}

	public int getSzavazat() {
		return szavazat;
	}

	public void setSzavazat(int szavazat) {
		this.szavazat = szavazat;
	}

	@Override
	public String toString() {
		return adoszam;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pontok){
			if(((Pontok)obj).getAdoszam()==this.adoszam){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return this.adoszam.hashCode();
	}

	public int getKapott() {
		return kapott;
	}

	public void setKapott(int kapott) {
		this.kapott = kapott;
	}
	
	
}
