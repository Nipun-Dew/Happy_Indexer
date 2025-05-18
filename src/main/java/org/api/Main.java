package org.api;

import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.api.model.HappyIndexEntry;
import org.api.store.HappyIndexDataStore;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws LifecycleException {

        try {
            // Extract Happy index data from the file and save it in the in-memory data store
            List<HappyIndexEntry> extractedData = extractData();

            // Initialize the extracted data in the data store
            HappyIndexDataStore.initialize(extractedData);

            // instantiate a tomcat embedded server and set configs
            Tomcat tomcat = new Tomcat();

            tomcat.setPort(8080);
            tomcat.getConnector();

            // Add base url for the server
            Context ctx = tomcat.addWebapp("api/", new File("src/main/webapp").getAbsolutePath());

            Tomcat.addServlet(ctx, "MainServlet", new MainServlet());
            ctx.addServletMappingDecoded("/happyIndex/*", "MainServlet");

            tomcat.start();

            System.out.println("Server started!!!");

            tomcat.getServer().await();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<HappyIndexEntry> extractData() {
        List<HappyIndexEntry> extractedData;

        try (FileReader fileReader = new FileReader("src/main/resources/2019.csv")) {
            extractedData = new CsvToBeanBuilder<HappyIndexEntry>(fileReader)
                    .withType(HappyIndexEntry.class).build().parse();

            return extractedData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
