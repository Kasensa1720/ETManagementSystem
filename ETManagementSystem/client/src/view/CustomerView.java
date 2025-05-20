package com.etms.client.Customer;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;

// package main.java.com.etms.client.Customer;

// import javax.swing.*;
// import javax.swing.border.EmptyBorder;
// import javax.swing.table.DefaultTableModel;

// import main.java.com.etms.MainDashboard.MainDashboard;
// import main.java.com.etms.server.customer.Customer;

// import java.awt.*;
// import java.awt.event.ActionListener;
// import java.util. ArrayList;

// public class CustomerView extends JFrame {
//     private JTextField nameField, emailField, phoneField, addressField;
//     private JButton addButton, updateButton, deleteButton, searchButton, viewAllButton, backButton;
//     private JTable table;
//     private DefaultTableModel model;

//     private MainDashboard mainDashboard;

//     public CustomerView(MainDashboard mainDashboard) {
//         this.mainDashboard = mainDashboard;
//         setTitle("Customer Management");
//         setSize(800, 500);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLocationRelativeTo(null);
//         setLayout(new BorderLayout());
//         getContentPane().setBackground(Color.WHITE);

//         // === Form Panel ===
//         JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
//         formPanel.setBorder(new EmptyBorder(20, 30, 10, 30));
//         formPanel.setBackground(Color.WHITE);

//         Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);

//         formPanel.add(createLabel("Name:", labelFont));
//         nameField = new JTextField();
//         formPanel.add(nameField);

//         formPanel.add(createLabel("Email:", labelFont));
//         emailField = new JTextField();
//         formPanel.add(emailField);

//         formPanel.add(createLabel("Phone:", labelFont));
//         phoneField = new JTextField();
//         formPanel.add(phoneField);

//         formPanel.add(createLabel("Address:", labelFont));
//         addressField = new JTextField();
//         formPanel.add(addressField);

//         addButton = createRoundedButton("Add Customer");
//         updateButton = createRoundedButton("Update Customer");
//         deleteButton = createRoundedButton("Delete Customer");
//         searchButton = createRoundedButton("Search Customer");
//         viewAllButton = createRoundedButton("View All Customers");

//         JPanel buttonGrid = new JPanel(new GridLayout(2, 3, 10, 10));
//         buttonGrid.setBackground(Color.WHITE);
//         buttonGrid.add(addButton);
//         buttonGrid.add(updateButton);
//         buttonGrid.add(deleteButton);
//         buttonGrid.add(searchButton);
//         buttonGrid.add(viewAllButton);

//         JPanel topPanel = new JPanel(new BorderLayout());
//         topPanel.setBackground(Color.WHITE);
//         topPanel.add(formPanel, BorderLayout.NORTH);
//         topPanel.add(buttonGrid, BorderLayout.CENTER);

//         // === Table ===
//         model = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone", "Address"}, 0);
//         table = new JTable(model);
//         JScrollPane scrollPane = new JScrollPane(table);
//         scrollPane.setBorder(BorderFactory.createTitledBorder("Customer Records"));

//         // === Back Button ===
//         backButton = createRoundedButton("Back to Dashboard");
//         backButton.addActionListener(e -> {
//             dispose();
//             this.mainDashboard.setVisible(true);
//         });

//         JPanel backPanel = new JPanel();
//         backPanel.setBackground(Color.WHITE);
//         backPanel.add(backButton);

//         // === Layout Assembly ===
//         add(topPanel, BorderLayout.NORTH);
//         add(scrollPane, BorderLayout.CENTER);
//         add(backPanel, BorderLayout.SOUTH);

//         setVisible(true);
//     }

//     private JLabel createLabel(String text, Font font) {
//         JLabel label = new JLabel(text);
//         label.setFont(font);
//         return label;
//     }

//     private JButton createRoundedButton(String text) {
//         JButton button = new JButton(text) {
//             @Override
//             protected void paintComponent(Graphics g) {
//                 Graphics2D g2 = (Graphics2D) g.create();
//                 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                 g2.setColor(getModel().isRollover() ? new Color(120, 160, 200) : new Color(60, 120, 180));
//                 g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
//                 g2.dispose();
//                 super.paintComponent(g);
//             }
//         };
//         button.setForeground(Color.WHITE);
//         button.setFont(new Font("Segoe UI", Font.BOLD, 14));
//         button.setFocusPainted(false);
//         button.setContentAreaFilled(false);
//         button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
//         return button;
//     }

