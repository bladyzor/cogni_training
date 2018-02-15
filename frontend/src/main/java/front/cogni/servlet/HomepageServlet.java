package front.cogni.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "homepageServlet", urlPatterns = {"/home"})
public class HomepageServlet extends HttpServlet {

    private static final String INDEX_JSP = "index.jsp";

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(INDEX_JSP).forward(request, response);
    }
}