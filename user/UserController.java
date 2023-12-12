package github.group.user;

public class UserController{
	private UserModel model; 

    public UserController(){
        this.model = new UserModel();
    }

	public boolean isRegister(String name, String vehicleType, String make, int yearModel, String color, char[] oR, char[] cR, char[] plateNo, char[] licenseNo, String vehicleSticker, char[] password){
		if (name.isEmpty() || vehicleType.isEmpty() || make.isEmpty() || yearModel == 0 || color.isEmpty()  || oR.length == 0 || cR.length == 0 || plateNo.length == 0 || licenseNo.length == 0 || password.length == 0) {
            return false; // Registration failed due to blank fields
        }

        // Assuming UserModel.register method returns a boolean indicating success
        boolean registrationSuccess = model.register(name, vehicleType, make, yearModel, color, oR, cR, plateNo, licenseNo, vehicleSticker, password);

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


}
