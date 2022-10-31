/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zavrsnirad.controller;

import java.util.List;
import org.hibernate.Session;
import zavrsnirad.model.Entitet;
import zavrsnirad.util.EdunovaException;
import zavrsnirad.util.HibernateUtil;

/**
 *
 * @author X
 */
public abstract class Obrada<T extends Entitet> {

    protected T entitet;
    protected Session session;

    public abstract List<T> read();

    public abstract void kontrolaCreate() throws EdunovaException;

    public abstract void kontrolaUpdate() throws EdunovaException;

    public abstract void kontrolaDelete() throws EdunovaException;

    protected abstract void getNazivEntiteta();

    public Obrada() {
        this.session = HibernateUtil.getSession();

    }

     public void create() throws EdunovaException{
        kontrolaCreate();
        persist();
    }
     
     public void update() throws EdunovaException{
         kontrolaUpdate();
         persist();
     }

     
     public  void delete() throws EdunovaException{
         kontrolaDelete();
         session.beginTransaction();
         session.remove(entitet);
         session.getTransaction().commit();
     }
     
     
    private void persist() {
        session.beginTransaction();
        session.persist(this.entitet);
        session.getTransaction().commit();
    }
     public T getEntitet() {
        return entitet;
    }

    public void setEntitet(T entitet) {
        this.entitet = entitet;
    }

}
