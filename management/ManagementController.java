package github.group.management;

import javax.swing.*;
import github.group.login.VehicleLoginController;

public class ManagementController{
	private JFrame frame;
	private VehicleLoginController login;

	public ManagementController(JFrame frame){
		this.frame = frame;
		new ManagementGUI(frame, this);

	}
	public void showLogin(){
		new VehicleLoginController(frame);
	}
}