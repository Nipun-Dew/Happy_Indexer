package org.api;

import com.opencsv.bean.CsvToBeanBuilder;
import org.api.model.HappyIndexEntry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/*")
public class MainServlet extends HttpServlet {
    private final List<HappyIndexEntry> data = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        try {
            List<HappyIndexEntry> extractedData;

            try (FileReader fileReader = new FileReader("2019.csv")) {
                extractedData = new CsvToBeanBuilder<HappyIndexEntry>(fileReader)
                        .withType(HappyIndexEntry.class).build().parse();
            }

            data.clear();
            data.addAll(extractedData);

        } catch (IOException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getRequestURI()
                .substring(request.getContextPath().length())
                .replaceAll("[/]+$", "");

        response.setContentType("text/plain");

        switch (path) {
            case "/helloworld":
                response.getWriter().write("Hello World!, Worlds happiest country is " + data.getFirst().getCountry());
                break;
            case "/test":
                response.getWriter().write("This is the test endpoint!");
                break;
            case "":
                response.getWriter().write("Welcome to the root path!");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
