// package com.etms.client.ServiceHistory;



// import java.awt.BorderLayout;
// import java.awt.Color;
// import java.awt.FlowLayout;
// import java.awt.Font;
// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.GridLayout;
// import java.awt.RenderingHints;
// import java.awt.event.ActionListener;
// import java.sql.Date;
// import java.util.List;

// import javax.swing.BorderFactory;
// import javax.swing.Box;
// import javax.swing.BoxLayout;
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
// import com.etms.server.servicehistory.ServiceHistory;

// public class ServiceHistoryView extends JFrame {
   

//     private JTextField tuningJobIdField,vehicleIdField, notesField, performedByField;
//     private JComboBox<String> serviceTypeBox;
//     private JTable table;
//     private DefaultTableModel model;
//     private JButton addButton, backButton, viewAllButton;

//  private MainDashboard mainDashboard; // ← Add this
    
//     public ServiceHistoryView(MainDashboard mainDashboard) { 
//         this.mainDashboard = mainDashboard;
//         setTitle("Service History Management");
//         setSize(850, 600);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLocationRelativeTo(null);
//         getContentPane().setBackground(Color.WHITE);
//         setLayout(new BorderLayout());

//         Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);

//         // === Form Panel ===
//         JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
//         formPanel.setBorder(new EmptyBorder(20, 40, 10, 40));
//         formPanel.setBackground(Color.WHITE);

//         formPanel.add(createLabel("Tuning Job ID:", labelFont));
//         tuningJobIdField = new JTextField();
//         formPanel.add(tuningJobIdField);

//         formPanel.add(createLabel("Vehicle ID:", labelFont));
//         vehicleIdField = new JTextField();
//         formPanel.add(vehicleIdField);

//         formPanel.add(createLabel("Service Type:", labelFont));
//         serviceTypeBox = new JComboBox<>(new String[]{"Oil Change", "Turbo Upgrade", "ECU Remap", "Other"});
//         formPanel.add(serviceTypeBox);

//         formPanel.add(createLabel("Notes:", labelFont));
//         notesField = new JTextField();
//         formPanel.add(notesField);

//         formPanel.add(createLabel("Performed By:", labelFont));
//         performedByField = new JTextField();
//         formPanel.add(performedByField);

//         addButton = createRoundedButton("Add Service History");
//         formPanel.add(new JLabel()); // Empty for alignment
//         formPanel.add(addButton);

//         // === Table Panel ===
//         model = new DefaultTableModel(new String[]{
//             "ID", "Vehicle ID", "Service Type", "Notes", "Performed By", "Service Date"
//         }, 0);
//         table = new JTable(model);
//         JScrollPane tableScrollPane = new JScrollPane(table);
//         tableScrollPane.setBorder(BorderFactory.createTitledBorder("Service History Records"));

//         // === Button Panel ===
//         JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
//         buttonPanel.setBackground(Color.WHITE);

//         viewAllButton = createRoundedButton("View All Service History");
//         backButton = createRoundedButton("Back to Dashboard");

//         backButton.addActionListener(e -> {
//             this.dispose();
//             this.mainDashboard.setVisible(true);
//         });
//         buttonPanel.add(viewAllButton);
//         buttonPanel.add(backButton);

//         // === Main Content Panel ===
//         JPanel contentPanel = new JPanel();
//         contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
//         contentPanel.setBackground(Color.WHITE);
//         contentPanel.add(formPanel);
//         contentPanel.add(Box.createVerticalStrut(10));
//         contentPanel.add(tableScrollPane);
//         contentPanel.add(Box.createVerticalStrut(10));
//         contentPanel.add(buttonPanel);

//         add(contentPanel, BorderLayout.CENTER);
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
//                 g2.setColor(getModel().isRollover() ? new Color(100, 140, 200) : new Color(70, 130, 180));
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

//     // === Getters ===
//     public String getTuningJobId() {
//         return tuningJobIdField.getText().trim();
//     }

//     public String getVehicleId(){
//         return vehicleIdField.getText().trim();
//     }

//     public String getServiceType() {
//         return serviceTypeBox.getSelectedItem().toString();
//     }

//     public String getNotes() {
//         return notesField.getText().trim();
//     }

//     public String getPerformedBy() {
//         return performedByField.getText().trim();
//     }

//     // === Data Builder ===
//     public ServiceHistory getServiceHistoryDetails() {
//         try {
//             int vehicleid = Integer.parseInt(getVehicleId());
//             int tuningJobId = Integer.parseInt(getTuningJobId());
//             String serviceType = getServiceType();
//             String notes = getNotes();
//             String performedBy = getPerformedBy();
//             Date serviceDate = new Date(System.currentTimeMillis());

//             if (notes.isEmpty() || performedBy.isEmpty()) {
//                 JOptionPane.showMessageDialog(this, "Notes and Performed By fields cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
//                 return null;
//             }

//             return new ServiceHistory(tuningJobId,vehicleid , serviceType, notes, performedBy, serviceDate, tuningJobId);
//         } catch (NumberFormatException e) {
//             JOptionPane.showMessageDialog(this, "Please enter a valid numeric Tuning Job ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
//             return null;
//         }
//     }

//     public void clearFields() {
//         tuningJobIdField.setText("");
//         notesField.setText("");
//         performedByField.setText("");
//         serviceTypeBox.setSelectedIndex(0);
//     }

//     public void displayServiceHistory(List<ServiceHistory> historyList) {
//         model.setRowCount(0);
//         for (ServiceHistory sh : historyList) {
//             model.addRow(new Object[]{
//                 sh.getServiceId(),
//                 sh.getVehicleId(),
//                 sh.getServiceType(),
//                 sh.getDetails(),
//                 sh.getPerformedBy(),
//                 sh.getServiceDate()
//             });
//         }
//     }

//     public void registerAddAction(ActionListener listener) {
//         addButton.addActionListener(listener);
//     }


//     public void registerViewAllAction(ActionListener listener) {
//         viewAllButton.addActionListener(listener);
//     }

    
// }
