package servlet;

import java.io.IOException;
import java.util.List;

import dao.ProductDao;
import dto.ProductDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin")
public class ProductManagementController extends HttpServlet {
    private ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ProductDto> products = productDao.getAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/views/manage_products.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int price = Integer.parseInt(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            String image_Path = request.getParameter("image_Path");

            productDao.addProduct(new ProductDto(0, name, description, price, stock, image_Path));
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int price = Integer.parseInt(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            String image_Path = request.getParameter("image_Path");

            productDao.updateProduct(new ProductDto(id, name, description, price, stock, image_Path));
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            productDao.deleteProduct(id);
        }

        response.sendRedirect("products");
    }
}
