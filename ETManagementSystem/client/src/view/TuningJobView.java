// package com.etms.client.TuningJob;




// import java.awt.BorderLayout;
// import java.awt.Color;
// import java.awt.FlowLayout;
// import java.awt.Font;
// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.GridLayout;
// import java.awt.RenderingHints;
// import java.awt.event.ActionListener;
// import java.math.BigDecimal;
// import java.util.ArrayList;

// import javax.swing.BorderFactory;
// import javax.swing.Box;
// import javax.swing.BoxLayout;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JOptionPane;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTable;
// import javax.swing.JTextField;
// import javax.swing.table.DefaultTableModel;

// import com.etms.MainDashboard.MainDashboard;
// import com.etms.server.tuningjob.TuningJob;

// public class TuningJobView extends JFrame {
//     private JTextField ecuFileField, beforeHpField, afterHpField, beforeTorqueField, afterTorqueField, fuelEfficiencyField, costField;
//     private JButton addButton, updateButton, deleteButton, searchButton, viewAllButton, backButton;
//     private JTable table;
//     private DefaultTableModel model;

//     private MainDashboard mainDashboard;
    
//     public TuningJobView(MainDashboard mainDashboard) {
//         this.mainDashboard = mainDashboard;
//         setTitle("Tuning Job Management");
//         setSize(950, 700);
//         setLocationRelativeTo(null);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         getContentPane().setBackground(Color.WHITE);
//         setLayout(new BorderLayout());

//         Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);

//         // === FORM PANEL ===
//         JPanel formPanel = new JPanel();
//         formPanel.setLayout(new GridLayout(7, 2, 10, 10));
//         formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));
//         formPanel.setBackground(Color.WHITE);

//         formPanel.add(createLabel("ECU File:", labelFont));
//         ecuFileField = new JTextField();
//         formPanel.add(ecuFileField);

//         formPanel.add(createLabel("Before HP:", labelFont));
//         beforeHpField = new JTextField();
//         formPanel.add(beforeHpField);

//         formPanel.add(createLabel("After HP:", labelFont));
//         afterHpField = new JTextField();
//         formPanel.add(afterHpField);

//         formPanel.add(createLabel("Before Torque:", labelFont));
//         beforeTorqueField = new JTextField();
//         formPanel.add(beforeTorqueField);

//         formPanel.add(createLabel("After Torque:", labelFont));
//         afterTorqueField = new JTextField();
//         formPanel.add(afterTorqueField);

//         formPanel.add(createLabel("Fuel Efficiency Change:", labelFont));
//         fuelEfficiencyField = new JTextField();
//         formPanel.add(fuelEfficiencyField);

//         formPanel.add(createLabel("Cost:", labelFont));
//         costField = new JTextField();
//         formPanel.add(costField);

//         // === BUTTON PANEL ===
//         JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
//         buttonPanel.setBackground(Color.WHITE);

//         addButton = createRoundedButton("Add Tuning Job");
//         updateButton = createRoundedButton("Update Tuning Job");
//         deleteButton = createRoundedButton("Delete Tuning Job");
//         searchButton = createRoundedButton("Search Tuning Job");
//         viewAllButton = createRoundedButton("View All");

//         buttonPanel.add(addButton);
//         buttonPanel.add(updateButton);
//         buttonPanel.add(deleteButton);
//         buttonPanel.add(searchButton);
//         buttonPanel.add(viewAllButton);

//         // === TABLE PANEL ===
//         model = new DefaultTableModel(new String[]{
//             "ID", "Vehicle ID", "ECU File", "Before HP", "After HP",
//             "Before Torque", "After Torque", "Fuel Efficiency Change", "Cost"
//         }, 0);
//         table = new JTable(model);
//         JScrollPane tableScrollPane = new JScrollPane(table);
//         tableScrollPane.setBorder(BorderFactory.createTitledBorder("Tuning Jobs"));

