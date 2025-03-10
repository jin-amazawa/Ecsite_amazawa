package servlet;

import java.io.IOException;

import dao.UsersDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        UsersDao usersDao = new UsersDao();
        boolean isRegistered = usersDao.registerUser(username, password);

        if (isRegistered) {
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            request.setAttribute("errorMessage", "登録に失敗しました。ユーザー名が既に使われている可能性があります。");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
        }
    }
}

