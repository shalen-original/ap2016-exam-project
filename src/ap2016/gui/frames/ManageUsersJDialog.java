package ap2016.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import ap2016.entities.Role;
import ap2016.entities.User;
import ap2016.gui.utilities.AvatarImageDisplay;
import ap2016.gui.utilities.CheckboxListCellRenderer;
import ap2016.gui.utilities.ValidablePasswordField;
import ap2016.gui.utilities.ValidableTextField;
import ap2016.gui.utilities.ViewEditComponent;
import ap2016.io.UserDataProvider;

@SuppressWarnings("serial")
public class ManageUsersJDialog extends JDialog
{
	User currentUser;
	private JComboBox<User> cmbUsers;
	private ViewEditComponent<JLabel, ValidableTextField> vecUsername;
	private JButton btnEditUsername;
	private AvatarImageDisplay aidAvatar;
	private JButton btnPickNew;
	private JButton btnResetToDefault;
	private ValidablePasswordField vpfNewPassword;
	private ValidablePasswordField vpfConfirmNewPassword;
	private JList<Role> lUserRoles;
	private JPanel pnlUserDetails;
	private JPasswordField pwOldPassword;
	
	public ManageUsersJDialog() {
		setupGUI();
	}
	
	public ManageUsersJDialog(JFrame parent, User currentUser){
		super(parent, "Manage users", true);
		setupGUI();
		
		this.currentUser = currentUser;
		
		vecUsername.getEditComponent().updateValidationTest(s -> User.isValidUsername(s));
		vecUsername.setViewToEditOperation((l, vt) -> vt.setText(l.getText()));
		vecUsername.setEditToViewOperation((l, vt) -> l.setText(vt.getText()));
		
		vpfNewPassword.updateValidationTest(c -> User.isValidPassword(c) && Arrays.equals(vpfNewPassword.getPassword(), vpfConfirmNewPassword.getPassword()));
		vpfConfirmNewPassword.updateValidationTest(c -> User.isValidPassword(c) && Arrays.equals(vpfNewPassword.getPassword(), vpfConfirmNewPassword.getPassword()));
		
		vecUsername.getViewComponent().setText(currentUser.getUsername());
		aidAvatar.setIcon(currentUser.getAvatar());
		
		DefaultListModel<Role> a = new DefaultListModel<>();
		
		for(Role r : Role.values())
		{
			a.addElement(r);
		}
		
		lUserRoles.setModel(a);
		lUserRoles.setCellRenderer(new CheckboxListCellRenderer());		
		
		fillCmbUsers();
		
	}
	
