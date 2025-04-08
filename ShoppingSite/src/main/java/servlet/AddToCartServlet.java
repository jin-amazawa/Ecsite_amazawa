package servlet;

import java.io.IOException;
import java.util.List;

import dao.ProductDao;
import dto.ProductDto;
import dto.UsersDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name="AddToCartServlet", urlPatterns={"/add-to-cart"})
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession(false);
    	UsersDto loginUser = (session != null) ? (UsersDto) session.getAttribute("loginUser") : null;

    	if (loginUser == null) {
    	    response.sendRedirect(request.getContextPath() + "/"); // 未ログインはトップへリダイレクト
    	    return;
    	}
    	
        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        
        
        
        
        //int id = Integer.parseInt(idParam);
        
        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        
        // ★ DBから商品を取得
        ProductDao dao = new ProductDao();
        ProductDto selected = dao.findById(id); // ← ここを修正！
        
        /*List<ProductDto> products = (List<ProductDto>) getServletContext().getAttribute("products");
        ProductDto selected = null;
        for(ProductDto p: products) {
            if(p.getId() == id) {
                selected = p;
                break;
            }
        }*/
        
        if(selected == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "商品が見つかりません");
            return;
        }
        //HttpSession session = request.getSession();
        List<ProductDto> cart = (List<ProductDto>) session.getAttribute("cart");
        if(cart == null) {
            cart = new java.util.ArrayList<>();
        }
        

		boolean found = false;
		for (ProductDto p : cart) {
		    if (p.getId() == selected.getId()) {
		        p.setStock(p.getStock() + 1); // 同じ商品なら個数を1増やす
		        found = true;
		        break;
		    }
		}
		
		if (!found) {
		    selected.setStock(1); // 初めて追加なら数量1に設定
		    cart.add(selected);
		}
        
        int total = 0;
        for(ProductDto p : cart) {
            total += p.getPrice() * p.getStock();
        }
        
        session.setAttribute("cart", cart);
        session.setAttribute("total", total);
        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
