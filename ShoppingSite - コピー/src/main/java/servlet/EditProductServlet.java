package servlet;

import java.io.IOException;

import dao.ProductDao;
import dto.ProductDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit-product")
public class EditProductServlet extends HttpServlet {
    private ProductDao productDao = new ProductDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String image_Path = request.getParameter("image_path");
        

        ProductDto product = new ProductDto(product_id, name, description, price, stock, image_Path);
        productDao.updateProduct(product);

        response.sendRedirect("admin"); // 更新後に一覧へリダイレクト
    }
}

