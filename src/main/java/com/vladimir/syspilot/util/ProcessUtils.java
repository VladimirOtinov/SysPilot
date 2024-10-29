package com.vladimir.syspilot.util;

import java.io.IOException;

public class ProcessUtils {

    public static boolean startProcess(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            return process.isAlive();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean terminateProcessByName(String processName) {
        String command = "taskkill /F /IM " + processName;
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
