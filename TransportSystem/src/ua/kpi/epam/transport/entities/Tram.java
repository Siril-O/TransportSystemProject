package ua.kpi.epam.transport.entities;

import ua.kpi.epam.transport.entities.enums.TransportType;

/**
 *
 * @author KIRIL
 */
public class Tram extends Transport {

    /**
     *
     */
    public Tram() {
		super();
		// TODO Auto-generated constructor stub
	}

    /**
     *
     * @param id
     * @param model
     * @param number
     */
    public Tram(Integer id, String model, Integer number) {
		super(id, model, number, TransportType.TRAM);

	}

}
