package Servlets;



 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class check1 extends HttpServlet {
    
String pwd;
 @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {      
String name=req.getParameter("id"); 
String tab = req.getParameter("id1"); 
/*String[] ar = name.split(",");
String email = ar[0];
String table = ar[1];*/
Connection con=null;        
Statement stmt=null;        
PrintWriter out=res.getWriter();        

        try{
            //get a connection
               con=DriverManager.getConnection("jdbc:mysql://localhost/"+tab,"root","ATul1996@@");
            
            //Create a statement
            stmt=con.createStatement();              

           //Execute a query
            int count=0;
              String query ="select * from "+tab+" where email = ?";
              String query1="select pwd from "+tab+"  where email = ?";
PreparedStatement st =con.prepareStatement(query);
st.setString(1,name);
ResultSet resultSet = st.executeQuery(); 
       try{
        while(resultSet.next())
            {
                ++count;
            }
       }catch(SQLException e)
       {
           System.out.println(e);
       }
       PreparedStatement st1 =con.prepareStatement(query1);
st1.setString(1,name);
ResultSet resultSet1 = st1.executeQuery();
   try{
        while(resultSet1.next())
            {
                pwd=resultSet1.getString("pwd");
            }
       }catch(SQLException e)
       {
           System.out.println(e);
       }
       res.setContentType("text/html");
       StringBuffer  returndata =new StringBuffer("{\"topic\":{");
returndata.append("\"name\": ");
   returndata.append("\"");
   returndata.append(tab);
   returndata.append("\"");
   returndata.append(",");
   returndata.append("\"name1\": ");
   returndata.append("\"");
   returndata.append(pwd);
   returndata.append("\"");
    returndata.append("}}");
       res.getWriter().write(returndata.toString());
       res.getWriter().flush();
res.getWriter().close();
}        
catch(SQLException e)        
{          out.println(e);               
 }
     
    }

}
