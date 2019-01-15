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
	@NamedQuery(name = "select_user", query = "SELECT usr FROM User usr WHERE usr.dolgozokod=:param")
})
@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="dolgozokod")
	private String dolgozokod;
	
	@Column(name="nev")
	private String nev;
	
	@Column(name="adminn")
	private int admin;

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDolgozokod() {
		return dolgozokod;
	}

	public void setDolgozokod(String dolgozokod) {
		this.dolgozokod = dolgozokod;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}
}
