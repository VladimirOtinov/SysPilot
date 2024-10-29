package com.vladimir.syspilot.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessUtils {

    // Возвращает список всех процессов
    public static String listAllProcesses() {
        return executeCommand("ps -e");
    }

    // Проверяет, работает ли процесс с данным PID
    public static boolean isProcessRunning(int pid) {
        String result = executeCommand("ps -p " + pid);
        return result.contains(String.valueOf(pid));
    }

    // Завершает процесс по PID
    public static boolean terminateProcess(int pid) {
        String result = executeCommand("kill " + pid);
        return result.isEmpty(); // Пустой результат означает успешное завершение
    }

    // Запускает процесс по команде
    public static boolean startProcess(String command) {
        try {
            Process process = new ProcessBuilder(command.split(" ")).start();
            return process.isAlive();
        } catch (IOException e) {
            System.err.println("Ошибка при запуске процесса: " + e.getMessage());
            return false;
        }
    }

    // Получает информацию о процессе по PID
    public static String getProcessInfo(int pid) {
        return executeCommand("ps -p " + pid + " -o pid,ppid,user,etime,%mem,%cpu,cmd");
    }

    // Вспомогательный метод для выполнения команды и получения результата
    private static String executeCommand(String command) {
        StringBuilder output = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            System.err.println("Ошибка при выполнении команды: " + e.getMessage());
        }
        return output.toString().trim();
    }
}
