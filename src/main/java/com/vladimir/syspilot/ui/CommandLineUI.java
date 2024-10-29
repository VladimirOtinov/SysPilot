package com.vladimir.syspilot.ui;

import com.vladimir.syspilot.model.ProcessInfo;
import com.vladimir.syspilot.service.ProcessManager;

import java.util.List;
import java.util.Scanner;

public class CommandLineUI {
    private final ProcessManager processManager;
    private final Scanner scanner;

    public CommandLineUI(ProcessManager processManager) {
        this.processManager = processManager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Показать список процессов");
            System.out.println("2. Завершить процесс по PID");
            System.out.println("3. Запустить новый процесс");
            System.out.println("4. Найти процессы по имени");
            System.out.println("5. Показать детали процесса по PID");
            System.out.println("6. Выйти");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> listProcesses();
                case 2 -> terminateProcess();
                case 3 -> startProcess();
                case 4 -> findProcessesByName();
                case 5 -> getProcessDetails();
                case 6 -> {
                    System.out.println("Выход...");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }
    }

    private void listProcesses() {
        List<ProcessInfo> processes = processManager.listProcesses();
        processes.forEach(System.out::println);
    }

    private void terminateProcess() {
        System.out.print("Введите PID для завершения: ");
        int pid = Integer.parseInt(scanner.nextLine());
        boolean success = processManager.killProcess(pid);
        System.out.println(success ? "Процесс завершен." : "Не удалось завершить процесс.");
    }

    private void startProcess() {
        System.out.print("Введите команду для запуска процесса: ");
        String command = scanner.nextLine();
        boolean success = processManager.startProcess(command);
        System.out.println(success ? "Процесс запущен." : "Не удалось запустить процесс.");
    }

    private void findProcessesByName() {
        System.out.print("Введите имя процесса для поиска: ");
        String name = scanner.nextLine();
        List<ProcessInfo> processes = processManager.findProcessesByName(name);
        processes.forEach(System.out::println);
    }

    private void getProcessDetails() {
        System.out.print("Введите PID для получения деталей: ");
        int pid = Integer.parseInt(scanner.nextLine());
        String details = processManager.getProcessDetails(pid);
        System.out.println("Детали процесса:\n" + details);
    }
}
