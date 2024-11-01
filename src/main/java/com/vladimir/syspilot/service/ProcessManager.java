package com.vladimir.syspilot.service;

import com.vladimir.syspilot.model.ProcessInfo;
import com.vladimir.syspilot.util.ProcessUtils;

import java.util.List;
import java.util.Optional;

public class ProcessManager {

    // Метод для получения списка всех процессов с полной информацией
    public List<ProcessInfo> listAllProcesses() {
        return ProcessUtils.getAllProcesses();
    }

    // Метод для поиска процесса по PID
    public Optional<ProcessInfo> findProcessByPid(int pid) {
        return listAllProcesses().stream()
                .filter(process -> process.getPid() == pid)
                .findFirst();
    }

    // Метод для запуска процесса по имени
    public void startProcess(String processName) {
        System.out.println("Запуск процесса: " + processName);
        ProcessUtils.startProcessByName(processName);
    }

    // Метод для отображения информации о всех процессах
    public void displayAllProcesses() {
        List<ProcessInfo> processes = listAllProcesses();
        System.out.printf("%-10s %-20s %-15s %-15s %-15s%n", "PID", "Process Name", "CPU Usage (ms)", "Memory Usage (MB)", "Disk Usage (KB)");
        System.out.println("-------------------------------------------------------------------------------");
        for (ProcessInfo process : processes) {
            System.out.printf("%-10d %-20s %-15d %-15d %-15d%n",
                    process.getPid(),
                    process.getName(),
                    process.getCpuUsage(),
                    process.getMemoryUsage(),
                    process.getDiskUsage());
        }
    }

    // Метод для отображения информации о процессе по PID
    public void displayProcessInfo(int pid) {
        Optional<ProcessInfo> process = findProcessByPid(pid);
        if (process.isPresent()) {
            ProcessInfo pInfo = process.get();
            System.out.printf("Процесс ID: %d%n", pInfo.getPid());
            System.out.printf("Имя процесса: %s%n", pInfo.getName());
            System.out.printf("Использование CPU: %d мс%n", pInfo.getCpuUsage());
            System.out.printf("Использование памяти: %d МБ%n", pInfo.getMemoryUsage());
            System.out.printf("Использование диска: %d КБ%n", pInfo.getDiskUsage());
        } else {
            System.out.println("Процесс с PID " + pid + " не найден.");
        }
    }
}
