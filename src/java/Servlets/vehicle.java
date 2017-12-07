/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class vehicle extends HttpServlet {

        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String no = req.getParameter("id3");
        String to = req.getParameter("id2");
        String from = req.getParameter("id1");
        String model = req.getParameter("id4");
        Connection con = null;
        Statement stmt = null;

        try {
            //get a connection
            con = DriverManager.getConnection("jdbc:mysql://localhost/vehicle", "root", "ATul1996@@");

            //Create a statement
            stmt = con.createStatement();
            //Execute a query
            String exec = "INSERT INTO vehicle" + " (dest,src,avail,name,car_no)" + " VALUES('" + to + "', '" +from + "', '" +1+ "','" + model + "', '"+ no + "');";
            int i = stmt.executeUpdate(exec);
            out.println("insertion done");
            if (i > 0) {
                out.println("Inserted Successfully");
            } else {
                out.println("Insert Unsuccessful");
            }
            res.setContentType("text/html");
            StringBuffer returndata = new StringBuffer("{\"topic\":{");
            returndata.append("\"name\": ");
            returndata.append("\"");
            returndata.append(i);
            returndata.append("\"");
            returndata.append("}}");
            res.getWriter().write(returndata.toString());
            res.getWriter().flush();
            res.getWriter().close();
        } catch (SQLException e) {
            out.println(e);
        }
    }
}
