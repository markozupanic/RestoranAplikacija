/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsnirad.controller;

import java.util.ArrayList;
import java.util.List;
import zavrsnirad.model.ProizvodRezervacije;
import zavrsnirad.model.Rezervacija;
import zavrsnirad.util.EdunovaException;

/**
 *
 * @author X
 */
public class ObradaRezervacija extends Obrada<Rezervacija>{
    
    
    
    
    private List<ProizvodRezervacije> proizvodRezervacije;

    @Override
    public void create() throws EdunovaException {
        kontrolaCreate();
        session.beginTransaction();
        session.persist(entitet);
        
        
        List<ProizvodRezervacije> proizvodRezervacije = new ArrayList<>();
        ProizvodRezervacije novi;
        for (ProizvodRezervacije pr : proizvodRezervacije) {
            novi  = new ProizvodRezervacije();
            novi.setRezervacija(entitet);
            novi.setProizvod(pr.getProizvod());
            novi.setKolicina(pr.getKolicina());
            session.persist(novi);
            proizvodRezervacije.add(novi);
        }
        entitet.setProizvodRezervacije(proizvodRezervacije);
        
        session.getTransaction().commit();
    }

    @Override
    public void delete() throws EdunovaException {
        kontrolaDelete();
        session.beginTransaction();
        for (ProizvodRezervacije pr : entitet.getProizvodRezervacije()) {
            session.remove(pr);
        }
        session.remove(entitet);
        session.getTransaction().commit();
    }
    
    

    @Override
    public void update() throws EdunovaException {
        kontrolaUpdate();
        session.beginTransaction();
        for (ProizvodRezervacije pr : entitet.getProizvodRezervacije()) {
            session.remove(pr);
        }
        for (ProizvodRezervacije pr : proizvodRezervacije) {
            session.persist(pr);
        }
        entitet.setProizvodRezervacije(proizvodRezervacije);
        session.persist(entitet);
        session.getTransaction().commit();
         for (ProizvodRezervacije pr : proizvodRezervacije) {
            session.refresh(pr);
        }
    }
    
    
    
    
    
    
    @Override
    public List<Rezervacija> read() {
        return session.createQuery("from Rezervacija",Rezervacija.class).list();
    }

    @Override
    public void kontrolaCreate() throws EdunovaException {
        kontrolaPrezimeObavezno();
        kotrolaDatumObavezno();
        kontrolaBrojLjudiObavezno();
    }

    @Override
    public void kontrolaUpdate() throws EdunovaException {
        kontrolaPrezimeObavezno();
        kotrolaDatumObavezno();
        kontrolaBrojLjudiObavezno();
    }

    @Override
    public void kontrolaDelete() throws EdunovaException {
    }

    @Override
    protected void getNazivEntiteta() {
    }
    
    
    public List<ProizvodRezervacije> getNoviProizvodi() {
        return proizvodRezervacije;
    }

    public void setNoviProizvodi(List<ProizvodRezervacije> proizvodRezervacije) {
        this.proizvodRezervacije = proizvodRezervacije;
    }

    private void kontrolaPrezimeObavezno() throws EdunovaException{
        if(entitet.getPrezime()==null || entitet.getPrezime().trim().isEmpty()){
            throw new EdunovaException("Prezime obavezno");
        }
    }

    private void kotrolaDatumObavezno() throws EdunovaException{
                if(entitet.getDolazakRezervacije()==null ){
            throw new EdunovaException("Datum obavezno");
        }
    }

    private void kontrolaBrojLjudiObavezno() throws EdunovaException{
        if(entitet.getBrojLjudi()==null || entitet.getBrojLjudi().equals(0)){
            throw new EdunovaException("Broj ljudi obavezno");
    }
    
    
    
    }
}
