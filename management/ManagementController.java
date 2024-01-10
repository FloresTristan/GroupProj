package github.group.management;

import javax.swing.*;
import github.group.login.VehicleLoginController;
import github.group.user.UserController;

public class ManagementController{
	private JFrame frame;
	private VehicleLoginController login;
	private UserController userCon;

	public ManagementController(JFrame frame){
		this.frame = frame;
		new ManagementGUI(frame, this);

	}
	public void showLogin(){
		new VehicleLoginController(frame);
	}
	public boolean addRole(String userName, String name, String vehicleType, String make, int yearModel, String color, char[] oR, char[] cR, char[] plateNo, char[] licenseNo, String vehicleSticker, String formattedRegDate, String formattedExpDate, String pass, int roleID){
		userCon = new UserController();
		return userCon.addingRole(userName, name,vehicleType, make, yearModel, color, oR, cR, plateNo, licenseNo, vehicleSticker, formattedRegDate, formattedExpDate, pass, roleID);
	}
}