package zavrsnirad.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import zavrsnirad.util.Pomocno;

@Entity
public class Rezervacija extends Entitet {

    @ManyToOne
    private Zaposlenik zaposlenik;

    @OneToMany(mappedBy = "rezervacija")
    private List<ProizvodRezervacije> proizvodRezervacije = new ArrayList<>();
    
    private Date dolazakRezervacije;
    private String ime;
    private String prezime;
    private String email;
    private Integer brojLjudi;
     private String napomena;
    
    
    public Rezervacija(Zaposlenik zaposlenik, Date dolazakRezervacije, String ime, String prezime, String email, Integer brojLjudi, String napomena, Integer sifra) {
        super(sifra);
        this.zaposlenik = zaposlenik;
        this.dolazakRezervacije = dolazakRezervacije;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.brojLjudi = brojLjudi;
        this.napomena = napomena;
    }

    
    
    
    
    

   public Rezervacija() {
    }

    public List<ProizvodRezervacije> getProizvodRezervacije() {
        return proizvodRezervacije;
    }

    public void setProizvodRezervacije(List<ProizvodRezervacije> proizvodRezervacije) {
        this.proizvodRezervacije = proizvodRezervacije;
    }

    

    public Zaposlenik getZaposlenik() {
        return zaposlenik;
    }

    public void setZaposlenik(Zaposlenik zaposlenik) {
        this.zaposlenik = zaposlenik;
    }

    public Date getDolazakRezervacije() {
        return dolazakRezervacije;
    }

    public void setDolazakRezervacije(Date dolazakRezervacije) {
        this.dolazakRezervacije = dolazakRezervacije;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBrojLjudi() {
        return brojLjudi;
    }

    public void setBrojLjudi(Integer brojLjudi) {
        this.brojLjudi = brojLjudi;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    @Override
    public String toString() {
        return Pomocno.getFormat(dolazakRezervacije);
    }
    
    

    

}
