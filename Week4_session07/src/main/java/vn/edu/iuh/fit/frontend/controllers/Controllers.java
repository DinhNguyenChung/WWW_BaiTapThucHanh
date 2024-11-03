package vn.edu.iuh.fit.frontend.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.backend.api.ProductResouce;
import vn.edu.iuh.fit.backend.repositories.entites.Product;
import vn.edu.iuh.fit.frontend.models.ProductModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controller", value = "/Product/controller")
public class Controllers extends HttpServlet {
    @Inject
    private   ProductModel productModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action.equalsIgnoreCase("add")) {
            String name = req.getParameter("name");
            String decs = req.getParameter("desc");
            String img = req.getParameter("img");
//            Product product = new Product(name, decs, img);
//            productModel.createProduct(product);
            System.out.println(name + " " + decs + " " + img);
            if (name != "" && decs != "" && img != "") {
                Product product = new Product(name, decs, img);
                productModel.createProduct(product);
                HttpSession session = req.getSession();
                session.setAttribute("product", product);
//                HttpSession session2 = req.getSession();
                session.setAttribute("name", name);
                session.setAttribute("desc", decs);
                session.setAttribute("img", img);
//                resp.getWriter().write("Product added successfully!");
                List<Product> products = new ArrayList<>();
                products = productModel.getAllProducts();
                session.setAttribute("products", products);
                resp.sendRedirect("ShowAllProducts.jsp");
                System.out.println("Product added successfully!");
            } else {
//                resp.getWriter().write("Missing product information!");
                HttpSession session = req.getSession();
                List<Product> products = new ArrayList<>();
                products = productModel.getAllProducts();
                session.setAttribute("products", products);
                resp.sendRedirect("ShowAllProducts.jsp");
            }
        } else if (action.equalsIgnoreCase("search")) {
            String id = req.getParameter("search");
            Product product = productModel.getProduct(Integer.parseInt(id));
            HttpSession session = req.getSession();
//            session.setAttribute("product", product);
            if (product != null) {
                List<Product> products = new ArrayList<>();
                products.add(product);
                session.setAttribute("products", products);
                resp.sendRedirect("ShowAllProducts.jsp");
            }

           else { // Xử lý khi không tìm thấy sản phẩm
                session.setAttribute("errorMessage", "Product not found.");
                resp.sendRedirect("ShowAllProducts.jsp");
            }
        }
        else{
            HttpSession session = req.getSession();
            List<Product> products = new ArrayList<>();
            products = productModel.getAllProducts();
            session.setAttribute("products", products);
            resp.sendRedirect("ShowAllProducts.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


}
