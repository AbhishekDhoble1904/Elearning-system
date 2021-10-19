/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Admin;

import Classes.Connect_To_Database;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditStaff extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            Connection c = Connect_To_Database.connect();

            String ar[] = request.getParameter("l").split(",");
            String id = ar[0];

            if (id.equals("null")) {
                String sql = "delete from staff where userid is null";
                PreparedStatement pd = c.prepareStatement(sql);
                pd.execute();
                out.println("<script>window.location.replace('./DisplayStaffData');  </script> ");
                return;
            }

            int op = Integer.parseInt(ar[1]);

            if (op == 1) {
                String sql = "select * from staff where userid=?";
                PreparedStatement pd = c.prepareStatement(sql);
                pd.setString(1, id);

                ResultSet rs = pd.executeQuery();
                rs.next();

                String name = rs.getString("name");

                String phoneno = rs.getString("phoneno");
                String password = rs.getString("password");

                out.println(" <link rel='stylesheet' href='AddStudent.css'>");

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet EditStudent</title>");
                out.println("</head>");
                out.println("<body> "
                        + "<div class='edit-staff'>"
                        + "<form method='post' action='SaveEditedStaff' autocomplete='off' class='form' > "
                        /*out.println("<input type=\"text\" id=\"fname\" name=\"id\" value='"+id+"' readonly='readonly'>");
                //out.println("<input type=\"text\" id=\"fname\" name=\"class\" value='"+cla+"'>");
                //out.println("<input type=\"text\" id=\"fname\" name=\"division\" value='"+division+"'>");
                //out.println("<input type=\"text\" id=\"fname\" name=\"rollno\" value='"+rollno+"'>");
                //out.println("<input type=\"text\" id=\"fname\" name=\"name\" value='"+name+"'>");
                out.println("<input type=\"text\" id=\"fname\" name=\"password\" value='"+password+"'>");
                //out.println("<input type=\"text\" id=\"fname\" name=\"phoneno\" value='"+phoneno+"'>");
                out.println("<button type=\"submit\"> UPDATE </button>");
                         */
                        + "      <center><h2>UPDATE STAFF RECORD</h2></center>"
                        + "      <br>"
                        + "      <br>"
                        + "      <br>"
                        + "      <div class='input'>"
                        + "        <input type='text' id='fname' name='id' value='" + id + "' required onfocus='blur()'>"
                        + "        <label for='userid'>GR No.</label>"
                        + "      </div>"
                        + "      <div class='input'>"
                        + "        <input type='text' id='fname' name='password' value='" + password + "' required>"
                        + "        <label for='password'>Password</label>"
                        + "      </div>"
                        + "      <div class='input'>"
                        + "        <input type='text' id='fname' name='name' value='" + name + "' required>"
                        + "        <label for='password'>Name</label>"
                        + "      </div>"
                        + "      <div class='input'>"
                        + "        <input type='text' id='fname' name='phoneno' value='" + phoneno + "' required>"
                        + "        <label for='password'>Phone No.</label>"
                        + "      </div>"
                        + "      <button type=\"submit\" class=\"submit-btn\">UPDATE</button>\n"
                        + "    </form>"
                        + "  </div>"
                        + "");

                out.println("</body>");
                out.println("</html>");
            } else if (op == 2) {

                //String sql = "delete from staff where userid=?";
                String sql = "update staff set del=0 where userid=?";
                PreparedStatement pd = c.prepareStatement(sql);
                pd.setString(1, id);

                ResultSet rs = pd.executeQuery();

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet EditStaff</title>");
                out.println("</head>");
                out.println("<body> ");
                out.println("<script> alert('Staff Record Deleted'); window.location.replace('./DisplayStaffData');  </script> ");
                out.println("</body></html>");

            } else {
                out.println("<script>window.location.replace('./DisplayStaffData');  </script> ");

            }

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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditStaff.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditStaff.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
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
