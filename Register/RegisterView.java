package github.group.register;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;


class RegisterView{
	private JFrame frame;
	private RegisterController regCon;
	private JTextField userField, userNameField;  
    private JComboBox<String> vehicleTypeComboBox; 
    private JComboBox<Integer> yearModelComboBox;  
    private JTextField colorField; 
    private JTextField makeField;  
    private JTextField orField;  
    private JTextField crField;  
    private JTextField plateField;  
    private JTextField licenseField;  
    private JPasswordField passField;
    private JTextField regDateField; // New field for registration date
    private JTextField expDateField;

	public RegisterView(JFrame frame, RegisterController regCon){
		this.frame = frame;
		this.regCon = regCon;
		initRegView();
	}
	
	public void initRegView(){
		JPanel logPanel = new JPanel();
		logPanel.setBounds(0,0,400,50);
		logPanel.setBackground(new Color(40, 145, 242));
		logPanel.setLayout(null);
		frame.add(logPanel);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(0,0,400,700);
		loginPanel.setBackground(new Color(109, 198, 248));
		loginPanel.setLayout(null);
		frame.add(loginPanel);

		JLabel loginLabel = new JLabel("CITE Vehicle Registration Form");
		loginLabel.setBounds(45,-20,300,100);
		loginLabel.setFont(new Font("Arial", Font.BOLD,20));
		logPanel.add(loginLabel);

		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(40,40,100,100);
		loginPanel.add(usernameLabel);

		userNameField = new JTextField("");
		userNameField.setBounds(40,100,300,25);
		loginPanel.add(userNameField);

		JLabel userLabel = new JLabel("Name:");
		userLabel.setBounds(40,90,100,100);
		loginPanel.add(userLabel);

		userField = new JTextField("");
		userField.setBounds(40,150,300,25);
		loginPanel.add(userField);

		JLabel passLabel = new JLabel("Password:");
		passLabel.setBounds(40, 140,100,100);
		loginPanel.add(passLabel);

		passField = new JPasswordField("");
		passField.setBounds(40, 200,300,25);
		loginPanel.add(passField);

		JLabel vehicleLabel = new JLabel("Vehicle Type:");
        vehicleLabel.setBounds(40, 215, 100, 50);
        loginPanel.add(vehicleLabel);

        // Replace JTextField with JComboBox for Vehicle Type
        String[] vehicleTypes = {"select option", "2 wheels", "4 wheels"};
        vehicleTypeComboBox = new JComboBox<>(vehicleTypes);
        vehicleTypeComboBox.setBounds(40, 250, 125, 25);
        loginPanel.add(vehicleTypeComboBox);

        // Add JComboBox for Year Model
        JLabel yearModelLabel = new JLabel("Year Model:");
        yearModelLabel.setBounds(220,215, 100, 50);
        loginPanel.add(yearModelLabel);

        Integer[] yearModelOptions = new Integer[2023 - 1990 + 1];
        for (int i = 0; i < yearModelOptions.length; i++) {
            yearModelOptions[i] = 1990 + i;
        }
        yearModelComboBox = new JComboBox<>(yearModelOptions);
        yearModelComboBox.setBounds(220,250, 120, 25);
        loginPanel.add(yearModelComboBox);

        JLabel colorLabel = new JLabel("Vehicle Color:");
        colorLabel.setBounds(40,265, 100, 50);
        loginPanel.add(colorLabel);

        colorField = new JTextField("");
        colorField.setBounds(40, 300, 125, 25);
        loginPanel.add(colorField);


		JLabel makeLabel = new JLabel("Make:");
		makeLabel.setBounds(220,265,100,50);
		loginPanel.add(makeLabel);

		makeField = new JTextField("");
		makeField.setBounds(220,300,120,25);
		loginPanel.add(makeField);

		JLabel orLabel = new JLabel("OR Number:");
		orLabel.setBounds(40,290,100,100);
		loginPanel.add(orLabel);

		orField = new JTextField("");
		orField.setBounds(40,350,300,25);
		loginPanel.add(orField);

		JLabel crLabel = new JLabel("CR Number:");
		crLabel.setBounds(40,340,100,100);
		loginPanel.add(crLabel);

		crField = new JTextField("");
		crField.setBounds(40,400,300,25);
		loginPanel.add(crField);

		JLabel plateLabel = new JLabel("Plate No:");
		plateLabel.setBounds(40,390,100,100);
		loginPanel.add(plateLabel);

		plateField = new JTextField("");
		plateField.setBounds(40,450,300,25);
		loginPanel.add(plateField);

		JLabel licenseLabel = new JLabel("License No:");
		licenseLabel.setBounds(40,440,100,100);
		loginPanel.add(licenseLabel);

		licenseField = new JTextField("");
		licenseField.setBounds(40,500,300,25);
		loginPanel.add(licenseField);

		JButton submitBttn = new JButton("submit");
		submitBttn.setBounds(60,560,100,40);
		submitBttn.setBackground(new Color(248,217,109));
		submitBttn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				String userName = userNameField.getText();
			    String name = userField.getText();
		        String vehicleType = (String) vehicleTypeComboBox.getSelectedItem();
		        int yearModel = (Integer) yearModelComboBox.getSelectedItem();
		        String color = colorField.getText();
		        String make = makeField.getText();
		        char[] orNumber = orField.getText().toCharArray();
		        char[] crNumber = crField.getText().toCharArray();
		        char[] plateNumber = plateField.getText().toCharArray();
		        char[] licenseNumber = licenseField.getText().toCharArray();
		        char[] password = passField.getPassword();

		        Date regDate = new Date();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        String formattedRegDate = dateFormat.format(regDate);

		        Calendar expCalendar = Calendar.getInstance();
		        expCalendar.setTime(regDate);
		        expCalendar.add(Calendar.YEAR, 1);
		        String formattedExpDate = dateFormat.format(expCalendar.getTime());
		        


		        // Validate input
		        if (userName.isEmpty() || name.isEmpty() || vehicleType.isEmpty() || make.isEmpty() || yearModel == 0 || color.isEmpty() || orNumber.length == 0 || crNumber.length == 0 || plateNumber.length == 0 || licenseNumber.length == 0 || password.length == 0) {
		            JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        String vehicleSticker = generateVehicleSticker(name, licenseField.getText());
		        // Ask for confirmation before registration
		        int result = JOptionPane.showOptionDialog(frame, "Do you want to submit?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		        
		        if (result == JOptionPane.YES_OPTION) {
            // Perform registration
           			 boolean registrationSuccess = regCon.getReg(userName, name, vehicleType, make, yearModel, color, orNumber, crNumber, plateNumber, licenseNumber, vehicleSticker, formattedRegDate, formattedExpDate, password);

            	if (registrationSuccess) {
			    // Generate and show vehicle sticker
			    String briefDetails = "Make: " + make + "\nVehicle Type: " + vehicleType + "\nRegistration Date: " + formattedRegDate
			            + "\nExpiration Date: " + formattedExpDate + "\nVehicle Sticker: " + vehicleSticker;

			    JOptionPane.showMessageDialog(frame, "You are now Registered!\n" + briefDetails, "Success",
			            JOptionPane.INFORMATION_MESSAGE);

					    // Clear input fields after successful registration
					    clearInputFields();
					} else {
					    JOptionPane.showMessageDialog(frame, "Registration failed. User or vehicle already exists.", "Error",JOptionPane.ERROR_MESSAGE);
						return;
					}


            // Show login after registration attempt
            frame.getContentPane().removeAll();
            frame.getContentPane().repaint();
            regCon.showLogin();
			    }
			}    

		});
		loginPanel.add(submitBttn);




		JButton cnclBttn = new JButton("cancel");
		cnclBttn.setBounds(215,560,100,40);
		cnclBttn.setBackground(new Color(248,217,109));
		loginPanel.add(cnclBttn);
		cnclBttn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.getContentPane().removeAll();
                frame.getContentPane().repaint();
                regCon.showLogin();
			}
		});

	}
	
     private void clearInputFields() {
		userNameField.setText("");
        userField.setText("");
        vehicleTypeComboBox.setSelectedIndex(0);
        yearModelComboBox.setSelectedIndex(0);
        colorField.setText("");
        makeField.setText("");
        orField.setText("");
        crField.setText("");
        plateField.setText("");
        licenseField.setText("");
    }
    private String generateVehicleSticker(String name, String licensePlate) {
    // Extract the first letter of the name
    char firstLetter = name.charAt(0);

    // Extract the last two digits of the license plate
    String lastTwoDigits = licensePlate.substring(licensePlate.length() - 2);

    // Build the vehicle sticker
    return "VH-" + Character.toUpperCase(firstLetter) + lastTwoDigits;
	}

}