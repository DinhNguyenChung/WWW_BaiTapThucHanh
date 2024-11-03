package vn.edu.iuh.fit.backend.business;

import vn.edu.iuh.fit.backend.repositories.entites.Product;

import java.util.List;

public interface ProductBeanRemote {
    void add(Product product);
    List<Product > getAll();
    Product getById(int id);
}
