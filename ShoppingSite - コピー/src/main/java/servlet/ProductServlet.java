package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.model.Product;

@WebServlet(name="ProductServlet", urlPatterns={"/product"})
public class ProductServlet extends HttpServlet {
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
        request.setAttribute("product", selected);
        request.getRequestDispatcher("/views/product.jsp").forward(request, response);
    }
}
