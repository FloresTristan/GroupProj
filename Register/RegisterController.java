package github.group.register;

import javax.swing.*;

public class RegisterController{
	private JFrame frame;

	public RegisterController(JFrame frame){
		this.frame = frame;
		new RegisterView(frame, this);

	}
}