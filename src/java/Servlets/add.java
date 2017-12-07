/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import static java.lang.System.out;
import java.util.Random;

public class add extends HttpServlet {

    String generate() {
        char[] chars = "0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String name = req.getParameter("id3");
        String pwd = req.getParameter("id2");
        String email = req.getParameter("id1");
        Connection con = null;
        Statement stmt = null;

        try {
            //get a connection
            con = DriverManager.getConnection("jdbc:mysql://localhost/user", "root", "ATul1996@@");

            //Create a statement
            stmt = con.createStatement();
            String rand = generate();
            //Execute a query
            String exec = "INSERT INTO user" + " (user_id,email,pwd,name)" + " VALUES('" + rand + "', '" + email + "', '" + pwd + "','" + name + "')";
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
