package example.com.servlet;

import example.com.magazine.Cart;
import example.com.magazine.Magazine;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class RemoveFromCardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Отримуємо назву журналу, який потрібно видалити
        String titleToRemove = request.getParameter("title");

        HttpSession session = request.getSession();
        // Отримуємо поточний кошик
        List<Cart> cart = (List<Cart>) session.getAttribute("cart");

        if (cart != null) {
            // Видаляємо журнал з кошика
            cart.removeIf(magazine -> magazine.getTitle().equals(titleToRemove));
        }

        // Після видалення перенаправляємо на сторінку журналів
        response.sendRedirect("magazine.jsp");
    }

}
