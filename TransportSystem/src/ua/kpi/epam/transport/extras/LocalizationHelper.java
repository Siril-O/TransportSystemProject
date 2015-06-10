/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.epam.transport.extras;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static ua.kpi.epam.transport.commands.user.ChangeLocaleCommand.LOCALE_ATTRIBUTE;

/**
 *
 * @author KIRIL
 */
public class LocalizationHelper {

    /**
     *
     */
    public static LocalizationHelper instance = new LocalizationHelper();

    private static final String MSG_PROPERTIE_BASE_PATH = "ua.kpi.epam.transport.properties.messages";
    private static final String LABEL_PROPERTIE_BASE_PATH = "ua.kpi.epam.transport.properties.labels";
    private static final String ERROR_MSG_PROPERTIE_BASE_PATH = "ua.kpi.epam.transport.properties.error_messages";

    private LocalizationHelper() {

    }

    /**
     *
     * @return
     */
    public static LocalizationHelper getInstanse() {
        return instance;
    }

    /**
     *
     * @param request
     * @param resourceName
     * @return
     */
    public String getLocalizedMessage(HttpServletRequest request, String resourceName) {
        return getString(request, resourceName, MSG_PROPERTIE_BASE_PATH);
    }

    /**
     *
     * @param resourceName
     * @return
     */
    public String getLocalizedErrorMsg(String resourceName) {
        return ResourceBundle.getBundle(ERROR_MSG_PROPERTIE_BASE_PATH).getString(resourceName);
    }

    /**
     *
     * @param request
     * @param resourceName
     * @return
     */
    public String getLocalizedLabel(HttpServletRequest request, String resourceName) {
        return getString(request, resourceName, LABEL_PROPERTIE_BASE_PATH);
    }

    /**
     *
     * @param request
     * @param resourceName
     * @param propertiePath
     * @return
     */
    public String getString(HttpServletRequest request, String resourceName, String propertiePath) {

        HttpSession session = request.getSession(false);

        Locale locale = null;
        if (session != null && session.getAttribute(LOCALE_ATTRIBUTE) != null) {
            locale = (Locale) session.getAttribute(LOCALE_ATTRIBUTE);
        } else {
            locale = request.getLocale();
        }
        return ResourceBundle.getBundle(propertiePath, locale).getString(resourceName);
    }
}
