package github.group.register;

import javax.swing.*;
import github.group.login.VehicleLoginController;
import github.group.user.UserController;

public class RegisterController implements RegisterControllerInterface{
	private JFrame frame;
	private UserController user;
	private VehicleLoginController logCon;

	public RegisterController(JFrame frame){
		this.frame = frame;
		new RegisterView(frame, this);

	}
	public boolean getReg(String userName, String name, String vehicleType, String make, int yearModel, String color, char[] oR, char[]cR, char[] plateNo, char[] licenseNo, String vehicleSticker, String formattedRegDate, String formattedExpDate, char[] password){
		user = new UserController();
		return user.isRegister(userName, name, vehicleType, make, yearModel, color, oR, cR, plateNo, licenseNo, vehicleSticker, formattedRegDate, formattedExpDate, password);

	}
	public void showLogin(){
		logCon = new VehicleLoginController();
		logCon.loginController(frame);
	}

}