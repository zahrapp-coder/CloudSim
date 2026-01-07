package vm;


import org.cloudbus.cloudsim.vms.Vm;
import org.cloudbus.cloudsim.vms.VmSimple;

import java.util.ArrayList;
import java.util.List;

public class VmBuilder {

    public List<Vm> createMyVms(int numberOfVms) {
        List<Vm> vmList = new ArrayList<>();

        int mips = 1000;
        int pesNumber = 1;

        for (int i = 0; i < numberOfVms; i++) {
            // In CloudSim Plus, we use VmSimple
            Vm vm = new VmSimple(mips, pesNumber);
            vm.setRam(512).setBw(1000).setSize(10000);
            vmList.add(vm);
        }

        return vmList;
    }
}
