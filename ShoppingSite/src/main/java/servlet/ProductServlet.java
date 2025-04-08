package servlet;

import java.io.IOException;

import dao.ProductDao;
import dto.ProductDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
       
        
        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "IDが不正です");
            return;
        }

        ProductDao dao = new ProductDao();
        ProductDto selected = dao.findById(id); // ←★DBから取得するように修正！

        
        
        /*int id = Integer.parseInt(idParam);
        List<ProductDto> products = (List<ProductDto>) getServletContext().getAttribute("products");
        ProductDto selected = null;
        for(ProductDto p: products) {
            if(p.getId() == id) {
                selected = p;
                break;
            }
        }*/
        if(selected == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "商品が見つかりません");
            return;
        }
        request.setAttribute("product", selected);
        request.getRequestDispatcher("/views/product.jsp").forward(request, response);
    }
}
