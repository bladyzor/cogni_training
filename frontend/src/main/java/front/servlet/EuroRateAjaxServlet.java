package front.servlet;

import back.service.NbpApiService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static back.utils.DateUtils.stringToDate;

@WebServlet(name = "myServlet", urlPatterns = {"/euroRate"})
public class EuroRateAjaxServlet extends HttpServlet {

    private NbpApiService nbpApiService;

    private static final String MIME_TYPE_APPLICATION_JSON = "application/json";
    private static final String KEY_DATE_PARAM = "date";
    private static final String NO_DATA_EXCEPTION_MESSAGE = "No data available for given date";
    private static final String INCORRECT_INPUT_EXCEPTION_MESSAGE = "Incorrect input value";

    @Override
    public void init() {
        this.nbpApiService = new NbpApiService();
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType(MIME_TYPE_APPLICATION_JSON);

        final String dateParam = request.getParameter(KEY_DATE_PARAM);
        Date date = null;
        Double euroRate = null;
        Object result = null;

        try {
            date = stringToDate(dateParam);
            euroRate = nbpApiService.getCachedValue(date);
        } catch (final ParseException e) {
            //logger
            result = INCORRECT_INPUT_EXCEPTION_MESSAGE;
        }

        if (euroRate != null) {
            result = euroRate;
        } else if(date != null){
            result = NO_DATA_EXCEPTION_MESSAGE;
        }

        sendAjaxResponse(response, result);
    }

    private void sendAjaxResponse(final HttpServletResponse response, final Object responseObject) throws IOException {
        response.getWriter().write(new Gson().toJson(responseObject));
    }
}