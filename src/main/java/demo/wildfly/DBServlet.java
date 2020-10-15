package demo.wildfly;

import org.apache.commons.logging.*;

import javax.naming.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.time.*;
import java.time.format.*;

@WebServlet(name = "DBServlet", urlPatterns = {"connect"}, loadOnStartup = 1)
public class DBServlet extends HttpServlet {

    private static Log LOGGER = LogFactory.getLog(DBServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        InitialContext cxt;
        try {
            cxt = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
            return;
        }

        DataSource ds;
        try {

            ds = (DataSource) cxt.lookup("java:/WildflyAppDS");
            Connection connection;
            connection = ds.getConnection();

            connection.prepareStatement("CREATE TABLE IF NOT EXISTS logs (message varchar(200))").execute();

            PreparedStatement insert = connection.prepareStatement("INSERT INTO logs VALUES (?)");
            insert.setString(1, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
            insert.execute();

            String msg = "Connection success";
            LOGGER.info(msg);
            response.getWriter().print(msg);

            connection.close();

        } catch (Exception e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

}