package com.vladimir.syspilot;

import com.vladimir.syspilot.service.ProcessManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessManagerTest {
    private final ProcessManager processManager = new ProcessManager();

    @Test
    public void testListProcesses() {
        assertNotNull(processManager.listProcesses());
    }

    // другие тесты для методов kill и start
}
