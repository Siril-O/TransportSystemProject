package ua.kpi.epam.transport.entities;

import ua.kpi.epam.transport.entities.enums.TransportType;

/**
 *
 * @author KIRIL
 */
public class Bus extends Transport {

    /**
     *
     */
    public Bus() {
		super();
	}

    /**
     *
     * @param id
     * @param model
     * @param number
     */
    public Bus(Integer id, String model, Integer number) {
		super(id, model, number, TransportType.BUS);

	}
}
