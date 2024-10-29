package com.vladimir.syspilot;

import com.vladimir.syspilot.service.ProcessManager;
import com.vladimir.syspilot.ui.CommandLineUI;

public class SysPilotApplication {
    public static void main(String[] args) {
        ProcessManager processManager = new ProcessManager();
        CommandLineUI ui = new CommandLineUI(processManager);
        ui.start();
    }
}
