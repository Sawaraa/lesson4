package example.com.servlet;

import example.com.magazine.Magazine;
import example.com.magazine.MagazineService;
import example.com.user.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MagazineService magazineService = MagazineService.getMagazineService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("adminPanel.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String author = request.getParameter("author");
        String pages = request.getParameter("pages");
        String price = request.getParameter("price");

        int initialSize = magazineService.getListOfMagazine().size();

        Magazine magazine = new Magazine(title,description,author,pages,price);
        magazineService.saveMagazine(magazine);

        HttpSession session = request.getSession();

        List<Magazine> magazineList = (List<Magazine>) session.getAttribute("magazines");

        if (magazineList == null) {
            // Якщо списку ще немає, створюємо новий
            magazineList = new ArrayList<>();
        }

        magazineList.add(magazine);
        session.setAttribute("magazines", magazineList);

        // Перевірка, чи було додано новий журнал
        boolean isMagazineAdded = checkIfMagazineAdded(initialSize);

        if (isMagazineAdded) {
            request.setAttribute("message", "New magazine added successfully!");
        } else {
            request.setAttribute("message", "Failed to add new magazine.");
        }

        request.getRequestDispatcher("adminPanel.jsp").forward(request, response);

    }

    // Метод для перевірки чи додався новий журнал
    private boolean checkIfMagazineAdded(int initialSize) {
        List<Magazine> magazines = magazineService.getListOfMagazine();
        int newSize = magazines.size();
        return newSize > initialSize;  // Повертає true, якщо розмір списку збільшився
    }

}
