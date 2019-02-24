package backend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="kikire")
public class Kikire {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="ki")
	private String ki;
	
	@Column(name="kire")
	private String kire;

	public String getKi() {
		return ki;
	}

	public void setKi(String ki) {
		this.ki = ki;
	}

	public String getKire() {
		return kire;
	}

	public void setKire(String kire) {
		this.kire = kire;
	}
}
