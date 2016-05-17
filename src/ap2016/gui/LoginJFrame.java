package ap2016.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class LoginJFrame extends JFrame
{
	private JComboBox cmbUsername;
	private JButton btnLogin;
	private JButton btnRegister;
	
	public LoginJFrame() {
		setTitle("NewsFeed - Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewsfeed = new JLabel("Welcome to NewsFeed");
		lblNewsfeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewsfeed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewsfeed.setBounds(52, 9, 215, 43);
		getContentPane().add(lblNewsfeed);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Please enter login details:", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 54, 295, 202);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(25, 30, 52, 14);
		panel.add(lblUsername);
		
		cmbUsername = new JComboBox();
		cmbUsername.setBounds(40, 49, 215, 20);
		panel.add(cmbUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(25, 86, 52, 14);
		panel.add(lblPassword);
		
		JPasswordField pfPassword = new JPasswordField();
		pfPassword.setBounds(40, 108, 215, 20);
		panel.add(pfPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(33, 155, 98, 28);
		panel.add(btnLogin);
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(164, 155, 98, 28);
		panel.add(btnRegister);
	}
}
