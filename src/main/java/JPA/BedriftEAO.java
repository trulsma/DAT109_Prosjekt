package JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BedriftEAO {

    private EntityManagerFactory emf;

    public BedriftEAO() {
        emf = Persistence.createEntityManagerFactory("PU");
    }

    public BedriftEntity getBedriftById(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(BedriftEntity.class, id);
    }

    public static void main(String[] args) {
        BedriftEAO eao = new BedriftEAO();
        BedriftEntity entity = eao.getBedriftById(1);
        System.out.println(entity);
    }
}
