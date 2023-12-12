package github.group.admin;

import javax.swing.*;

public class AdminController{
	private JFrame frame;

	public AdminController(JFrame frame){
		this.frame = frame;
		new AdminGUI(frame, this);

	}
}