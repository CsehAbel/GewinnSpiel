package kepek;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="kep")
public class Kep {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Lob
	@Column(name="kep")
	private byte[] kep;
	
	@Column(name="pont")
	private int pont;
	
	@Column(name="leiras")
	private String leiras;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getKep() {
		return kep;
	}

	public void setKep(byte[] kep) {
		this.kep = kep;
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
