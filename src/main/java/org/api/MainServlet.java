package org.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.api.model.HappyIndexEntry;
import org.api.store.HappyIndexDataStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/*")
public class MainServlet extends HttpServlet {
    private List<HappyIndexEntry> data = null;

    @Override
    public void init() {
        data = HappyIndexDataStore.getData();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // extract the routing substring of the url
        String path = request.getRequestURI()
                .substring(request.getContextPath().length())
                .replaceFirst("^/[^/]+", "");

        // log each request to the servlet
        System.out.println(path);

        // Set as the default content type
        response.setContentType("text/plain");

        // Router logic
        switch (path) {
            case "/index":
                String responseString = getDetailsByCountry(request);
                sendJsonMessage(responseString, response);
                break;
            case "/happiest":
                response.getWriter().write("World's happiest country is " + data.getFirst().getCountry());
                break;
            case "/test":
                response.getWriter().write("This is the test endpoint!");
                break;
            case "":
                response.getWriter().write("Welcome to the Happy Index API!, use correct endpoints to fetch the data.");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private String getDetailsByCountry(HttpServletRequest request) throws IOException {
        String country = request.getParameter("country").toLowerCase();

        HappyIndexEntry happyIndexEntry = data.stream()
                .filter(entry -> entry.getCountry().toLowerCase().equals(country))
                .findAny().orElse(null);

        if (happyIndexEntry == null) {
            throw new IOException("No data found for the country " + country);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(happyIndexEntry);
    }

    private void sendJsonMessage(String jsonString, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(jsonString);
        out.flush();
    }
}
