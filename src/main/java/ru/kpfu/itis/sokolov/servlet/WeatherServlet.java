package ru.kpfu.itis.sokolov.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kpfu.itis.sokolov.helper.WeatherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "weatherServlet", urlPatterns = "/weather")
public class WeatherServlet extends HttpServlet {

    private final WeatherService weatherService = new WeatherService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        resp.sendRedirect("weatherpage.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");
        Map<String, String> result = weatherService.get(city);
        String json = new ObjectMapper().writeValueAsString(result);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        if(result == null) {
            resp.getWriter().write("No city");
        } else {
            resp.getWriter().write(json);
        }
    }
}