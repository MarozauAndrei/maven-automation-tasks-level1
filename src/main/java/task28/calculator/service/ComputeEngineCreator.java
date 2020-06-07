package task28.calculator.service;

import task28.calculator.model.ComputerEngine;

public class ComputeEngineCreator {

  public static final String NUMBER_OF_INSTANCES = "testdata.computerEngine.numberOfInstances";
  public static final String MACHINE_TYPE = "testdata.computerEngine.machineType";
  public static final String NUMBER_OF_GPU = "testdata.computerEngine.numberOfGpu";
  public static final String GPU_TYPE = "testdata.computerEngine.GpuType";
  public static final String LOCAL_SSD = "testdata.computerEngine.LocalSsd";
  public static final String DATACENTER_LOCATION = "testdata.computerEngine.datacenterLocation";
  public static final String COMMITTED_USAGE = "testdata.computerEngine.committedUsage";

  public static ComputerEngine createNewComputerEngine() {
    return new ComputerEngine(TestDataReader.getTestData(NUMBER_OF_INSTANCES),
        TestDataReader.getTestData(MACHINE_TYPE),
        TestDataReader.getTestData(NUMBER_OF_GPU),
        TestDataReader.getTestData(GPU_TYPE),
        TestDataReader.getTestData(LOCAL_SSD),
        TestDataReader.getTestData(DATACENTER_LOCATION),
        TestDataReader.getTestData(COMMITTED_USAGE));
  }
}
