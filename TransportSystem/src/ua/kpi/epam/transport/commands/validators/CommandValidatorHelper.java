/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.epam.transport.commands.validators;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KIRIL
 */
public class CommandValidatorHelper {

    /**
     *
     */
    public static CommandValidatorHelper instance = new CommandValidatorHelper();

    private CommandValidatorHelper() {
    }

    /**
     *
     * @return
     */
    public static CommandValidatorHelper getInstance() {
        return instance;
    }

    /**
     *
     * @param attributes
     * @param resultAttribute
     * @param destPage
     * @param errorMgs
     * @param request
     * @param response
     * @return
     */
    public boolean isNullValidate(String[] attributes, String resultAttribute,
            String destPage, String errorMgs, HttpServletRequest request,
            HttpServletResponse response) {

        boolean result = true;
        for (String attribute : attributes) {
            if (request.getParameter(attribute) == null) {
                result = false;
                request.setAttribute(resultAttribute, errorMgs);
                try {
                    request.getRequestDispatcher(destPage).forward(
                            request, response);
                } catch (ServletException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     *
     * @param attributes
     * @param resultAttribute
     * @param destPage
     * @param errorMgs
     * @param request
     * @param response
     * @return
     */
    public boolean isEmptyValidate(String[] attributes, String resultAttribute,
            String destPage, String errorMgs, HttpServletRequest request,
            HttpServletResponse response) {

        boolean result = true;
        for (String attribute : attributes) {
            if (request.getParameter(attribute).isEmpty()) {
                result = false;
                request.setAttribute(resultAttribute, errorMgs);
                try {
                    request.getRequestDispatcher(destPage).forward(
                            request, response);
                } catch (ServletException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     *
     * @param attribute
     * @param regEx
     * @param resultAttribute
     * @param destPage
     * @param errorMgs
     * @param request
     * @param response
     * @return
     */
    public boolean matchesValidate(String attribute, String regEx, String resultAttribute,
            String destPage, String errorMgs, HttpServletRequest request,
            HttpServletResponse response) {

        boolean result = true;

        if (!request.getParameter(attribute).matches(regEx)) {
            result = false;
            request.setAttribute(resultAttribute, errorMgs);
            try {
                request.getRequestDispatcher(destPage).forward(
                        request, response);
            } catch (ServletException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return result;
    }
}
