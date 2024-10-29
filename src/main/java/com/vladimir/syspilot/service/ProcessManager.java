package com.vladimir.syspilot.service;

import com.vladimir.syspilot.model.ProcessInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessManager {
    public List<ProcessInfo> listProcesses() {
        List<ProcessInfo> processes = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec("ps -e");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // Пропускаем первую строку с заголовками
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+", 4);
                if (parts.length >= 3) {
                    try {
                        int pid = Integer.parseInt(parts[0]);
                        String name = parts[3];
                        String status = "Running";
                        processes.add(new ProcessInfo(pid, name, status));
                    } catch (NumberFormatException e) {
                        // Логирование ошибки, если формат PID некорректен
                        System.err.println("Ошибка преобразования PID: " + parts[0]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return processes;
    }


    public void killProcess(int pid) {
        try {
            Runtime.getRuntime().exec("kill " + pid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startProcess(String command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ProcessInfo> findProcessesByName(String name) {
        List<ProcessInfo> allProcesses = listProcesses();
        return allProcesses.stream()
                .filter(process -> process.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}
