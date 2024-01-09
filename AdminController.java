package github.group.admin;

import github.group.user.UserController;
import github.group.register.RegisterController;
import github.group.login.VehicleLoginController;
import javax.swing.*;

public class AdminController{
	private JFrame frame;
	private UserController userCon;
	private RegisterController regCon;

	public AdminController(JFrame frame){
		this.frame = frame;
		new AdminGUI(frame, this);

	}
	public boolean addRole(String userName, String name, String vehicleType, String make, int yearModel, String color, char[] oR, char[] cR, char[] plateNo, char[] licenseNo, String vehicleSticker, String formattedRegDate, String formattedExpDate, String pass, int roleID){
		userCon = new UserController();
		return userCon.addingRole(userName, name,vehicleType, make, yearModel, color, oR, cR, plateNo, licenseNo, vehicleSticker, formattedRegDate, formattedExpDate, pass, roleID);
	}
	public void showLogin(){
		new VehicleLoginController(frame);
	}
} 