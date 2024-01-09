package github.group.login;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

class VehicleLoginView {
    private JFrame frame;
    private VehicleLoginController control;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton loginButton, regBtn;
    private JCheckBox showPasswordCheckbox;

    public VehicleLoginView(JFrame frame, VehicleLoginController control) {
        this.frame = frame;
        this.control = control;
        initPanel();
    }

    public void initPanel() {
        
        JPanel regPanel = new JPanel();
        regPanel.setBounds(0, 0, 400, 700);
        regPanel.setLayout(null);
        regPanel.setBackground(new Color(109, 198, 248));
        frame.add(regPanel);

        JLabel reg = new JLabel("LOGIN");
        reg.setHorizontalAlignment(JLabel.CENTER);
        reg.setVerticalAlignment(JLabel.CENTER);
        reg.setFont(new Font("Arial", Font.BOLD, 20));
        reg.setBounds(0, 10, 400, 50);
        reg.setOpaque(true);
        reg.setBackground(new Color(40, 145, 242));
        regPanel.add(reg);

        nameField = new JTextField();
        addTitledField(regPanel, "Username", nameField, 20, 150);

        passwordField = new JPasswordField();
        addTitledField(regPanel, "Password", passwordField, 20, 240);

        showPasswordCheckbox = new JCheckBox("Show Password");
        showPasswordCheckbox.setBounds(20, 300, 120, 20);
        showPasswordCheckbox.setBackground(new Color(109, 198, 248));
        regPanel.add(showPasswordCheckbox);

        loginButton = new JButton("Login");
        loginButton.setBounds(140, 350, 100, 30);
        loginButton.setBackground(new Color(248, 217, 109));
        regPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                char[] password = passwordField.getPassword();
                System.out.println("Attempting login for user: " + name);

                if (name.isEmpty() || password.length == 0) {
                    JOptionPane.showMessageDialog(frame, "Please fill all the blanks", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (control.getLogin(name, password)) {
                    //JOptionPane.showMessageDialog(frame,"Login successful","Success", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(frame, "User does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        showPasswordCheckbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (showPasswordCheckbox.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Show password
                } else {
                    passwordField.setEchoChar('*'); // Hide password
                }
            }
        });

        /*regBtn = new JButton("Register");
        regBtn.setBounds(240, 350, 100, 30);
        regBtn.setBackground(new Color(248, 217, 109));
        regPanel.add(regBtn);
        regBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                control.showRegView(frame);
            }
        });*/
        JLabel regbtnLabel = new JLabel("No Account? Register.");
        regbtnLabel.setFont(regbtnLabel.getFont().deriveFont(Font.PLAIN));
        regbtnLabel.setBounds(135, 400, 150, 30);
        regPanel.add(regbtnLabel);
        regbtnLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                regbtnLabel.setForeground(Color.BLUE);
                regbtnLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                regbtnLabel.setForeground(Color.BLACK);
                regbtnLabel.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                regbtnLabel.setForeground(Color.RED);
                frame.getContentPane().removeAll();
                frame.repaint();
                control.showRegView(frame);
            }
            
        });

    }

    private void addTitledField(JPanel panel, String title, JComponent component, int x, int y) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        fieldPanel.setBounds(x, y, 340, 50);
        fieldPanel.setBackground(new Color(109, 198, 248));
        panel.add(fieldPanel);

        TitledBorder titledBorder = BorderFactory.createTitledBorder(title);
        fieldPanel.setBorder(titledBorder);

        component.setBounds(10, 15, 320, 25);
        fieldPanel.add(component);
    }

    public void welcome() {
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        control.showAdminInit(frame);
    }
    
}
