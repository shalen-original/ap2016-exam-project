package ap2016.gui.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import ap2016.entities.User;
import ap2016.gui.utilities.ValidablePasswordField;
import ap2016.gui.utilities.ValidableTextField;

@SuppressWarnings("serial")
public class RegisterJFrame extends JFrame
{

	public RegisterJFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("NewsFeed - Register new user");
		getContentPane().setLayout(null);
		setPreferredSize(new Dimension(500, 500));
		
		JLabel lblNewUserRegistration = new JLabel("New user registration");
		lblNewUserRegistration.setBounds(100, 11, 243, 22);
		lblNewUserRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewUserRegistration.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblNewUserRegistration);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "New user details:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(30, 54, 512, 340);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(32, 33, 78, 14);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(65, 84, 78, 14);
		panel.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setBounds(55, 128, 116, 14);
		panel.add(lblConfirmPassword);
		
		JLabel lblAvatarImage = new JLabel("Avatar image:");
		lblAvatarImage.setBounds(65, 172, 78, 14);
		panel.add(lblAvatarImage);
		
		JLabel lblAvatarPath = new JLabel("AVATAR PATH");
		lblAvatarPath.setBounds(100, 197, 106, 14);
		panel.add(lblAvatarPath);
		
		JButton btnRegisterAndLogin = new JButton("Register and login");
		btnRegisterAndLogin.setBounds(245, 266, 129, 28);
		panel.add(btnRegisterAndLogin);
		
		JButton btnAbort = new JButton("Abort");
		btnAbort.setBounds(55, 266, 129, 28);
		panel.add(btnAbort);
		
		JButton btnSelectImage = new JButton("Choose avatar");
		btnSelectImage.setBounds(174, 234, 106, 23);
		panel.add(btnSelectImage);
		
		JLabel lblImage = new JLabel("Image");
		lblImage.setBounds(344, 55, 116, 102);
		panel.add(lblImage);
		
		ValidableTextField validableTextField = new ValidableTextField(s -> User.isValidUsername(s));
		validableTextField.setBounds(82, 58, 102, 18);
		panel.add(validableTextField);
		
		ValidablePasswordField validablePasswordField = new ValidablePasswordField();
		ValidablePasswordField validablePasswordField_1 = new ValidablePasswordField();
		
		validablePasswordField.updateValidationTest(c -> User.isValidPassword(c) && Arrays.equals(validablePasswordField.getPassword(), validablePasswordField_1.getPassword()));
		validablePasswordField_1.updateValidationTest(c -> User.isValidPassword(c) && Arrays.equals(validablePasswordField.getPassword(), validablePasswordField_1.getPassword()));
		
		validablePasswordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validablePasswordField_1.updateValidationState();
			}
		});
		
		validablePasswordField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				validablePasswordField.updateValidationState();
			}
		});
		
		validablePasswordField.setBounds(245, 139, 129, 18);
		panel.add(validablePasswordField);
		
		
		validablePasswordField_1.setBounds(245, 170, 129, 18);
		panel.add(validablePasswordField_1);
		
		
		
		
	}
	
	public RegisterJFrame(JFrame originalLoginFrame)
	{
		this();
	}
}
