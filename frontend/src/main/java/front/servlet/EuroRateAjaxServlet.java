package front.servlet;

import back.service.NbpApiService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static back.utils.DateUtils.stringToDate;

@WebServlet(name = "myServlet", urlPatterns = {"/euroRate"})
public class EuroRateAjaxServlet extends HttpServlet {

    private NbpApiService nbpApiService;

    private static final Gson gson = new Gson();

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
        LocalDate date = null;
        Double euroRate = null;
        Object result = null;

        try {
            date = stringToDate(dateParam);
            euroRate = nbpApiService.getEuroRateByDate(date);
        } catch (final DateTimeParseException e) {
            //logger
            result = INCORRECT_INPUT_EXCEPTION_MESSAGE;
        }

        if (euroRate != null) {
            result = euroRate;
        } else if(date != null) {
            result = NO_DATA_EXCEPTION_MESSAGE;
        }

        sendJsonResponse(response, result);
    }

    private void sendJsonResponse(final HttpServletResponse response, final Object responseObject) throws IOException {
        response.getWriter().write(gson.toJson(responseObject));
    }
}