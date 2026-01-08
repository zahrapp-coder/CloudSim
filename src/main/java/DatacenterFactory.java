import org.cloudbus.cloudsim.core.Simulation;
import org.cloudbus.cloudsim.datacenters.DatacenterSimple;
import org.cloudbus.cloudsim.hosts.Host;
import org.cloudbus.cloudsim.hosts.HostSimple;
import org.cloudbus.cloudsim.resources.Pe;
import org.cloudbus.cloudsim.resources.PeSimple;
import org.cloudbus.cloudsim.schedulers.vm.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicySimple;

import java.util.ArrayList;
import java.util.List;

public class DatacenterFactory {

    // Méthode dynamique avec toutes les caractéristiques
    public static DatacenterSimple createDatacenter(
            Simulation simulation,
            int numberOfHosts,
            long hostMips,
            long hostRam,
            long hostBw,
            long hostStorage
    ) {
        List<Host> hostList = new ArrayList<>();

        for (int i = 0; i < numberOfHosts; i++) {
            List<Pe> peList = new ArrayList<>();
            peList.add(new PeSimple(hostMips)); // MIPS dynamique

            Host host = new HostSimple(hostRam, hostBw, hostStorage, peList);
            host.setVmScheduler(new VmSchedulerTimeShared());
            hostList.add(host);
        }

        return new DatacenterSimple(simulation, hostList, new VmAllocationPolicySimple());
    }
}
