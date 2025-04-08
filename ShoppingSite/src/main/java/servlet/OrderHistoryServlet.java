package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.OrderDao;
import dto.OrderHistoryDto;

@WebServlet("/orders")
public class OrderHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        try {
            OrderDao dao = new OrderDao();
            List<OrderHistoryDto> orders = dao.findAllOrderHistory();

            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/views/orders.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

