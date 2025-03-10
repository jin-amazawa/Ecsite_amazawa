package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDao;
import dto.ProductDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.model.Product;

@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // クライアントからのソートパラメータを取得
        String sort = request.getParameter("sort");

        // デフォルトのソートを設定（指定がない場合は価格の安い順）
        if (sort == null || sort.equals("default")) {
            sort = "price_asc";
        }

        // 商品リストを取得
        ProductDao productDao = new ProductDao();
        //List<ProductDto> sortedProducts = productDao.getSortedProducts(sort);
        List<Product> sortedProducts = new ArrayList<>();
        for (ProductDto dto : productDao.getSortedProducts(sort)) {
            Product product = new Product(dto.getId(), dto.getName(), dto.getPrice(), dto.getDescription(), dto.getImagePath());
            sortedProducts.add(product);
        }


        
        
        // 取得した商品リストをリクエストスコープに保存
        request.setAttribute("sortedProducts", sortedProducts);

        // 商品一覧ページへフォワード
        request.getRequestDispatcher("/views/index.jsp").forward(request, response);
    }
}
