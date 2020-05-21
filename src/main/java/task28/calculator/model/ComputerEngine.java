package task28.calculator.model;

import java.util.Objects;

public class ComputerEngine {
    private String numberOfInstances;
    private String machineType;
    private String numberOfGpu;
    private String gpuType;
    private String localSsd;
    private String datacenterLocation;
    private String committedUsage;

    public ComputerEngine(String numberOfInstances, String machineType, String numberOfGpu,
                          String gpuType, String localSsd, String datacenterLocation, String committedUsage) {
        this.numberOfInstances = numberOfInstances;
        this.machineType = machineType;
        this.numberOfGpu = numberOfGpu;
        this.gpuType = gpuType;
        this.localSsd = localSsd;
        this.datacenterLocation = datacenterLocation;
        this.committedUsage = committedUsage;
    }

    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public String getMachineType() {
        return machineType;
    }

    public String getNumberOfGpu() {
        return numberOfGpu;
    }

    public String getGpuType() {
        return gpuType;
    }

    public String getLocalSsd() {
        return localSsd;
    }

    public String getDatacenterLocation() {
        return datacenterLocation;
    }

    public String getCommittedUsage() {
        return committedUsage;
    }

    public void setNumberOfInstances(String numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public void setNumberOfGpu(String numberOfGpu) {
        this.numberOfGpu = numberOfGpu;
    }

    public void setGpuType(String gpuType) {
        this.gpuType = gpuType;
    }

    public void setLocalSsd(String localSsd) {
        this.localSsd = localSsd;
    }

    public void setDatacenterLocation(String datacenterLocation) {
        this.datacenterLocation = datacenterLocation;
    }

    public void setCommittedUsage(String committedUsage) {
        this.committedUsage = committedUsage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerEngine that = (ComputerEngine) o;
        return numberOfInstances == that.numberOfInstances &&
                numberOfGpu == that.numberOfGpu &&
                machineType.equals(that.machineType) &&
                gpuType.equals(that.gpuType) &&
                localSsd.equals(that.localSsd) &&
                datacenterLocation.equals(that.datacenterLocation) &&
                committedUsage.equals(that.committedUsage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfInstances, machineType, numberOfGpu, gpuType, localSsd, datacenterLocation, committedUsage);
    }

    @Override
    public String toString() {
        return "ComputerEngine{" +
                "numberOfInstances=" + numberOfInstances +
                ", machineType='" + machineType + '\'' +
                ", numberOfGpu=" + numberOfGpu +
                ", gpuType='" + gpuType + '\'' +
                ", localSsd='" + localSsd + '\'' +
                ", datacenterLocation='" + datacenterLocation + '\'' +
                ", committedUsage='" + committedUsage + '\'' +
                '}';
    }
}
