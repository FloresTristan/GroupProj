package github.group.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class VehicleLoginView{
    private JFrame frame;
    private VehicleLoginController control;
    private JLabel reg, name, passLabel;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton loginButton, regBtn;

    public VehicleLoginView(JFrame frame, VehicleLoginController control){
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

        reg = new JLabel("LOGIN");
        reg.setHorizontalAlignment(JLabel.CENTER);
        reg.setVerticalAlignment(JLabel.CENTER);
        reg.setFont(new Font("Arial", Font.BOLD, 20));
        reg.setBounds(0, 10, 400, 50);
        reg.setOpaque(true);
        reg.setBackground(new Color(40,145,242));  
        regPanel.add(reg);

        name = new JLabel("Name");
        name.setFont(new Font("Calibre", Font.BOLD, 14));
        name.setBounds(20, 150, 150, 50);
        regPanel.add(name);

        nameField = new JTextField();
        nameField.setBounds(20, 190, 340, 25);
        regPanel.add(nameField);

        passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Calibre", Font.BOLD, 14));
        passLabel.setBounds(20, 240, 150, 50);
        regPanel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(20, 280, 340, 25);
        regPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(30, 350, 100, 30);
        loginButton.setBackground(new Color(248,217,109));
        regPanel.add(loginButton);

        regBtn = new JButton("Register");
        regBtn.setBounds(240, 350, 100, 30);
        regBtn.setBackground(new Color(248,217,109));
        regPanel.add(regBtn);
        regBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.getContentPane().removeAll();
                frame.repaint();
                control.showRegView(frame);
            }
        });
    }
}