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

@WebServlet(name="UpdateCartServlet", urlPatterns={"/update-cart"})
public class UpdateCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        
        if (cart == null) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }
        
        // 商品削除処理
        String removeId = request.getParameter("remove");
        if (removeId != null) {
            int idToRemove = Integer.parseInt(removeId);
            cart.removeIf(p -> p.getId() == idToRemove);
            session.setAttribute("cart", cart);
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }
        
        // 数量更新処理
        for (Product p : cart) {
            String quantityParam = request.getParameter("quantity_" + p.getId());
            if (quantityParam != null) {
                int newQuantity = Integer.parseInt(quantityParam);
                if (newQuantity > 0) {
                    p.setQuantity(newQuantity);
                }
            }
        }
        
        /*int total = 0;
        for (Product p : cart) {
            total += p.getPrice() * p.getQuantity();
        }*/
        
        session.setAttribute("cart", cart);
        //session.setAttribute("total", total);
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}

