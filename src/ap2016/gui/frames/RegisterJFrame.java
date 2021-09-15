package ap2016.gui.frames;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import ap2016.application.ApplicationConstants;
import ap2016.entities.Role;
import ap2016.entities.User;
import ap2016.gui.utilities.AvatarImageDisplay;
import ap2016.gui.utilities.ValidablePasswordField;
import ap2016.gui.utilities.ValidableTextField;
import ap2016.io.UserDataProvider;


public class RegisterJFrame extends JFrame
{
	/**
	 * Serial version UUID.
	 */
	private static final long serialVersionUID = 6481328649798566773L;
	private ValidableTextField vtfUsername;
	private ValidablePasswordField vpfPassword;
	private ValidablePasswordField vpfPasswordConfirm;
	private JButton btnSelectImage;
	private JButton btnRegisterAndLogin;
	private JButton btnAbort;

	private JFrame originalLoginFrame;
	private AvatarImageDisplay aidImage;
	private JLabel lblAvatarPath;

	/**
	 * Constructor used by the GUI builder
	 */
	public RegisterJFrame()
	{
		setSize(new Dimension(553, 387));
		setPreferredSize(new Dimension(553, 387));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("NewsFeed - Register new user");
		getContentPane().setLayout(null);

		JLabel lblNewUserRegistration = new JLabel("New user registration");
		lblNewUserRegistration.setBounds(154, 18, 243, 22);
		lblNewUserRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewUserRegistration.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblNewUserRegistration);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "New user details:",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(23, 54, 498, 282);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(32, 33, 78, 14);
		panel.add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(32, 80, 78, 14);
		panel.add(lblPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setBounds(32, 126, 116, 14);
		panel.add(lblConfirmPassword);

		JLabel lblAvatarImage = new JLabel("Avatar image:");
		lblAvatarImage.setBounds(32, 174, 104, 14);
		panel.add(lblAvatarImage);

		this.lblAvatarPath = new JLabel("default");
		this.lblAvatarPath.setBounds(42, 193, 242, 14);
		panel.add(this.lblAvatarPath);

		this.btnRegisterAndLogin = new JButton("Register and login");
		this.btnRegisterAndLogin.addActionListener(e -> btnRegisterAndLogin_Click());
		this.btnRegisterAndLogin.setBounds(272, 237, 180, 28);
		panel.add(this.btnRegisterAndLogin);

		this.btnAbort = new JButton("Abort");
		this.btnAbort.addActionListener(e -> btnAbort_Click());
		this.btnAbort.setBounds(46, 237, 180, 28);
		panel.add(this.btnAbort);

		this.btnSelectImage = new JButton("Choose avatar");
		this.btnSelectImage.addActionListener(e -> btnSelectImage_Click());
		this.btnSelectImage.setBounds(333, 189, 140, 23);
		panel.add(this.btnSelectImage);

		this.aidImage = new AvatarImageDisplay();
		this.aidImage.setHorizontalAlignment(SwingConstants.CENTER);
		this.aidImage.setBackground(Color.WHITE);
		this.aidImage.setBounds(347, 50, 113, 113);
		try
		{
			this.aidImage.setIcon(
					new ImageIcon(ImageIO.read(Paths.get(ApplicationConstants.assetsBase, "default").toFile())));
		} catch (IOException ex)
		{
			this.aidImage.setText("Default user image");
		}
		panel.add(this.aidImage);

		this.vtfUsername = new ValidableTextField(s -> User.isValidUsername(s));
		this.vtfUsername.setBounds(42, 50, 242, 18);
		panel.add(this.vtfUsername);

		this.vpfPassword = new ValidablePasswordField();
		this.vpfPasswordConfirm = new ValidablePasswordField();

		this.vpfPassword.updateValidationTest(c -> User.isValidPassword(c)
				&& Arrays.equals(this.vpfPassword.getPassword(), this.vpfPasswordConfirm.getPassword()));
		this.vpfPasswordConfirm.updateValidationTest(c -> User.isValidPassword(c)
				&& Arrays.equals(this.vpfPassword.getPassword(), this.vpfPasswordConfirm.getPassword()));

		this.vpfPassword.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				RegisterJFrame.this.vpfPasswordConfirm.updateValidationState();
			}
		});

		this.vpfPasswordConfirm.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				RegisterJFrame.this.vpfPassword.updateValidationState();
			}
		});

		this.vpfPassword.setBounds(42, 97, 242, 18);
		panel.add(this.vpfPassword);

		this.vpfPasswordConfirm.setBounds(42, 144, 242, 18);
		panel.add(this.vpfPasswordConfirm);

	}

	/**
	 * Creates a frame that allows a new user to register.
	 * @param originalLoginFrame The login frame to show if the registration process is aborted.
	 */
	public RegisterJFrame(JFrame originalLoginFrame)
	{
		this();
		this.originalLoginFrame = originalLoginFrame;
	}

	/**
	 * Aborts the current registration operation and shows the original login frame, if present.
	 */
	private void btnAbort_Click()
	{
		if (this.originalLoginFrame != null)
		{
			this.originalLoginFrame.setVisible(true);
			setVisible(false);
			dispose();
		}
	}

	/**
	 * Uses the immitted information to create a new user and to display the main application window to that user.
	 */
	private void btnRegisterAndLogin_Click()
	{

		if (!this.vtfUsername.isValid())
		{
			JOptionPane.showMessageDialog(this, "Error: the username is invalid");
			return;
		}

		if (!this.vpfPassword.isValid())
		{
			JOptionPane.showMessageDialog(this, "Error: the password is invalid");
			return;
		}

		User u = new User(this.vtfUsername.getText(), this.vpfPassword.getPassword());
		if (!this.lblAvatarPath.getText().equals("default"))
		{
			try
			{
				u.setAvatar(this.lblAvatarPath.getText());
			} catch (IOException ex)
			{
				// If, for any reason, the avatar path is not valid, the avatar will just be the
				// default one.
			}

		}

		u.grantRole(Role.READ);
		if (UserDataProvider.getInstance().getData().size() == 0)
		{
			// If this user is the first user, then it is automatically promoted to administrator.
			u.grantRole(Role.SEARCH);
			u.grantRole(Role.ADD_NEWS);
			u.grantRole(Role.DELETE_NEWS);
			u.grantRole(Role.IMPORT_NEWS_FROM_FILE);
			u.grantRole(Role.MANAGE_USER);
			u.grantRole(Role.EDIT_NEWS);
		}

		UserDataProvider.getInstance().getData().add(u);
		UserDataProvider.getInstance().saveDataToFile();

		this.originalLoginFrame.dispose();
		setVisible(false);
		(new MainJFrame(u)).setVisible(true);
		dispose();

	}

	/**
	 * Allows the user to select an image as avatar.
	 */
	private void btnSelectImage_Click()
	{
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Choose avatar image file");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setMultiSelectionEnabled(false);
		fc.setFileFilter(new FileFilter()
		{

			private String[] validExt = { ".jpg", ".jpeg", ".png" };

			/**
			 * Checks if a given file is valid for this application.
			 */
			@Override
			public boolean accept(File arg0)
			{
				boolean ans = arg0.isDirectory();

				for (int i = 0; i < this.validExt.length; i++)
				{
					ans = ans || arg0.getName().endsWith(this.validExt[i]);
				}

				return ans;
			}

			/**
			 * Returns the description of this filter
			 */
			@Override
			public String getDescription()
			{
				return "Compatible images type (*.jpg; *.png)";
			}

		});

		int ans = fc.showOpenDialog(this);
		if (ans == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				this.aidImage.setIcon(new ImageIcon(ImageIO.read(fc.getSelectedFile())));
				this.lblAvatarPath.setText(fc.getSelectedFile().getAbsolutePath());
			} catch (IOException ex)
			{
				this.aidImage.setText("Default user image");
			}
		}

	}
}
