package com.vladimir.syspilot.ui;

import com.vladimir.syspilot.model.ProcessInfo;
import com.vladimir.syspilot.service.ProcessManager;
import com.vladimir.syspilot.util.ProcessUtils;

import java.util.List;
import java.util.Scanner;

public class CommandLineUI {

    private final ProcessManager processManager;

    public CommandLineUI(ProcessManager processManager) {
        this.processManager = processManager;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Список процессов\n2. Завершить процесс\n3. Запустить процесс\n4. Выйти");
            System.out.print("Выберите действие: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    listProcesses();
                    break;
                case "2":
                    terminateProcess(scanner);
                    break;
                case "3":
                    startProcess(scanner);
                    break;
                case "4":
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void listProcesses() {
        List<ProcessInfo> processes = processManager.listProcesses();
        System.out.printf("%-30s%-10s%-15s\n", "Имя", "PID", "Используемая память (KB)");
        for (ProcessInfo process : processes) {
            System.out.printf("%-30s%-10d%-15d\n", process.getName(), process.getPid(), process.getMemoryUsage() / 1024);
        }
    }

    private void terminateProcess(Scanner scanner) {
        System.out.print("Введите PID процесса для завершения: ");
        int pid = Integer.parseInt(scanner.nextLine());
        if (processManager.terminateProcess(pid)) {
            System.out.println("Процесс завершен.");
        } else {
            System.out.println("Не удалось завершить процесс.");
        }
    }

    private void startProcess(Scanner scanner) {
        System.out.print("Введите команду для запуска процесса: ");
        String command = scanner.nextLine();
        if (ProcessUtils.startProcess(command)) {
            System.out.println("Процесс запущен.");
        } else {
            System.out.println("Не удалось запустить процесс.");
        }
    }
}
