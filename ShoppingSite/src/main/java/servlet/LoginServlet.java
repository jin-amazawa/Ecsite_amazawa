package servlet;

import java.io.IOException;

import dao.UsersDao;
import dto.UsersDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    //private static final String USERNAME = "user";
    //private static final String PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String username = request.getParameter("username");
        String password = request.getParameter("password");

       // if (USERNAME.equals(username) && PASSWORD.equals(password)) {
        
        UsersDao usersDao = new UsersDao();
        UsersDto user = usersDao.selectByIdAndPassword(username, password); //データベース認証
        
        if(user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("loginUser", user);
            session.setAttribute("username", user.getUsername());
            session.setMaxInactiveInterval(1800);
            
            // 管理者かどうかをチェック
            if ("admin".equals(user.getRole())) {
                session.setAttribute("isAdmin", true);
                response.sendRedirect(request.getContextPath() + "/admin"); // 管理者用ページへ
            } else {
                session.setAttribute("isAdmin", false);
                response.sendRedirect(request.getContextPath() + "/"); // 一般ユーザー用ページへ
            }
            
            //response.sendRedirect(request.getContextPath() + "/");
        } else {
        	
            request.setAttribute("errorMessage", "ユーザー名またはパスワードが正しくありません。");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}
