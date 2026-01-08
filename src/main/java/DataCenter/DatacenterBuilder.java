package DataCenter;

import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.datacenters.Datacenter;
import org.cloudbus.cloudsim.datacenters.DatacenterSimple;
import org.cloudbus.cloudsim.hosts.Host;
import org.cloudbus.cloudsim.hosts.HostSimple;
import org.cloudbus.cloudsim.resources.Pe;
import org.cloudbus.cloudsim.resources.PeSimple;
import org.cloudbus.cloudsim.schedulers.vm.VmSchedulerTimeShared;

import java.util.ArrayList;
import java.util.List;

public class DatacenterBuilder {

    public static Datacenter createDatacenter(CloudSim simulation) {

        List<Host> hostList = new ArrayList<>();

        int mips = 1000;
        int ram = 16384;
        long storage = 1_000_000;
        int bw = 10000;

        List<Pe> peList = new ArrayList<>();
        peList.add(new PeSimple(mips));
        peList.add(new PeSimple(mips));

        Host host = new HostSimple(ram, bw, storage, peList);
        host.setVmScheduler(new VmSchedulerTimeShared());

        hostList.add(host);

        return new DatacenterSimple(
                simulation,
                hostList,
                new VmAllocationPolicySimple()
        );
    }
}
