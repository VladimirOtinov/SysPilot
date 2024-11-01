package com.vladimir.syspilot.ui.controller;

import com.vladimir.syspilot.model.ProcessInfo;
import com.vladimir.syspilot.service.ProcessManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainController {
    @FXML private TableView<ProcessInfo> processTableView;
    @FXML private TableColumn<ProcessInfo, Integer> pidColumn;
    @FXML private TableColumn<ProcessInfo, String> nameColumn;
    @FXML private TableColumn<ProcessInfo, String> statusColumn;

    private final ProcessManager processManager = new ProcessManager();

    @FXML
    public void initialize() {
        pidColumn.setCellValueFactory(data -> data.getValue().pidProperty().asObject());
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        statusColumn.setCellValueFactory(data -> data.getValue().statusProperty());
        loadProcessList();
    }

    public void onListProcesses() {
        loadProcessList();
    }

    public void onKillProcess() {
        ProcessInfo selectedProcess = processTableView.getSelectionModel().getSelectedItem();
        if (selectedProcess != null) {
            processManager.killProcess(selectedProcess.getPid());
            loadProcessList();
        } else {
            showAlert("No process selected", "Please select a process to kill.");
        }
    }

    public void onStartProcess() {
        // Открытие нового окна для ввода команды запуска процесса
    }

    public void onProcessInfo() {
        ProcessInfo selectedProcess = processTableView.getSelectionModel().getSelectedItem();
        if (selectedProcess != null) {
            // Вывод дополнительной информации о процессе
        } else {
            showAlert("No process selected", "Please select a process to view details.");
        }
    }

    private void loadProcessList() {
        ObservableList<ProcessInfo> processList = FXCollections.observableArrayList(processManager.listProcesses());
        processTableView.setItems(processList);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, content, ButtonType.OK);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
