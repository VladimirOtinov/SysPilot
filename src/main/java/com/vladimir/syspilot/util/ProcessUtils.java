package com.vladimir.syspilot.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessUtils {

    // Метод для получения информации о процессах
    public static String getProcessInfo(int pid) {
        StringBuilder info = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec("ps -p " + pid + " -o pid,comm,%cpu,%mem");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                info.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка получения информации о процессе.";
        }
        return info.toString();
    }

    // Метод для проверки, запущен ли процесс по PID
    public static boolean isProcessRunning(int pid) {
        try {
            Process process = Runtime.getRuntime().exec("ps -p " + pid);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            line = reader.readLine(); // Пропускаем заголовок
            line = reader.readLine(); // Считываем строку с информацией о процессе
            return line != null && line.contains(String.valueOf(pid));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для получения списка всех процессов в виде строки
    public static String listAllProcesses() {
        StringBuilder processList = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec("ps -e");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                processList.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка получения списка процессов.";
        }
        return processList.toString();
    }

    // Метод для запуска процесса с указанием команды
    public static boolean startProcess(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            return process.isAlive();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для завершения процесса по PID
    public static boolean terminateProcess(int pid) {
        try {
            Process process = Runtime.getRuntime().exec("kill " + pid);
            return process.waitFor() == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
