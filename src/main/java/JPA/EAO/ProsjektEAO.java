package JPA.EAO;


import JPA.Entity.ProsjektEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ProsjektEAO {

    private EntityManagerFactory emf;

    public ProsjektEAO(){
        emf = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    }

    public ProsjektEntity getProsjektById(int id){
        EntityManager em = emf.createEntityManager();
        ProsjektEntity entity = em.find(ProsjektEntity.class, id);
        em.close();
        return entity;
    }

    public static void main(String[] args) {
        ProsjektEAO eao = new ProsjektEAO();
        ProsjektEntity entity = eao.getProsjektById(1);
        System.out.println(entity);
    }
}
