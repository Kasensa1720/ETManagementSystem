package controller;

import javax.swing.*;
import model.ServiceHistory;
import remote.ServiceHistoryService;
import view.ServiceHistoryView;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.List;

public class ServiceHistoryController {
    private final ServiceHistoryView view;
    private final ServiceHistoryService serviceHistoryService;

    public ServiceHistoryController(ServiceHistoryView view, ServiceHistoryService serviceHistoryService) {
        this.view = view;
        this.serviceHistoryService = serviceHistoryService;
        setupEventListeners();
        loadInitialData();
    }

    private void setupEventListeners() {
        view.registerAddAction(this::handleAdd);
        view.registerViewAllAction(this::handleViewAll);
    }

    private void loadInitialData() {
        refreshTable();
    }

    private void handleAdd(ActionEvent e) {
        try {
            ServiceHistory serviceHistory = view.getServiceHistoryDetails();
            if (serviceHistory != null && serviceHistoryService.createServiceHistory(serviceHistory)) {
                JOptionPane.showMessageDialog(view, "Service history added successfully!");
                view.clearFields();
                refreshTable();
            }
        } catch (RemoteException ex) {
            showError("Failed to add service history: " + ex.getMessage());
        }
    }

    private void handleViewAll(ActionEvent e) {
        refreshTable();
    }

    private void refreshTable() {
        try {
            List<ServiceHistory> historyList = serviceHistoryService.findAllServiceHistory();
            view.displayServiceHistory(historyList);
        } catch (RemoteException ex) {
            showError("Failed to load service history: " + ex.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(view, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}