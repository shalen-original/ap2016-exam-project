package ap2016.gui.frames;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import ap2016.entities.User;
import ap2016.io.UserDataProvider;


public class LoginJFrame extends JFrame
{
	/**
	 * Serial version UUID.
	 */
	private static final long serialVersionUID = -3436452526077145912L;
	private JComboBox<User> cmbUsername;
	private JButton btnLogin;
	private JButton btnRegister;
	private JPasswordField pfPassword;

	/**
	 * Creates a new login frame.
	 */
	public LoginJFrame()
	{
		setTitle("NewsFeed - Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setPreferredSize(new Dimension(326, 295));

		JLabel lblNewsfeed = new JLabel("Welcome to NewsFeed");
		lblNewsfeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewsfeed.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewsfeed.setBounds(52, 7, 215, 43);
		getContentPane().add(lblNewsfeed);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true),
				"Please enter login details:", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 49, 295, 202);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(25, 30, 89, 14);
		panel.add(lblUsername);

		this.cmbUsername = new JComboBox<>();
		this.cmbUsername.setBounds(40, 49, 215, 20);
		panel.add(this.cmbUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(25, 86, 89, 14);
		panel.add(lblPassword);

		this.pfPassword = new JPasswordField();
		this.pfPassword.setBounds(40, 108, 215, 20);
		panel.add(this.pfPassword);

		this.btnLogin = new JButton("Login");
		this.btnLogin.setBounds(164, 155, 98, 28);
		this.btnLogin.addActionListener(e -> btnLogin_click());
		panel.add(this.btnLogin);

		this.btnRegister = new JButton("Register");
		this.btnRegister.setBounds(33, 155, 98, 28);
		this.btnRegister.addActionListener(e -> btnRegister_click());
		panel.add(this.btnRegister);

		afterGUIInitialization();
	}

	/**
	 * Fills the username combo box with all the usernames of the users that already registered.
	 */
	private void afterGUIInitialization()
	{
		UserDataProvider udp = UserDataProvider.getInstance();

		try
		{
			udp.readDataFromFile();
		} catch (Exception ex)
		{
			// The file is either not well structured or doesn't exist.
			// If it doesn't exists it will be created on the first save on file
			// If it is not well structured, the user cannot fix it manually
			// In both cases, the file will be rewritten/created from scratch on the next save
			// and everything will be fine again.
		}

		DefaultComboBoxModel<User> m = new DefaultComboBoxModel<>();
		udp.getData().forEach(i -> m.addElement(i));

		this.cmbUsername.setModel(m);

		if (this.cmbUsername.getItemCount() > 0)
		{
			this.cmbUsername.setSelectedIndex(0);
		}
	}

	/**
	 * Validates the selected username with the given password. If the pair matches the content of the user.xml file, the main window of the application is displayed.
	 */
	private void btnLogin_click()
	{
		User u = (User) this.cmbUsername.getSelectedItem();

		if (u == null)
		{
			JOptionPane.showMessageDialog(this, "Select an username or register\na new user before trying to login");
		} else
		{
			char[] pwdChar = this.pfPassword.getPassword();

			if (u.isRightPassword(pwdChar))
			{
				for (int i = 0; i < pwdChar.length; i++)
				{
					pwdChar[i] = 0;
					pwdChar[i] = (char) 0;
				}

				setVisible(false);
				JFrame a = new MainJFrame(u);
				a.pack();
				a.setLocationRelativeTo(null);
				a.setVisible(true);
				dispose();
			} else
			{
				JOptionPane.showMessageDialog(this, "The password is not correct");
				this.pfPassword.setText("");
			}
		}

	}

	/**
	 * Shows the windows that allows to register a new user.
	 */
	private void btnRegister_click()
	{
		JFrame a = new RegisterJFrame(this);
		a.pack();
		a.setLocationRelativeTo(null);

		setVisible(false);
		a.setVisible(true);
	}
}
