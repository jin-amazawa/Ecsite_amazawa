package servlet;

import java.io.IOException;
import java.util.List;

import dao.HomeDao;
import dto.ProductDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="HomeServlet", urlPatterns={"/"})
public class HomeServlet extends HttpServlet {
    private List<ProductDto> products;
    
   /* @Override
    public void init() throws ServletException {
        HomeDao productDao = new HomeDao();
        products = productDao.getAllProducts(); // データベースから取得
        getServletContext().setAttribute("products", products);
    }*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        HomeDao productDao = new HomeDao();
        List<ProductDto> products = productDao.getAllProducts(); // ★ 毎回最新データを取得

        request.setAttribute("sortedProducts", products); // ★ JSPに渡す
    	
        request.getRequestDispatcher("/views/index.jsp").forward(request, response);
    }
}
