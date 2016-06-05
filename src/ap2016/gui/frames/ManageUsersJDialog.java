package ap2016.gui.frames;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import ap2016.entities.Role;
import ap2016.entities.User;
import ap2016.gui.utilities.AvatarImageDisplay;
import ap2016.gui.utilities.RoleCheckboxListCellRenderer;
import ap2016.gui.utilities.ValidablePasswordField;
import ap2016.gui.utilities.ValidableTextField;
import ap2016.gui.utilities.ViewEditComponent;
import ap2016.io.UserDataProvider;


public class ManageUsersJDialog extends JDialog
{
	/**
	 * Serial version UUID.
	 */
	private static final long serialVersionUID = -2051021382115171944L;
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

	/**
	 * Constructor used by the GUI builder.
	 */
	public ManageUsersJDialog()
	{
		setupGUI();
	}

	/**
	 * Creates a windows that allows a user to manage its account and (if required) the permission given to other users.
	 * @param parent The JFrame that is the parent of this dialog.
	 * @param currentUser The current user. This user's permission will be considered in order to choose which part of the dialog have to be shown or hidden.
	 */
	public ManageUsersJDialog(JFrame parent, User currentUser)
	{
		super(parent, "Manage users", true);
		setupGUI();

		this.currentUser = currentUser;

		this.vecUsername.getEditComponent().updateValidationTest(s -> User.isValidUsername(s));
		this.vecUsername.setViewToEditOperation((l, vt) -> vt.setText(l.getText()));
		this.vecUsername.setEditToViewOperation((l, vt) -> l.setText(vt.getText()));

		this.vpfNewPassword.updateValidationTest(c -> User.isValidPassword(c)
				&& Arrays.equals(this.vpfNewPassword.getPassword(), this.vpfConfirmNewPassword.getPassword()));
		this.vpfConfirmNewPassword.updateValidationTest(c -> User.isValidPassword(c)
				&& Arrays.equals(this.vpfNewPassword.getPassword(), this.vpfConfirmNewPassword.getPassword()));

		this.vecUsername.getViewComponent().setText(currentUser.getUsername());
		this.aidAvatar.setIcon(currentUser.getAvatar());

		DefaultListModel<Role> a = new DefaultListModel<>();

		for (Role r : Role.values())
		{
			a.addElement(r);
		}

		this.lUserRoles.setModel(a);
		this.lUserRoles.setCellRenderer(new RoleCheckboxListCellRenderer());

		fillCmbUsers();

	}

	/**
	 * Allows the user to edit it's own username.
	 */
	private void btnEditUsername_Click()
	{
		if (this.btnEditUsername.getText().equals("Edit"))
		{
			this.btnEditUsername.setText("Save");
			this.vecUsername.setEditState();
		} else
		{

			if (!this.vecUsername.getEditComponent().isValid())
			{
				JOptionPane.showMessageDialog(this, "The new username is not valid");
				return;
			}

			this.btnEditUsername.setText("Edit");
			this.vecUsername.setViewState();
			this.currentUser.setUsername(this.vecUsername.getViewComponent().getText());
			fillCmbUsers();
		}
	}

	/**
	 * Allows the user to pick a new avatar image.
	 */
	private void btnPickNew_Click()
	{
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Choose avatar image file");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setMultiSelectionEnabled(false);
		fc.setFileFilter(new FileFilter()
		{

			private String[] validExt = { ".jpg", ".jpeg", ".png" };

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
				this.currentUser.setAvatar(fc.getSelectedFile());
			} catch (IOException ex)
			{
				this.aidAvatar.setText("Default user image");
			}
		}

		this.aidAvatar.setIcon(this.currentUser.getAvatar());

	}

	/**
	 * Allows the user to reset its avatar to the default one.
	 */
	private void btnResetToDefault_Click()
	{
		this.currentUser.setAvatarFromName("default");
		this.aidAvatar.setIcon(this.currentUser.getAvatar());
	}

	/**
	 * Allows the user to update its current password.
	 */
	private void btnUpdatePassword_Click()
	{
		if (!this.currentUser.isRightPassword(this.pwOldPassword.getPassword()))
		{
			JOptionPane.showMessageDialog(this, "The old password inserted is not valid, password not updated");
			return;
		}

		if ((!this.vpfNewPassword.isValid()) || (!this.vpfConfirmNewPassword.isValid()))
		{
			JOptionPane.showMessageDialog(this, "The new passwords are invalid or they don't match");
			return;
		}

		this.currentUser.setNewPassword(this.vpfNewPassword.getPassword());

		JOptionPane.showMessageDialog(this, "The new passwords has been updated correctly");

		this.pwOldPassword.setText("");
		this.vpfNewPassword.setText("");
		this.vpfConfirmNewPassword.setText("");

	}

