package github.group.register;

import javax.swing.*;
import github.group.login.VehicleLoginController;
import github.group.user.UserController;

public class RegisterController{
	private JFrame frame;
	private UserController user;

	public RegisterController(JFrame frame){
		this.frame = frame;
		new RegisterView(frame, this);

	}
	public boolean getReg(String name, String vehicleType, String make, int yearModel, String color, char[] oR, char[]cR, char[] plateNo, char[] licenseNo, String vehicleSticker, char[] password){
		user = new UserController();
		return user.isRegister(name, vehicleType, make, yearModel, color, oR, cR, plateNo, licenseNo, vehicleSticker, password);

	}
	public void showLogin(){
		new VehicleLoginController(frame);
	}

}