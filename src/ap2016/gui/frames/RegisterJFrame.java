package ap2016.gui.frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
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

@SuppressWarnings("serial")
public class RegisterJFrame extends JFrame
{
	private ValidableTextField vtfUsername;
	private ValidablePasswordField vpfPassword;
	private ValidablePasswordField vpfPasswordConfirm;
	private JButton btnSelectImage;
	private JButton btnRegisterAndLogin;
	private JButton btnAbort;
	
	private JFrame originalLoginFrame;
	private AvatarImageDisplay aidImage;
	private JLabel lblAvatarPath;

	public RegisterJFrame() {
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
		panel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "New user details:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		lblAvatarPath = new JLabel("default");
		lblAvatarPath.setBounds(42, 193, 242, 14);
		panel.add(lblAvatarPath);
		
		btnRegisterAndLogin = new JButton("Register and login");
		btnRegisterAndLogin.addActionListener(e -> btnRegisterAndLogin_Click(e));
		btnRegisterAndLogin.setBounds(283, 237, 147, 28);
		panel.add(btnRegisterAndLogin);
		
		btnAbort = new JButton("Abort");
		btnAbort.addActionListener(e -> btnAbort_Click(e));
		btnAbort.setBounds(68, 237, 147, 28);
		panel.add(btnAbort);
		
		btnSelectImage = new JButton("Choose avatar");
		btnSelectImage.addActionListener(e -> btnSelectImage_Click(e));
		btnSelectImage.setBounds(333, 189, 133, 23);
		panel.add(btnSelectImage);
		
		aidImage = new AvatarImageDisplay();
		aidImage.setHorizontalAlignment(SwingConstants.CENTER);
		aidImage.setBackground(Color.WHITE);
		aidImage.setBounds(343, 50, 113, 113);
		try
		{
			aidImage.setIcon(new ImageIcon(ImageIO.read(Paths.get(ApplicationConstants.assetsBase, "default").toFile())));
		}catch (IOException ex){
			aidImage.setText("Default user image");
		}
		panel.add(aidImage);
		
		vtfUsername = new ValidableTextField(s -> User.isValidUsername(s));
		vtfUsername.setBounds(42, 50, 242, 18);
		panel.add(vtfUsername);
		
		vpfPassword = new ValidablePasswordField();
		vpfPasswordConfirm = new ValidablePasswordField();
		
		vpfPassword.updateValidationTest(c -> User.isValidPassword(c) && Arrays.equals(vpfPassword.getPassword(), vpfPasswordConfirm.getPassword()));
		vpfPasswordConfirm.updateValidationTest(c -> User.isValidPassword(c) && Arrays.equals(vpfPassword.getPassword(), vpfPasswordConfirm.getPassword()));
		
		vpfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				vpfPasswordConfirm.updateValidationState();
			}
		});
		
		vpfPasswordConfirm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				vpfPassword.updateValidationState();
			}
		});
		
		vpfPassword.setBounds(42, 97, 242, 18);
		panel.add(vpfPassword);
		
		
		vpfPasswordConfirm.setBounds(42, 144, 242, 18);
		panel.add(vpfPasswordConfirm);
		
		
		
		
	}
	
	public RegisterJFrame(JFrame originalLoginFrame)
	{
		this();
		this.originalLoginFrame = originalLoginFrame;
	}
	
	
	private void btnAbort_Click(ActionEvent e)
	{
		if (originalLoginFrame != null)
		{
			originalLoginFrame.setVisible(true);
			this.setVisible(false);
			this.dispose();
		}
	}
	
	private void btnSelectImage_Click(ActionEvent e)
	{
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Choose avatar image file");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setMultiSelectionEnabled(false);
		fc.setFileFilter(new FileFilter() {

			private String[] validExt = {".jpg", ".jpeg", ".png"};
			
			@Override
			public boolean accept(File arg0)
			{
				boolean ans = arg0.isDirectory();
				
				for (int i = 0; i < validExt.length; i++)
				{
					ans = ans || arg0.getName().endsWith(validExt[i]);
				}
				
				return ans;
			}

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
				aidImage.setIcon(new ImageIcon(ImageIO.read(fc.getSelectedFile())));
				lblAvatarPath.setText(fc.getSelectedFile().getAbsolutePath());
			}catch (IOException ex){
				aidImage.setText("Default user image");
			}
		}
		
		
	}
	
	private void btnRegisterAndLogin_Click(ActionEvent e)
	{
		
		if (!vtfUsername.isValid())
		{
			JOptionPane.showMessageDialog(this, "Error: the username is invalid");
			return;
		}
		
		if (!vpfPassword.isValid())
		{
			JOptionPane.showMessageDialog(this, "Error: the password is invalid");
			return;
		}


		User u = new User(vtfUsername.getText(), vpfPassword.getPassword());
		if (!lblAvatarPath.getText().equals("default"))
		{
			try{
				u.setAvatar(lblAvatarPath.getText());
			}catch (IOException ex){
				//If, for any reason, the avatar path is not valid, the avatar will just be the default one.
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
		
		originalLoginFrame.dispose();
		this.setVisible(false);
		(new MainJFrame(u)).setVisible(true);
		this.dispose();
		
	}
}
