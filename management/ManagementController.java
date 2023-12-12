package github.group.management;

import javax.swing.*;

public class ManagementController{
	private JFrame frame;

	public ManagementController(JFrame frame){
		this.frame = frame;
		new ManagementGUI(frame, this);

	}
}