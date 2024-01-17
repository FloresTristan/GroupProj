package github.group.login;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import github.group.register.RegisterController;
import github.group.user.UserController;
import github.group.admin.AdminController;
import github.group.management.ManagementController;
import github.group.details.DetailsController;

abstract class AbstractVehicleLoginController {
    protected JFrame frame;
    protected UserController user;
    protected VehicleLoginView logView = new VehicleLoginView();
    protected DetailsController details = new DetailsController();
    protected static final String databaseDirectory = "database/";
    protected String filePath = databaseDirectory + "user.dat";
    protected String remarkPath = databaseDirectory + "remark.dat";

    public abstract void loginController(JFrame frame);

    public abstract void showRegView(JFrame frame);

    public abstract boolean getLogin(String username, char[] password);

    public abstract void showAdminInit(JFrame frame);

    public abstract void showManagementInit(JFrame frame);

    public abstract void showDetail(String username, String make, String type, String color, String regDate, String expDate, String sticker);

    public void initFrame() {
        Image icon = Toolkit.getDefaultToolkit().getImage(databaseDirectory + "citeIcon2.png");
        frame.setIconImage(icon);
        frame.setSize(400, 700);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
