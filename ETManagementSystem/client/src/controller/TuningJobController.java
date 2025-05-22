package controller;

import javax.swing.*;
import model.TuningJob;
import remote.TuningJobService;
import view.TuningJobView;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class TuningJobController {
    private final TuningJobView view;
    private final TuningJobService tuningJobService;

    public TuningJobController(TuningJobView view, TuningJobService tuningJobService) {
        this.view = view;
        this.tuningJobService = tuningJobService;
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
            view.displayAllTuningJobs(new ArrayList<>(tuningJobService.findAllTuningJobs()));
        } catch (RemoteException e) {
            showError("Failed to load tuning jobs: " + e.getMessage());
        }
    }

    private void handleCreate(ActionEvent e) {
        try {
            TuningJob tuningJob = view.getTuningJobDetails();
            if (tuningJob != null && tuningJobService.createTuningJob(tuningJob)) {
                JOptionPane.showMessageDialog(view, "Tuning job created successfully!");
                refreshTable();
            }
        } catch (RemoteException ex) {
            showError("Creation failed: " + ex.getMessage());
        }
    }

    private void handleUpdate(ActionEvent e) {
        try {
            TuningJob tuningJob = view.getTuningJobDetails();
            if (tuningJob != null) {
                Integer id = view.getTuningJobId();
                if (id != null) {
                    tuningJob.setTuningId(id);
                    if (tuningJobService.updateTuningJob(tuningJob)) {
                        JOptionPane.showMessageDialog(view, "Tuning job updated successfully!");
                        refreshTable();
                    }
                }
            }
        } catch (RemoteException ex) {
            showError("Update failed: " + ex.getMessage());
        }
    }

    private void handleDelete(ActionEvent e) {
        try {
            Integer id = view.getTuningJobId();
            if (id != null && tuningJobService.deleteTuningJob(id)) {
                JOptionPane.showMessageDialog(view, "Tuning job deleted successfully!");
                refreshTable();
            }
        } catch (RemoteException ex) {
            showError("Deletion failed: " + ex.getMessage());
        }
    }

    private void handleSearch(ActionEvent e) {
        try {
            Integer id = view.getTuningJobId();
            if (id != null) {
                TuningJob tuningJob = tuningJobService.findTuningJob(id);
                if (tuningJob != null) {
                    view.displayTuningJob(tuningJob);
                } else {
                    showError("Tuning job not found!");
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
            view.displayAllTuningJobs(new ArrayList<>(tuningJobService.findAllTuningJobs()));
        } catch (RemoteException ex) {
            showError("Failed to refresh data: " + ex.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(view, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}