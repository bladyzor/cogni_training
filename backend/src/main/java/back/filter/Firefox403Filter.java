package back.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

@WebFilter(filterName = "firefox403Filter")
public class Firefox403Filter implements Filter {

    private static final String KEY_USER_AGENT = "User-Agent";
    private static final String STRING_MOZILLA = "mozilla";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final Calendar calendar = Calendar.getInstance();

        if (request.getHeader(KEY_USER_AGENT).toLowerCase().contains(STRING_MOZILLA) && calendar.get(Calendar.HOUR_OF_DAY) < 13) {
            try {
                response.sendError(403);
                return;
            } catch (IOException e) {
                //logger
            }
        }

        filterChain.doFilter(servletRequest, response);
    }
}