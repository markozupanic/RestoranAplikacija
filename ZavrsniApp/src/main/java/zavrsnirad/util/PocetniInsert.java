/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsnirad.util;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import org.hibernate.Session;
import org.mindrot.jbcrypt.BCrypt;
import zavrsnirad.model.Operater;
import zavrsnirad.model.Proizvod;
import zavrsnirad.model.ProizvodRezervacije;
import zavrsnirad.model.Rezervacija;

import zavrsnirad.model.Zaposlenik;

/**
 *za git hub sto ide
 * @author X
 */
public class PocetniInsert {

    
    private List<Proizvod> proizvodi;
    private List<Rezervacija> dolazakRezervacija;
    private List<Zaposlenik> zaposlenici;

    private Session sess;
    private Faker faker;

    public PocetniInsert() {

        proizvodi = new ArrayList<>();
        dolazakRezervacija = new ArrayList<>();
        zaposlenici = new ArrayList<>();
        

        sess = HibernateUtil.getSession();
        faker = new Faker();
        sess.beginTransaction();
        kreirajProizvode(50);
        kreirajReazervacije(15);
        kreirajZaposlenike(5);
        kreirajOperatera();
        sess.getTransaction().commit();

    }

    private void kreirajProizvode(int broj) {
        for (int i = 0; i < broj; i++) {
            proizvodi.add(kreirajProizvod());
            
        }
    }

    private Proizvod kreirajProizvod() {
        Proizvod p = new Proizvod();
        p.setNaziv(faker.food().dish());
        p.setVrsta(faker.beer().style());
        //p.setKolicina(Pomocno.randomKolicina(0, 1100));
        p.setCijena(Pomocno.randomKolicina(0,150));
       /*ProizvodRezervacije pr;
        pr=new ProizvodRezervacije();
            pr.setProizvod(p);
            pr.setKolicina(faker.lorem().sentence(2));
            pr.setRezervacija(dolazakRezervacija.get(1));
            sess.persist(pr);
            p.getProizvodRezervacije().add(pr);*/
        
        
        
        sess.persist(p);
        return p;
    }

    

    private void kreirajZaposlenike(int broj) {
        for (int i = 0; i < broj; i++) {
            zaposlenici.add(kreirajZaposlenika());
        }
    }

    private Zaposlenik kreirajZaposlenika() {
        Zaposlenik z = new Zaposlenik();
        z.setIme(faker.name().firstName());
        z.setPrezime(faker.name().lastName());
        z.setKontakt(Pomocno.randomKolicina(100000000, 999999999).toString());
        //z.setSmjena(new Date());
        z.setZnanje(faker.company().profession());
        sess.persist(z);
        return z;
    }

    private void kreirajOperatera() {
        
         Operater o = new Operater();
        o.setIme("Marko");
        o.setPrezime("Županić");
        o.setEmail("marko.zupanic@gmail.com");
        o.setLozinka(BCrypt.hashpw("m", BCrypt.gensalt()));
        sess.persist(o);
        
    }

    private void kreirajReazervacije(int broj) {
        for (int i = 0; i < broj; i++) {
            dolazakRezervacija.add(kreirajRezervaciju(i));
        }
    }

    private Rezervacija kreirajRezervaciju(int i) {
        Rezervacija dr = new Rezervacija();
        dr.setDolazakRezervacije(new Date());
       // sess.persist(dr);

        
        dr.setIme(faker.name().firstName());
        dr.setPrezime(faker.name().lastName());
        dr.setEmail(faker.name().title());
        dr.setBrojLjudi(Pomocno.randomKolicina(1, 20));
        dr.setNapomena(faker.color().name());
        
        ProizvodRezervacije pr;
        
        for(int t=i*5;t<(t*5)+5;t++){
            if(t>=proizvodi.size()){
                continue;
            }
        
        pr=new ProizvodRezervacije();
            pr.setProizvod(proizvodi.get(t));
            pr.setKolicina(Pomocno.randomKolicina(0, 20).toString());
            pr.setRezervacija(dr);
            sess.persist(pr);
            dr.getProizvodRezervacije().add(pr);
        
        sess.persist(dr);
       
        }
        return null;
        
    
    }
}



    

    
    


    
    
    


