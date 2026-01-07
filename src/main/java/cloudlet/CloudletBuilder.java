package cloudlet;

import org.cloudbus.cloudsim.cloudlets.Cloudlet;
import org.cloudbus.cloudsim.cloudlets.CloudletSimple;
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModel;
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModelFull;

import java.util.ArrayList;
import java.util.List;

public class CloudletBuilder {
    public List<Cloudlet> createMyCloudlets(int numberOfCloudlets) {
        List<Cloudlet> cloudletList = new ArrayList<>();

        // Parameters
        long length = 10000; // Millions of Instructions
        long pesNumber = 1;  // Number of CPU cores

        for (int i = 0; i < numberOfCloudlets; i++) {
            // 1. Use CloudletSimple instead of Cloudlet
            Cloudlet cloudlet = new CloudletSimple(length, pesNumber);

            // 2. Set the utilization model (How much CPU/RAM it uses)
            cloudlet.setUtilizationModel(new UtilizationModelFull());

            cloudletList.add(cloudlet);
        }

        return cloudletList;
    }
}
