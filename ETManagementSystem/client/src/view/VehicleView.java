// package com.etms.client.Vehicle;



// import java.awt.BorderLayout;
// import java.awt.Color;
// import java.awt.Font;
// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.GridLayout;
// import java.awt.RenderingHints;
// import java.awt.event.ActionListener;
// import java.util.ArrayList;

// import javax.swing.BorderFactory;
// import javax.swing.JButton;
// import javax.swing.JComboBox;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JOptionPane;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTable;
// import javax.swing.JTextField;
// import javax.swing.border.EmptyBorder;
// import javax.swing.table.DefaultTableModel;

// import com.etms.MainDashboard.MainDashboard;
// import com.etms.server.vehicle.Vehicle;

// public class VehicleView extends JFrame {
//     private JTextField vehicleIdField, customerIdField, makeField, modelField, yearField;
//     private JComboBox<String> engineTypeCombo;
//     private JButton addButton, updateButton, deleteButton, searchButton, viewAllButton, backButton;
//     private JTable table;
//     private DefaultTableModel model;

//     private MainDashboard mainDashboard;

    
        
//     public VehicleView(MainDashboard mainDashboard) {
//         this.mainDashboard = mainDashboard;
//         setTitle("Vehicle Management");
//         setSize(900, 600);
//         setLocationRelativeTo(null);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLayout(new BorderLayout());
//         getContentPane().setBackground(Color.WHITE);

//         Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);

//         // === Form Panel ===
//         JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
//         formPanel.setBorder(new EmptyBorder(20, 30, 10, 30));
//         formPanel.setBackground(Color.WHITE);

//         formPanel.add(createLabel("Vehicle ID:", labelFont));
//         vehicleIdField = new JTextField();
//         formPanel.add(vehicleIdField);

//         formPanel.add(createLabel("Customer ID:", labelFont));
//         customerIdField = new JTextField();
//         formPanel.add(customerIdField);

//         formPanel.add(createLabel("Make:", labelFont));
//         makeField = new JTextField();
//         formPanel.add(makeField);

//         formPanel.add(createLabel("Model:", labelFont));
//         modelField = new JTextField();
//         formPanel.add(modelField);

//         formPanel.add(createLabel("Year:", labelFont));
//         yearField = new JTextField();
//         formPanel.add(yearField);

//         formPanel.add(createLabel("Engine Type:", labelFont));
//         engineTypeCombo = new JComboBox<>(new String[]{
//                 "TURBO_PETROL", "NA_PETROL", "SUPERCHARGED_PETROL",
//                 "NA_DIESEL", "TURBO_DIESEL", "SUPERCHARGED_DIESEL"
//         });
//         formPanel.add(engineTypeCombo);

//         // === Button Panel ===
//         addButton = createRoundedButton("Add Vehicle");
//         updateButton = createRoundedButton("Update Vehicle");
//         deleteButton = createRoundedButton("Delete Vehicle");
//         searchButton = createRoundedButton("Search Vehicle");
//         viewAllButton = createRoundedButton("View All Vehicles");

//         JPanel buttonGrid = new JPanel(new GridLayout(2, 3, 10, 10));
//         buttonGrid.setBackground(Color.WHITE);
//         buttonGrid.add(addButton);
//         buttonGrid.add(updateButton);
//         buttonGrid.add(deleteButton);
//         buttonGrid.add(searchButton);
//         buttonGrid.add(viewAllButton);

//         // === Top Panel (Form + Buttons) ===
//         JPanel topPanel = new JPanel(new BorderLayout());
//         topPanel.setBackground(Color.WHITE);
//         topPanel.add(formPanel, BorderLayout.NORTH);
//         topPanel.add(buttonGrid, BorderLayout.CENTER);

//         // === Table ===
//         model = new DefaultTableModel(new String[]{"ID", "Customer ID", "Make", "Model", "Year", "Engine Type"}, 0);
//         table = new JTable(model);
//         JScrollPane scrollPane = new JScrollPane(table);
//         scrollPane.setBorder(BorderFactory.createTitledBorder("Vehicle Records"));

//         // === Back Button ===
//         backButton = createRoundedButton("Back to Dashboard");
//         backButton.addActionListener(e -> {
//             dispose();
//              this.mainDashboard.setVisible(true);
//         });

//         JPanel backPanel = new JPanel();
//         backPanel.setBackground(Color.WHITE);
//         backPanel.add(backButton);

//         // === Final Layout ===
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

//     // === CRUD Support Methods ===
//     public Vehicle getVehicleDetails() {
//         try {
//             int vehicleId = vehicleIdField.getText().isEmpty() ? 0 : Integer.parseInt(vehicleIdField.getText());
//             int customerId = Integer.parseInt(customerIdField.getText());
//             String make = makeField.getText().trim();
//             String model = modelField.getText().trim();
//             int year = Integer.parseInt(yearField.getText());
//             Vehicle.EngineType engineType = Vehicle.EngineType.valueOf((String) engineTypeCombo.getSelectedItem());

//             return new Vehicle(vehicleId, customerId, make, model, year, engineType);
//         } catch (Exception ex) {
//             JOptionPane.showMessageDialog(this, "Please fill in all fields correctly.", "Validation Error", JOptionPane.ERROR_MESSAGE);
//             return null;
//         }
//     }

//     public int getVehicleId() {
//         try {
//             return Integer.parseInt(JOptionPane.showInputDialog("Enter Vehicle ID:"));
//         } catch (NumberFormatException e) {
//             JOptionPane.showMessageDialog(this, "Invalid Vehicle ID!", "Error", JOptionPane.ERROR_MESSAGE);
//             return -1;
//         }
//     }

//     public void displayVehicle(Vehicle vehicle) {
//         if (vehicle != null) {
//             vehicleIdField.setText(String.valueOf(vehicle.getVehicleId()));
//             customerIdField.setText(String.valueOf(vehicle.getCustomerId()));
//             makeField.setText(vehicle.getMake());
//             modelField.setText(vehicle.getModel());
//             yearField.setText(String.valueOf(vehicle.getYear()));
//             engineTypeCombo.setSelectedItem(vehicle.getEngineType().toString());
//         }
//     }

//     public void displayAllVehicles(ArrayList<Vehicle> vehicles) {
//         model.setRowCount(0);
//         for (Vehicle v : vehicles) {
//             model.addRow(new Object[]{
//                     v.getVehicleId(),
//                     v.getCustomerId(),
//                     v.getMake(),
//                     v.getModel(),
//                     v.getYear(),
//                     v.getEngineType().toString()
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
