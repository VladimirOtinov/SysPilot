package com.vladimir.syspilot.model;

public class ProcessInfo {
    private int pid;
    private String name;
    private String status;

    public ProcessInfo(int pid, String name, String status) {
        this.pid = pid;
        this.name = name;
        this.status = status;
    }

    // Геттеры
    public int getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "PID: " + pid + ", Name: " + name + ", Status: " + status;
    }
}
