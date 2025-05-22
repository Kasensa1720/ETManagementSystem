package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import controller.*;

public class MainDashboard extends JFrame {
    private static final Color BACKGROUND_COLOR = new Color(20, 20, 20);
    private static final Color BUTTON_GRADIENT_START = new Color(60, 120, 180);
    private static final Color BUTTON_GRADIENT_END = new Color(30, 80, 140);
    private static final Color BUTTON_HOVER_START = new Color(120, 160, 200);
    private static final Color BUTTON_HOVER_END = new Color(80, 140, 200);
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 28);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 16);
    
    private JLabel backgroundImageLabel;
    private BufferedImage originalImage;

    public MainDashboard() {
        initializeFrame();
        setupUI();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Vehicle Tuning Management System");
        setSize(1000, 700);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void setupUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        mainPanel.add(createImagePanel(), BorderLayout.CENTER);
        mainPanel.add(createButtonsPanel(), BorderLayout.SOUTH);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        headerPanel.setBackground(BACKGROUND_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("Main Menu");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        try {
            ImageIcon logoIcon = new ImageIcon("C:\\Users\\USER\\Documents\\NetBeansProjects\\ETManagementSystem\\ETManagementSystem\\client\\src\\view\\images\\DSR.png");
            Image scaledLogo = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
            logoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            headerPanel.add(logoLabel, BorderLayout.EAST);
        } catch (Exception e) {
            JLabel logoLabel = new JLabel("Logo");
            logoLabel.setForeground(Color.WHITE);
            headerPanel.add(logoLabel, BorderLayout.EAST);
        }

        return headerPanel;
    }

    private JPanel createImagePanel() {
        JLayeredPane imageLayeredPane = new JLayeredPane();
        imageLayeredPane.setLayout(new BorderLayout());
        imageLayeredPane.setPreferredSize(new Dimension(getWidth(), (int)(getHeight() * 0.7)));

        backgroundImageLabel = new JLabel();
        backgroundImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundImageLabel.setVerticalAlignment(SwingConstants.CENTER);
        loadOriginalImage();
        updateBackgroundImage();

        imageLayeredPane.add(backgroundImageLabel, BorderLayout.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateBackgroundImage();
            }
        });

        JPanel container = new JPanel(new BorderLayout());
        container.add(imageLayeredPane, BorderLayout.CENTER);
        return container;
    }

    private JPanel createButtonsPanel() {
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

        String[][] buttonData = {
            {"Manage Customers", "Manage Vehicles"},
            {"Manage Tuning Jobs", "View Service History"}
        };

        for (int row = 0; row < buttonData.length; row++) {
            for (int col = 0; col < buttonData[row].length; col++) {
                gbc.gridx = col;
                gbc.gridy = row;
                buttonsPanel.add(createMenuButton(buttonData[row][col]), gbc);
            }
        }

        return buttonsPanel;
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D)g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                Color startColor = getModel().isRollover() ? BUTTON_HOVER_START : BUTTON_GRADIENT_START;
                Color endColor = getModel().isRollover() ? BUTTON_HOVER_END : BUTTON_GRADIENT_END;
                
                GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, getHeight(), endColor);
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(new Color(200, 200, 200, 100));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        button.setForeground(Color.WHITE);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        button.addActionListener(getButtonAction(text));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent evt) {
                button.setCursor(Cursor.getDefaultCursor());
            }
        });

        return button;
    }

    private ActionListener getButtonAction(String buttonText) {
        return e -> {
            try {
                JFrame view = null;
                switch (buttonText) {
                    case "Manage Customers":
                        view = new CustomerView(this);
                        new CustomerController((CustomerView)view, ClientApp.customerService);
                        break;
                    case "Manage Vehicles":
                        view = new VehicleView(this);
                        new VehicleController((VehicleView)view, ClientApp.vehicleService);
                        break;
                    case "Manage Tuning Jobs":
                        view = new TuningJobView(this);
                        new TuningJobController((TuningJobView)view, ClientApp.tuningJobService);
                        break;
                    case "View Service History":
                        view = new ServiceHistoryView(this);
                        new ServiceHistoryController((ServiceHistoryView)view, ClientApp.serviceHistoryService);
                        break;
                }
                if (view != null) {
                    this.setVisible(false);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                    "Failed to connect to " + buttonText.replace(" ", "") + "Service!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        };
    }

    private void loadOriginalImage() {
        try {
            originalImage = ImageIO.read(new File("C:\\users\\user\\Downloads\\WhatsApp Image 2025-04-09 at 11.31.38_d11ee7dd.jpg"));
            backgroundImageLabel.setBackground(Color.BLACK);
            backgroundImageLabel.setOpaque(true);
        } catch (Exception e) {
            backgroundImageLabel.setText("Image not found");
            backgroundImageLabel.setForeground(Color.WHITE);
            backgroundImageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            backgroundImageLabel.setBackground(Color.BLACK);
            backgroundImageLabel.setOpaque(true);
        }
    }

    private void updateBackgroundImage() {
        if (originalImage != null) {
            int panelWidth = getWidth();
            int panelHeight = (int)(getHeight() * 0.7);
            
            // Calculate scaling factors
            double widthRatio = (double) panelWidth / originalImage.getWidth();
            double heightRatio = (double) panelHeight / originalImage.getHeight();
            
            // Use the larger ratio to ensure full coverage
            double scale = Math.max(widthRatio, heightRatio);
            
            int scaledWidth = (int)(originalImage.getWidth() * scale);
            int scaledHeight = (int)(originalImage.getHeight() * scale);
            
            // Calculate position to center the image
            int x = (panelWidth - scaledWidth) / 2;
            int y = (panelHeight - scaledHeight) / 2;
            
            // Create scaled image
            Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            
            // Create a new BufferedImage to draw on
            BufferedImage result = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = result.createGraphics();
            
            // Fill with black background first
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, panelWidth, panelHeight);
            
            // Draw the scaled image
            g2d.drawImage(scaledImage, x, y, null);
            g2d.dispose();
            
            backgroundImageLabel.setIcon(new ImageIcon(result));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new MainDashboard();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}