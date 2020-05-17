package task24.calculator.service;

import task24.calculator.model.ComputerEngine;

public class ComputeEngineCreator {
    private static final String NUMBER_OF_INSTANCES = "4";
    private static final String MACHINE_TYPE = "n1-standard-8 (vCPUs: 8, RAM: 30GB)";
    private static final String NUMBER_OF_GPU = "1";
    private static final String GPU_TYPE = "NVIDIA Tesla V100";
    private static final String LOCAL_SSD = "2x375 GB";
//    private static final String DATACENTER_LOCATION = "Frankfurt (europe-west3)";
    private static final String DATACENTER_LOCATION = "Frankfurt";
    private static final String COMMITTED_USAGE = "1 Year";

    public static ComputerEngine createNewComputerEngine() {
        return new ComputerEngine(NUMBER_OF_INSTANCES, MACHINE_TYPE, NUMBER_OF_GPU, GPU_TYPE, LOCAL_SSD,
                DATACENTER_LOCATION, COMMITTED_USAGE);
    }
}
