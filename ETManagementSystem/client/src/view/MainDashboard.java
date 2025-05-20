package com.etms.MainDashboard;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.Naming;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.etms.client.Customer.CustomerController;
import com.etms.client.Customer.CustomerView;
import com.etms.client.ServiceHistory.ServiceHistoryController;
import com.etms.client.ServiceHistory.ServiceHistoryView;
import com.etms.client.TuningJob.TuningJobController;
import com.etms.client.TuningJob.TuningJobView;
import com.etms.client.Vehicle.VehicleController;
import com.etms.client.Vehicle.VehicleView;
import com.etms.server.customer.CustomerService;
import com.etms.server.servicehistory.ServiceHistoryService;
import com.etms.server.tuningjob.TuningJobService;
import com.etms.server.vehicle.VehicleService;

public class MainDashboard extends JFrame {
    private JLabel backgroundImageLabel;
    private JLayeredPane imageLayeredPane;
    private BufferedImage originalImage;

    public MainDashboard() {
        setTitle("Vehicle Tuning Management System");
        setSize(1000, 700);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        // HEADER
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        headerPanel.setBackground(new Color(20, 20, 20));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("Main Menu");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        headerPanel.add(titleLabel, BorderLayout.EAST);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // IMAGE SECTION
        imageLayeredPane = new JLayeredPane();
        imageLayeredPane.setLayout(new BorderLayout());
        imageLayeredPane.setPreferredSize(new Dimension(getWidth(), (int) (getHeight() * 0.7)));

        backgroundImageLabel = new JLabel();
        backgroundImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundImageLabel.setVerticalAlignment(SwingConstants.CENTER);
        loadOriginalImage();
        resizeImageLayer();

        imageLayeredPane.add(backgroundImageLabel, BorderLayout.CENTER);
        mainPanel.add(imageLayeredPane, BorderLayout.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeImageLayer();
            }
        });

        // BUTTONS SECTION
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setBackground(Color.BLACK);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        // First row of buttons
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonsPanel.add(createResponsiveButton("Manage Customers", e -> setUpCustomerView()), gbc);

        gbc.gridx = 1;
        buttonsPanel.add(createResponsiveButton("Manage Vehicles", e -> setUpVehicleView()), gbc);

        // Second row
        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonsPanel.add(createResponsiveButton("Manage Tuning Jobs", e -> setUpTuningJobView()), gbc);

        gbc.gridx = 1;
        buttonsPanel.add(createResponsiveButton("View Service History", e -> setUpServiceHistoryView()), gbc);

        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void resizeImageLayer() {
        int width = getWidth();
        int height = (int) (getHeight() * 0.7);
        backgroundImageLabel.setBounds(0, 0, width, height);
        updateBackgroundImage(width, height);
    }

    private void loadOriginalImage() {
        try {
            originalImage = ImageIO.read(new File("C:\\users\\user\\Downloads\\WhatsApp Image 2025-04-09 at 11.31.38_d11ee7dd.jpg"));
        } catch (Exception e) {
            backgroundImageLabel.setText("Image not found");
            backgroundImageLabel.setForeground(Color.WHITE);
            backgroundImageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        }
    }

    private void updateBackgroundImage(int width, int height) {
        if (originalImage != null) {
            Image scaled = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            backgroundImageLabel.setIcon(new ImageIcon(scaled));
        }
    }

    private JButton createResponsiveButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradient = new GradientPaint(0, 0, new Color(60, 120, 180),
                        0, getHeight(), new Color(30, 80, 140));
                g2.setPaint(getModel().isRollover() ?
                        new GradientPaint(0, 0, new Color(120, 160, 200),
                                0, getHeight(), new Color(80, 140, 200)) : gradient);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(new Color(200, 200, 200, 100));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        button.addActionListener(action);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return button;
    }

    private JFrame setUpCustomerView() {
        try {
            CustomerView view = new CustomerView();
            CustomerService service = (CustomerService) Naming.lookup("rmi://localhost/CustomerService");
            new CustomerController(view);
            this.setVisible(false);
            return view;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to CustomerService!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return this;
        }
    }

    private JFrame setUpVehicleView() {
        try {
            VehicleView view = new VehicleView(this);
            VehicleService service = (VehicleService) Naming.lookup("rmi://localhost/VehicleService");
            new VehicleController(view, service);
            this.setVisible(false);
            return view;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to VehicleService!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return this;
        }
    }

    private JFrame setUpTuningJobView() {
        try {
            TuningJobView view = new TuningJobView(this);
            TuningJobService service = (TuningJobService) Naming.lookup("rmi://localhost/TuningJobService");
            new TuningJobController(view, service);
            this.setVisible(false);
            return view;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to TuningJobService!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return this;
        }
    }

    private JFrame setUpServiceHistoryView() {
        try {
            ServiceHistoryView view = new ServiceHistoryView(this);
            ServiceHistoryService service = (ServiceHistoryService) Naming.lookup("rmi://localhost/ServiceHistoryService");
            new ServiceHistoryController(view, service);
            this.setVisible(false);
            return view;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to ServiceHistoryService!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MainDashboard();
        });
    }
}
