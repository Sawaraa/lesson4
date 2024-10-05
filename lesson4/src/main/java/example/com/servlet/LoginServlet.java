package example.com.servlet;

import java.io.IOException;

import example.com.user.User;
import example.com.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserService userService = UserService.getUserService();
        User user = userService.getUser(email);

        if (user == null) {
            request.setAttribute("errorMessage", "Invalid login or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else if (user.getPassword().equals(password)) {
            // Зберігаємо користувача у сесію після успішного логіну
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", user);  // зберігаємо об'єкт користувача у сесію
            request.setAttribute("email", email);
            request.getRequestDispatcher("magazine.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Invalid login or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
