// V.2.1.0 Public release.
//             -`  ©               
//              .o+`                   
//             `ooo/                   
//            `+oooo:                  
//           `+oooooo:                 
//           -+oooooo+:                
//         `/:-:++oooo+:                  Recommended For Arch Linux
//        `/++++/+++++++:                 Arch Linux©          
//       `/++++++++++++++:                NOT ASSOCIATED WITH ARCH LINUX, ONLY FAN MADE!       
//      `/+++ooooooooooooo/`           
//     ./ooosssso++osssssso+`          
//    .oossssso-````/osssssso+    
//   -osssssso.      :ssssssso.   
//  :osssssss/        osssso+++.  
// /ossssssss/        +ssssooo/-  
// Fan made program made to run on Linux, MacOS X, and Windows 10+.
// NOT ASSOCIATED WITH ARCH LINUX, but reccomended for Arch Linux
// Reccomended for XFCE and GNOME, some buttons might not work on GNOME and KDE
// No Garantee to work, might increase performance, performance is subject to computer specs.

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class App {

    ImageIcon logo = new ImageIcon(getClass().getClassLoader().getResource("Logo.png"));

    public static void main(String[] args) {
        new App();
    }
    
    public App() {
        JFrame mainFrame = new JFrame("Optomizer");
        mainFrame.setIconImage(logo.getImage());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 170);
        mainFrame.setLocationRelativeTo(null); // Center the frame on the screen
        mainFrame.setLayout(null);

        JOptionPane.showMessageDialog(mainFrame, 
            "Use this app with proper knowledge. \n \n Any damage caused to the system is not our responsibility, \n \n Entering the wrong PID could cause critical errors in the system. \n \n Also make shure \n before typing PID confirm that the PID is correct. \n \n MAKE SURE YOU ARE RUNNING THIS APP AS ROOT ELSE WONT WORK!", 
            "Warning", 
            JOptionPane.WARNING_MESSAGE
        );

        int cnfrmmsg = JOptionPane.showConfirmDialog(
            mainFrame,
            "Are you running the app with administrator privileges?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (cnfrmmsg == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(
                mainFrame,
                "Please run the app with administrator privileges to ensure proper functionality.\n The main concept of this app wont work without administrator privlages.",
                "Warning",
                JOptionPane.WARNING_MESSAGE
            );
        } else if (cnfrmmsg == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(mainFrame, 
                "Successfully proceeding with the app... \n Press ok to continue.", 
                "Info", 
                JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                mainFrame,
                "No option selected. Please restart the application and choose an option.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            System.exit(0);
        }

        String os = System.getProperty("os.name").toLowerCase();
        
        if (os.contains("linux")) {

            System.out.println("Target is linux OS detected os: " + os);
            System.out.println("");
            System.out.println("Target reached... Succesfully Starting...");
            System.out.println("");
            System.out.println("Any ongoing background processes will be displayed on the terminal");
            System.out.println("");
            System.out.println("If you see any errors please check the terminal for error messages");
            System.out.println("");
            System.out.println("If you are unable to figure out the error then just send the error message or the error picture at our Discord server, Have a Nice Day!");
            System.out.println("");

            JPanel panel = new JPanel();
            panel.setBounds(0, 0, 800, 600);
            panel.setLayout(null);
            panel.setBackground(Color.WHITE); // Default background color
            mainFrame.add(panel);

            JTextField pidField = new JTextField("Select PID from dropdown or type PID manually");
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
                pidLabel.setForeground(Color.RED); // Change text color to red
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
                pidLabel.setForeground(Color.RED); // Change text color to red
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

            JButton discord = new JButton("Discord");
            discord.setBounds(690, 40, 100, 20);
            discord.addActionListener(e -> {
                try {
                    Desktop.getDesktop().browse(new java.net.URI("https://discord.gg/3HKF29KuMX"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainFrame, 
                        "Error opening Discord link: " + ex.getMessage(),
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            });
            panel.add(discord);

            JButton helpButton = new JButton("Help");
            helpButton.setBounds(10, 90, 100, 30);
            helpButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(mainFrame, 
                    "This is an app designed to help you optimize your computer for better performance.\n\n" +
                    "You can use the buttons to perform different actions.\n\n" +
                    "If you have any questions, please contact the developer.\n\n" +
                    "Thank you for using Optomizer! \n\n" + 
                    "You can also visit the github Optomizer wiki for help on how to run \n\n" + 
                    "Bug reporting can be done on our Discord Server and also on our github page under the 'issues section'\n\n" + 
                    "You can get further support on our Discord server",
                    "Help", 
                    JOptionPane.INFORMATION_MESSAGE
                );

                try {
                    Desktop.getDesktop().browse(new java.net.URI("https://github.com/AlphaWolf6940/Optomizer/wiki"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainFrame, 
                        "Error opening wiki: " + ex.getMessage(),
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }

            });
            panel.add(helpButton);

            JButton exitButton = new JButton("Exit");
            exitButton.setBounds(690, 90, 100, 30);
            exitButton.addActionListener(e -> {
                int response = JOptionPane.showConfirmDialog(mainFrame, 
                    "You want to kill this innocent app :( ?", 
                    "Exit Dialog", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE
                );
                if (response == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(mainFrame, 
                        "Confirm Exit! \n \n by clicking ok you exit the app!");
                    System.exit(0);
                }
                else{
                    JOptionPane.showMessageDialog(mainFrame, "Exit cancelled by user!", "Exit Dialog", JOptionPane.WARNING_MESSAGE);
                }
            });
            panel.add(exitButton);

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

            ProcessBuilder pline = new ProcessBuilder("ps", "-eo", "pid,ni,comm", "--sort=-ni");

            try {
                Process lprocess = pline.start();
                BufferedReader lreader = new BufferedReader(new InputStreamReader(lprocess.getInputStream()));
                String line;
            
                // Skip the first line (header) if necessary
                lreader.readLine();
            
                while ((line = lreader.readLine()) != null) {
                    System.out.println(line); // Debugging: Print each line
                    model.addElement(line.trim()); // Add each line to the combo box model
                }
            
                lprocess.waitFor();
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(mainFrame, 
                    "Error fetching process list: " + ex.getMessage(),
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }

            JComboBox<String> comboBox = new JComboBox<>(model);
            comboBox.setBounds(10, 63, 600, 20);
            comboBox.addActionListener(event -> {
                String selectedProcess = (String) comboBox.getSelectedItem();
                
                if (selectedProcess != null) {
                    // Split the line into parts: PID, Nice value, and command name
                    String[] processDetails = selectedProcess.trim().split("\\s+");
            
                    // PID is the first element
                    pidField.setText(processDetails[0]);
                    
                    // You can now use the PID to renice or whatever
                    System.out.println("PID of the selected app is: " + processDetails[0] + " type that number in the pid box(The textbox) if not aldready added automatically.");
                }
            });
            panel.add(comboBox);

            JButton settingsButton = new JButton("Settings");
            settingsButton.setBounds(350, 90, 100, 30);
            settingsButton.addActionListener(e -> {
                JFrame settingsFrame = new JFrame("Settings");
                settingsFrame.setIconImage(logo.getImage());
                settingsFrame.setResizable(false);
                settingsFrame.setSize(400, 300); //width, height
                settingsFrame.setLayout(null); 

                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.setBounds(10, 10, 360, 240); //x, y, width, height

                JPanel generalTab = new JPanel();
                generalTab.setLayout(null);
                JLabel generalLabel = new JLabel("General Settings");
                generalLabel.setBounds(10, 10, 150, 20);
                generalTab.add(generalLabel);

                JButton darkModeSwitch = new JButton("Dark Mode");
                darkModeSwitch.setBounds(10, 40, 150, 20);
                darkModeSwitch.addActionListener(e1 ->panel.setBackground(Color.BLACK));
                generalTab.add(darkModeSwitch);

                JLabel warning = new JLabel("Warning: Dark mode is still under development please cooperate.");
                warning.setBounds(10, 100, 350, 20);
                warning.setForeground(Color.RED);
                generalTab.add(warning);

                //This is our old technique of changing theme but it was depricated due to flaws, you may uncomment it to use it.
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
                JLabel creditsLabel = new JLabel("Credits: Thanks to AlphaWolf6940 for all the coding \n & Idea by JoeDuck2020!");
                //JLabel creditsLabel1 = new JLabel("Credits to JoeDuck2020 for the Idea");
                creditsLabel.setBounds(10, 40, 350, 20);
                creditsTab.add(creditsLabel);
                //creditsTab.add(creditsLabel1);

                tabbedPane.addTab("General", generalTab);
                tabbedPane.addTab("Credits", creditsTab);

                settingsFrame.add(tabbedPane);
                settingsFrame.setVisible(true);
            });
            panel.add(settingsButton);

        } 
        
        else if (os.contains("mac")) {
            System.out.println("Detected os: " + os);
            System.out.println("Target linux not detected proceeding with " + os);
            System.out.println("");
            System.out.println("Target not reached but accepting... Succesfully Starting...");
            System.out.println("");
            System.out.println("Any ongoing background processes will be displayed on the terminal");
            System.out.println("");
            System.out.println("If you feel there are any errors please check the terminal for error messages");
            System.out.println("");
            System.out.println("If you are unable to figure out the error then just send the error message or the error pic to 'zerotheorymrt@gmail.com' or send us the error description in our Discord server, Have a Nice Day!");
            System.out.println("");

            JPanel panel = new JPanel();
            panel.setBounds(0, 0, 800, 600);
            panel.setLayout(null);
            panel.setBackground(Color.WHITE); // Default background color
            mainFrame.add(panel);

            
            JTextField pidField = new JTextField("Select PID from dropdown or type PID manually");
            pidField.setBounds(10, 10, 600, 20);
            panel.add(pidField);

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

            ProcessBuilder pline = new ProcessBuilder("ps", "-eo", "pid,ni,comm");

            try {
                Process lprocess = pline.start();
                BufferedReader lreader = new BufferedReader(new InputStreamReader(lprocess.getInputStream()));
                String line;
            
                // Skip the first line (header) if necessary
                lreader.readLine();
            
                while ((line = lreader.readLine()) != null) {
                    System.out.println(line); // Debugging: Print each line
                    model.addElement(line.trim()); // Add each line to the combo box model
                }
            
                lprocess.waitFor();
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(mainFrame, 
                    "Error fetching process list: " + ex.getMessage(),
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }


            JComboBox<String> comboBox = new JComboBox<>(model);
            comboBox.setBounds(10, 63, 600, 20);
            comboBox.addActionListener(event -> {
                String selectedProcess = (String) comboBox.getSelectedItem();
                
                if (selectedProcess == null) {
                    return; // Exit the method if no process is selected
                }
                // Split the line into parts: PID, Nice value, and command name
                String[] processDetails = selectedProcess.trim().split("\\s+");
            
                // PID is the first element
                pidField.setText(processDetails[0]);
                 
                // You can now use the PID to renice or whatever
                System.out.println("PID of the selected app is: " + processDetails[0] + " type that number in the pid box(The textbox) if not aldready done.");
            });
            panel.add(comboBox);

            JButton pidButton = new JButton("Send PID");
            pidButton.setBounds(10, 40, 100, 18);
            JLabel pidLabel = new JLabel();
            pidLabel.setBounds(10, 70, 300, 20);
            panel.add(pidLabel);

            JButton discord = new JButton("Discord");
            discord.setBounds(690, 40, 100, 20);
            discord.addActionListener(e -> {
                try {
                    Desktop.getDesktop().browse(new java.net.URI("https://discord.gg/3HKF29KuMX"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainFrame, 
                        "Error opening Discord link: " + ex.getMessage(),
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            });
            panel.add(discord);

            pidButton.addActionListener(e -> {
                String pidText = pidField.getText();
                pidLabel.setText("PID set to: " + pidText); // Display the PID
                pidLabel.setForeground(Color.RED); // Change text color to red
                try {
                    String pidcommand = "sudo renice -n -20 -p " + pidText;
                    ProcessBuilder processBuilder = new ProcessBuilder(pidcommand.split(" "));
                    Process process = processBuilder.start();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }

                    process.waitFor();
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
                pidLabel.setForeground(Color.RED); // Change text color to red
                System.out.println("PID set to: " + pidTtext); // Display the PID in the terminal
                try {
                    String command = "renice -n 0 -p " + pidTtext;
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
                    "Thank you for using Optomizer! \n\n" + 
                    "You can also visit the github Optomizer wiki for help on how to run \n\n" + 
                    "Bug reporting can be done on our Discord Server and also on our github page under the 'issues section'\n\n" + 
                    "You can get further support on our Discord server",
                    "Help", 
                    JOptionPane.INFORMATION_MESSAGE
                );

                try {
                    Desktop.getDesktop().browse(new java.net.URI("https://github.com/AlphaWolf6940/Optomizer/wiki"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainFrame, 
                        "Error opening wiki: " + ex.getMessage(),
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }

            });
            panel.add(helpButton);

            JButton exitButton = new JButton("Exit");
            exitButton.setBounds(690, 90, 100, 30);
            exitButton.addActionListener(e -> {
                int response = JOptionPane.showConfirmDialog(mainFrame, 
                    "You want to kill this innosent app :( ?", 
                    "Exit Dialog", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE
                );
                if (response == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(mainFrame, 
                        "Confirm Exit! \n \n by clicking ok you exit the app!");
                    System.exit(0);
                }
                else{
                    JOptionPane.showMessageDialog(mainFrame, "Exit cancelled by user!", "Exit Dialog", JOptionPane.WARNING_MESSAGE);
                }
            });
            panel.add(exitButton);

            JButton settingsButton = new JButton("Settings");
            settingsButton.setBounds(350, 90, 100, 30);
            settingsButton.addActionListener(e -> {
                JFrame settingsFrame = new JFrame("Settings");
                settingsFrame.setIconImage(logo.getImage());
                settingsFrame.setResizable(false);
                settingsFrame.setSize(400, 300); //width, height
                settingsFrame.setLayout(null);

                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.setBounds(10, 10, 360, 240); //x, y, width, height

                JPanel generalTab = new JPanel();
                generalTab.setLayout(null);
                JLabel generalLabel = new JLabel("General Settings");
                generalLabel.setBounds(10, 10, 150, 20);
                generalTab.add(generalLabel);

                JButton darkModeSwitch = new JButton("Dark Mode");
                darkModeSwitch.setBounds(10, 40, 150, 20);
                darkModeSwitch.addActionListener(e1 ->panel.setBackground(Color.BLACK));
                generalTab.add(darkModeSwitch);

                JLabel warning = new JLabel("Warning: Dark mode is still under development please cooperate.");
                warning.setBounds(10, 100, 350, 20);
                warning.setForeground(Color.RED);
                generalTab.add(warning);

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
                //JLabel creditsLabel1 = new JLabel("Credits to JoeDuck2020 for the Idea");
                creditsLabel.setBounds(10, 40, 350, 20);
                creditsTab.add(creditsLabel);
                //creditsTab.add(creditsLabel1);

                tabbedPane.addTab("General", generalTab);
                tabbedPane.addTab("Credits", creditsTab);

                settingsFrame.add(tabbedPane);
                settingsFrame.setVisible(true);
            });
            panel.add(settingsButton);
        }

        else if (os.toLowerCase().contains("windows")) {

            System.out.println("Target is Linux OS detected os: " + os);
            System.out.println("");
            System.out.println("Target not reached but accepted... Successfully Starting...");
            System.out.println("");
            System.out.println("Any ongoing background processes will be displayed on the terminal");
            System.out.println("");
            System.out.println("If you feel there are any errors, please check the terminal for error messages");
            System.out.println("");
            System.out.println("If you are unable to figure out the error, then just send the error message or the error pic to 'zerotheorymrt@gmail.com' or message us the problem in our Discored server, Have a Nice Day!");
            System.out.println("");
        
            JPanel panel = new JPanel();
            panel.setBounds(0, 0, 800, 600);
            panel.setLayout(null);
            panel.setBackground(Color.WHITE); // Default background color
            mainFrame.add(panel);
        
            JTextField pidField = new JTextField("Select PID from dropdown or type PID manually");
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
                pidLabel.setForeground(Color.RED); // Change text color to red
                try {
                    // Use WMIC to set process priority in Windows
                    String command = "wmic process where ProcessId=" + pidText + " CALL setpriority 128"; // 13 is 'High' in Windows
                    ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
                    Process process = processBuilder.start();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
        
                    process.waitFor();
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
                pidLabel.setForeground(Color.RED); // Change text color to red
                System.out.println("PID set to: " + pidTtext); // Display the PID in the terminal
                try {
                    // Use WMIC to reset process priority to Normal
                    String command = "wmic process where ProcessId=" + pidTtext + " CALL setpriority 32"; // 8 is 'Normal' in Windows
                    ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
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
                    System.out.println(ex.getMessage());
                }
            });
            panel.add(pidnormal);
        
            JButton discord = new JButton("Discord");
            discord.setBounds(690, 40, 100, 20);
            discord.addActionListener(e -> {
                try {
                    Desktop.getDesktop().browse(new java.net.URI("https://discord.gg/3HKF29KuMX"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            panel.add(discord);
        
            JButton helpButton = new JButton("Help");
            helpButton.setBounds(10, 90, 100, 30);
            helpButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(mainFrame, 
                    "This is an app designed to help you optimize your computer for better performance.\n\n" +
                    "You can use the buttons to perform different actions.\n\n" +
                    "If you have any questions, please contact the developer.\n\n" +
                    "Thank you for using Optomizer! \n\n" + 
                    "You can also visit the github Optomizer wiki for help on how to run \n\n" + 
                    "Bug reporting can be done on our Discord Server and also on our github page under the 'issues section'\n\n" + 
                    "You can get further support on our Discord server",
                    "Help", 
                    JOptionPane.INFORMATION_MESSAGE
                );

                try {
                    Desktop.getDesktop().browse(new java.net.URI("https://github.com/AlphaWolf6940/Optomizer/wiki"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(mainFrame, 
                        "Error opening wiki: " + ex.getMessage(),
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }

            });
            panel.add(helpButton);
        
            JButton exitButton = new JButton("Exit");
            exitButton.setBounds(690, 90, 100, 30);
            exitButton.addActionListener(e -> {
                int response = JOptionPane.showConfirmDialog(mainFrame, 
                    "You want to kill this innocent app :( ?", 
                    "Exit Dialog", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE
                );
                if (response == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(mainFrame, 
                        "Confirm Exit! \n \n by clicking OK you exit the app!");
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Exit cancelled by user!", "Exit Dialog", JOptionPane.WARNING_MESSAGE);
                }
            });
            panel.add(exitButton);
        
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        
            // Use tasklist to fetch processes in Windows
            ProcessBuilder pline = new ProcessBuilder("cmd.exe", "/c", "tasklist");
        
            try {
                Process lprocess = pline.start();
                BufferedReader lreader = new BufferedReader(new InputStreamReader(lprocess.getInputStream()));
                String line;
        
                // Skip the first few lines (header) if necessary
                int skipLines = 3; // Adjust based on tasklist output
                for (int i = 0; i < skipLines; i++) {
                    lreader.readLine();
                }
        
                while ((line = lreader.readLine()) != null) {
                    System.out.println(line); // Debugging: Print each line
                    model.addElement(line.trim()); // Add each line to the combo box model
                }
        
                lprocess.waitFor();
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(mainFrame, 
                    "Error fetching process list: " + ex.getMessage(),
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        
            JComboBox<String> comboBox = new JComboBox<>(model);
            comboBox.setBounds(10, 63, 600, 20);
            comboBox.addActionListener(event -> {
                String selectedProcess = (String) comboBox.getSelectedItem();
        
                if (selectedProcess != null) {
                    // Extract PID from tasklist output
                    String[] processDetails = selectedProcess.trim().split("\\s+");
                    pidField.setText(processDetails[1]); // Assuming PID is the second column in tasklist output
                    System.out.println("PID of the selected app is: " + processDetails[1]);
                }
            });
            panel.add(comboBox);

            JButton settingsButton = new JButton("Settings");
            settingsButton.setBounds(350, 90, 100, 30);
            settingsButton.addActionListener(e -> {
                JFrame settingsFrame = new JFrame("Settings");
                settingsFrame.setIconImage(logo.getImage());
                settingsFrame.setResizable(false);
                settingsFrame.setSize(400, 300); //width, height
                settingsFrame.setLayout(null);

                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.setBounds(10, 10, 360, 240); //x, y, width, height

                JPanel generalTab = new JPanel();
                generalTab.setLayout(null);
                JLabel generalLabel = new JLabel("General Settings");
                generalLabel.setBounds(10, 10, 150, 20);
                generalTab.add(generalLabel);

                JButton darkModeSwitch = new JButton("Dark Mode");
                darkModeSwitch.setBounds(10, 40, 150, 20);
                darkModeSwitch.addActionListener(e1 ->panel.setBackground(Color.BLACK));
                generalTab.add(darkModeSwitch);

                JLabel warning = new JLabel("Warning: Dark mode is still under development please cooperate.");
                warning.setBounds(10, 100, 350, 20);
                warning.setForeground(Color.RED);
                generalTab.add(warning);

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
                //JLabel creditsLabel1 = new JLabel("Credits to JoeDuck2020 for the Idea");
                creditsLabel.setBounds(10, 40, 350, 20);
                creditsTab.add(creditsLabel);
                //creditsTab.add(creditsLabel1);

                tabbedPane.addTab("General", generalTab);
                tabbedPane.addTab("Credits", creditsTab);

                settingsFrame.add(tabbedPane);
                settingsFrame.setVisible(true);
            });
            panel.add(settingsButton);
        
            // Settings button remains unchanged
            // ...
        }

        else {
            JOptionPane.showMessageDialog(mainFrame, 
                ":( This app is only supported on Linux, Mac OS, and Windows. \n You could try using it on a Virtual Matchine \n btw what os are u using, msg it in discord. Lol.", 
                "Unsupported OS", 
                JOptionPane.ERROR_MESSAGE
            );
        }
        
        mainFrame.setVisible(true);

    }

    public void yourMethod() throws IOException, InterruptedException {
        ProcessBuilder pline = new ProcessBuilder("ps", "-eo", "pid,ni,comm", "--sort=-ni");
        Process lprocess = pline.start();
        BufferedReader lreader = new BufferedReader(new InputStreamReader(lprocess.getInputStream()));
        String line;
    
        while ((line = lreader.readLine()) != null) {
            System.out.println(line); // Process each line
        }
    
        lprocess.waitFor();
    }  
}