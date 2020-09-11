package demo.wildfly;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConfigurationServlet", urlPatterns = {"config"}, loadOnStartup = 1)
public class ConfigurationServlet extends HttpServlet {

    private static Log LOGGER = LogFactory.getLog(ConfigurationServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName("wildflyapp-env.properties"));
        try {
            Configuration config = builder.getConfiguration();
            String val = config.getString(request.getParameter("key"));
            response.getWriter().print(val);
            LOGGER.info("Successfully read value from properties");
        }
        catch(ConfigurationException | IOException cex) {
            LOGGER.error("Failure to read property", cex);
        }
    }

}