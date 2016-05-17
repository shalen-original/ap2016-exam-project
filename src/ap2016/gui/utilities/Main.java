package ap2016.gui.utilities;

import javax.swing.JFrame;

import ap2016.gui.frames.LoginJFrame;

public class Main
{

	public static void main(String[] args)
	{
		
		JFrame app = new LoginJFrame();
		app.pack();
		app.setLocationRelativeTo(null);
		app.setVisible(true);

	}

}
