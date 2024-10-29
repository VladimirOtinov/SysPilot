package com.vladimir.syspilot.model;

public class ProcessInfo {
    private String name;
    private int pid;
    private long memoryUsage;

    public ProcessInfo(String name, int pid, long memoryUsage) {
        this.name = name;
        this.pid = pid;
        this.memoryUsage = memoryUsage;
    }

    public String getName() { return name; }
    public int getPid() { return pid; }
    public long getMemoryUsage() { return memoryUsage; }
}
