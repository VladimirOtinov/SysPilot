package com.vladimir.syspilot.service;

import com.vladimir.syspilot.model.ProcessInfo;
import com.vladimir.syspilot.util.ProcessUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessManager {

    public List<ProcessInfo> listProcesses() {
        List<ProcessInfo> processes = new ArrayList<>();
        String command = "tasklist /fo csv /nh";

        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\",\"");

                // Получаем имя и PID процесса, очищая от лишних кавычек
                String name = parts[0].replace("\"", "");
                int pid = Integer.parseInt(parts[1].replace("\"", ""));

                // Убираем все нецифровые символы перед преобразованием в число
                String memoryUsageStr = parts[4].replaceAll("[^\\d]", "");
                long memoryUsage = Long.parseLong(memoryUsageStr) * 1024;

                processes.add(new ProcessInfo(name, pid, memoryUsage));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return processes;
    }

    public boolean terminateProcess(int pid) {
        String command = "taskkill /F /PID " + pid;
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            return process.exitValue() == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
