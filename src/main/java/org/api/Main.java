package org.api;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws LifecycleException {

        try {
            // instantiate a tomcat embedded server
            Tomcat tomcat = new Tomcat();

            // config the server
            tomcat.setPort(8080);
            tomcat.getConnector();

            // Add base url for the server
            Context ctx = tomcat.addWebapp("api/", new File("src/main/webapp").getAbsolutePath());

            Tomcat.addServlet(ctx, "MainServlet", new MainServlet());
            ctx.addServletMappingDecoded("/happyIndex/*", "MainServlet");

            tomcat.start();

            System.out.println("Server started!!!");

            tomcat.getServer().await();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
