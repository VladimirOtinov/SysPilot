package com.vladimir.syspilot.model;

import javafx.beans.property.*;

public class ProcessInfo {
    private final IntegerProperty pid;
    private final StringProperty name;
    private final StringProperty status;

    public ProcessInfo(int pid, String name, String status) {
        this.pid = new SimpleIntegerProperty(pid);
        this.name = new SimpleStringProperty(name);
        this.status = new SimpleStringProperty(status);
    }

    public int getPid() { return pid.get(); }
    public IntegerProperty pidProperty() { return pid; }

    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }

    public String getStatus() { return status.get(); }
    public StringProperty statusProperty() { return status; }
}
