package adminhome;

import java.time.LocalDateTime;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@ManagedBean
@Entity
@RequestScoped
@Table(name="levon")
public class Levon {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="ftorzsszam")
	private String ftorzsszam;
	
	@Column(name="levontpont")
	private int levontpont;
	
	@Column(name="kitol")
	private String kitol;
	
	@Column(name="dat")
	private LocalDateTime dat;
	
	public Levon cloneLevon(){
		Levon l=new Levon();
		l.setDat(dat);
		l.setFtorzsszam(ftorzsszam);
		l.setId(id);
		l.setLevontpont(levontpont);
		l.setKitol(kitol);
		return l;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFtorzsszam() {
		return ftorzsszam;
	}

	public void setFtorzsszam(String ftorzsszam) {
		this.ftorzsszam = ftorzsszam;
	}

	public int getLevontpont() {
		return levontpont;
	}

	public void setLevontpont(int levontpont) {
		this.levontpont = levontpont;
	}

	public String getKitol() {
		System.out.println("Levon: get "+kitol);
		return kitol;
	}

	public void setKitol(String kitol) {
		System.out.println("Levon: set "+kitol);
		this.kitol = kitol;
	}

	public LocalDateTime getDat() {
		return dat;
	}

	public void setDat(LocalDateTime dat) {
		this.dat = dat;
	}

}
