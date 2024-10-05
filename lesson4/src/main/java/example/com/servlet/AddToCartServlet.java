package example.com.servlet;

import example.com.magazine.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Отримуємо назву та ціну журналу
        String title = request.getParameter("title");
        String priceString = request.getParameter("price");
        double price = Double.parseDouble(priceString);

        Cart cart = new Cart(title,price);
        // Створюємо об'єкт для кошика

        // Отримуємо поточний кошик з сесії
        List<Cart> listOfcart = (List<Cart>) session.getAttribute("cart");
        if (listOfcart == null) {
            listOfcart = new ArrayList<>();
        }

        // Додаємо новий товар до кошика
        listOfcart.add(cart);

        // Оновлюємо кошик у сесії
        session.setAttribute("cart", listOfcart);

        // Підраховуємо загальну суму
        double totalPrice = 0;
        for (Cart item : listOfcart) {
            totalPrice += item.getPrice();
        }

        // Оновлюємо загальну суму у сесії
        session.setAttribute("totalPrice", totalPrice);

        // Повертаємося на сторінку з журналами
        response.sendRedirect("magazine.jsp");
    }


}
