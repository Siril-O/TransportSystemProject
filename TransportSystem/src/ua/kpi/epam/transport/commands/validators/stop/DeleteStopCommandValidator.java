/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.epam.transport.commands.validators.stop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.kpi.epam.transport.commands.validators.CommandValidator;
import ua.kpi.epam.transport.commands.validators.CommandValidatorHelper;
import static  ua.kpi.epam.transport.commands.stop.StopCommand.*;
import ua.kpi.epam.transport.extras.LocalizationHelper;
/**
 *
 * @author KIRIL
 */
public class DeleteStopCommandValidator implements CommandValidator{

     private static final String ERROR_MSG = "NotChoosedStopToDelete";

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) {

         String message = LocalizationHelper.getInstanse().getLocalizedMessage(request, ERROR_MSG);
         
        return CommandValidatorHelper.getInstance().isNullValidate(new String[]{ID_ATTRIBUTE},
                RESULT_ATTRIBUTE, DESTINATION_PAGE, message, request, response);
    }
    
    
}
