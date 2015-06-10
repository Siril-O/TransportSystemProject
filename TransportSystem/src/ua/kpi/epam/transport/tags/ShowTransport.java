/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.epam.transport.tags;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import ua.kpi.epam.transport.entities.Schedule;
import ua.kpi.epam.transport.entities.Transport;
import ua.kpi.epam.transport.extras.LocalizationHelper;

/**
 *
 * @author KIRIL
 */
public class ShowTransport extends SimpleTagSupport {

    private static final String END_TAG = "\" >";
    private static final String TABLE_CELL_START_COLSPAN = "<td colspan=\"";
    private static final String TABLE_CELL_END = "</td>";
    private static final String  TABLE_CELL_START= "<td>";
    private static final String TABLE_ROW_START = "<tr>";
    private static final String TABLE_ROW_END = "</tr>";
    private static final String END_TABLE_TAG = "</table></div></li>";
    private static final String ORDERED_LIST = "</ol>";
    private static final String MODEL = "Model";
    private static final String NUMBER = "Number";
    private static final String TYPE = "Type";
    private static final String ID = "ID";
    private static final String TRANSPORT = "Transport";
    private static final String SCHEDULE_ID = "ScheduleId";
    private static final String TABLE_START_TAG = "<table width=\"900px\" cellspacing=\"2\" border=\"1\" cellpadding=\"5\">";
    private static final String LI_TAG_WITH_CLASS = "<li><div class=\"";
    private static final String OL_TAG = "<ol>";
    
    
    private String map;
    private String className;

    /**
     *
     * @return
     */
    public String getMap() {
        return map;
    }

    /**
     *
     * @param map
     */
    public void setMap(String map) {
        this.map = map;
    }

    /**
     *
     * @return
     */
    public String getClassName() {
        return className;
    }

    /**
     *
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     *
     * @throws IOException
     */
    public void doTag() throws IOException {

        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        Map<Schedule, Transport> transportMap = (Map<Schedule, Transport>) request.getAttribute(map);

        LocalizationHelper lhelp = LocalizationHelper.getInstanse();

        JspWriter out = getJspContext().getOut();

        StringBuffer output = new StringBuffer();

        output.append(OL_TAG);
        for (Map.Entry<Schedule, Transport> item : transportMap.entrySet()) {

            output.append(LI_TAG_WITH_CLASS).append(className).append(END_TAG);
            output.append(TABLE_START_TAG);

            addRowStart(output);
            addCellStart(output, 3);
            output.append(lhelp.getLocalizedLabel(request, SCHEDULE_ID));
            addCellEnd(output);
            addCellStart(output);
            output.append(item.getKey().getId());
            addCellEnd(output);
            addRowEnd(output);
            addRowStart(output);
            addCellStart(output, 4);
            output.append(lhelp.getLocalizedLabel(request, TRANSPORT));
            addCellEnd(output);
            addRowEnd(output);

            addRowStart(output);
            addCellStart(output, 4);
            output.append(lhelp.getLocalizedLabel(request, ID));
            addCellEnd(output);
            addCellStart(output, 4);
            output.append(lhelp.getLocalizedLabel(request, TYPE));
            addCellEnd(output);
            addCellStart(output, 4);
            output.append(lhelp.getLocalizedLabel(request, NUMBER));
            addCellEnd(output);
            addCellStart(output, 4);
            output.append(lhelp.getLocalizedLabel(request, MODEL));
            addCellEnd(output);
            addRowEnd(output);

            addRowStart(output);
            addCellStart(output, 4);
            output.append(item.getValue().getId());
            addCellEnd(output);
            addCellStart(output, 4);
            output.append(item.getValue().getType());
            addCellEnd(output);
            addCellStart(output, 4);
            output.append(item.getValue().getNumber());
            addCellEnd(output);
            addCellStart(output, 4);
            output.append(item.getValue().getModel());
            addCellEnd(output);
            addRowEnd(output);
            output.append(END_TABLE_TAG);

        }
        output.append(ORDERED_LIST);
        out.write(output.toString());
    }

    private void addRowStart(StringBuffer result) {
        result.append(TABLE_ROW_START);
    }

    private void addRowEnd(StringBuffer result) {
        result.append(TABLE_ROW_END);
    }

    private void addCellStart(StringBuffer result) {
        result.append(TABLE_CELL_START);
    }

    private void addCellStart(StringBuffer result, int colspan) {
        result.append(TABLE_CELL_START_COLSPAN).append(colspan).append(END_TAG);
    }

 

    private void addCellEnd(StringBuffer result) {
        result.append(TABLE_CELL_END);

    }

}
