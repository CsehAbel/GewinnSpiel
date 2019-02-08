package authentication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = "select_dolgozo", query = "SELECT dolg FROM Dolgozo dolg WHERE dolg.torzsszam=:param")
})
@Entity
@Table(name="dolgozo")
public class Dolgozo {
	
	@Id
	@Column(name="torzsszam")
	private String torzsszam;
	
	@Column(name="adoszam")
	private String adoszam;
	
	@Column(name="nev")
	private String nev;
	
	@Column(name="uzemegyseg")
	private String uzemegyseg;
	
	@Column(name="munkakor")
	private String munkakor;
	
	@Column(name="adminn")
	private int admin;

	public String getTorzsszam() {
		return torzsszam;
	}

	public void setTorzsszam(String torzsszam) {
		this.torzsszam = torzsszam;
	}

	public String getAdoszam() {
		return adoszam;
	}

	public void setAdoszam(String adoszam) {
		this.adoszam = adoszam;
	}

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public String getUzemegyseg() {
		return uzemegyseg;
	}

	public void setUzemegyseg(String uzemegyseg) {
		this.uzemegyseg = uzemegyseg;
	}

	public String getMunkakor() {
		return munkakor;
	}

	public void setMunkakor(String munkakor) {
		this.munkakor = munkakor;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}
}