//     public Customer getCustomerDetails() {
//         String name = nameField.getText().trim();
//         String email = emailField.getText().trim();
//         String phone = phoneField.getText().trim();
//         String address = addressField.getText().trim();

//         if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
//             JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
//             return null;
//         }

//         if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
//             JOptionPane.showMessageDialog(this, "Invalid email format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
//             return null;
//         }

//         if (!phone.matches("\\d{7,15}")) {
//             JOptionPane.showMessageDialog(this, "Phone must be numeric and between 7 to 15 digits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
//             return null;
//         }

//         Customer customer = new Customer();
//         customer.setName(name);
//         customer.setEmail(email);
//         customer.setPhone(phone);
//         customer.setAddress(address);

//         return customer;
//     }

//     public int getId() {
//         return Integer.parseInt(JOptionPane.showInputDialog("Enter Customer ID:"));
//     }

//     public void displayCustomer(Customer customer) {
//         if (customer != null) {
//             nameField.setText(customer.getName());
//             emailField.setText(customer.getEmail());
//             phoneField.setText(customer.getPhone());
//             addressField.setText(customer.getAddress());
//         }
//     }

//     public void displayAllCustomers(ArrayList<Customer> customers) {
//         model.setRowCount(0);
//         for (Customer customer : customers) {
//             model.addRow(new Object[]{
//                     customer.getCustomerId(),
//                     customer.getName(),
//                     customer.getEmail(),
//                     customer.getPhone(),
//                     customer.getAddress()
//             });
//         }
//     }

//     public void registerCreateAction(ActionListener listener) {
//         addButton.addActionListener(listener);
//     }

//     public void registerUpdateAction(ActionListener listener) {
//         updateButton.addActionListener(listener);
//     }

//     public void registerDeleteAction(ActionListener listener) {
//         deleteButton.addActionListener(listener);
//     }

//     public void registerSearchAction(ActionListener listener) {
//         searchButton.addActionListener(listener);
//     }

//     public void registerViewAllAction(ActionListener listener) {
//         viewAllButton.addActionListener(listener);
//     }

// }
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.etms.server.customer.Customer;

public class CustomerView extends JFrame {
    private JTextField idField, nameField, emailField, phoneField, addressField;
    private JButton registerButton, updateButton, deleteButton, searchButton, viewAllButton;
    private JTable customerTable;
    private DefaultTableModel tableModel;

    public CustomerView() {
        setTitle("Customer Management");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Customer ID:"));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        formPanel.add(phoneField);

        formPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        formPanel.add(addressField);

        // Buttons
        registerButton = new JButton("Register");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        searchButton = new JButton("Search");
        viewAllButton = new JButton("View All");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(registerButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(viewAllButton);

        formPanel.add(buttonPanel);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone", "Address"}, 0);
        customerTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(customerTable);

        add(formPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        setVisible(true); 
    }

    // Get input from form
    public Customer getCustomerDetails() {
        try {
            Customer customer = new Customer();
            String idText = idField.getText().trim();
            if (!idText.isEmpty()) {
                customer.setCustomerId(Integer.parseInt(idText));
            }
            customer.setName(nameField.getText().trim());
            customer.setEmail(emailField.getText().trim());
            customer.setPhone(phoneField.getText().trim());
            customer.setAddress(addressField.getText().trim());
            return customer;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public int getId() {
        try {
            return Integer.parseInt(idField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public void displayCustomer(Customer customer) {
        idField.setText(String.valueOf(customer.getCustomerId()));
        nameField.setText(customer.getName());
        emailField.setText(customer.getEmail());
        phoneField.setText(customer.getPhone());
        addressField.setText(customer.getAddress());
    }

    public void displayAllCustomers(List<Customer> customers) {
        tableModel.setRowCount(0); // clear existing
        for (Customer customer : customers) {
            tableModel.addRow(new Object[]{
                customer.getCustomerId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress()
            });
        }
    }

    // Action listeners
    public void registerCreateAction(ActionListener listener) {
        registerButton.addActionListener(listener);
    }

    public void registerUpdateAction(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void registerDeleteAction(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void registerSearchAction(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void registerViewAllAction(ActionListener listener) {
        viewAllButton.addActionListener(listener);
    }
}
