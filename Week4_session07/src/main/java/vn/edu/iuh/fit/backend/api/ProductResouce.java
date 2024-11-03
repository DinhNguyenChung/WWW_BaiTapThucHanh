package vn.edu.iuh.fit.backend.api;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.business.ProductBeanRemote;
import vn.edu.iuh.fit.backend.repositories.entites.Product;

@Path("/products")
public class ProductResouce {
    @EJB
    private ProductBeanRemote productBean;

    @GET
    public Response getAllProducts() {
        return Response.ok(productBean.getAll()).build();
    }
    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") int id) {
        return Response.ok(productBean.getById(id)).build();
    }
    @POST
    public Response createProduct(Product product) {
        if(product ==null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Product can be not").build();
        }
        productBean.add(product);
        return Response.ok(product).build();
    }
}
