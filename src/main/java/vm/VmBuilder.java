package vm;


import org.cloudbus.cloudsim.vms.Vm;
import org.cloudbus.cloudsim.vms.VmSimple;

import java.util.ArrayList;
import java.util.List;

public class VmBuilder {

    public List<Vm> createMyVms(int numberOfVms, long mips, int pesNumber, int ram, long bw, long size) {
        List<Vm> vmList = new ArrayList<>();
        for (int i = 0; i < numberOfVms; i++) {
            Vm vm = new VmSimple(mips, pesNumber);
            vm.setRam(ram).setBw(bw).setSize(size);
            vmList.add(vm);
        }
        return vmList;
    }

}
