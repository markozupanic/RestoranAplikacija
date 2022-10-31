/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsnirad.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import zavrsnirad.model.Proizvod;
import zavrsnirad.model.ProizvodRezervacije;
import zavrsnirad.util.EdunovaException;

/**
 *
 * @author X
 */
public class ObradaProizvod extends Obrada<Proizvod>{

     /*private List<ProizvodRezervacije> proizvodRezervacije;
    
    
     @Override
    public void create() throws EdunovaException {
        kontrolaCreate();
        session.beginTransaction();
        session.persist(entitet);
        
        
        List<ProizvodRezervacije> proizvodRezervacije = new ArrayList<>();
        ProizvodRezervacije novi;
        for (ProizvodRezervacije pr : proizvodRezervacije) {
            novi  = new ProizvodRezervacije();
            novi.setProizvod(entitet);
            novi.setRezervacija(pr.getRezervacija());
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
    }*/
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public List<Proizvod> read() {
        return session.createQuery("from Proizvod",Proizvod.class).list();
    }
    
    public List<Proizvod> read(String uvjet) {
        return session.createQuery("from Proizvod p where "
                + " lower(concat(p.naziv)) like :uvjet", 
                Proizvod.class)
                .setParameter("uvjet", "%" + uvjet.toLowerCase() + "%")
                .setMaxResults(10)
                .list();
    }
    

    @Override
    public void kontrolaCreate() throws EdunovaException {
        if(entitet==null){
            throw new EdunovaException("Proizvod nije konstriran");
        }
        kontrolaNaziv();
        kontrolaCijena();
        kontrolaVrsta();
        
        
         
    }

    @Override
    public void kontrolaUpdate() throws EdunovaException {
        if(entitet==null){
            throw new EdunovaException("Proizvod nije konstriran");
        }
        kontrolaNaziv();
        kontrolaCijena();
        kontrolaVrsta();
    }

    @Override
    public void kontrolaDelete() throws EdunovaException {
        
        Integer i = session.createNativeQuery(
               "select count(*) from proizvodrezervacije where proizvod_sifra=:p", 
               Integer.class).setParameter("p", entitet.getSifra()).getSingleResult();
        if(i>0){
           throw  new EdunovaException("Proizvod je na  jednoj ili više rezervacija");
        }
        
    }

    @Override
    protected void getNazivEntiteta() {
        
    }

    private void kontrolaNaziv() throws EdunovaException {
        kontrolaNazivMoraBitUnesena();
        kontrolaNazivBrojZnakova(50);
        kontrolaIstiNazivUBazi();
    }

    private void kontrolaCijena() throws EdunovaException{
        if(entitet.getCijena()==null || entitet.getCijena().equals(BigDecimal.ZERO)){
            throw new EdunovaException("Cijena nije postavljena ili je 0");
        }
    }

    private void kontrolaNazivMoraBitUnesena() throws EdunovaException {
        if(entitet.getNaziv()==null || entitet.getNaziv().trim().isEmpty()){
            throw new EdunovaException("Naziv proizvoda obavezan");
        }
    }

    private void kontrolaNazivBrojZnakova(int brojZnakova) throws EdunovaException {
        if(entitet.getNaziv().length()>brojZnakova){
            throw new EdunovaException("Naziv ne smije imati višse od "
            + brojZnakova + "znakova");
        }
    }

    private void kontrolaIstiNazivUBazi() throws EdunovaException {
        Proizvod p=null;
        try {
            p =session.createQuery("from Proizvod p" + "where s.naziv=naziv",Proizvod.class
            ).setParameter("naziv", entitet.getNaziv()).getSingleResult();
        } catch (Exception e) {
        }
        if(p!=null){
            throw new EdunovaException("Isti naziv proizvoda već postoji");
        }
    }

    private void kontrolaVrsta() throws EdunovaException {
        kontrolaVrstaMOraBitUnesena();
        
    }

    private void kontrolaVrstaMOraBitUnesena() throws EdunovaException {
        if(entitet.getVrsta()==null || entitet.getVrsta().trim().isEmpty()){
            throw new EdunovaException("Vrsta proizvoda obavezna");
        }
    }

    
    
}