	/**
	 * When the selected user is changed, this method updates the GUI to reflect the state of the selected user.
	 */
	private void cmbUsers_SelectedItemChanged()
	{
		if (this.cmbUsers.getSelectedItem() == null)
		{
			return;
		}

		if (this.cmbUsers.getSelectedItem().equals(this.currentUser))
		{
			this.pnlUserDetails.setVisible(true);
		} else
		{
			this.pnlUserDetails.setVisible(false);
		}

		((RoleCheckboxListCellRenderer) this.lUserRoles.getCellRenderer())
				.updateCurrentUser((User) this.cmbUsers.getSelectedItem());

	}

	/**
	 * Reloads the list of users that have to be shown to the {@code currentUser}.
	 */
	private void fillCmbUsers()
	{
		Object prev = this.cmbUsers.getSelectedItem();

		this.cmbUsers.removeAllItems();
		if (this.currentUser.hasRole(Role.MANAGE_USER))
		{
			this.lUserRoles.setEnabled(true);
			for (User u : UserDataProvider.getInstance().getData())
			{
				this.cmbUsers.addItem(u);
			}
		} else
		{
			this.cmbUsers.addItem(this.currentUser);
			this.lUserRoles.setEnabled(false);
		}

		this.cmbUsers.setSelectedItem(prev);

		if (this.cmbUsers.getSelectedItem() == null)
		{
			this.cmbUsers.setSelectedItem(this.currentUser);
		}

	}

	/**
	 * Creates the GUI for this dialog.
	 */
	private void setupGUI()
	{
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		setPreferredSize(new Dimension(570, 670));

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
		panel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true),
				"Currently inspecting user:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		Component verticalStrut_2 = Box.createVerticalStrut(10);
		panel_3.add(verticalStrut_2);

		this.cmbUsers = new JComboBox<>();
		this.cmbUsers.addActionListener(e -> cmbUsers_SelectedItemChanged());
		panel_3.add(this.cmbUsers);

