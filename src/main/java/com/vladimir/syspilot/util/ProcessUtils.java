package com.vladimir.syspilot.util;

import com.vladimir.syspilot.model.ProcessInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessUtils {

    // Получение списка всех процессов
    public static List<ProcessInfo> getAllProcesses() {
        List<ProcessInfo> processes = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec("ps -eo pid,comm");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            reader.readLine(); // Пропустить заголовок
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+", 2);
                int pid = Integer.parseInt(parts[0]);
                String name = parts[1];
                processes.add(new ProcessInfo(name, pid, getCpuUsage(pid), getMemoryUsage(pid), getDiskUsage(pid)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return processes;
    }

    // Получение использования CPU для процесса по PID (в миллисекундах)
    public static long getCpuUsage(int pid) {
        try {
            Process process = Runtime.getRuntime().exec("ps -p " + pid + " -o %cpu");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            reader.readLine(); // Пропустить заголовок
            String line = reader.readLine();
            return line != null ? (long) (Double.parseDouble(line.trim()) * 10) : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Получение использования памяти для процесса по PID (в МБ)
    public static long getMemoryUsage(int pid) {
        try {
            Process process = Runtime.getRuntime().exec("ps -p " + pid + " -o rss");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            reader.readLine(); // Пропустить заголовок
            String line = reader.readLine();
            return line != null ? Long.parseLong(line.trim()) / 1024 : 0; // Конвертируем из KB в MB
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Получение использования диска для процесса по PID (в КБ)
    public static long getDiskUsage(int pid) {
        // Зависит от системы: используем команду iotop или другую для получения использования диска.
        // Здесь поставим заглушку.
        return 0; // Заглушка, требует реализации
    }

    // Запуск процесса по имени
    public static void startProcessByName(String processName) {
        try {
            Runtime.getRuntime().exec(processName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