//         // === BACK BUTTON PANEL ===
//         backButton = createRoundedButton("Back to Dashboard");
//         backButton.addActionListener(e -> {
//             dispose();
//             this.mainDashboard.setVisible(true);
//         });

//         JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 10));
//         bottomPanel.setBackground(Color.WHITE);
//         bottomPanel.add(backButton);

//         // === MAIN CONTENT PANEL ===
//         JPanel contentPanel = new JPanel();
//         contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
//         contentPanel.setBackground(Color.WHITE);
//         contentPanel.add(formPanel);
//         contentPanel.add(Box.createVerticalStrut(10));
//         contentPanel.add(buttonPanel);
//         contentPanel.add(Box.createVerticalStrut(10));
//         contentPanel.add(tableScrollPane);
//         contentPanel.add(Box.createVerticalStrut(10));
//         contentPanel.add(bottomPanel);

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

//     // === CRUD INTERFACE METHODS ===

//     public TuningJob getTuningJobDetails() {
//         try {
//             String ecuFile = ecuFileField.getText().trim();
//             int beforeHp = Integer.parseInt(beforeHpField.getText().trim());
//             int afterHp = Integer.parseInt(afterHpField.getText().trim());
//             int beforeTorque = Integer.parseInt(beforeTorqueField.getText().trim());
//             int afterTorque = Integer.parseInt(afterTorqueField.getText().trim());
//             double fuelEfficiencyChange = Double.parseDouble(fuelEfficiencyField.getText().trim());
//             double cost = Double.parseDouble(costField.getText().trim());

//             if (ecuFile.isEmpty()) {
//                 JOptionPane.showMessageDialog(this, "ECU file cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
//                 return null;
//             }

//             if (afterHp <= beforeHp) {
//                 JOptionPane.showMessageDialog(this, "After HP must be greater than Before HP.", "Validation Error", JOptionPane.ERROR_MESSAGE);
//                 return null;
//             }

//             int vehicleId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Vehicle ID:"));
//             return new TuningJob(0, vehicleId, ecuFile, beforeHp, afterHp, beforeTorque, afterTorque,
//                     BigDecimal.valueOf(fuelEfficiencyChange), BigDecimal.valueOf(cost), null);

//         } catch (NumberFormatException e) {
//             JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
//             return null;
//         }
//     }

//     public Integer getTuningJobId() {
//         try {
//             return Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Tuning Job ID:"));
//         } catch (NumberFormatException e) {
//             JOptionPane.showMessageDialog(this, "Invalid ID entered.", "Input Error", JOptionPane.ERROR_MESSAGE);
//             return null;
//         }
//     }

//     public void displayTuningJob(TuningJob tuningJob) {
//         if (tuningJob != null) {
//             ecuFileField.setText(tuningJob.getEcuFileUsed());
//             beforeHpField.setText(String.valueOf(tuningJob.getBeforeHp()));
//             afterHpField.setText(String.valueOf(tuningJob.getAfterHp()));
//             beforeTorqueField.setText(String.valueOf(tuningJob.getBeforeTorque()));
//             afterTorqueField.setText(String.valueOf(tuningJob.getAfterTorque()));
//             fuelEfficiencyField.setText(String.valueOf(tuningJob.getFuelEfficiencyChange()));
//             costField.setText(String.valueOf(tuningJob.getCost()));
//         }
//     }

//     public void displayAllTuningJobs(ArrayList<TuningJob> tuningJobs) {
//         model.setRowCount(0);
//         for (TuningJob job : tuningJobs) {
//             model.addRow(new Object[]{
//                 job.getTuningId(), job.getVehicleId(), job.getEcuFileUsed(),
//                 job.getBeforeHp(), job.getAfterHp(),
//                 job.getBeforeTorque(), job.getAfterTorque(),
//                 job.getFuelEfficiencyChange(), job.getCost()
//             });
//         }
//     }

//     // === ACTION REGISTRATION ===

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
