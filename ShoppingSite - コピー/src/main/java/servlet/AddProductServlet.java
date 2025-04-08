package servlet;

import java.io.IOException;

import dao.ProductDao;
import dto.ProductDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add-product")
public class AddProductServlet extends HttpServlet {
    private ProductDao productDao = new ProductDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // フォームの値を取得
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        String image_Path = request.getParameter("image_path"); // 画像URL

        // 商品情報を作成
        ProductDto product = new ProductDto(0, name, description, price, stock, image_Path);
        
        // データベースに追加
        productDao.addProduct(product);

        // 商品一覧へリダイレクト
        response.sendRedirect(request.getContextPath() + "/admin");
    }
}

