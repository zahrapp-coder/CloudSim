import org.cloudbus.cloudsim.vms.Vm;
import cloudlet.CloudletBuilder;
import vm.VmBuilder;
import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.datacenters.DatacenterSimple;
import org.cloudbus.cloudsim.cloudlets.Cloudlet;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class Main {

    public static void main(String[] args) throws Exception {

        int numberOfHosts = 2;
        int numberOfVms = 2;
        int numberOfCloudlets = 50;

        VmBuilder vmBuilder = new VmBuilder();
        CloudletBuilder cloudletBuilder = new CloudletBuilder();

        // ----------- 1️⃣ Impact du MIPS -----------
        long[] mipsValues = {500, 1000, 2000};
        DefaultCategoryDataset datasetMips = new DefaultCategoryDataset();

        for (long mips : mipsValues) {
            CloudSim simulation = new CloudSim();

            DatacenterSimple dc = DatacenterFactory.createDatacenter(
                    simulation, numberOfHosts, mips, 8192, 10000, 1000000
            );

            List<Vm> vmList = vmBuilder.createMyVms(numberOfVms, mips, 1, 2048, 5000, 20000);
            List<Cloudlet> cloudlets = cloudletBuilder.createMyCloudlets(numberOfCloudlets);

            DatacenterBrokerSimple broker = new DatacenterBrokerSimple(simulation);
            broker.submitVmList(vmList);
            broker.submitCloudletList(cloudlets);

            simulation.start();

            double total = broker.getCloudletFinishedList().stream()
                    .mapToDouble(Cloudlet::getActualCpuTime).sum();
            double avg = total / numberOfCloudlets;

            datasetMips.addValue(avg, "Temps Moyen", mips + " MIPS");
        }

        JFreeChart chartMips = ChartFactory.createBarChart(
                "Impact du MIPS sur le temps moyen",
                "MIPS du Host",
                "Temps moyen (s)",
                datasetMips
        );
        ChartFrame frameMips = new ChartFrame("MIPS", chartMips);
        frameMips.pack();
        frameMips.setVisible(true);
        frameMips.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // ----------- 2️⃣ Impact de la RAM -----------
        long[] ramValues = {4096, 8192, 16384};
        DefaultCategoryDataset datasetRam = new DefaultCategoryDataset();

        for (long ram : ramValues) {
            CloudSim simulation = new CloudSim();

            DatacenterSimple dc = DatacenterFactory.createDatacenter(
                    simulation, numberOfHosts, 1000, ram, 10000, 1000000
            );

            List<Vm> vmList = vmBuilder.createMyVms(numberOfVms, 1000, 1, (int)ram/2, 5000, 20000);
            List<Cloudlet> cloudlets = cloudletBuilder.createMyCloudlets(numberOfCloudlets);

            DatacenterBrokerSimple broker = new DatacenterBrokerSimple(simulation);
            broker.submitVmList(vmList);
            broker.submitCloudletList(cloudlets);

            simulation.start();

            double total = broker.getCloudletFinishedList().stream()
                    .mapToDouble(Cloudlet::getActualCpuTime).sum();
            double avg = total / numberOfCloudlets;

            datasetRam.addValue(avg, "Temps Moyen", ram + " MB");
        }

        JFreeChart chartRam = ChartFactory.createBarChart(
                "Impact de la RAM sur le temps moyen",
                "RAM du Host",
                "Temps moyen (s)",
                datasetRam
        );
        ChartFrame frameRam = new ChartFrame("RAM", chartRam);
        frameRam.pack();
        frameRam.setVisible(true);
        frameRam.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // ----------- 3️⃣ Impact de la bande passante -----------
        long[] bwValues = {5000, 10000, 20000};
        DefaultCategoryDataset datasetBw = new DefaultCategoryDataset();

        for (long bw : bwValues) {
            CloudSim simulation = new CloudSim();

            DatacenterSimple dc = DatacenterFactory.createDatacenter(
                    simulation, numberOfHosts, 1000, 8192, bw, 1000000
            );

            List<Vm> vmList = vmBuilder.createMyVms(numberOfVms, 1000, 1, 2048, bw/2, 20000);
            List<Cloudlet> cloudlets = cloudletBuilder.createMyCloudlets(numberOfCloudlets);

            DatacenterBrokerSimple broker = new DatacenterBrokerSimple(simulation);
            broker.submitVmList(vmList);
            broker.submitCloudletList(cloudlets);

            simulation.start();

            double total = broker.getCloudletFinishedList().stream()
                    .mapToDouble(Cloudlet::getActualCpuTime).sum();
            double avg = total / numberOfCloudlets;

            datasetBw.addValue(avg, "Temps Moyen", bw + " BW");
        }

        JFreeChart chartBw = ChartFactory.createBarChart(
                "Impact de la bande passante sur le temps moyen",
                "BW du Host",
                "Temps moyen (s)",
                datasetBw
        );
        ChartFrame frameBw = new ChartFrame("BW", chartBw);
        frameBw.pack();
        frameBw.setVisible(true);
        frameBw.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // ----------- 4️⃣ Impact du stockage -----------
        long[] storageValues = {1000000, 2000000, 4000000};
        DefaultCategoryDataset datasetStorage = new DefaultCategoryDataset();

        for (long storage : storageValues) {
            CloudSim simulation = new CloudSim();

            DatacenterSimple dc = DatacenterFactory.createDatacenter(
                    simulation, numberOfHosts, 1000, 8192, 10000, storage
            );

            List<Vm> vmList = vmBuilder.createMyVms(numberOfVms, 1000, 1, 2048, 5000, storage/2);
            List<Cloudlet> cloudlets = cloudletBuilder.createMyCloudlets(numberOfCloudlets);

            DatacenterBrokerSimple broker = new DatacenterBrokerSimple(simulation);
            broker.submitVmList(vmList);
            broker.submitCloudletList(cloudlets);

            simulation.start();

            double total = broker.getCloudletFinishedList().stream()
                    .mapToDouble(Cloudlet::getActualCpuTime).sum();
            double avg = total / numberOfCloudlets;

            datasetStorage.addValue(avg, "Temps Moyen", storage + " Storage");
        }

        JFreeChart chartStorage = ChartFactory.createBarChart(
                "Impact du stockage sur le temps moyen",
                "Storage du Host",
                "Temps moyen (s)",
                datasetStorage
        );
        ChartFrame frameStorage = new ChartFrame("Storage", chartStorage);
        frameStorage.pack();
        frameStorage.setVisible(true);
        frameStorage.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    }
}
