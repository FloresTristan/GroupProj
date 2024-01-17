package github.group.details;

import javax.swing.*;

import github.group.details.AddAnotherVehicle;
import github.group.login.VehicleLoginController;
import github.group.register.RegisterController;
import github.group.user.UserController;

public class DetailsController {

    private DetailsView detailsview; 
    private AddAnotherVehicle regVehicle;
    private VehicleLoginController loginCon;
    private JFrame frame;

    

    public void detailsController(JFrame frame, String username, String make, String type, String color, String regDate, String expDate, String sticker) {
        detailsview = new DetailsView();
        System.out.println("Details from detCon: " + make + ", " + type + ", " + color + ", " + regDate + ", " + expDate+", "+sticker);
        this.frame = frame;
        detailsview.setDetailsController(this);
        detailsview.detailsView(frame,username,make,type,color,regDate,expDate,sticker);
    }

    public void showLoginView() {
        loginCon = new VehicleLoginController();
        loginCon.loginController(frame);
    }
    /*public void regAnotherVehicle(){
        new AddAnotherVehicle();
        regVehicle.addVehicle(frame);
    }*/

    
}
