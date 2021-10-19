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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StartTest extends HttpServlet {

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

            Connection c = Classes.Connect_To_Database.connect();

            String test_id = request.getParameter("l");

            PreparedStatement pd = c.prepareStatement("select * from test_headers where test_id = ?");
            pd.setString(1, test_id);

            ResultSet rs;
            rs = pd.executeQuery();

            rs.next();

            int time = Integer.parseInt(rs.getString("test_pass"));
            time = time * 60;

            out.println("<!DOCTYPE html>");

            out.println("<script>"
                    + ""
                    + "var timeout = '" + time + "';"
                    + ";\n"
                    + "function timer()\n"
                    + "{"
                    + "if( --timeout > 0 )\n"
                    + "{\n"
                    + "document.forma.clock.value = timeout;\n"
                    + "window.setTimeout( \"timer()\", 1000 );\n"
                    + "}\n"
                    + "else\n"
                    + "{\n"
                    + "document.forma.clock.value = \"Time over\";\n"
                    + ""
                    + "document.getElementById('t').submit();"
                    + "}"
                    + ""
                    + "}"
                    + ""
                    + "</script>");

            out.println("<style>\n"
                    + "	body{\n"
                    + "		background: white;\n"
                    + "		}\n"
                    + "	form{\n"
                    + "		text-align: center;\n"
                    + "		\n"
                    + "		width: 600px;\n"
                    + "		margin-left: 400px;\n"
                    + "		background: #fff;\n"
                    + "		border-radius: 15px;\n"
                    + "		box-shadow: rgba(221, 221, 221, 0.99) 1px 0px 20px;\n"
                    + "\n"
                    + "	}\n"
                    + "	.d{\n"
                    + "		margin-left: 20px; \n"
                    + "		margin-right: 298px;\n"
                    + "		margin-top: 60px;\n"
                    + "		width: 550px;\n"
                    + "		height: 20px;\n"
                    + "		padding: 20px 7px;\n"
                    + "		outline: none;\n"
                    + "		border: 1px solid blue;\n"
                    + "		border-radius: 15px;\n"
                    + "         max-width: 550px;"
                    + "	\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "	}\n"
                    + "	.d1{\n"
                    + "		margin-left: 50px;\n"
                    + "		margin-right: 350px;\n"
                    + "		margin-top: 25px;\n"
                    + "	}\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "	.sum{\n"
                    + "		margin-left: -1050px;\n"
                    + "		 margin-top: 50px;\n"
                    + "		 border-radius: 3px;\n"
                    + "	 \n"
                    + "		box-shadow: inset 0 1px 0 #8dc2f0;\n"
                    + "	width: 160px;\n"
                    + "	height: 35px;\n"
                    + "	\n"
                    + "	border-radius: 30px;\n"
                    + "		\n"
                    + "	background-color: green;\n"
                    + "	cursor: pointer;\n"
                    + "	color: white;\n"
                    + "	font-weight: bold;\n"
                    + "	text-shadow: 0 -1px 0 #336895;\n"
                    + "	\n"
                    + "	font-size: 15px;\n"
                    + "	}\n"
                    + "	</style>");

            out.println(""
                    + "<script>\n"
                    + "	var i = 1;\n"
                    + "	\n"
                    + ""
                    + "function temp(a){"
                    + " alert(a);"
                    + "}"
                    + ""
                    + "		function addelement(que,o1,o2,o3,o4,ca)\n"
                    + "		{\n"
                    + "				\n"
                    + ""
                    + "				var abc = document.createElement('textarea');\n"
                    + "					abc.type='text';\n"
                    + "					abc.className = 'd';\n"
                    + "					abc.name = \"a\"+i;\n"
                    + "					abc.value=que;\n"
                    + "					abc.setAttribute(\"required\",true);\n"
                    + "					abc.readOnly = true;\n"
                    + "					\n"
                    + "				var opt1 = document.createElement('input');\n"
                    + "					opt1.type='radio';\n"
                    + "					opt1.className = 'd1';\n"
                    + "					opt1.name = \"a\"+i+\"a\";\n"
                    + "					opt1.value=1;\n"
                    + "					opt1.setAttribute(\"required\",true);\n"
                    + "                         var label1 = document.createElement('input');\n"
                    + "                                     label1.type = 'text'; "
                    + "                                     label1.value=o1;"
                    + "                                     label1.readOnly = true;"
                    + "                                     label1.name = 'a'+i+'1';						"
                    + "			\n"
                    + "				var opt2 = document.createElement('input');\n"
                    + "					opt2.type='radio';\n"
                    + "					opt2.className = 'd1';\n"
                    + "					opt2.name = \"a\"+i+\"a\";\n"
                    + "					opt2.value=2;\n"
                    + "					opt2.setAttribute(\"required\",true);\n"
                    + "						\n"
                    + "					\n"
                    + "                         var label2 = document.createElement('input');\n"
                    + "                                     label2.type = 'text'; "
                    + "                                     label2.value=o2;"
                    + "                                     label2.readOnly = true;"
                    + "                                      label2.name = 'a'+i+'2';                            "
                    + "				var opt3 = document.createElement('input');\n"
                    + "					opt3.type='radio';\n"
                    + "					opt3.className = 'd1';\n"
                    + "					opt3.name = \"a\"+i+\"a\";\n"
                    + "					opt3.value=3;\n"
                    + "					opt3.setAttribute(\"required\",true);\n"
                    + "                         var label3 = document.createElement('input');\n"
                    + "                                     label3.type = 'text'; "
                    + "                                     label3.value=o3;"
                    + "                                     label3.readOnly = true;"
                    + "                                     label3.name = 'a'+i+'3';"
                    + ""
                    + "					\n"
                    + "				var opt4 = document.createElement('input');\n"
                    + "					opt4.type='radio';\n"
                    + "					opt4.className = 'd1';\n"
                    + "					opt4.name = \"a\"+i+\"a\";\n"
                    + "					opt4.value=4;\n"
                    + "					opt4.style=\"margin-right: 180px;\";\n"
                    + "					opt4.setAttribute(\"required\",true);\n"
                    + "					\n"
                    + "                         var label4 = document.createElement('input');\n"
                    + "                                     label4.type = 'text'; "
                    + "                                     label4.value=o4;"
                    + "                                     label4.name = 'a'+i+'4';"
                    + ""
                    + "                                     label4.readOnly = true;"
                    + "					\n"
                    + "				var cans = document.createElement('input');\n"
                    + "					cans.type='number';\n"
                    + "					cans.name = \"a\"+i+\"z\";\n"
                    + "					\n"
                    + "					cans.value=ca;\n"
                    + "					cans.style.visibility = \"hidden\";\n"
                    + "					\n"
                    + "				\n"
                    + "				document.getElementById(\"t\").appendChild(abc);\n"
                    + "				document.getElementById(\"t\").appendChild(opt1);"
                    + "                         document.getElementById(\"t\").appendChild(label1);"
                    + "				document.getElementById(\"t\").appendChild(opt2);\n"
                    + "                         document.getElementById(\"t\").appendChild(label2);"
                    + "				document.getElementById(\"t\").appendChild(opt3);\n"
                    + "                         document.getElementById(\"t\").appendChild(label3);"
                    + "				document.getElementById(\"t\").appendChild(opt4);\n"
                    + "                         document.getElementById(\"t\").appendChild(label4);"
                    + "				document.getElementById(\"t\").appendChild(cans);\n"
                    + "				\n"
                    + "				i++;\n"
                    + "				\n"
                    + "			}\n"
                    + "	\n"
                    + "	\n"
                    + "	\n"
                    + "</script>");

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StartTest</title>");
            out.println("</head>");
            out.println("<body>"
                    + ""
                    + "<form action='StudentDetails' name=\"forma\">\n"
                    + "Seconds remaining: <input type=\"text\" id='clock' name=\"clock\" style=\"border:0px solid white\">\n"
                    + "...\n"
                    + "</form>"
                    + "<script> timer(); </script>"
                    + ""
                    + "<div id =\"newelement\">	\n"
                    + "		<form method =\"post\" action=\"Result\" id=\"t\">\n"
                    + "				<button type='submit' class=\"sum\" >Submit</button>	\n"
                    + "				<br>\n"
                    + "		</form>\n"
                    + "	</div>"
                    + "");

            pd = c.prepareStatement("select * from test_questions where test_id = ?");
            pd.setInt(1, Integer.parseInt(test_id));

            pd.execute();

            rs = pd.executeQuery();
            int length = 0;
            while (rs.next()) {

                String question = rs.getString("question_name");
                String option_a = rs.getString("option_a");
                String option_b = rs.getString("option_b");
                String option_c = rs.getString("option_c");
                String option_d = rs.getString("option_d");
                String cans = rs.getString("option_answer");

                length++;
                out.println("<script> 	addelement('" + question + "' , '" + option_a + "' , '" + option_b + "' , '" + option_c + "' , '" + option_d + "' , '" + cans + "'); </script>");

            }

            HttpSession session = request.getSession();
            session.setAttribute("length", length);
            //System.out.println("lenght:"+length); 
            session.setAttribute("test_id", test_id);
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
            Logger.getLogger(StartTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StartTest.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(StartTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StartTest.class.getName()).log(Level.SEVERE, null, ex);
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
