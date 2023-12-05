package finals.exam.cite;


import javax.swing.*;



public class VehicleRegistrationController{
	private JFrame frame;
	private VehicleRegistrationView view;
	private VehicleRegistrationModel model;


	public VehicleRegistrationController(JFrame frame){
		this.frame = frame;
		this.view = new VehicleRegistrationView(frame, this);
		initFrame();
	}






	public void initFrame(){
		frame.setSize(400,700);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}