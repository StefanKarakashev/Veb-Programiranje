package mk.finki.ukim.mk.lab.web.Util;

import jakarta.servlet.http.HttpServletRequest;
import org.thymeleaf.context.WebContext;

public class ThymeLeafUtil {

    private static void updateIfValid(WebContext context, String variable, String parameter){
        if (parameter != null)
            context.setVariable(variable, parameter);
    }

    private static String getStringFromSession(HttpServletRequest req, String attributeName) {
        Object attr = req.getSession().getAttribute(attributeName);
        return attr != null ? attr.toString() : null;
    }


    public static void setCommonVariables(WebContext context, HttpServletRequest req) {
        String balloonName = getStringFromSession(req, "balloon");
        String balloonSize = getStringFromSession(req, "balloonSize");

        String clientName = getStringFromSession(req, "clientName");
        String clientAddress = getStringFromSession(req, "clientAddress");

        updateIfValid(context, "balloon_name", balloonName);
        updateIfValid(context, "balloon_size", balloonSize);
        updateIfValid(context, "client_name", clientName);
        updateIfValid(context, "client_address", clientAddress);
    }
}
