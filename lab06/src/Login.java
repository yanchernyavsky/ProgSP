import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/Login")
public class Login extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String massive = request.getParameter("massive");
        String choice = request.getParameter("choice");
        PrintWriter writer = response.getWriter();
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(massive);
        ArrayList<Integer> mass = new ArrayList<Integer>();
        while (m.find())
        {
            int n = Integer.parseInt(m.group());
            mass.add(n);
            // append n to list
        }
        if(choice.equals("Up"))
        {
            Collections.sort(mass);
        }
        else
        {
            Collections.sort(mass);
            Collections.reverse(mass);
        }
        massive = "";
        for (Integer element: mass)
        {
                massive += element.toString() + "  ";
        }
        request.setAttribute("result", massive);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}