	private void setupGUI()
	{
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		setPreferredSize(new Dimension(550, 670));
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(10);
		getContentPane().add(horizontalStrut_4);
		
		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_4.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut);
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 28));
		panel_2.add(panel);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Currently inspecting user:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		panel_3.add(verticalStrut_2);
		
		cmbUsers = new JComboBox<>();
		cmbUsers.addActionListener(e -> cmbUsers_SelectedItemChanged());
		panel_3.add(cmbUsers);
		
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		panel_3.add(verticalStrut_3);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1);
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_1);
		
		pnlUserDetails = new JPanel();
		pnlUserDetails.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "User details:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(pnlUserDetails);
		pnlUserDetails.setLayout(new BoxLayout(pnlUserDetails, BoxLayout.X_AXIS));
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		pnlUserDetails.add(horizontalStrut_6);
		
		JPanel panel_5 = new JPanel();
		pnlUserDetails.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		Component verticalStrut_8 = Box.createVerticalStrut(10);
		verticalStrut_8.setMaximumSize(new Dimension(0, 20));
		panel_5.add(verticalStrut_8);
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
		
		JLabel lblUsername = new JLabel("Username:");
		panel_6.add(lblUsername);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(10);
		horizontalStrut_8.setMaximumSize(new Dimension(10, 0));
		panel_6.add(horizontalStrut_8);
		
		JLabel label = new JLabel("username");
		ValidableTextField validableTextField = new ValidableTextField();
		vecUsername = new ViewEditComponent<JLabel, ValidableTextField>(label, validableTextField);
		vecUsername.setMaximumSize(new Dimension(2147483647, 25));
		vecUsername.setPreferredSize(new Dimension(200, 30));
		panel_6.add(vecUsername);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut_9);
		
		btnEditUsername = new JButton("Edit");
		btnEditUsername.addActionListener(e -> btnEditUsername_Click());
		panel_6.add(btnEditUsername);
		
		Component verticalStrut_9 = Box.createVerticalStrut(5);
		verticalStrut_9.setMaximumSize(new Dimension(0, 20));
		panel_5.add(verticalStrut_9);
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));
		
		aidAvatar = new AvatarImageDisplay();
		aidAvatar.setMaximumSize(new Dimension(100, 100));
		aidAvatar.setHorizontalAlignment(SwingConstants.CENTER);
		aidAvatar.setText("Avatar");
		aidAvatar.setPreferredSize(new Dimension(100, 100));
		aidAvatar.setSize(new Dimension(100, 100));
		panel_7.add(aidAvatar);
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		panel_7.add(horizontalStrut_10);
		
		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
		
		Component verticalStrut_11 = Box.createVerticalStrut(5);
		panel_9.add(verticalStrut_11);
		
		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.X_AXIS));
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel_10.add(horizontalGlue);
		
		btnPickNew = new JButton("Pick new");
		btnPickNew.addActionListener(e -> btnPickNew_Click());
		panel_10.add(btnPickNew);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(30);
		horizontalStrut_11.setMaximumSize(new Dimension(30, 0));
		panel_10.add(horizontalStrut_11);
		
		btnResetToDefault = new JButton("Reset to default");
		btnResetToDefault.addActionListener(e -> btnResetToDefault_Click());
		panel_10.add(btnResetToDefault);
		
		Component verticalStrut_13 = Box.createVerticalStrut(5);
		panel_9.add(verticalStrut_13);
		
		Component verticalStrut_10 = Box.createVerticalStrut(10);
		panel_5.add(verticalStrut_10);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Update password:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.X_AXIS));
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		panel_8.add(horizontalStrut_12);
		
		JPanel panel_11 = new JPanel();
		panel_8.add(panel_11);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.Y_AXIS));
		
		Component verticalStrut_12 = Box.createVerticalStrut(10);
		panel_11.add(verticalStrut_12);
		
		JPanel panel_12 = new JPanel();
		panel_11.add(panel_12);
		panel_12.setLayout(new BoxLayout(panel_12, BoxLayout.X_AXIS));
		
		JLabel lblEnterOldPassword = new JLabel("Enter old password:");
		panel_12.add(lblEnterOldPassword);
		
		Component horizontalStrut_14 = Box.createHorizontalStrut(20);
		panel_12.add(horizontalStrut_14);
		
		pwOldPassword = new JPasswordField();
		pwOldPassword.setMaximumSize(new Dimension(2147483647, 30));
		panel_12.add(pwOldPassword);
		
		Component verticalStrut_14 = Box.createVerticalStrut(5);
		panel_11.add(verticalStrut_14);
		
		JPanel panel_13 = new JPanel();
		panel_11.add(panel_13);
		panel_13.setLayout(new BoxLayout(panel_13, BoxLayout.X_AXIS));
		
		JLabel lblEnterNewPassword = new JLabel("Enter new password:");
		panel_13.add(lblEnterNewPassword);
		
		Component horizontalStrut_15 = Box.createHorizontalStrut(20);
		panel_13.add(horizontalStrut_15);
		
		vpfNewPassword = new ValidablePasswordField();
		vpfNewPassword.setMaximumSize(new Dimension(2147483647, 30));
		panel_13.add(vpfNewPassword);
		
		Component verticalStrut_15 = Box.createVerticalStrut(5);
		panel_11.add(verticalStrut_15);
		
		JPanel panel_14 = new JPanel();
		panel_11.add(panel_14);
		panel_14.setLayout(new BoxLayout(panel_14, BoxLayout.X_AXIS));
		
		JLabel lblConfirmNewPassword = new JLabel("Confirm new password:");
		panel_14.add(lblConfirmNewPassword);
		
		Component horizontalStrut_16 = Box.createHorizontalStrut(20);
		panel_14.add(horizontalStrut_16);
		
		vpfConfirmNewPassword = new ValidablePasswordField();
		vpfConfirmNewPassword.setMaximumSize(new Dimension(2147483647, 30));
		panel_14.add(vpfConfirmNewPassword);
		
		Component verticalStrut_16 = Box.createVerticalStrut(10);
		panel_11.add(verticalStrut_16);
		
		JPanel panel_15 = new JPanel();
		panel_11.add(panel_15);
		panel_15.setLayout(new BoxLayout(panel_15, BoxLayout.X_AXIS));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_15.add(horizontalGlue_1);
		
		JButton btnUpdatePassword = new JButton("Update password");
		btnUpdatePassword.addActionListener(e -> btnUpdatePassword_Click());
		panel_15.add(btnUpdatePassword);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panel_15.add(horizontalGlue_2);
		
		Component verticalStrut_17 = Box.createVerticalStrut(10);
		panel_11.add(verticalStrut_17);
		
		Component horizontalStrut_13 = Box.createHorizontalStrut(20);
		panel_8.add(horizontalStrut_13);
		
		Component verticalStrut_18 = Box.createVerticalStrut(5);
		panel_5.add(verticalStrut_18);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		pnlUserDetails.add(horizontalStrut_7);
		
		Component verticalStrut_7 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_7);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Roles of the selected user:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		panel_1.add(horizontalStrut_2, BorderLayout.WEST);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(10);
		panel_1.add(horizontalStrut_3, BorderLayout.EAST);
		
		Component verticalStrut_4 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_4, BorderLayout.NORTH);
		
		Component verticalStrut_5 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_5, BorderLayout.SOUTH);
		
		lUserRoles = new JList<>();
		lUserRoles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				User selectedUser = (User)cmbUsers.getSelectedItem();
				if (selectedUser.hasRole(lUserRoles.getSelectedValue()))
				{
					selectedUser.removeRole(lUserRoles.getSelectedValue());
				}else{
					selectedUser.grantRole(lUserRoles.getSelectedValue());
				}
				
				fillCmbUsers();
				
				lUserRoles.repaint();
				
			}
		});
		panel_1.add(lUserRoles, BorderLayout.CENTER);
		
		Component verticalStrut_6 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_6);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(10);
		getContentPane().add(horizontalStrut_5);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{horizontalStrut_4, verticalStrut, panel_4, panel_2, getContentPane(), panel, horizontalStrut, panel_3, verticalStrut_2, cmbUsers, verticalStrut_3, horizontalStrut_1, verticalStrut_1, pnlUserDetails, horizontalStrut_6, panel_5, verticalStrut_8, panel_6, lblUsername, horizontalStrut_8, vecUsername, vecUsername.getViewComponent(), label, vecUsername.getEditComponent(), validableTextField, horizontalStrut_9, btnEditUsername, verticalStrut_9, panel_7, aidAvatar, horizontalStrut_10, panel_9, verticalStrut_11, panel_10, horizontalGlue, btnPickNew, horizontalStrut_11, btnResetToDefault, verticalStrut_13, verticalStrut_10, panel_8, horizontalStrut_12, panel_11, verticalStrut_12, panel_12, lblEnterOldPassword, horizontalStrut_14, pwOldPassword, verticalStrut_14, panel_13, lblEnterNewPassword, horizontalStrut_15, vpfNewPassword, verticalStrut_15, panel_14, lblConfirmNewPassword, horizontalStrut_16, vpfConfirmNewPassword, verticalStrut_16, panel_15, horizontalGlue_1, btnUpdatePassword, horizontalGlue_2, verticalStrut_17, horizontalStrut_13, verticalStrut_18, horizontalStrut_7, verticalStrut_7, panel_1, horizontalStrut_2, horizontalStrut_3, verticalStrut_4, verticalStrut_5, lUserRoles, verticalStrut_6, horizontalStrut_5}));
	}

	private void cmbUsers_SelectedItemChanged()
	{
		if (cmbUsers.getSelectedItem() == null)
			return;
		
		if (cmbUsers.getSelectedItem().equals(currentUser))
		{
			pnlUserDetails.setVisible(true);
		}else{
			pnlUserDetails.setVisible(false);
		}
		
		((CheckboxListCellRenderer)lUserRoles.getCellRenderer()).updateCurrentUser((User)cmbUsers.getSelectedItem());
				
	}
	
	
	private void fillCmbUsers()
	{
		Object prev = cmbUsers.getSelectedItem();
		
		cmbUsers.removeAllItems();
		if (currentUser.hasRole(Role.MANAGE_USER))
		{
			lUserRoles.setEnabled(true);
			for (User u : UserDataProvider.getInstance().getData())
			{
				cmbUsers.addItem(u);
			}
		}else{
			cmbUsers.addItem(currentUser);
			lUserRoles.setEnabled(false);
		}
		
		cmbUsers.setSelectedItem(prev);
		
		if (cmbUsers.getSelectedItem() == null)
			cmbUsers.setSelectedItem(currentUser);
		
	}
	
	private void btnEditUsername_Click()
	{
		if (btnEditUsername.getText().equals("Edit"))
		{
			btnEditUsername.setText("Save");
			vecUsername.setEditState();
		}else{
			
			if (!vecUsername.getEditComponent().isValid())
			{
				JOptionPane.showMessageDialog(this, "The new username is not valid");
				return;
			}
			
			btnEditUsername.setText("Edit");
			vecUsername.setViewState();
			currentUser.setUsername(vecUsername.getViewComponent().getText());
			fillCmbUsers();
		}
	}
	
	private void btnResetToDefault_Click()
	{
		currentUser.setAvatarFromName("default");
		aidAvatar.setIcon(currentUser.getAvatar());
	}
	
	private void btnPickNew_Click()
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
				currentUser.setAvatar(fc.getSelectedFile());
			}catch (IOException ex){
				aidAvatar.setText("Default user image");
			}
		}
		
		aidAvatar.setIcon(currentUser.getAvatar());
		
	}

	private void btnUpdatePassword_Click(){
		if (!currentUser.isRightPassword(pwOldPassword.getPassword()))
		{
			JOptionPane.showMessageDialog(this, "The old password inserted is not valid, password not updated");
		}
		
		if ((!vpfNewPassword.isValid()) || (!vpfConfirmNewPassword.isValid()))
		{
			JOptionPane.showMessageDialog(this, "The new passwords are invalid or they doesn't match");
		}
		
		currentUser.setNewPassword(vpfNewPassword.getPassword());
		
	}
}
