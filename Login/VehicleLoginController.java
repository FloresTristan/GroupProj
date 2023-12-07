package github.group.login;


import javax.swing.*;
import github.group.register.RegisterController;


public class VehicleLoginController{
	private JFrame frame;
	private VehicleLoginView view;
	//private VehicleRegistrationModel model;


	public VehicleLoginController(JFrame frame){
		this.frame = frame;
		this.view = new VehicleLoginView(frame, this);
		initFrame();
	}
	public void showRegView(JFrame frame){
		new RegisterController(frame);

	}






	public void initFrame(){
		frame.setSize(400,700);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setUndecorated(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}