//package com.optimizerapp.ui;

//import com.optimizerapp.system.ProcessManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JTextField pidField;
    private JLabel pidLabel;
    private JPanel panel;

    public MainFrame() {
        super("Optimizer");
        setSize(800, 170);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        showInitialWarning();

        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            setupUI();
        } else {
            JOptionPane.showMessageDialog(this,
                    ":( This app is only supported on Linux",
                    "Unsupported OS",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showInitialWarning() {
        JOptionPane.showMessageDialog(this,
            "Use this app with proper knowledge...\nMAKE SURE YOU ARE RUNNING THIS APP AS ROOT.",
            "Warning",
            JOptionPane.WARNING_MESSAGE);
    }

    private void setupUI() {
        panel = new JPanel(null);
        panel.setBounds(0, 0, 800, 600);
        panel.setBackground(Color.WHITE);
        add(panel);

        pidField = new JTextField("Enter PID of the app");
        pidField.setBounds(10, 10, 600, 20);
        panel.add(pidField);

        pidLabel = new JLabel();
        pidLabel.setBounds(10, 70, 300, 20);
        panel.add(pidLabel);

        addButtons();
    }

    private void addButtons() {
        JButton sendButton = new JButton("Send PID");
        sendButton.setBounds(10, 40, 100, 18);
        sendButton.addActionListener(e -> {
            String pid = pidField.getText();
            pidLabel.setText("PID set to: " + pid);
            ProcessManager.setPriority(pid, -20, this);
        });
        panel.add(sendButton);

        JButton normalButton = new JButton("Set to normal priority");
        normalButton.setBounds(130, 40, 190, 18);
        normalButton.addActionListener(e -> {
            String pid = pidField.getText();
            pidLabel.setText("PID set to: " + pid);
            ProcessManager.setPriority(pid, 0, this);
        });
        panel.add(normalButton);

        JButton helpButton = new JButton("Help");
        helpButton.setBounds(10, 90, 100, 30);
        helpButton.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Use buttons to change PID priority.\nContact developer for issues.",
                "Help", JOptionPane.INFORMATION_MESSAGE));
        panel.add(helpButton);

        JButton settingsButton = new JButton("Settings");
        settingsButton.setBounds(350, 90, 100, 30);
        settingsButton.addActionListener(e -> new SettingsDialog(this, panel));
        panel.add(settingsButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(690, 90, 100, 30);
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to exit?", "Exit",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        panel.add(exitButton);
    }
}