package vn.edu.iuh.fit.backend.business;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import vn.edu.iuh.fit.backend.repositories.entites.Product;

import java.util.List;

@Stateless
public class ProductBean implements ProductBeanRemote {

    @PersistenceContext(unitName = "lab_week4")
    private EntityManager em;


    @Override
    @Consumes("application/json")
    @Produces("application/json")
    public void add(Product product) {
        try {
            System.out.println("Persisting product: " + product);
            em.persist(product);  // Thao tác persist phải được thực hiện
            System.out.println("Product persisted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Consumes("application/json")
    @Produces("application/json")
    public List<Product> getAll() {
        return em.createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    @Override
    @Consumes("application/json")
    @Produces("application/json")
    public Product getById(int id) {
        return em.createNamedQuery("Product.findById", Product.class).setParameter("id", id).getSingleResult();
    }
}
