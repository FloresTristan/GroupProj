 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;

 public class DetailsView{
 	public static void main(String []args){
 		private JFrame = frame;
 		private JPanel = detailspanel;




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
 	area.setBackground(new Color(217,217,217));
 	area.setEditable(false);
 	detailspanel.add(area);

 	JLabel detaillabel = new JLabel("DETAILS");
 	detaillabel.setBounds(120,4,100,40);
 	detaillabel.setFont(new Font("Arial", Font.BOLD,20));
 	area.add(detaillabel);

 	JLabel makelabel = new JLabel("Make");
 	makelabel.setBounds(40,100,100,40);
 	makelabel.setFont(new Font("Arial", Font.BOLD,20));
 	area.add(makelabel); 
	
	JLabel typelabel = new JLabel("Type");
 	typelabel.setBounds(210,100,100,40);
 	typelabel.setFont(new Font("Arial", Font.BOLD,20));
 	area.add(typelabel);

 	JLabel stickerlabel = new JLabel("Sticker No.");
 	stickerlabel.setBounds(100,250,150,40);
 	stickerlabel.setFont(new Font("Arial", Font.BOLD,20));
 	area.add(stickerlabel);

 	JLabel passwordlabel = new JLabel("Password:");
 	passwordlabel.setBounds(5,450,150,40);
 	stickerlabel.setFont(new Font("Arial", Font.BOLD,20));
 	area.add(passwordlabel);


 	JButton backbutton = new JButton("Back");
 	backbutton.setBounds(110,600,70,40);
 	detailspanel.add(backbutton);

 	JButton quitbutton = new JButton("Quit");
 	quitbutton.setBounds(210,600,70,40);
 	detailspanel.add(quitbutton);





 	frame.setVisible(true);

 	}

 }