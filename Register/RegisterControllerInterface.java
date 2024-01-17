package github.group.register;

import javax.swing.JFrame;

interface RegisterControllerInterface {
    boolean getReg(String userName, String name, String vehicleType, String make, int yearModel, String color,char[] oR, char[] cR, char[] plateNo, char[] licenseNo, String vehicleSticker,String formattedRegDate, String formattedExpDate, char[] password);
    void showLogin();
}
