package ap2016.gui.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import ap2016.entities.User;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import ap2016.gui.utilities.AvatarImageDisplay;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainJFrame extends JFrame
{
	private JTextField textField;

	public MainJFrame() {
		setSize(new Dimension(900, 649));
		setPreferredSize(new Dimension(900, 649));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("NewsFeed");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mntmExit = new JMenu("Exit");
		menuBar.add(mntmExit);
		
		JMenu mntmAbout = new JMenu("About");
		menuBar.add(mntmAbout);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(5, 0));
		
		JPanel DrawerPanel = new JPanel();
		DrawerPanel.setSize(new Dimension(250, 0));
		DrawerPanel.setMaximumSize(new Dimension(250, 32767));
		DrawerPanel.setPreferredSize(new Dimension(250, 200));
		DrawerPanel.setMinimumSize(new Dimension(250, 200));
		DrawerPanel.setBounds(new Rectangle(0, 0, 250, 0));
		panel.add(DrawerPanel);
		DrawerPanel.setLayout(new BoxLayout(DrawerPanel, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(10);
		DrawerPanel.add(verticalStrut);
		
		JPanel UsernameDisplay = new JPanel();
		UsernameDisplay.setMaximumSize(new Dimension(32767, 97));
		UsernameDisplay.setMinimumSize(new Dimension(10, 97));
		UsernameDisplay.setPreferredSize(new Dimension(10, 97));
		DrawerPanel.add(UsernameDisplay);
		UsernameDisplay.setLayout(null);
		
		AvatarImageDisplay avatarImg = new AvatarImageDisplay();
		avatarImg.setBorder(new LineBorder(new Color(0, 0, 0)));
		avatarImg.setBackground(Color.WHITE);
		avatarImg.setBounds(10, 11, 75, 75);
		UsernameDisplay.add(avatarImg);
		avatarImg.setText("Test");
		avatarImg.setMinimumSize(new Dimension(50, 50));
		avatarImg.setPreferredSize(new Dimension(50, 50));
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(95, 54, 145, 32);
		UsernameDisplay.add(lblUsername);
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		DrawerPanel.add(verticalStrut_1);
		
		JPanel SelectNewsChannel = new JPanel();
		SelectNewsChannel.setSize(new Dimension(0, 80));
		SelectNewsChannel.setPreferredSize(new Dimension(10, 60));
		SelectNewsChannel.setMinimumSize(new Dimension(10, 100));
		SelectNewsChannel.setMaximumSize(new Dimension(32767, 100));
		DrawerPanel.add(SelectNewsChannel);
		SelectNewsChannel.setLayout(new BoxLayout(SelectNewsChannel, BoxLayout.X_AXIS));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		horizontalStrut_2.setMaximumSize(new Dimension(10, 0));
		SelectNewsChannel.add(horizontalStrut_2);
		
		JPanel SelectNewsChannelInternal = new JPanel();
		SelectNewsChannelInternal.setMaximumSize(new Dimension(32767, 60));
		SelectNewsChannelInternal.setMinimumSize(new Dimension(10, 60));
		SelectNewsChannelInternal.setBounds(new Rectangle(0, 0, 0, 60));
		SelectNewsChannelInternal.setSize(new Dimension(0, 60));
		SelectNewsChannelInternal.setPreferredSize(new Dimension(10, 60));
		SelectNewsChannelInternal.setName("");
		SelectNewsChannelInternal.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Select news channel to show:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		SelectNewsChannel.add(SelectNewsChannelInternal);
		SelectNewsChannelInternal.setLayout(new BoxLayout(SelectNewsChannelInternal, BoxLayout.X_AXIS));
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(10);
		horizontalStrut_12.setMaximumSize(new Dimension(10, 0));
		SelectNewsChannelInternal.add(horizontalStrut_12);
		
		JComboBox cmbNewsChannel = new JComboBox();
		cmbNewsChannel.setMaximumSize(new Dimension(32767, 20));
		SelectNewsChannelInternal.add(cmbNewsChannel);
		
		Component horizontalStrut_13 = Box.createHorizontalStrut(10);
		horizontalStrut_13.setMaximumSize(new Dimension(10, 0));
		SelectNewsChannelInternal.add(horizontalStrut_13);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(10);
		horizontalStrut_3.setMaximumSize(new Dimension(10, 0));
		SelectNewsChannel.add(horizontalStrut_3);
		
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		DrawerPanel.add(verticalStrut_2);
		
		JPanel QuickActions = new JPanel();
		DrawerPanel.add(QuickActions);
		QuickActions.setLayout(new BoxLayout(QuickActions, BoxLayout.X_AXIS));
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(10);
		horizontalStrut_7.setMaximumSize(new Dimension(10, 0));
		QuickActions.add(horizontalStrut_7);
		
		JPanel QuickActionsInternal = new JPanel();
		QuickActionsInternal.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Quick actions:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		QuickActions.add(QuickActionsInternal);
		QuickActionsInternal.setLayout(new BoxLayout(QuickActionsInternal, BoxLayout.X_AXIS));
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(10);
		horizontalStrut_10.setMaximumSize(new Dimension(10, 0));
		QuickActionsInternal.add(horizontalStrut_10);
		
		JPanel panel_2 = new JPanel();
		QuickActionsInternal.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		Component verticalStrut_6 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(32767, 25));
		panel_2.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Save all:");
		panel_1.add(lblNewLabel);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(10);
		horizontalStrut_4.setMaximumSize(new Dimension(10, 30));
		panel_1.add(horizontalStrut_4);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setMaximumSize(new Dimension(200, 25));
		comboBox.setPreferredSize(new Dimension(80, 25));
		comboBox.setMinimumSize(new Dimension(40, 25));
		panel_1.add(comboBox);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(10);
		horizontalStrut_5.setMaximumSize(new Dimension(10, 30));
		panel_1.add(horizontalStrut_5);
		
		JButton btnExportDataAsHTML = new JButton("Go");
		btnExportDataAsHTML.setPreferredSize(new Dimension(50, 25));
		panel_1.add(btnExportDataAsHTML);
		btnExportDataAsHTML.setMinimumSize(new Dimension(50, 25));
		btnExportDataAsHTML.setMaximumSize(new Dimension(50, 25));
		
		Component verticalStrut_5 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_5);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel2 = new JLabel("Save channel:");
		panel_3.add(lblNewLabel2);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(10);
		horizontalStrut_8.setMaximumSize(new Dimension(20, 30));
		panel_3.add(horizontalStrut_8);
		
		
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setMaximumSize(new Dimension(200, 25));
		comboBox_2.setPreferredSize(new Dimension(80, 25));
		comboBox_2.setMinimumSize(new Dimension(40, 25));
		panel_3.add(comboBox_2);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(10);
		horizontalStrut_9.setMaximumSize(new Dimension(20, 30));
		panel_3.add(horizontalStrut_9);
		
		
		JButton btnExportDataAsRTF = new JButton("Go");
		btnExportDataAsRTF.setPreferredSize(new Dimension(50, 25));
		panel_3.add(btnExportDataAsRTF);
		btnExportDataAsRTF.setMinimumSize(new Dimension(50, 25));
		btnExportDataAsRTF.setMaximumSize(new Dimension(50, 25));
		
		Component verticalStrut_9 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_9);
		
		JPanel panel_5 = new JPanel();
		panel_5.setMaximumSize(new Dimension(32767, 25));
		panel_2.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JButton btnOpenStatistic = new JButton("Show statistics");
		btnOpenStatistic.setPreferredSize(new Dimension(30, 25));
		btnOpenStatistic.setMinimumSize(new Dimension(119, 25));
		btnOpenStatistic.setMaximumSize(new Dimension(119, 25));
		panel_5.add(btnOpenStatistic, BorderLayout.CENTER);
		
		Component verticalStrut_7 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_7);
		
		JPanel panel_6 = new JPanel();
		panel_6.setMaximumSize(new Dimension(32767, 25));
		panel_2.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JButton btnManageUsers = new JButton("Manage users");
		panel_6.add(btnManageUsers, BorderLayout.NORTH);
		
		Component verticalStrut_8 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_8);
		
		JPanel panel_4 = new JPanel();
		panel_4.setMaximumSize(new Dimension(32767, 25));
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JButton btnImportData = new JButton("Import data file");
		panel_4.add(btnImportData);
		
		Component verticalStrut_10 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_10);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(10);
		horizontalStrut_6.setMaximumSize(new Dimension(10, 0));
		QuickActionsInternal.add(horizontalStrut_6);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(10);
		horizontalStrut_11.setMaximumSize(new Dimension(10, 0));
		QuickActions.add(horizontalStrut_11);
		
		Component verticalGlue = Box.createVerticalGlue();
		DrawerPanel.add(verticalGlue);
		
		JPanel LogoutExit = new JPanel();
		DrawerPanel.add(LogoutExit);
		LogoutExit.setLayout(new BoxLayout(LogoutExit, BoxLayout.X_AXIS));
		
		Component rigidArea = Box.createRigidArea(new Dimension(10, 30));
		LogoutExit.add(rigidArea);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setMaximumSize(new Dimension(95, 30));
		btnExit.setMinimumSize(new Dimension(95, 30));
		btnExit.setPreferredSize(new Dimension(95, 30));
		LogoutExit.add(btnExit);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		LogoutExit.add(horizontalGlue);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setMinimumSize(new Dimension(95, 30));
		btnLogout.setPreferredSize(new Dimension(95, 30));
		btnLogout.setMaximumSize(new Dimension(95, 30));
		LogoutExit.add(btnLogout);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(10, 30));
		LogoutExit.add(rigidArea_1);
		
		Component verticalStrut_4 = Box.createVerticalStrut(13);
		DrawerPanel.add(verticalStrut_4);
		
		JPanel MainPanel = new JPanel();
		getContentPane().add(MainPanel, BorderLayout.CENTER);
		MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
		
		Component verticalStrut_11 = Box.createVerticalStrut(10);
		MainPanel.add(verticalStrut_11);
		
		JPanel Title = new JPanel();
		Title.setPreferredSize(new Dimension(10, 30));
		Title.setMinimumSize(new Dimension(10, 30));
		Title.setMaximumSize(new Dimension(32767, 30));
		MainPanel.add(Title);
		Title.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewsfeed = new JLabel("NewsFeed");
		lblNewsfeed.setFont(new Font("Dialog", Font.PLAIN, 21));
		lblNewsfeed.setHorizontalAlignment(SwingConstants.CENTER);
		Title.add(lblNewsfeed, BorderLayout.CENTER);
		
		Component verticalStrut_12 = Box.createVerticalStrut(10);
		MainPanel.add(verticalStrut_12);
		
		JPanel News = new JPanel();
		MainPanel.add(News);
		News.setLayout(new BoxLayout(News, BoxLayout.X_AXIS));
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		News.add(horizontalStrut);
		
		JPanel NewsInternal = new JPanel();
		News.add(NewsInternal);
		NewsInternal.setLayout(new BorderLayout(0, 0));
		
		JScrollPane NewsScrollPanel = new JScrollPane();
		NewsScrollPanel.setBorder(null);
		NewsInternal.add(NewsScrollPanel, BorderLayout.CENTER);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		News.add(horizontalStrut_1);
		
		Component verticalStrut_13 = Box.createVerticalStrut(10);
		MainPanel.add(verticalStrut_13);
		
		JPanel NewsButton = new JPanel();
		MainPanel.add(NewsButton);
		NewsButton.setLayout(new BoxLayout(NewsButton, BoxLayout.X_AXIS));
		
		Component horizontalStrut_16 = Box.createHorizontalStrut(10);
		NewsButton.add(horizontalStrut_16);
		
		JPanel NewsButtonInternal = new JPanel();
		NewsButtonInternal.setMaximumSize(new Dimension(32767, 30));
		NewsButton.add(NewsButtonInternal);
		NewsButtonInternal.setLayout(new BoxLayout(NewsButtonInternal, BoxLayout.X_AXIS));
		
		textField = new JTextField();
		textField.setSize(new Dimension(500, 0));
		textField.setMinimumSize(new Dimension(500, 20));
		textField.setPreferredSize(new Dimension(500, 20));
		textField.setMaximumSize(new Dimension(500, 2147483647));
		NewsButtonInternal.add(textField);
		textField.setColumns(10);
		
		Component horizontalStrut_18 = Box.createHorizontalStrut(10);
		horizontalStrut_18.setMaximumSize(new Dimension(10, 32767));
		NewsButtonInternal.add(horizontalStrut_18);
		
		JButton btnSearch = new JButton("Search");
		NewsButtonInternal.add(btnSearch);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalGlue_1.setMaximumSize(new Dimension(300, 0));
		NewsButtonInternal.add(horizontalGlue_1);
		
		JButton btnAdd = new JButton("Add");
		NewsButtonInternal.add(btnAdd);
		
		Component horizontalStrut_19 = Box.createHorizontalStrut(10);
		NewsButtonInternal.add(horizontalStrut_19);
		
		JButton btnRemove = new JButton("Remove");
		NewsButtonInternal.add(btnRemove);
		
		Component horizontalStrut_17 = Box.createHorizontalStrut(10);
		NewsButton.add(horizontalStrut_17);
		
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		MainPanel.add(verticalStrut_3);
		
		JPanel NewsChannelDetail = new JPanel();
		MainPanel.add(NewsChannelDetail);
		NewsChannelDetail.setLayout(new BoxLayout(NewsChannelDetail, BoxLayout.X_AXIS));
		
		Component horizontalStrut_15 = Box.createHorizontalStrut(10);
		horizontalStrut_15.setMaximumSize(new Dimension(10, 0));
		NewsChannelDetail.add(horizontalStrut_15);
		
		JPanel NewsChannelDetailInternal = new JPanel();
		NewsChannelDetailInternal.setPreferredSize(new Dimension(10, 100));
		NewsChannelDetailInternal.setMaximumSize(new Dimension(32767, 100));
		NewsChannelDetailInternal.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "News channel details:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		NewsChannelDetail.add(NewsChannelDetailInternal);
		
		Component horizontalStrut_14 = Box.createHorizontalStrut(10);
		horizontalStrut_14.setMaximumSize(new Dimension(10, 0));
		NewsChannelDetail.add(horizontalStrut_14);
		
		Component verticalStrut_14 = Box.createVerticalStrut(10);
		MainPanel.add(verticalStrut_14);}
	
	
	public MainJFrame(User u) 
	{
		this();
		
	}
}
