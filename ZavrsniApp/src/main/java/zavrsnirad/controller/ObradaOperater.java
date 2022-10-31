/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsnirad.controller;

import jakarta.persistence.NoResultException;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

import zavrsnirad.model.Operater;
import zavrsnirad.util.EdunovaException;

/**
 *
 * @author X
 */
public class ObradaOperater extends Obrada<Operater>{
    
    public Operater autoriziraj(String email, char[] lozinka) {
        Operater o;
        try {
            o = session.createQuery("from Operater where email=:email",
                    Operater.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        if(BCrypt.checkpw(new String(lozinka), o.getLozinka())){
            Operater vrati = new Operater();
           // vrati.setSifra(o.getSifra());
            vrati.setIme(o.getIme());
            vrati.setPrezime(o.getPrezime());
            vrati.setEmail(o.getEmail());
            // ne postavljamo lozinku da ne bude u memoriji
            return vrati;     
        }

        return null;
    }

    @Override
    public List<Operater> read() {
        return session.createQuery("from Operater", Operater.class).list();
    }

    @Override
    public void kontrolaCreate() throws EdunovaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void kontrolaUpdate() throws EdunovaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void kontrolaDelete() throws EdunovaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void getNazivEntiteta() {
      // return "Operater";
    }
    
}
