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

public class Go extends HttpServlet {

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

        out.print(ipAddress+"#");
        String fname=request.getParameter("fname");
        //out.print(fname);




        PdfReader pdfReader = new PdfReader("/tmp/"+fname);
        int pages = pdfReader.getNumberOfPages();
        for(int i=0;i<pages;i++){
        String content = PdfTextExtractor.getTextFromPage(pdfReader, 1);
        content = content.toLowerCase();
        content = content.replace(",", " ");
        content = content.replace(".", " ");
        content = content.replace("!", " ");
        content = content.replace(":", " ");
        content = content.replace("-", " ");
        content = content.replace("`", " ");


Statement st = null;
        try{
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(jdbcURL ,jdbcUser,jdbcPass);
            st = conn.createStatement();
        }
        catch (Exception e){
             // out.println("Got an exception! ");
              out.println(e);
            }

        
        String[] words = content.split("\\s+");
        for(String w:words){
            //out.println(w);

            if(!w.equals(" ")){

            try{

                String query = "SELECT * FROM healthhack.vendor WHERE Medicine=\""+w+"\" ;";
                ResultSet rs = st.executeQuery(query);
                while(rs.next()){

                    String name = rs.getString(1);
                    String timing = rs.getString(2);
                    String medicine = rs.getString(3);
                    String quantity = rs.getString(4);
                    String prize = rs.getString(5);
                    String location = rs.getString(6);
                    out.println(name+"|"+timing+"|"+medicine+"|"+quantity+"|"+prize+"|"+location+"+");
                }
            }
            catch (Exception e){
             // out.println("Got an exception! ");
              out.println(e);
            }
        }
            

        }
        }
        pdfReader.close();



        out.close();
   }
}
