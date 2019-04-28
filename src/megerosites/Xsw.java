package megerosites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="xsw")
public class Xsw {
		
		@Id
		@Column(name="dolgozokod")
		private String dolgozokod;
		
		@Column(name="kartyaszam")
		private int kartyaszam;
		
		@Column(name="nev")
		private String nev;

		public String getDolgozokod() {
			return dolgozokod;
		}

		public void setDolgozokod(String dolgozokod) {
			this.dolgozokod = dolgozokod;
		}

		public int getKartyaszam() {
			return kartyaszam;
		}

		public void setKartyaszam(int kartyaszam) {
			this.kartyaszam = kartyaszam;
		}

		public String getNev() {
			return nev;
		}

		public void setNev(String nev) {
			this.nev = nev;
		}
	
}
