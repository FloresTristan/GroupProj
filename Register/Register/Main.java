
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main{
	public static void main(String[]args){
		JFrame frame = new JFrame("Register");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,700);
		frame.setLayout(null);



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
		userLabel.setBounds(20,80,100,100);
		loginPanel.add(userLabel);

		JTextField userField = new JTextField("");
		userField.setBounds(20,140,300,25);
		loginPanel.add(userField);

		JLabel vehicleLabel = new JLabel("Vehicle Type:");
		vehicleLabel.setBounds(20,135,100,100);
		loginPanel.add(vehicleLabel);

		JTextField vehicleField = new JTextField("");
		vehicleField.setBounds(20,195,300,25);
		loginPanel.add(vehicleField);

		JLabel makeLabel = new JLabel("Make:");
		makeLabel.setBounds(20,195,100,100);
		loginPanel.add(makeLabel);

		JTextField makeField = new JTextField("");
		makeField.setBounds(20,255,300,25);
		loginPanel.add(makeField);

		JLabel orLabel = new JLabel("OR Number:");
		orLabel.setBounds(20,255,100,100);
		loginPanel.add(orLabel);

		JTextField orField = new JTextField("");
		orField.setBounds(20,315,300,25);
		loginPanel.add(orField);

		JLabel crLabel = new JLabel("CR Number:");
		crLabel.setBounds(20,315,100,100);
		loginPanel.add(crLabel);

		JTextField crField = new JTextField("");
		crField.setBounds(20,375,300,25);
		loginPanel.add(crField);

		JLabel plateLabel = new JLabel("Plate No:");
		plateLabel.setBounds(20,375,100,100);
		loginPanel.add(plateLabel);

		JTextField plateField = new JTextField("");
		plateField.setBounds(20,435,300,25);
		loginPanel.add(plateField);

		JLabel licenseLabel = new JLabel("License No:");
		licenseLabel.setBounds(20,435,100,100);
		loginPanel.add(licenseLabel);

		JTextField licenseField = new JTextField("");
		licenseField.setBounds(20,495,300,25);
		loginPanel.add(licenseField);

		JButton submitBttn = new JButton("submit");
		submitBttn.setBounds(60,560,75,40);
		loginPanel.add(submitBttn);
		
			

		JButton cnclBttn = new JButton("cancel");
		cnclBttn.setBounds(215,560,75,40);
		loginPanel.add(cnclBttn);



			
		

























 
		frame.setVisible(true);

	}
}