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

@WebServlet(name="CartServlet", urlPatterns={"/cart"})
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if(cart == null) {
            cart = new java.util.ArrayList<>();
            session.setAttribute("cart", cart);
        }
        int total = 0;
        for(Product p : cart) {
            total += p.getPrice();
        }
        request.setAttribute("total", total);
        request.getRequestDispatcher("/views/cart.jsp").forward(request, response);
    }
}
