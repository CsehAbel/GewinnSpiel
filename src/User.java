public class User {
	private int szavazatok;
	
	private int kapott;
	
	private String nev;
	
	public User(){
	}
	
	public User(String nev,int szavazatok){
		this.nev=nev;
		this.szavazatok=szavazatok;
		this.kapott=0;
	}

	public String getNev() {
		return nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	public int getSzavazatok() {
		return szavazatok;
	}

	public void setSzavazatok(int szavazatok) {
		this.szavazatok = szavazatok;
	}
	
	@Override
	public String toString() {
		return nev;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User){
			if(((User)obj).getNev()==this.getNev()){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return this.nev.hashCode();
	}

	public int getKapott() {
		return kapott;
	}

	public void setKapott(int kapott) {
		this.kapott = kapott;
	}
	
	
}
