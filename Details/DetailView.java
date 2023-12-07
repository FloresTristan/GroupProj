 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;

 public class DetailView{
 	public static void main(String []args){




 	JFrame frame = new JFrame("Details");
 	frame.setSize(400,700);
 	frame.setLayout(null);
 	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


 	JPanel detailspanel = new JPanel();
 	detailspanel.setBounds(0,0,400,700);
 	detailspanel.setLayout(null);
 	detailspanel.setBackground(new Color(109, 198, 248));
 	frame.add(detailspanel);

 	JTextArea area = new JTextArea();
 	area.setBounds(45,45,300,500);
 	area.setBackground(new Color(40,145,242));
 	area.setEditable(false);
 	detailspanel.add(area);

 	JTextArea area1 = new JTextArea();
 	area1.setBounds(1,42,400,2);
 	area1.setBackground(new Color(0,0,0));
 	area1.setEditable(false);
 	area.add(area1);

 	JLabel detaillabel = new JLabel("Vehicle Details");
 	detaillabel.setBounds(80,4,300,40);
 	detaillabel.setFont(new Font("Abyssinica SIL", Font.BOLD,20));
 	area.add(detaillabel);

 	JLabel makelabel = new JLabel("Make");
 	makelabel.setBounds(20,40,100,40);
 	makelabel.setFont(new Font("Abyssinica SIL", Font.BOLD,14));
 	area.add(makelabel); 
	
	JLabel typelabel = new JLabel("Type");
 	typelabel.setBounds(20,100,100,40);
 	typelabel.setFont(new Font("Abyssinica SIL", Font.BOLD,14));
 	area.add(typelabel);

 	JLabel stickerlabel = new JLabel("Sticker No.");
 	stickerlabel.setBounds(20,280,150,40);
 	stickerlabel.setFont(new Font("Abyssinica SIL", Font.BOLD,14));
 	area.add(stickerlabel);

 	JLabel registrationdatelabel = new JLabel("Registration Date");
 	registrationdatelabel.setBounds(20,160,150,40);
 	registrationdatelabel.setFont(new Font("Abyssinica SIL", Font.BOLD,14));
 	area.add(registrationdatelabel);

 	JLabel expirydatelabel = new JLabel("Expiration Date");
 	expirydatelabel.setBounds(20,220,150,40);
 	expirydatelabel.setFont(new Font("Abyssinica SIL", Font.BOLD,14));
 	expirydatelabel.setForeground(Color.RED);
 	area.add(expirydatelabel);

 	JLabel passwordlabel = new JLabel("Password:");
 	passwordlabel.setBounds(20,450,150,40);
 	passwordlabel.setFont(new Font("Abyssinica SIL", Font.BOLD,14));
 	area.add(passwordlabel);


 	JButton backbutton = new JButton("Back");
 	backbutton.setBounds(110,600,70,40);
 	backbutton.setBackground(new Color(248,217,109));
	backbutton.setFont(new Font("Abyssinica SIL", Font.BOLD,14));
	detailspanel.add(backbutton);

 	JButton quitbutton = new JButton("Quit");
 	quitbutton.setBounds(210,600,70,40);
 	quitbutton.setBackground(new Color(248,217,109));
	quitbutton.setFont(new Font("Abyssinica SIL", Font.BOLD,14));
	detailspanel.add(quitbutton);





 	frame.setVisible(true);

 	}

 }