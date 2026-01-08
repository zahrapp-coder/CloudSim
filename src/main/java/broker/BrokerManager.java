package broker;

import org.cloudbus.cloudsim.brokers.DatacenterBroker;
import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple;
import org.cloudbus.cloudsim.core.CloudSim;

public class BrokerManager {

    public static DatacenterBroker createBroker(CloudSim simulation) {
        return new DatacenterBrokerSimple(simulation);
    }
}
