/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.epam.transport.commands.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KIRIL
 */
public interface CommandValidator {

    /**
     *
     * @param request
     * @param response
     * @return
     */
    boolean validate(HttpServletRequest request, HttpServletResponse response);
    
//    public boolean isNotNull(Object obj){
//        if(obj != null){
//            return true;
//        }else{
//            return false;
//        }
//    }

}

