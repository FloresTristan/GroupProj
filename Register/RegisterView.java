package github.group.register;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
class RegisterView{
	private JFrame frame;
	private RegisterController regCon;
	private JTextField userField;  
    private JComboBox<String> vehicleTypeComboBox; 
    private JComboBox<Integer> yearModelComboBox;  
    private JTextField colorField; 
    private JTextField makeField;  
    private JTextField orField;  
    private JTextField crField;  
    private JTextField plateField;  
    private JTextField licenseField;  
    private JPasswordField passField;

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

		JLabel userLabel = new JLabel("Name:");
		userLabel.setBounds(40,40,100,100);
		loginPanel.add(userLabel);

		userField = new JTextField("");
		userField.setBounds(40,100,300,25);
		loginPanel.add(userField);

		JLabel passLabel = new JLabel("Password:");
		passLabel.setBounds(40,90,100,100);
		loginPanel.add(passLabel);

		passField = new JPasswordField("");
		passField.setBounds(40,150,300,25);
		loginPanel.add(passField);

		JLabel vehicleLabel = new JLabel("Vehicle Type:");
        vehicleLabel.setBounds(40, 165, 100, 50);
        loginPanel.add(vehicleLabel);

        // Replace JTextField with JComboBox for Vehicle Type
        String[] vehicleTypes = {"select option", "2 wheels", "4 wheels"};
        vehicleTypeComboBox = new JComboBox<>(vehicleTypes);
        vehicleTypeComboBox.setBounds(40, 200, 125, 25);
        loginPanel.add(vehicleTypeComboBox);

        // Add JComboBox for Year Model
        JLabel yearModelLabel = new JLabel("Year Model:");
        yearModelLabel.setBounds(220, 165, 100, 50);
        loginPanel.add(yearModelLabel);

        Integer[] yearModelOptions = new Integer[2023 - 1990 + 1];
        for (int i = 0; i < yearModelOptions.length; i++) {
            yearModelOptions[i] = 1990 + i;
        }
        yearModelComboBox = new JComboBox<>(yearModelOptions);
        yearModelComboBox.setBounds(220, 200, 120, 25);
        loginPanel.add(yearModelComboBox);

        JLabel colorLabel = new JLabel("Vehicle Color:");
        colorLabel.setBounds(40, 215, 100, 50);
        loginPanel.add(colorLabel);

        colorField = new JTextField("");
        colorField.setBounds(40, 250, 125, 25);
        loginPanel.add(colorField);


		JLabel makeLabel = new JLabel("Make:");
		makeLabel.setBounds(220,215,100,50);
		loginPanel.add(makeLabel);

		makeField = new JTextField("");
		makeField.setBounds(220,250,120,25);
		loginPanel.add(makeField);

		JLabel orLabel = new JLabel("OR Number:");
		orLabel.setBounds(40,255,100,100);
		loginPanel.add(orLabel);

		orField = new JTextField("");
		orField.setBounds(40,315,300,25);
		loginPanel.add(orField);

		JLabel crLabel = new JLabel("CR Number:");
		crLabel.setBounds(40,315,100,100);
		loginPanel.add(crLabel);

		crField = new JTextField("");
		crField.setBounds(40,375,300,25);
		loginPanel.add(crField);

		JLabel plateLabel = new JLabel("Plate No:");
		plateLabel.setBounds(40,375,100,100);
		loginPanel.add(plateLabel);

		plateField = new JTextField("");
		plateField.setBounds(40,435,300,25);
		loginPanel.add(plateField);

		JLabel licenseLabel = new JLabel("License No:");
		licenseLabel.setBounds(40,435,100,100);
		loginPanel.add(licenseLabel);

		licenseField = new JTextField("");
		licenseField.setBounds(40,495,300,25);
		loginPanel.add(licenseField);

		JButton submitBttn = new JButton("submit");
		submitBttn.setBounds(60,560,100,40);
		submitBttn.setBackground(new Color(248,217,109));
		submitBttn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {

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
		        


		        // Validate input
		        if (name.isEmpty() || vehicleType.isEmpty() || make.isEmpty() || yearModel == 0 || color.isEmpty() || orNumber.length == 0 || crNumber.length == 0 || plateNumber.length == 0 || licenseNumber.length == 0 || password.length == 0) {
		            JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        String vehicleSticker = generateVehicleSticker(name, licenseField.getText());
		        // Ask for confirmation before registration
		        int result = JOptionPane.showOptionDialog(frame, "Do you want to submit?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		        
		        if (result == JOptionPane.YES_OPTION) {
            // Perform registration
            boolean registrationSuccess = regCon.getReg(name, vehicleType, make, yearModel, color, orNumber, crNumber, plateNumber, licenseNumber, vehicleSticker, password);

            if (registrationSuccess) {
                // Generate and show vehicle sticker
                
                JOptionPane.showMessageDialog(frame, "You are now Registered!\nVehicle Sticker: " + vehicleSticker, "Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear input fields after successful registration
                clearInputFields();
            } else {
                JOptionPane.showMessageDialog(frame, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
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
	/*private void handleUserInput() {
        // Retrieve user input from text fields and combo boxes
        String name = userField.getText();
        String vehicleType = (String) vehicleTypeComboBox.getSelectedItem();
        int yearModel = (Integer) yearModelComboBox.getSelectedItem();
        String color = colorField.getText();
        String make = makeField.getText();
        char[] orNumber = orField.getText().toCharArray();
		char[] crNumber = crField.getText().toCharArray();
		char[] plateNumber = plateField.getText().toCharArray();
		char[] licenseNumber = licenseField.getText().toCharArray();
		char[] password = "s".toCharArray();


        // Print or process the retrieved values (you can modify this part accordingly)
        if (name.isEmpty() || vehicleType.isEmpty() || make.isEmpty() || orNumber.length == 0 || crNumber.length == 0 || plateNumber.length == 0 || licenseNumber.length == 0 || password.length == 0) {
    // Rest of your code...


            JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean registrationSuccess = regCon.getReg(name,vehicleType, make, orNumber, crNumber,plateNumber, licenseNumber, password); 

        if (registrationSuccess) {
            JOptionPane.showMessageDialog(frame, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }


        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        regCon.showLogin();
    }*/
     private void clearInputFields() {
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