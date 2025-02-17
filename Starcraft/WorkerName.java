package Starcraft;

public enum WorkerName {
    TERRAN_WORKER("SCV"),
    ZERG_WORKER("Drone"),
    PROTOSS_WORKER("Probe");

    private final String workerName;

    private WorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerName() {
        return workerName;
    }
}
