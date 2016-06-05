package ap2016.gui.utilities;


import javax.swing.JFrame;
// import javax.swing.JOptionPane;

// import ap2016.application.ApplicationConstants;
import ap2016.gui.frames.LoginJFrame;


/**
 * This class represents the entry point of the entire application.
 * @author Matteo Nardini
 *
 */
public class Main
{

	public static void main(String[] args)
	{

		/*
		 * if (true)
		 * {
		 * String ans = "";
		 * ans += "ApplicationConstants.dataBase =>" + ApplicationConstants.dataBase + "\n";
		 * ans += "ApplicationConstants.assetsBase =>" + ApplicationConstants.assetsBase + "\n";
		 *
		 * JOptionPane.showMessageDialog(null, ans);
		 * }
		 */

		JFrame app = new LoginJFrame();
		app.pack();
		app.setLocationRelativeTo(null);
		app.setVisible(true);
	}

}
