/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 1405093
 */
public class Demand_car extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        StringBuffer returndata = new StringBuffer();
        Connection con = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/vehicle", "root", "ATul1996@@");
            String from = req.getParameter("from");
            String to = req.getParameter("to");
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select name , car_no from vehicle where src='" + from + "' and dest='" + to + "' and avail="+1);

            returndata.append("<table class=\"table table-bordered\">");
            returndata.append("<tr><th>Vehicle Name</th><tr>");
            while (rs.next()) {
                String n = rs.getString("name");
                String g = rs.getString("car_no");
                returndata.append("<tr><td>"+ n + "<input type=\"checkbox\" class=\"check\" value="+g+"></td></tr>");
            }
            returndata.append("</table>");
            returndata.append("<input type=\"submit\" id=\"btn\" value=\"Submit\" class=\"btn btn-outline-light\" onclick=\"submit()\">");
            response.getWriter().write(returndata.toString());
            response.getWriter().flush();
            response.getWriter().close();
            con.close();
        } catch (SQLException | IOException e2) {
            out.println("e");
        }

    }
}
