package com.etms.client.ServiceHistory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.etms.server.servicehistory.ServiceHistory;
import com.etms.server.servicehistory.ServiceHistoryService;

public class ServiceHistoryController {
    private ServiceHistoryView view;
    private ServiceHistoryService service;  // RMI service interface

    public ServiceHistoryController(ServiceHistoryView view, ServiceHistoryService service) {
        this.view = view;
        this.service = service;

        // Registering button listeners
        this.view.registerAddAction(new AddServiceHistoryListener());
        this.view.registerViewAllAction(new ViewAllServiceHistoryListener());
    }

    // Listener for Add Service History button
    class AddServiceHistoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ServiceHistory serviceHistory = view.getServiceHistoryDetails();
            if (serviceHistory != null) {
                try {
                    int result = service.createServiceHistory(serviceHistory); // RMI call
                    if (result > 0) {
                        JOptionPane.showMessageDialog(view, "Service History Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        view.clearFields();
                        loadServiceHistory();
                    } else {
                        JOptionPane.showMessageDialog(view, "Failed to Add Service History.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (RemoteException ex) {
                    JOptionPane.showMessageDialog(view, "RemoteException: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }
    }

    // Listener for View All Service History button
    class ViewAllServiceHistoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadServiceHistory();
        }
    }

    // Load service history list using RMI service
    private void loadServiceHistory() {
        try {
            ArrayList<ServiceHistory> serviceHistoryList = service.getAllServiceHistory(); // RMI call
            view.displayServiceHistory(serviceHistoryList);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(view, "Failed to load service history: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
