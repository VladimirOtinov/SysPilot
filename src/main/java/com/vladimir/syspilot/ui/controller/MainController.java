package com.vladimir.syspilot.ui.controller;

import com.vladimir.syspilot.model.ProcessInfo;
import com.vladimir.syspilot.service.ProcessManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

public class MainController {

    @FXML
    private TableView<ProcessInfo> processTable;
    @FXML
    private TableColumn<ProcessInfo, String> nameColumn;
    @FXML
    private TableColumn<ProcessInfo, Integer> pidColumn;
    @FXML
    private TableColumn<ProcessInfo, Long> cpuUsageColumn;
    @FXML
    private TableColumn<ProcessInfo, Long> memoryUsageColumn;
    @FXML
    private TableColumn<ProcessInfo, Long> diskUsageColumn;
    @FXML
    private Button startProcessButton;

    private ProcessManager processManager = new ProcessManager();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        pidColumn.setCellValueFactory(new PropertyValueFactory<>("pid"));
        cpuUsageColumn.setCellValueFactory(new PropertyValueFactory<>("cpuUsage"));
        memoryUsageColumn.setCellValueFactory(new PropertyValueFactory<>("memoryUsage"));
        diskUsageColumn.setCellValueFactory(new PropertyValueFactory<>("diskUsage"));

        updateProcessTable();
    }

    private void updateProcessTable() {
        processTable.getItems().setAll(processManager.listProcesses());
    }

    @FXML
    private void handleStartProcess(ActionEvent event) {
        ProcessInfo selectedProcess = processTable.getSelectionModel().getSelectedItem();
        if (selectedProcess != null) {
            processManager.startProcess(selectedProcess.getName());
            updateProcessTable();  // обновить таблицу после запуска
        }
    }
}
