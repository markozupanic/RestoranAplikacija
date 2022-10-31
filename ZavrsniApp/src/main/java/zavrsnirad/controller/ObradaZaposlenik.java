/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsnirad.controller;

import java.util.List;
import zavrsnirad.model.Zaposlenik;
import zavrsnirad.util.EdunovaException;

/**
 *
 * @author X
 */
public class ObradaZaposlenik extends Obrada<Zaposlenik>{

    @Override
    public List<Zaposlenik> read() {
        return session.createQuery("from Zaposlenik",Zaposlenik.class).list();
    }

    @Override
    public void kontrolaCreate() throws EdunovaException {
        kontrolaNazivObavezno();
        kontrolaPrezimeObavezno();
        kontrolakontaktObavezno();
        kontrolaZvanjeObavezno();
        kontrolaNazivIstiUBazi();
    }

    @Override
    public void kontrolaUpdate() throws EdunovaException {
        kontrolaNazivObavezno();
        kontrolaPrezimeObavezno();
        kontrolakontaktObavezno();
        kontrolaZvanjeObavezno();
        kontrolaNazivIstiUBazi();
        
        
    }

    @Override
    public void kontrolaDelete() throws EdunovaException {
         // kontrolaDelete();
        Integer i = session.createNativeQuery(
               "select count(*) from rezervacija where zaposlenik_sifra=:p", 
               Integer.class).setParameter("p", entitet.getSifra()).getSingleResult();
        if(i>0){
           throw  new EdunovaException("Zaposlenik radi na jednoj ili više rezervacija");
       }
    
    }
    

    @Override
    protected void getNazivEntiteta() {
    }

    private void kontrolaNazivObavezno() throws EdunovaException {
        if(entitet.getIme()==null || entitet.getIme().trim().isEmpty()){
            throw new EdunovaException("Ime zaposlenika obavezno");
        }
    }

    private void kontrolaNazivIstiUBazi() throws EdunovaException {
        Zaposlenik z=null;
        try {
            z=session.createQuery("from Zaposlenik z"+"where z.naziv=:naziv",Zaposlenik.class)
                    .setParameter("ime", entitet.getIme() ).getSingleResult();
        } catch (Exception e) {
        }
        if(z!=null)
            throw new EdunovaException("Isti zaposlenik već postoji");
    }

    private void kontrolaPrezimeObavezno() throws EdunovaException{
                if(entitet.getPrezime()==null || entitet.getPrezime().trim().isEmpty()){
            throw new EdunovaException("Prezime zaposlenika obavezno");
        }
        
    }

    private void kontrolakontaktObavezno() throws EdunovaException{
        if(entitet.getKontakt()==null || entitet.getKontakt().trim().isEmpty()){
            throw new EdunovaException("Kontakt zaposlenika obavezno");
        }
    }

    private void kontrolaZvanjeObavezno() throws EdunovaException{
        if(entitet.getZnanje()==null || entitet.getZnanje().trim().isEmpty()){
            throw new EdunovaException("Zvanje zaposlenika obavezno");
    }
    }
    
}
