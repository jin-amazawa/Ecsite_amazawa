package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servlet.model.Product;

@WebServlet(name="AddToCartServlet", urlPatterns={"/add-to-cart"})
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        int id = Integer.parseInt(idParam);
        List<Product> products = (List<Product>) getServletContext().getAttribute("products");
        Product selected = null;
        for(Product p: products) {
            if(p.getId() == id) {
                selected = p;
                break;
            }
        }
        if(selected == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "商品が見つかりません");
            return;
        }
        HttpSession session = request.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if(cart == null) {
            cart = new java.util.ArrayList<>();
        }
        cart.add(selected);
        
        int total = 0;
        for(Product p : cart) {
            total += p.getPrice();
        }
        
        session.setAttribute("cart", cart);
        session.setAttribute("total", total);
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
