package controller;

import javax.swing.*;
import model.Vehicle;
import remote.VehicleService;
import view.VehicleView;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class VehicleController {
    private final VehicleView view;
    private final VehicleService vehicleService;

    public VehicleController(VehicleView view, VehicleService vehicleService) {
        this.view = view;
        this.vehicleService = vehicleService;
        setupEventListeners();
        loadInitialData();
    }

    private void setupEventListeners() {
        view.registerCreateAction(this::handleCreate);
        view.registerUpdateAction(this::handleUpdate);
        view.registerDeleteAction(this::handleDelete);
        view.registerSearchAction(this::handleSearch);
        view.registerViewAllAction(this::handleViewAll);
    }

    private void loadInitialData() {
        try {
            view.displayAllVehicles(new ArrayList<>(vehicleService.findAllVehicles()));
        } catch (RemoteException e) {
            showError("Failed to load vehicles: " + e.getMessage());
        }
    }

    private void handleCreate(ActionEvent e) {
        try {
            Vehicle vehicle = view.getVehicleDetails();
            if (vehicle != null && vehicleService.createVehicle(vehicle)) {
                JOptionPane.showMessageDialog(view, "Vehicle created successfully!");
                refreshTable();
            }
        } catch (RemoteException ex) {
            showError("Creation failed: " + ex.getMessage());
        }
    }

    private void handleUpdate(ActionEvent e) {
        try {
            Vehicle vehicle = view.getVehicleDetails();
            if (vehicle != null && vehicleService.updateVehicle(vehicle)) {
                JOptionPane.showMessageDialog(view, "Vehicle updated successfully!");
                refreshTable();
            }
        } catch (RemoteException ex) {
            showError("Update failed: " + ex.getMessage());
        }
    }

    private void handleDelete(ActionEvent e) {
        try {
            int id = view.getVehicleId();
            if (id > 0 && vehicleService.deleteVehicle(id)) {
                JOptionPane.showMessageDialog(view, "Vehicle deleted successfully!");
                refreshTable();
            }
        } catch (RemoteException ex) {
            showError("Deletion failed: " + ex.getMessage());
        }
    }

    private void handleSearch(ActionEvent e) {
        try {
            int id = view.getVehicleId();
            if (id > 0) {
                Vehicle vehicle = vehicleService.findVehicle(id);
                if (vehicle != null) {
                    view.displayVehicle(vehicle);
                } else {
                    showError("Vehicle not found!");
                }
            }
        } catch (RemoteException ex) {
            showError("Search failed: " + ex.getMessage());
        }
    }

    private void handleViewAll(ActionEvent e) {
        refreshTable();
    }

    private void refreshTable() {
        try {
            view.displayAllVehicles(new ArrayList<>(vehicleService.findAllVehicles()));
        } catch (RemoteException ex) {
            showError("Failed to refresh data: " + ex.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(view, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}