package example.com.servlet;

import example.com.magazine.Magazine;
import example.com.magazine.MagazineService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class MagazineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MagazineService magazineService = MagazineService.getMagazineService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Отримуємо список журналів
        List<Magazine> magazines = magazineService.getListOfMagazine();

        // Встановлюємо атрибут для передачі на JSP сторінку
        request.setAttribute("magazines", magazines);

        // Переадресовуємо на сторінку з журналами
        request.getRequestDispatcher("magazine.jsp").forward(request, response);
    }
}