		Component verticalStrut_3 = Box.createVerticalStrut(10);
		panel_3.add(verticalStrut_3);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1);

		Component verticalStrut_1 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_1);

		this.pnlUserDetails = new JPanel();
		this.pnlUserDetails.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true),
				"User details:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(this.pnlUserDetails);
		this.pnlUserDetails.setLayout(new BoxLayout(this.pnlUserDetails, BoxLayout.X_AXIS));

		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		this.pnlUserDetails.add(horizontalStrut_6);

		JPanel panel_5 = new JPanel();
		this.pnlUserDetails.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		Component verticalStrut_8 = Box.createVerticalStrut(10);
		verticalStrut_8.setMaximumSize(new Dimension(0, 20));
		panel_5.add(verticalStrut_8);

		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));

		this.aidAvatar = new AvatarImageDisplay();
		this.aidAvatar.setMaximumSize(new Dimension(100, 100));
		this.aidAvatar.setHorizontalAlignment(SwingConstants.CENTER);
		this.aidAvatar.setText("Avatar");
		this.aidAvatar.setPreferredSize(new Dimension(100, 100));
		this.aidAvatar.setSize(new Dimension(100, 100));
		panel_7.add(this.aidAvatar);

		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		panel_7.add(horizontalStrut_10);

		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));

		Component verticalStrut_19 = Box.createVerticalStrut(5);
		panel_9.add(verticalStrut_19);

		JPanel panel_6 = new JPanel();
		panel_9.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));

		JLabel lblUsername = new JLabel("Username:");
		panel_6.add(lblUsername);

		Component horizontalStrut_8 = Box.createHorizontalStrut(10);
		horizontalStrut_8.setMaximumSize(new Dimension(10, 0));
		panel_6.add(horizontalStrut_8);

		JLabel label = new JLabel("username");
		ValidableTextField validableTextField = new ValidableTextField();
		this.vecUsername = new ViewEditComponent<JLabel, ValidableTextField>(label, validableTextField);
		this.vecUsername.setMaximumSize(new Dimension(2147483647, 25));
		this.vecUsername.setPreferredSize(new Dimension(200, 30));
		panel_6.add(this.vecUsername);

		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		horizontalStrut_9.setMaximumSize(new Dimension(20, 0));
		panel_6.add(horizontalStrut_9);

		this.btnEditUsername = new JButton("Edit");
		this.btnEditUsername.addActionListener(e -> btnEditUsername_Click());
		panel_6.add(this.btnEditUsername);

		Component verticalStrut_11 = Box.createVerticalStrut(10);
		panel_9.add(verticalStrut_11);

		JPanel panel_10 = new JPanel();
		panel_9.add(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.X_AXIS));

		this.btnPickNew = new JButton("Pick new avatar");
		this.btnPickNew.addActionListener(e -> btnPickNew_Click());

		Component horizontalStrut_11 = Box.createHorizontalStrut(10);
		panel_10.add(horizontalStrut_11);
		panel_10.add(this.btnPickNew);

		this.btnResetToDefault = new JButton("Reset avatar to default");
		this.btnResetToDefault.addActionListener(e -> btnResetToDefault_Click());

		Component horizontalGlue_4 = Box.createHorizontalGlue();
		horizontalGlue_4.setMaximumSize(new Dimension(200, 0));
		panel_10.add(horizontalGlue_4);
		panel_10.add(this.btnResetToDefault);

		Component horizontalStrut_17 = Box.createHorizontalStrut(10);
		panel_10.add(horizontalStrut_17);

		Component verticalStrut_13 = Box.createVerticalStrut(5);
		panel_9.add(verticalStrut_13);

		Component verticalStrut_10 = Box.createVerticalStrut(10);
		panel_5.add(verticalStrut_10);

		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Update password:",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

		this.pwOldPassword = new JPasswordField();
		this.pwOldPassword.setMaximumSize(new Dimension(2147483647, 30));
		panel_12.add(this.pwOldPassword);

		Component verticalStrut_14 = Box.createVerticalStrut(5);
		panel_11.add(verticalStrut_14);

		JPanel panel_13 = new JPanel();
		panel_11.add(panel_13);
		panel_13.setLayout(new BoxLayout(panel_13, BoxLayout.X_AXIS));

		JLabel lblEnterNewPassword = new JLabel("Enter new password:");
		panel_13.add(lblEnterNewPassword);

		Component horizontalStrut_15 = Box.createHorizontalStrut(20);
		panel_13.add(horizontalStrut_15);

		this.vpfNewPassword = new ValidablePasswordField();
		this.vpfNewPassword.setMaximumSize(new Dimension(2147483647, 30));
		panel_13.add(this.vpfNewPassword);

		Component verticalStrut_15 = Box.createVerticalStrut(5);
		panel_11.add(verticalStrut_15);

		JPanel panel_14 = new JPanel();
		panel_11.add(panel_14);
		panel_14.setLayout(new BoxLayout(panel_14, BoxLayout.X_AXIS));

		JLabel lblConfirmNewPassword = new JLabel("Confirm new password:");
		panel_14.add(lblConfirmNewPassword);

		Component horizontalStrut_16 = Box.createHorizontalStrut(20);
		panel_14.add(horizontalStrut_16);

		this.vpfConfirmNewPassword = new ValidablePasswordField();
		this.vpfConfirmNewPassword.setMaximumSize(new Dimension(2147483647, 30));
		panel_14.add(this.vpfConfirmNewPassword);

		this.vpfNewPassword.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				ManageUsersJDialog.this.vpfConfirmNewPassword.updateValidationState();
			}
		});

		this.vpfConfirmNewPassword.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				ManageUsersJDialog.this.vpfNewPassword.updateValidationState();
			}
		});

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
		this.pnlUserDetails.add(horizontalStrut_7);

		Component verticalStrut_7 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_7);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true),
				"Roles of the selected user:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

		this.lUserRoles = new JList<>();
		this.lUserRoles.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				User selectedUser = (User) ManageUsersJDialog.this.cmbUsers.getSelectedItem();
				if (selectedUser.hasRole(ManageUsersJDialog.this.lUserRoles.getSelectedValue()))
				{
					selectedUser.removeRole(ManageUsersJDialog.this.lUserRoles.getSelectedValue());
				} else
				{
					selectedUser.grantRole(ManageUsersJDialog.this.lUserRoles.getSelectedValue());
				}

				fillCmbUsers();

				ManageUsersJDialog.this.lUserRoles.repaint();

			}
		});
		panel_1.add(this.lUserRoles, BorderLayout.CENTER);

		Component verticalStrut_6 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_6);

		Component horizontalStrut_5 = Box.createHorizontalStrut(10);
		getContentPane().add(horizontalStrut_5);

	}
}
