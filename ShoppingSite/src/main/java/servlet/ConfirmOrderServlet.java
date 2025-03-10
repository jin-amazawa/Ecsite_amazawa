package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import servlet.model.Product;

@WebServlet("/confirm-order")
public class ConfirmOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        
        System.out.println("カート内容:");
        for (Product item : cart) {
            System.out.println("商品名: " + item.getName() + ", 価格: " + item.getPrice() + ", 数量: " + item.getQuantity());
        }


        if (cart == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
            return;
        }

        
        // 注文処理のロジックをここに記述
        // 例: データベースに注文情報を保存、在庫の更新など


        // 注文完了ページへリダイレクト
        response.sendRedirect(request.getContextPath() + "/views/order-complete.jsp");
    }
}

