package controller;

import javax.swing.*;
import model.Customer;
import remote.CustomerService;
import view.CustomerView;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class CustomerController {
    private final CustomerView view;
    private final CustomerService customerService;

    public CustomerController(CustomerView view, CustomerService customerService) {
        this.view = view;
        this.customerService = customerService;
        setupEventListeners();
    }

    private void setupEventListeners() {
        view.registerCreateAction(this::handleCreate);
        view.registerUpdateAction(this::handleUpdate);
        view.registerDeleteAction(this::handleDelete);
        view.registerSearchAction(this::handleSearch);
        view.registerViewAllAction(this::handleViewAll);
    }

    private void handleCreate(ActionEvent e) {
        try {
            Customer customer = view.getCustomerDetails();
            if (customer != null && customerService.createCustomer(customer)) {
                JOptionPane.showMessageDialog(view, "Customer created successfully!");
                view.displayAllCustomers(new ArrayList<>(customerService.findAllCustomers()));
            }
        } catch (RemoteException ex) {
            showError("Creation failed: " + ex.getMessage());
        }
    }

    private void handleUpdate(ActionEvent e) {
        try {
            Customer customer = view.getCustomerDetails();
            if (customer != null) {
                int id = view.getId();
                customer.setCustomerId(id);
                if (customerService.updateCustomer(customer)) {
                    JOptionPane.showMessageDialog(view, "Customer updated successfully!");
                    view.displayAllCustomers(new ArrayList<>(customerService.findAllCustomers()));
                }
            }
        } catch (RemoteException ex) {
            showError("Update failed: " + ex.getMessage());
        }
    }

    private void handleDelete(ActionEvent e) {
        try {
            int id = view.getId();
            if (customerService.deleteCustomer(id)) {
                JOptionPane.showMessageDialog(view, "Customer deleted successfully!");
                view.displayAllCustomers(new ArrayList<>(customerService.findAllCustomers()));
            }
        } catch (RemoteException ex) {
            showError("Deletion failed: " + ex.getMessage());
        }
    }

    private void handleSearch(ActionEvent e) {
        try {
            int id = view.getId();
            Customer customer = customerService.findCustomer(id);
            if (customer != null) {
                view.displayCustomer(customer);
            } else {
                showError("Customer not found!");
            }
        } catch (RemoteException ex) {
            showError("Search failed: " + ex.getMessage());
        }
    }

    private void handleViewAll(ActionEvent e) {
        try {
            view.displayAllCustomers(new ArrayList<>(customerService.findAllCustomers()));
        } catch (RemoteException ex) {
            showError("Failed to load customers: " + ex.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(view, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}