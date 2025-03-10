package servlet;

import java.io.IOException;
import java.util.List;

import dao.SearchDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.model.Product;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");

        List<Product> products = SearchDao.searchProducts(query);
        
        
        request.setAttribute("products", products);
        
        
        RequestDispatcher rd = request.getRequestDispatcher("/views/searchResults.jsp");
        rd.forward(request, response);
    }
}


