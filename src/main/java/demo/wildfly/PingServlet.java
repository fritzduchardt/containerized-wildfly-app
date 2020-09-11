package demo.wildfly;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PingServlet", urlPatterns = {"ping"}, loadOnStartup = 1)
public class PingServlet extends HttpServlet {

    private static Log LOGGER = LogFactory.getLog(PingServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String msg = "Pong";
        response.getWriter().print(msg);
        LOGGER.info(msg);
    }

}