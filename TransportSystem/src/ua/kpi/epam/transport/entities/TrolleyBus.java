package ua.kpi.epam.transport.entities;

import ua.kpi.epam.transport.entities.enums.TransportType;

/**
 *
 * @author KIRIL
 */
public class TrolleyBus extends Transport {

    /**
     *
     */
    public TrolleyBus() {
		super();
		// TODO Auto-generated constructor stub
	}

    /**
     *
     * @param id
     * @param model
     * @param number
     */
    public TrolleyBus(Integer id, String model, Integer number) {
		super(id, model, number, TransportType.TROLLEYBUS);

	}
}
