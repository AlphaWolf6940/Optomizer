import javax.swing.*;
import java.awt.*;
import java.io.*;

public class App {
    public static void main(String[] args) {

        JFrame mainFrame = new JFrame("Optomizer");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 170);
        mainFrame.setLayout(null);

        JOptionPane.showMessageDialog(mainFrame, 
            "Use this app with proper knowledge \n \n any damage caused to the system is not our responsibility \n \n entering the wrong PID could cause \n \n critical errors in the system. \n \n we are not threatening you but we are warning you about the consequences \n that occur if something could go wrong. \n \n before typing PID confirm that the PID is correct. \n \n MAKE SURE YOU ARE RUNNING THIS APP AS ROOT.", 
            "Warning", 
            JOptionPane.WARNING_MESSAGE
        );

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("linux")) {

            System.out.println("Target is linux OS detected os: " + os);
            System.out.println("");
            System.out.println("Target reached... Succesfully Starting...");
            System.out.println("");
            System.out.println("Any ongoing background processes will be displayed on the terminal");
            System.out.println("");
            System.out.println("If you feel there are any errors please check the terminal for error messages");
            System.out.println("");
            System.out.println("If you are unable to figure out the error then just send the error message or the error pic to 'zerotheorymrt@gmail.com', Have a Nice Day!");
            System.out.println("");

            JPanel panel = new JPanel();
            panel.setBounds(0, 0, 800, 600);
            panel.setLayout(null);
            panel.setBackground(Color.WHITE); // Default background color
            mainFrame.add(panel);

            JTextField pidField = new JTextField("Enter PID of the app");
            pidField.setBounds(10, 10, 600, 20);
            panel.add(pidField);

            JButton pidButton = new JButton("Send PID");
            pidButton.setBounds(10, 40, 100, 18);
            JLabel pidLabel = new JLabel();
            pidLabel.setBounds(10, 70, 300, 20);
            panel.add(pidLabel);

            pidButton.addActionListener(e -> {
                String pidText = pidField.getText();
                pidLabel.setText("PID set to: " + pidText); // Display the PID
                try {
                    String command = "renice -n -20 " + pidText;
                    ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
                    Process process = processBuilder.start();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }

                    process.waitFor();
                    System.out.println("Priority set successfully.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainFrame, 
                        "Error setting priority: " + ex.getMessage(),
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            });
            panel.add(pidButton);

            JButton pidnormal = new JButton("Set to normal priority");
            pidnormal.setBounds(130, 40, 190, 18);
            pidnormal.addActionListener(e -> {
                String pidTtext = pidField.getText();
                pidLabel.setText("PID set to: " + pidTtext); // Display the PID
                System.out.println("PID set to: " + pidTtext); // Display the PID in the terminal
                try {
                    String command = "renice -n 0 " + pidTtext;
                    ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
                    Process process = processBuilder.start();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }

                    process.waitFor();
                    System.out.println("Priority set successfully.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainFrame, 
                        "Error setting priority: " + ex.getMessage(),
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                        System.out.println(ex.getMessage()
                    );
                }
            });
            panel.add(pidnormal);

            JButton helpButton = new JButton("Help");
            helpButton.setBounds(10, 90, 100, 30);
            helpButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(mainFrame, 
                    "This is an app designed to help you optimize your computer for better performance.\n\n" +
                    "You can use the buttons to perform different actions.\n\n" +
                    "If you have any questions, please contact the developer.\n\n" +
                    "Thank you for using Optomizer!", 
                    "Help", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            });
            panel.add(helpButton);

            JButton exitButton = new JButton("Exit");
            exitButton.setBounds(690, 90, 100, 30);
            exitButton.addActionListener(e -> {
                int response = JOptionPane.showConfirmDialog(mainFrame, 
                    "Are you sure you want to kill this innosent app :( ?", 
                    "Exit", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE
                );
                if (response == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(mainFrame, 
                        "Exit Confirm! \n \n by clicking ok you exit the app!");
                    System.exit(0);
                }
                else{
                    JOptionPane.showMessageDialog(mainFrame, "Exit cancelled by user!", "Exit", JOptionPane.WARNING_MESSAGE);
                }
            });
            panel.add(exitButton);

            JButton settingsButton = new JButton("Settings");
            settingsButton.setBounds(350, 90, 100, 30);
            settingsButton.addActionListener(e -> {
                JFrame settingsFrame = new JFrame("Settings");
                settingsFrame.setSize(400, 300);
                settingsFrame.setLayout(null);

                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.setBounds(10, 10, 360, 240);

                JPanel generalTab = new JPanel();
                generalTab.setLayout(null);
                JLabel generalLabel = new JLabel("General Settings");
                generalLabel.setBounds(10, 10, 150, 20);
                generalTab.add(generalLabel);

                JButton darkModeSwitch = new JButton("Dark Mode");
                darkModeSwitch.setBounds(10, 40, 150, 20);
                darkModeSwitch.addActionListener(e1 -> panel.setBackground(Color.BLACK));
                generalTab.add(darkModeSwitch);

                //JRadioButton darkMode = new JRadioButton("Dark Mode");
                //darkMode.setBounds(10, 40, 150, 20);
                //darkMode.addActionListener(e1 -> panel.setBackground(Color.BLACK));
                //generalTab.add(darkMode);

                JButton lightMode = new JButton("Light Mode");
                lightMode.setBounds(10, 70, 150, 20);
                lightMode.addActionListener(e1 -> panel.setBackground(Color.WHITE));
                generalTab.add(lightMode);

                ButtonGroup modeGroup = new ButtonGroup();
                modeGroup.add(darkModeSwitch);
                modeGroup.add(lightMode);

                JButton exitSettingsMenu = new JButton("Exit Settings Menu");
                exitSettingsMenu.setBounds(10, 130, 150, 20);
                exitSettingsMenu.addActionListener(e1 -> settingsFrame.dispose());
                generalTab.add(exitSettingsMenu);

                JPanel creditsTab = new JPanel();
                creditsTab.setLayout(null);
                JLabel creditsLabel = new JLabel("Credits: Thanks to AlphaWolf6940 for all the coding & Idea by JoeDuck2020!");
                //JLabel creditsLabel1 = new JLabel("Credits to Aarush for the Idea");
                creditsLabel.setBounds(10, 40, 350, 20);
                creditsTab.add(creditsLabel);
                //creditsTab.add(creditsLabel1);

                tabbedPane.addTab("General", generalTab);
                tabbedPane.addTab("Credits", creditsTab);

                settingsFrame.add(tabbedPane);
                settingsFrame.setVisible(true);
            });
            panel.add(settingsButton);

        } else {
            JOptionPane.showMessageDialog(mainFrame, 
                ":( This app is only supported on Linux \n you could try using it on a Virtual Box", 
                "Unsupported OS", 
                JOptionPane.ERROR_MESSAGE
            );
        }

        mainFrame.setVisible(true);
    }
}   