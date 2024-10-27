package com.vladimir.syspilot.ui;

import com.vladimir.syspilot.model.ProcessInfo;
import com.vladimir.syspilot.service.ProcessManager;

import java.util.List;
import java.util.Scanner;

public class CommandLineUI {
    private ProcessManager processManager;
    private Scanner scanner;

    public CommandLineUI() {
        this.processManager = new ProcessManager();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        String command;
        System.out.println("Добро пожаловать в SysPilot. Введите команду (list, start, kill, exit):");

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine().trim();
            String[] args = command.split(" ");

            switch (args[0].toLowerCase()) {
                case "list":
                    List<ProcessInfo> processes = processManager.listProcesses();
                    processes.forEach(System.out::println);
                    break;
                case "start":
                    if (args.length > 1) {
                        processManager.startProcess(args[1]);
                        System.out.println("Процесс запущен: " + args[1]);
                    } else {
                        System.out.println("Укажите команду для запуска.");
                    }
                    break;
                case "kill":
                    if (args.length > 1) {
                        try {
                            int pid = Integer.parseInt(args[1]);
                            processManager.killProcess(pid);
                            System.out.println("Процесс завершен: " + pid);
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный PID.");
                        }
                    } else {
                        System.out.println("Укажите PID для завершения.");
                    }
                    break;
                case "exit":
                    System.out.println("Выход из SysPilot.");
                    return;
                default:
                    System.out.println("Неизвестная команда. Пожалуйста, попробуйте снова.");
                    break;
            }
        }
    }
}
