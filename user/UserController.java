package github.group.user;

import github.group.login.VehicleLoginController;
import github.group.details.DetailsController;
import github.group.admin.AdminController;
import javax.swing.JFrame;

public class UserController {
    private UserModel model;
    private DetailsController detailsController;
    private VehicleLoginController login;
    

    public UserController() {
        this.model = new UserModel(this);
        
        
        
    }

	public boolean isRegister(String userName, String name, String vehicleType, String make, int yearModel, String color, char[] oR, char[] cR, char[] plateNo, char[] licenseNo, String vehicleSticker, String formattedRegDate, String formattedExpDate, char[] password){
		if (userName.isEmpty() || name.isEmpty() || vehicleType.isEmpty() || make.isEmpty() || yearModel == 0 || color.isEmpty()  || oR.length == 0 || cR.length == 0 || plateNo.length == 0 || licenseNo.length == 0 || password.length == 0) {
            return false; // Registration failed due to blank fields
        }

        // Assuming UserModel.register method returns a boolean indicating success
        boolean registrationSuccess = model.register(userName, name, vehicleType, make, yearModel, color, oR, cR, plateNo, licenseNo, vehicleSticker, formattedRegDate, formattedExpDate, password);

        return registrationSuccess;
    }
    public boolean isLogin(String name, char[] password){
    	if(name.isEmpty() || password.length == 0){
    		return false;
    	}
    	return model.login(name, password);
    }
        public int authenticateAndGetRoleId(String username, char[] password) {
        return model.authenticateAndGetRoleId(username, password);
    }
    
    public boolean addingRole(String userName, String name, String vehicleType, String make, int yearModel, String color, char[] oR, char[] cR, char[] plateNo, char[] licenseNo, String vehicleSticker, String formattedRegDate, String formattedExpDate, String password, int roleID){
        if(userName.isEmpty()||name.isEmpty()||password.isEmpty()||roleID == 0){
            return false;
        }
        boolean createSuccess = model.createRoles(userName,name, vehicleType, make, yearModel, color, oR, cR, plateNo, licenseNo, vehicleSticker, formattedRegDate, formattedExpDate, password,roleID);

        return createSuccess;
    }

    
}



