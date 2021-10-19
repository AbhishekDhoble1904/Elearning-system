/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes.Staff;

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

public class DisplayResult extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
         
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
            
            
                 out.println(""
                    + " "
                    + "<table id='tab' class='table table-hover table-bordered results'>"
                    + "<thead>"
                    + "<tr>"
                    + "      <th class=\"th-sm\">TEST ID\n"
                    + "      </th>\n"
                    + "      <th class=\"th-sm\">CLASS\n"
                    + "      </th>\n"
                    + "      <th class=\"th-sm\">DIVISION\n"
                    + "      </th>\n"
                    + "      <th class=\"th-sm\">ROLL NO\n"
                    + "      </th>\n"
                    + "      <th class=\"th-sm\"> NAME  \n"
                    + "      </th>\n"
                    + "      <th class=\"th-sm\">MARKS \n"
                    + "      </th>\n"
                    + "      <th class=\"th-sm\">\n"
                    + "      </th>\n"
                   
                    + "  </thead>\n");
            
            
            String info[] = request.getParameter("l").split(",");
            
            String test_id = info[0];
            String cla = info[1];
            String div = info[2];
            
            Connection c = Classes.Connect_To_Database.connect();
            
            String sql = "";
            PreparedStatement pd;
            ResultSet rs = null;
            
            if(div.equals("0")){
                sql = "select * from test_history where class=?";
                pd = c.prepareStatement(sql);
                pd.setString(1, cla);
                rs = pd.executeQuery();
            }
            else{
                sql = "select * from test_history where class=? and div = ?";
                pd = c.prepareStatement(sql);
                pd.setString(1, cla);
                pd.setString(2, div);
                rs = pd.executeQuery();
            }

            while(rs.next()){
                String test_ids[] = (rs.getString("test_ids").split(","));      // given tests
                int index = -1;
                
                for(int i = 1 ; i < test_ids.length ; i++){
                    if(test_ids[i].equals(test_id)){
                        index = i;
                        break;
                    }
                }
                
                if(index != -1){ // student appeared
                    
                    String marks[] = rs.getString("marks").split(",");
                            
                    pd = c.prepareStatement("select * from student where userid = ?");
                    pd.setString(1, rs.getString("student_id"));
                    ResultSet rs1 = pd.executeQuery();
                    rs1.next();
                    String name = rs1.getString("name");
                    String rollno = rs1.getString("rollno");
                    String division = rs1.getString("division");
                    
                    String test_marks = marks[index];
                    
                    
                    
                    out.println("<tbody><tr><td>" + test_id + "</td><td>" + cla + "</td><td>" + division + "</td>"
                                + "<td>" + rollno + "</td><td>"+name+"</td><td>"+test_marks+"</td>"
                                + "<td style='color: green;' >Appeared</td>"
                                + "</tr></tbody>");
                    
                    
                }
            }
            

            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DisplayResult.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DisplayResult.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DisplayResult.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DisplayResult.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
