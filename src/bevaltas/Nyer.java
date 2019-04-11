package bevaltas;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nyer")
public class Nyer {
	
	/*id int(11) not null auto_increment,
	torzsszam varchar(40),
    dat timestamp,
    dpont int(4),
    nyid int(4),
    nypont int(4),
    primary key (id),*/
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="torzsszam")
	private String torzsszam;
	
	@Column(name="dat")
	private LocalDateTime dat;
	
	@Column(name="dpont")
	private int pont;
	
	@Column(name="nyid")
	private int nyid;
	
	@Column(name="nypont")
	private int nypont;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTorzsszam() {
		return torzsszam;
	}

	public void setTorzsszam(String torzsszam) {
		this.torzsszam = torzsszam;
	}

	public LocalDateTime getDat() {
		return dat;
	}

	public void setDat(LocalDateTime dat) {
		this.dat = dat;
	}

	public int getPont() {
		return pont;
	}

	public void setPont(int pont) {
		this.pont = pont;
	}

	public int getNyid() {
		return nyid;
	}

	public void setNyid(int nyid) {
		this.nyid = nyid;
	}

	public int getNypont() {
		return nypont;
	}

	public void setNypont(int nypont) {
		this.nypont = nypont;
	}
	
	

}
