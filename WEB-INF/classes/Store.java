import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.IOException;
import java.util.*;

public class Store extends HttpServlet {

    private String jdbcDriver   = "";
    private String jdbcURL      = "";
    private String jdbcUser     = "";
    private String jdbcPass     = "";
    private String homeURL      = "";


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = getServletContext();

        jdbcDriver  = context.getInitParameter("jdbcDriver");
        jdbcURL     = context.getInitParameter("jdbcURL");
        jdbcUser    = context.getInitParameter("jdbcUser");
        jdbcPass    = context.getInitParameter("jdbcPass");
        homeURL     = context.getInitParameter("homeURL");
    }




public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException
{
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String redir = request.getHeader("referer"); // Yes, with the legendary misspelling.

        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if(ipAddress == null){
            ipAddress = request.getRemoteAddr();
        }

        String name=request.getParameter("name");
        String timing=request.getParameter("timing");
        String medicine=request.getParameter("medicine");
        String quantity=request.getParameter("quantity");
        String prize=request.getParameter("prize");

        try{
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(jdbcURL ,jdbcUser,jdbcPass);

            String query = "INSERT into healthhack.vendor values(?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString   (1, name);
            preparedStmt.setString   (2, timing);
            preparedStmt.setString   (3, medicine);
            preparedStmt.setString   (4, quantity);
            preparedStmt.setString   (5, prize);
            preparedStmt.setString   (6, ipAddress);
            preparedStmt.executeUpdate();

            out.println("ok");
            conn.close();
        }
        catch (Exception e){
          out.println(e);
        }



        out.close();
   }
}
