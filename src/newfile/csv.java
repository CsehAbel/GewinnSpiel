package newfile;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name = "find_user", query = "SELECT c FROM csv c where c.cDolgozokod = :k")
})
@Named
@RequestScoped
@Entity
@Table(name="csv")
public class csv {
	
	@Id
	@Column(name="cId")
	private int cId;
	
	@Column(name="cDolgozokod")
	private String cDolgozokod;
	
	@Column(name="cNev")
	private String cNev;
	
	@Column(name="cTerulet")
	private String cTerulet;
	
	@Column(name="cBeosztas")
	private String cBeosztas;
	
	@Column(name="szavazat")
	private int szavazat;
	
	@Column(name="kapott")
	private int kapott;
	
	@Column(name="adminn")
	private int adminn;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcDolgozokod() {
		return cDolgozokod;
	}

	public void setcDolgozokod(String cDolgozokod) {
		this.cDolgozokod = cDolgozokod;
	}

	public String getcNev() {
		return cNev;
	}

	public void setcNev(String cNev) {
		this.cNev = cNev;
	}

	public String getcTerulet() {
		return cTerulet;
	}

	public void setcTerulet(String cTerulet) {
		this.cTerulet = cTerulet;
	}

	public String getcBeosztas() {
		return cBeosztas;
	}

	public void setcBeosztas(String cBeosztas) {
		this.cBeosztas = cBeosztas;
	}

	public int getSzavazat() {
		return szavazat;
	}

	public void setSzavazat(int szavazat) {
		this.szavazat = szavazat;
	}

	public int getKapott() {
		return kapott;
	}

	public void setKapott(int kapott) {
		this.kapott = kapott;
	}

	public int getAdminn() {
		return adminn;
	}

	public void setAdminn(int adminn) {
		this.adminn = adminn;
	}
}
