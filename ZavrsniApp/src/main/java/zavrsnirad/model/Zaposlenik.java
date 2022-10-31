package zavrsnirad.model;

import jakarta.persistence.Entity;
import java.util.Date;
@Entity

public class Zaposlenik extends Entitet {
	
	private String ime;
	private String prezime;
	private String kontakt;
	//private Date smjena;
	private String znanje;
	
	
	
	public Zaposlenik(Integer sifra, String ime, String prezime, String kontakt, /*Date smjena*/ String znanje) {
		super(sifra);
		this.ime = ime;
		this.prezime = prezime;
		this.kontakt = kontakt;
		//this.smjena = smjena;
		this.znanje = znanje;
	}
	public Zaposlenik() {
		super();
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getKontakt() {
		return kontakt;
	}
	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}
	/*public Date getSmjena() {
		return smjena;
	}
	public void setSmjena(Date smjena) {
		this.smjena = smjena;
	}*/
	public String getZnanje() {
		return znanje;
	}
	public void setZnanje(String znanje) {
		this.znanje = znanje;
	}

    @Override
    public String toString() {
        return this.ime + " " + this.prezime;
    }
	
	
	

}
