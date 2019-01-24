package megerosites;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Named
@RequestScoped
@Entity
@Table(name="xsw")
public class xsw {

	@Id
	@Column(name="xDolgozokod")
	private int xDolgozokod;
	
	@Column(name="xNev")
	private String xNev;
	
	@Column(name="xKartyaszam")
	private int xKartyaszam;

	public int getxDolgozokod() {
		return xDolgozokod;
	}

	public void setxDolgozokod(int xDolgozokod) {
		this.xDolgozokod = xDolgozokod;
	}

	public String getxNev() {
		return xNev;
	}

	public void setxNev(String xNev) {
		this.xNev = xNev;
	}

	public int getxKartyaszam() {
		return xKartyaszam;
	}

	public void setxKartyaszam(int xKartyaszam) {
		this.xKartyaszam = xKartyaszam;
	}
}
