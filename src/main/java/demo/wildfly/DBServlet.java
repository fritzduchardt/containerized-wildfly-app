package demo.wildfly;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DBServlet", urlPatterns = {"connect"}, loadOnStartup = 1)
public class DBServlet extends HttpServlet {

    private static Log LOGGER = LogFactory.getLog(DBServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        InitialContext cxt;
        try {
            cxt = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
            return;
        }

        DataSource ds;
        try {
            ds = (DataSource) cxt.lookup( "java:/WildflyAppDS" );
        } catch (NamingException e) {
            e.printStackTrace();
            return;
        }

        try {
            ds.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return;
        }
        String msg = "Connection success";
        LOGGER.info(msg);
        response.getWriter().print(msg);
    }

}