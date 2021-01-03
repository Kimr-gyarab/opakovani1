package main.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRulesException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {""})
public class MoonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long unixTime = System.currentTimeMillis();
        long moonTime = (unixTime - 1578334440L) / 1000L;
        long length = (long) (29.53058770576 * 24 * 60 * 60);
        double moonPhase = (double) (moonTime % length) / length * 100;
        double moon = 0;
        if (moonPhase <= 50) {
            moon = moonPhase * 2;
        }
        if (moonPhase > 50) {
            moon = 2 * (100 - moonPhase);
        }
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "</head>" +
                    "<body>" +
                    moon + "% mesice" +
                    "<br>" +
                    "</body>" +
                    "</html>");
        }
    }
}