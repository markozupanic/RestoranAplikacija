package zavrsnirad.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity

public class ProizvodRezervacije extends Entitet{
	@ManyToOne
	private Proizvod proizvod;
        @ManyToOne
	private Rezervacija rezervacija;
        
        private String kolicina;

    public ProizvodRezervacije(Proizvod proizvod, Rezervacija rezervacija, String kolicina, Integer sifra) {
        super(sifra);
        this.proizvod = proizvod;
        this.rezervacija = rezervacija;
        this.kolicina = kolicina;
    }
	
	
	
	
	
	public ProizvodRezervacije() {
		super();
	}

    public String getKolicina() {
        return kolicina;
    }

    public void setKolicina(String kolicina) {
        this.kolicina = kolicina;
    }
        
        
        
        
	public Proizvod getProizvod() {
		return proizvod;
	}
	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}
	public Rezervacija getRezervacija() {
		return rezervacija;
	}
	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}

    @Override
    public String toString() {
        return proizvod.toString();
    }
	
	
}
