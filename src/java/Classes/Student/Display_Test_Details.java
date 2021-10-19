/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Display_Test_Details extends HttpServlet {

    // hs = hashmap
    // hm = hashset
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

        HashMap<Integer, String> hs = new HashMap<Integer, String>();
        HashMap<Integer, String> hs1 = new HashMap<>(); 
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println(""
                    + "<link href=\"https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.19.1/css/mdb.min.css\" rel=\"stylesheet\">"
                    + "<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>"
                    + "<link href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css\" rel=\"stylesheet\">"
                    + "<link href=\"https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap\" rel=\"stylesheet\">"
                    + "<link href=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.6.0/mdb.min.css\" rel=\"stylesheet\" >");

            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");

            Connection c = Classes.Connect_To_Database.connect();
            HttpSession session = request.getSession();

            String userid = (String) session.getAttribute("student_id");

            PreparedStatement pd = c.prepareStatement("select * from student where userid = ? and del=1");
            pd.setString(1, userid);

            ResultSet rs = pd.executeQuery();

            rs.next();

            String cla = rs.getString("class");
            String div = rs.getString("division");

            session.setAttribute("class", cla);
            session.setAttribute("division", div);
            
            
            pd = c.prepareStatement("select * from test_headers where class = ? and  (div = '0' or div = ?) ");


            pd.setString(1, cla);
            pd.setString(2, div);
            rs = pd.executeQuery();

            out.println(""
                    + " "
                    + "<form method='post' action='StartTest'>"
                    + "<input type='text' id='l' name='l' value='default' hidden> "
                    + "<table id='tab' class='table table-hover table-bordered results'>"
                    + "<thead>"
                    + "<tr>"
                    + "      <th class=\"th-sm\">TEST ID\n"
                    + "      </th>\n"
                    + "      <th class=\"th-sm\">TEST NAME\n"
                    + "      </th>\n"
                    + "      <th class=\"th-sm\">CLASS\n"
                    + "      </th>\n"
                    + "      <th class=\"th-sm\">DIVISION\n"
                    + "      </th>\n"
                    + "      <th class=\"th-sm\"> Test Time (minutes) \n"
                    + "      </th>\n"
                    + "      <th class=\"th-sm\">\n"
                    + "      </th>\n"
                    + "      <th class=\"th-sm\">\n"
                    + "      </th>\n"
                   
                    + "  </thead>\n");

            int no_of_test = 0;

            while (rs.next()) {
 
                String test_id = Integer.toString(rs.getInt("test_id"));
                
                String test_name = rs.getString("test_name");

                hs.put(Integer.parseInt(test_id), test_name);
                hs1.put(Integer.parseInt(test_id), rs.getString("test_pass"));
                           
                no_of_test++;

            }

            pd = c.prepareStatement("select * from test_history where student_id=? ");

            pd.setString(1, userid);

            rs = pd.executeQuery();

            String test_ids = "";

            if (rs.next()) {
                test_ids = rs.getString("test_ids");

                HashSet<Integer> hm = new HashSet<Integer>();

                int i = 1;
                String temp = "";

                while (i < test_ids.length()) {
                    while (i < test_ids.length() && test_ids.charAt(i) != ',') {
                        temp += test_ids.charAt(i);
                        i++;
                    }
                    i++;
                   
                  
                    hm.add(Integer.parseInt(temp));
                    temp = "";
                }

                for (Map.Entry mapElement : hs.entrySet()) {

                    Integer key = (Integer) mapElement.getKey();
                    String value = (String) mapElement.getValue();

                    String time = hs1.get(key);
                   
                    
                    if (hm.contains(key)) { // student appeared
                        out.println("<tbody><tr><td>" + key + "</td><td>" + hs.get(key) + "</td><td>" + cla + "</td>"
                                + "<td>" + div + "</td><td>"+time+"</td><td>"
                                + "</td><td>"
                                + "<td style='color: green;' >Appeared</td>"
                                + "</tr></tbody>");
                    } else // not appeared
                    {
                        out.println("<tbody><tr><td>" + key + "</td><td>" + hs.get(key) + "</td><td>" + cla + "</td>"
                                + "<td>" + div + "</td><td>"+time+"</td><td>"
                                + "</td><td>"
                                + "<td><button type='submit' onClick='start(this)' id='" + key + "' name='" + key + "'   class='btn btn btn-outline-success btn-rounded btn-sm my-0'><i class='fas fa-play  fa-2x'></i> START</button></td>"
                                + "</tr></tbody>");
                    }
                }

            } 
            else 
            {
                Iterator hmIterator = hs.entrySet().iterator();

                for (Map.Entry mapElement : hs.entrySet()) {

                    Object key =  mapElement.getKey();
                  
                    Object value =  mapElement.getValue();

                   String time = hs1.get(key);

                    out.println("<tbody><tr><td>" + key + "</td><td>" + value + "</td><td>" + cla + "</td>"
                            + "<td>" + div + "</td><td>"+time+"</td><td>"
                            + "</td><td>"
                            + "<td><button type='submit' onClick='start(this)' id='" + key + "' name='" + key + "'   class='btn btn btn-outline-success btn-rounded btn-sm my-0'><i class='fas fa-play  fa-2x'></i> START</button></td>"
                            + "</tr></tbody>");

                }
            }

            out.println("</table>"
                    + "</form>");

            out.println("<script>"
                    + "function start(btn)"
                    + "{"
                    + "document.getElementById('l').value = btn.id; "
                    + "}"
                    + "</script>");

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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Display_Test_Details.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Display_Test_Details.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Display_Test_Details.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Display_Test_Details.class.getName()).log(Level.SEVERE, null, ex);
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
