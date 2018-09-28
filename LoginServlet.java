/****************************************************
* Patricia Renee Taylor 9/10/18
* CIST 2373 
* Lab 2 
* Part II – Next, build a simple Servlet called LoginServlet in your 
* “ChattBank” Project. Now make it so that when the Customer logs in, 
* the LoginServlet will get called and will validate the user id and password.
* ++++
* Part III – Now, modify the LoginServlet.  
* 1.)	Make it so that when the Servlet gets called, it reads the id and 
* password from the Login Form.  
* Use :    request.getParameter() to get these items.  
* At first just read in these 2 strings and display them to the Server Log. 
* 2.) If the id = “admin” and the Password = “123”, return an HTML page that 
* says “Valid Login”. 
* 3.) If not return an HTML page that says “InValid Login”.  Use out.println() 
* to send these HTML messages.  
* 4.) Test out your WebApp.
****************************************************/

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String pwdb;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        
        String id = request.getParameter("userName");
         System.out.println("id = "+id);
        String pw = request.getParameter("password");
         System.out.println("pw = " +pw); 
         
/* open connection to data base*/         
         try{
            System.out.println("Starting to open DB");
            Connection con = null; 
            
            String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
            Class.forName(driver);
            con = 
              //DriverManager.getConnection("jdbc:ucanaccess://C:/Use"
              //+ "rs/ptaylo14/Desktop/ChattBankMDB.mdb");
            
            DriverManager.getConnection("jdbc:ucanaccess://C:/Users/prtaylor/"
                    + "Desktop/ChattBankMDB.mdb");      
           
            Statement stmt;    
            stmt = con.createStatement();
            
/* create sql query, print to log to verify*/
            String sql;
            sql = "select * from Customers where CustID = " + id;
            System.out.println(sql);
            
/* get password from db, searching on the id*/            
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            rs.next();
                pwdb = rs.getString("CustPassword");
                System.out.println(pwdb);
            con.close();    
 
        }catch(Exception e){
                System.out.println(e);  
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ChattBank Login Result</title>");            
            out.println("</head>");
            out.println("<body>");
                
            if( pw.equals(pwdb) ) {
                out.println("<h1>Welcome to ChattBank, valued customer!</h1>");
            }else{
                out.println("<h1>Wrong password.</h1>");
            }
            
            out.println("<br><br><h2>Servlet LoginServlet at " + request.getContextPath() + "</h2>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
