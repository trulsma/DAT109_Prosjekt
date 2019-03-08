package no.hvl.dat109.JPA.EAO;

import no.hvl.dat109.JPA.Entity.BedriftEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class BedriftEAO {

    private EntityManagerFactory emf;

    public BedriftEAO() {
        emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    }

    public BedriftEntity getBedriftById(int id) {
        EntityManager em = emf.createEntityManager();
        BedriftEntity entity = em.find(BedriftEntity.class, id);
        em.close();
        return entity;
    }

    public static void main(String[] args) {
        BedriftEAO eao = new BedriftEAO();
        BedriftEntity entity = eao.getBedriftById(1);
        System.out.println(entity);
    }
}
