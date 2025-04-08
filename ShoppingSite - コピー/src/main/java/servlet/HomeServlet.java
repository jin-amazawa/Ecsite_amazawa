package servlet;

import java.io.IOException;
import java.util.List;

import dao.HomeDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.model.Product;

@WebServlet(name="HomeServlet", urlPatterns={"/"})
public class HomeServlet extends HttpServlet {
    private List<Product> products;
    
    @Override
    public void init() throws ServletException {
        HomeDao productDao = new HomeDao();
        products = productDao.getAllProducts(); // データベースから取得
        getServletContext().setAttribute("products", products);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/index.jsp").forward(request, response);
    }
}
