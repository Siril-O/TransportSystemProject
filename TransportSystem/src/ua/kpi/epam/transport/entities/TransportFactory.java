package ua.kpi.epam.transport.entities;

import ua.kpi.epam.transport.entities.enums.TransportType;

/**
 *
 * @author KIRIL
 */
public class TransportFactory {

	private static TransportFactory instance = new TransportFactory();

    /**
     *
     */
    public TransportFactory() {
		super();
	}

    /**
     *
     * @return
     */
    public static TransportFactory getInstance() {
		return instance;
	}

    /**
     *
     * @param id
     * @param type
     * @param number
     * @param model
     * @return
     */
    public Transport getTransport(Integer id, TransportType type,
			Integer number, String model) {
		switch (type) {
		case BUS: {
			return new Bus(id, model, number);
		}
		case TRAM: {
			return new Tram(id, model, number);
		}
		case TROLLEYBUS: {
			return new TrolleyBus(id, model, number);
		}
		default: {
			return null;
		}

		}
	}
}
