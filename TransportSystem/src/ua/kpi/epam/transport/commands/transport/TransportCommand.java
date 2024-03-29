package ua.kpi.epam.transport.commands.transport;

import ua.kpi.epam.transport.commands.Command;

/**
 *
 * @author KIRIL
 */
public interface TransportCommand extends Command {

    /**
     *
     */
    String ID_ATTRIBUTE = "transport_id";

    /**
     *
     */
    String MODEL_ATTRIBUTE = "transport_model";

    /**
     *
     */
    String NUMBER_ATTRIBUTE = "transport_number";

    /**
     *
     */
    String TYPE_ATTRIBUTE = "transport_type";

    /**
     *
     */
    String RESULT_ATTRIBUTE = "result";

    /**
     *
     */
    String TRANSPORT_RESULT_ATTRIBUTE ="transport";

    /**
     *
     */
    String RESULT_LIST_ATTRIBUTE = "transportList";

    /**
     *
     */
    String RESULT_MAP_ATTRIBUTE = "transportMap";

    /**
     *
     */
    String DESTINATION_PAGE = "./admin/transport.jsp";

    /**
     *
     */
    String DESTINATION_ADMIN_PAGE ="./admin/transport.jsp";
}
