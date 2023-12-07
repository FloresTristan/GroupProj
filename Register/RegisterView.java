package github.group.register;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class RegisterView {
    private JFrame frame;
    private RegisterController regCon;

    public RegisterView(JFrame frame, RegisterController regCon) {
        this.frame = frame;
        this.regCon = regCon;
        initRegView();
    }

<<<<<<< HEAD
	public void initRegView(){
		JPanel logPanel = new JPanel();
=======
    public void initRegView() {
        JPanel logPanel = new JPanel();
>>>>>>> 5cd421c86cb20079773daf8fa2c1179b1dc99901
        logPanel.setBounds(0, 0, 400, 50);
        logPanel.setBackground(new Color(40, 145, 242));
        logPanel.setLayout(null);
        frame.add(logPanel);

        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(0, 50, 400, 700);
        loginPanel.setBackground(new Color(109, 198, 248));
        loginPanel.setLayout(null);
        frame.add(loginPanel);

        JLabel loginLabel = new JLabel("CITE Vehicle Registration Form");
        loginLabel.setBounds(45, -20, 300, 100);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
        logPanel.add(loginLabel);

        // Add dropdown for Make, Type, Year Model
        JLabel dropdownLabel = new JLabel("Make, Type, Year Model:");
        dropdownLabel.setBounds(20, 495, 180, 25);
        loginPanel.add(dropdownLabel);

        String[] makeTypeYearModelChoices = {"Make1 Type1 Year1", "Make2 Type2 Year2", "Make3 Type3 Year3"};
        JComboBox<String> makeTypeYearModelComboBox = new JComboBox<>(makeTypeYearModelChoices);
        makeTypeYearModelComboBox.setBounds(200, 495, 180, 25);
        loginPanel.add(makeTypeYearModelComboBox);

        JLabel userLabel = new JLabel("Name:");
        userLabel.setBounds(20, 80, 100, 100);
        loginPanel.add(userLabel);

        JTextField userField = new JTextField("");
        userField.setBounds(20, 140, 300, 25);
        loginPanel.add(userField);

        JLabel vehicleLabel = new JLabel("Vehicle Type:");
        vehicleLabel.setBounds(20, 135, 100, 100);
        loginPanel.add(vehicleLabel);

        JTextField vehicleField = new JTextField("");
        vehicleField.setBounds(20, 195, 300, 25);
        loginPanel.add(vehicleField);

        JLabel makeLabel = new JLabel("Make:");
        makeLabel.setBounds(20, 195, 100, 100);
        loginPanel.add(makeLabel);

        JTextField makeField = new JTextField("");
        makeField.setBounds(20, 255, 300, 25);
        loginPanel.add(makeField);

        JLabel orLabel = new JLabel("OR Number:");
        orLabel.setBounds(20, 255, 100, 100);
        loginPanel.add(orLabel);

        JTextField orField = new JTextField("");
        orField.setBounds(20, 315, 300, 25);
        loginPanel.add(orField);

        JLabel crLabel = new JLabel("CR Number:");
        crLabel.setBounds(20, 315, 100, 100);
        loginPanel.add(crLabel);

        JTextField crField = new JTextField("");
        crField.setBounds(20, 375, 300, 25);
        loginPanel.add(crField);

        JLabel plateLabel = new JLabel("Plate No:");
        plateLabel.setBounds(20, 375, 100, 100);
        loginPanel.add(plateLabel);

        JTextField plateField = new JTextField("");
        plateField.setBounds(20, 435, 300, 25);
        loginPanel.add(plateField);

        JLabel licenseLabel = new JLabel("License No:");
        licenseLabel.setBounds(20, 435, 100, 100);
        loginPanel.add(licenseLabel);

        JTextField licenseField = new JTextField("");
        licenseField.setBounds(20, 495, 300, 25);
        loginPanel.add(licenseField);

        JButton submitBttn = new JButton("submit");
        submitBttn.setBounds(60, 560, 100, 40);
        submitBttn.setBackground(new Color(248, 217, 109));
        submitBttn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, "Do you want to submit?");
                if (result == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "You are now Registered!");
                }
            }
        });
        loginPanel.add(submitBttn);
<<<<<<< HEAD

        JButton cnclBttn = new JButton("cancel");
        cnclBttn.setBounds(215, 560, 100, 40);
        cnclBttn.setBackground(new Color(248, 217, 109));
        loginPanel.add(cnclBttn);
=======
>>>>>>> 5cd421c86cb20079773daf8fa2c1179b1dc99901

        JButton cnclBttn = new JButton("cancel");
        cnclBttn.setBounds(215, 560, 100, 40);
        cnclBttn.setBackground(new Color(248, 217, 109));
        loginPanel.add(cnclBttn);
    }
}

class RegisterController {
    // Your controller logic goes here
}

public class MainClass {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Vehicle Registration");
                frame.setSize(400, 700);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                RegisterController regCon = new RegisterController();
                new RegisterView(frame, regCon);
                frame.setLayout(null);
                frame.setVisible(true);
            }
        });
    }
}
