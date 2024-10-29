package com.vladimir.syspilot.service;

import com.vladimir.syspilot.model.ProcessInfo;
import com.vladimir.syspilot.util.ProcessUtils;

import java.util.ArrayList;
import java.util.List;

public class ProcessManager {

    public List<ProcessInfo> listProcesses() {
        List<ProcessInfo> processes = new ArrayList<>();
        String allProcessesInfo = ProcessUtils.listAllProcesses();
        String[] lines = allProcessesInfo.split("\n");

        for (int i = 1; i < lines.length; i++) {
            String[] parts = lines[i].trim().split("\\s+", 4);
            if (parts.length >= 3) {
                try {
                    int pid = Integer.parseInt(parts[0]);
                    String name = parts[3];
                    String status = ProcessUtils.isProcessRunning(pid) ? "Running" : "Not Running";
                    processes.add(new ProcessInfo(pid, name, status));
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка преобразования PID: " + parts[0]);
                }
            }
        }
        return processes;
    }

    public boolean killProcess(int pid) {
        return ProcessUtils.terminateProcess(pid);
    }

    public boolean startProcess(String command) {
        return ProcessUtils.startProcess(command);
    }

    public List<ProcessInfo> findProcessesByName(String name) {
        List<ProcessInfo> allProcesses = listProcesses();
        List<ProcessInfo> matchedProcesses = new ArrayList<>();

        for (ProcessInfo process : allProcesses) {
            if (process.getName().toLowerCase().contains(name.toLowerCase())) {
                matchedProcesses.add(process);
            }
        }

        return matchedProcesses;
    }

    public String getProcessDetails(int pid) {
        return ProcessUtils.getProcessInfo(pid);
    }
}
