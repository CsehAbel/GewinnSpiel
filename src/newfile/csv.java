package newfile;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
