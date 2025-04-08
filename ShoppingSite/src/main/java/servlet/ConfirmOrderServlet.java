package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.OrderDao;
import dto.ProductDto;
import dto.UsersDto;

@WebServlet("/confirm-order")
public class ConfirmOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false);
    	UsersDto loginUser = (session != null) ? (UsersDto) session.getAttribute("loginUser") : null;

    	if (loginUser == null) {
    	    response.sendRedirect(request.getContextPath() + "/"); // 未ログインはトップへリダイレクト
    	    return;
    	}
    	
        //HttpSession session = request.getSession();
        List<ProductDto> cart = (List<ProductDto>) session.getAttribute("cart");
        
        System.out.println("カート内容:");
        for (ProductDto item : cart) {
            System.out.println("商品名: " + item.getName() + ", 価格: " + item.getPrice() + ", 数量: " + item.getStock());
        }


        if (cart == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
            return;
        }

        
        try {
            OrderDao orderDao = new OrderDao();
            orderDao.insertOrder(loginUser.getId(), cart);
            


            session.setAttribute("orderedProducts", cart);
            System.out.println("orderedProducts セット完了");
            
            // セッションからカートを削除
            session.removeAttribute("cart");

            // 完了ページにリダイレクト
            response.sendRedirect(request.getContextPath() + "/views/order-complete.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "注文処理中にエラーが発生しました。");
            request.getRequestDispatcher("/views/checkout.jsp").forward(request, response);
        }

        // 例: データベースに注文情報を保存、在庫の更新など



    }
}

