package ap2016.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import ap2016.application.ApplicationConstants;
import ap2016.application.ApplicationUtilities;
import ap2016.entities.NewsChannel;
import ap2016.entities.Role;
import ap2016.entities.User;
import ap2016.gui.panels.NewsListPanel;
import ap2016.gui.utilities.AvatarImageDisplay;
import ap2016.gui.utilities.ValidableTextField;
import ap2016.gui.utilities.ViewEditComponent;
import ap2016.io.NewsChannelDataProvider;
import ap2016.io.UserDataProvider;

@SuppressWarnings("serial")
public class MainJFrame extends JFrame
{	
	private User currentUser;
	private NewsChannel currentNewsChannel;
	
	
	
	private AvatarImageDisplay avatarImg;
	private JLabel lblUsername;
	private JComboBox<NewsChannel> cmbNewsChannel;
	private JComboBox<String> cmbSaveAllType;
	private JButton btnExport;
	private JButton btnManageUsers;
	private JButton btnImportData;
	private JButton btnAbout;
	private JButton btnExit;
	private JButton btnLogout;
	private JButton btnToggleDrawer;
	private JPanel DrawerPanel;
	private JLabel lblDataFilePath;
	private JPanel MainContentCentered;
	private ViewEditComponent<JLabel, ValidableTextField> vecChannelTitle;
	private ViewEditComponent<JLabel, ValidableTextField> vecChannelLink;
	private ViewEditComponent<JLabel, ValidableTextField> vecChannelLanguage;
	private JButton btnEdit;
	private ViewEditComponent<JLabel, JTextField> vecChannelDescription;
	private JLabel lblDataRecordCount;
	private JLabel lblAddedRecords;
	private JLabel lblDeletedRecords;
	private NewsListPanel nlp;
	
	public MainJFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
					NewsChannelDataProvider.getInstance().saveDataToFile();
					UserDataProvider.getInstance().saveDataToFile();
			}
		});
		setSize(new Dimension(1000, 649));
		setPreferredSize(new Dimension(1000, 649));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("NewsFeed");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		DrawerPanel = new JPanel();
		getContentPane().add(DrawerPanel, BorderLayout.WEST);
		DrawerPanel.setLayout(new BorderLayout(5, 0));
		
		JPanel DrawerPanelInternal = new JPanel();
		DrawerPanelInternal.setSize(new Dimension(250, 0));
		DrawerPanelInternal.setMaximumSize(new Dimension(250, 32767));
		DrawerPanelInternal.setPreferredSize(new Dimension(250, 200));
		DrawerPanelInternal.setMinimumSize(new Dimension(250, 200));
		DrawerPanelInternal.setBounds(new Rectangle(0, 0, 250, 0));
		DrawerPanel.add(DrawerPanelInternal);
		DrawerPanelInternal.setLayout(new BoxLayout(DrawerPanelInternal, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(10);
		DrawerPanelInternal.add(verticalStrut);
		
		JPanel UsernameDisplay = new JPanel();
		UsernameDisplay.setMaximumSize(new Dimension(32767, 97));
		UsernameDisplay.setMinimumSize(new Dimension(10, 97));
		UsernameDisplay.setPreferredSize(new Dimension(10, 97));
		DrawerPanelInternal.add(UsernameDisplay);
		UsernameDisplay.setLayout(null);
		
		avatarImg = new AvatarImageDisplay();
		avatarImg.setBorder(new LineBorder(new Color(0, 0, 0)));
		avatarImg.setBackground(Color.WHITE);
		avatarImg.setBounds(10, 11, 75, 75);
		UsernameDisplay.add(avatarImg);
		avatarImg.setText("Test");
		avatarImg.setMinimumSize(new Dimension(50, 50));
		avatarImg.setPreferredSize(new Dimension(50, 50));
		
		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(102, 54, 138, 32);
		UsernameDisplay.add(lblUsername);
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		DrawerPanelInternal.add(verticalStrut_1);
		
		JPanel SelectNewsChannel = new JPanel();
		SelectNewsChannel.setSize(new Dimension(0, 80));
		SelectNewsChannel.setPreferredSize(new Dimension(10, 50));
		SelectNewsChannel.setMinimumSize(new Dimension(10, 50));
		SelectNewsChannel.setMaximumSize(new Dimension(32767, 50));
		DrawerPanelInternal.add(SelectNewsChannel);
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
		
		cmbNewsChannel = new JComboBox<>();
		cmbNewsChannel.addActionListener(e -> cmbNewChannel_SelectedItemChanged());
		cmbNewsChannel.setMaximumSize(new Dimension(32767, 20));
		SelectNewsChannelInternal.add(cmbNewsChannel);
		
		Component horizontalStrut_13 = Box.createHorizontalStrut(10);
		horizontalStrut_13.setMaximumSize(new Dimension(10, 0));
		SelectNewsChannelInternal.add(horizontalStrut_13);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(10);
		horizontalStrut_3.setMaximumSize(new Dimension(10, 0));
		SelectNewsChannel.add(horizontalStrut_3);
		
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		DrawerPanelInternal.add(verticalStrut_2);
		
		JPanel QuickActions = new JPanel();
		DrawerPanelInternal.add(QuickActions);
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
		
		JLabel lblNewLabel = new JLabel("Export as:");
		panel_1.add(lblNewLabel);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(10);
		horizontalStrut_4.setMaximumSize(new Dimension(10, 30));
		panel_1.add(horizontalStrut_4);
		
		cmbSaveAllType = new JComboBox<>();
		cmbSaveAllType.setModel(new DefaultComboBoxModel<String>(new String[] {"HTML", "RTF"}));
		cmbSaveAllType.setSelectedIndex(0);
		cmbSaveAllType.setMaximumSize(new Dimension(200, 25));
		cmbSaveAllType.setPreferredSize(new Dimension(80, 25));
		cmbSaveAllType.setMinimumSize(new Dimension(40, 25));
		panel_1.add(cmbSaveAllType);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(10);
		horizontalStrut_5.setMaximumSize(new Dimension(10, 30));
		panel_1.add(horizontalStrut_5);
		
		btnExport = new JButton("Go");
		btnExport.setPreferredSize(new Dimension(50, 25));
		btnExport.addActionListener(e -> btnExport_Click());
		panel_1.add(btnExport);
		btnExport.setMinimumSize(new Dimension(50, 25));
		btnExport.setMaximumSize(new Dimension(50, 25));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		JPanel panel_5 = new JPanel();
		panel_5.setMaximumSize(new Dimension(32767, 25));
		panel_2.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		Component verticalStrut_7 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_7);
		
		JPanel panel_6 = new JPanel();
		panel_6.setMaximumSize(new Dimension(32767, 25));
		panel_2.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		btnManageUsers = new JButton("Manage users");
		btnManageUsers.addActionListener(e -> btnManageUsers_Click());
		panel_6.add(btnManageUsers, BorderLayout.NORTH);
		
		Component verticalStrut_8 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_8);
		
		JPanel panel_4 = new JPanel();
		panel_4.setMaximumSize(new Dimension(32767, 25));
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		btnImportData = new JButton("Import data file");
		btnImportData.addActionListener((e) -> btnImportData_Click());
		panel_4.add(btnImportData);
		
		Component verticalStrut_10 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_10);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(10);
		horizontalStrut_6.setMaximumSize(new Dimension(10, 0));
		QuickActionsInternal.add(horizontalStrut_6);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(10);
		horizontalStrut_11.setMaximumSize(new Dimension(10, 0));
		QuickActions.add(horizontalStrut_11);
		
		Component verticalStrut_5 = Box.createVerticalStrut(10);
		DrawerPanelInternal.add(verticalStrut_5);
		
		JPanel Statistics = new JPanel();
		DrawerPanelInternal.add(Statistics);
		Statistics.setLayout(new BoxLayout(Statistics, BoxLayout.X_AXIS));
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(10);
		horizontalStrut_8.setMaximumSize(new Dimension(10, 0));
		Statistics.add(horizontalStrut_8);
		
		JPanel StatisticsInternal = new JPanel();
		StatisticsInternal.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)), "Current session's statistics:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Statistics.add(StatisticsInternal);
		StatisticsInternal.setLayout(new BoxLayout(StatisticsInternal, BoxLayout.X_AXIS));
		
		Component horizontalStrut_20 = Box.createHorizontalStrut(10);
		horizontalStrut_20.setMaximumSize(new Dimension(10, 0));
		StatisticsInternal.add(horizontalStrut_20);
		
		JPanel panel_12 = new JPanel();
		StatisticsInternal.add(panel_12);
		panel_12.setLayout(new BoxLayout(panel_12, BoxLayout.Y_AXIS));
		
		Component verticalStrut_9 = Box.createVerticalStrut(5);
		verticalStrut_9.setMaximumSize(new Dimension(0, 10));
		panel_12.add(verticalStrut_9);
		
		JPanel panel_8 = new JPanel();
		panel_8.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_12.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		JLabel lblDataFilePathText = new JLabel("Data file path:");
		panel_8.add(lblDataFilePathText);
		
		Component verticalStrut_15 = Box.createVerticalStrut(3);
		panel_8.add(verticalStrut_15);
		
		lblDataFilePath = new JLabel("DATA FILE PATH");
		lblDataFilePath.setMinimumSize(new Dimension(200, 16));
		lblDataFilePath.setMaximumSize(new Dimension(200, 16));
		panel_8.add(lblDataFilePath);
		
		Component verticalStrut_16 = Box.createVerticalStrut(5);
		verticalStrut_16.setMaximumSize(new Dimension(0, 10));
		panel_12.add(verticalStrut_16);
		
		JPanel panel_9 = new JPanel();
		panel_12.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
		
		JLabel lblNumberOfData = new JLabel("Number of data records:");
		panel_9.add(lblNumberOfData);
		
		Component verticalStrut_17 = Box.createVerticalStrut(3);
		panel_9.add(verticalStrut_17);
		
		lblDataRecordCount = new JLabel("DR");
		panel_9.add(lblDataRecordCount);
		
		Component verticalStrut_18 = Box.createVerticalStrut(5);
		panel_12.add(verticalStrut_18);
		
		JPanel panel_10 = new JPanel();
		panel_12.add(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.Y_AXIS));
		
		JLabel lblNumberOfAdded = new JLabel("Number of added records:");
		panel_10.add(lblNumberOfAdded);
		
		Component verticalStrut_19 = Box.createVerticalStrut(3);
		panel_10.add(verticalStrut_19);
		
		lblAddedRecords = new JLabel("AR");
		panel_10.add(lblAddedRecords);
		
		Component verticalStrut_20 = Box.createVerticalStrut(5);
		panel_12.add(verticalStrut_20);
		
		JPanel panel_11 = new JPanel();
		panel_12.add(panel_11);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.Y_AXIS));
		
		JLabel lblNumberOfDeleted = new JLabel("Number of deleted records:");
		panel_11.add(lblNumberOfDeleted);
		
		Component verticalStrut_21 = Box.createVerticalStrut(3);
		panel_11.add(verticalStrut_21);
		
		lblDeletedRecords = new JLabel("DR");
		panel_11.add(lblDeletedRecords);
		
		Component horizontalStrut_21 = Box.createHorizontalStrut(10);
		horizontalStrut_21.setMaximumSize(new Dimension(10, 0));
		StatisticsInternal.add(horizontalStrut_21);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(10);
		horizontalStrut_9.setMaximumSize(new Dimension(10, 0));
		Statistics.add(horizontalStrut_9);
		
		Component verticalGlue = Box.createVerticalGlue();
		DrawerPanelInternal.add(verticalGlue);
		
		JPanel About = new JPanel();
		About.setPreferredSize(new Dimension(10, 30));
		About.setMaximumSize(new Dimension(32767, 30));
		DrawerPanelInternal.add(About);
		About.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut_16 = Box.createHorizontalStrut(10);
		About.add(horizontalStrut_16, BorderLayout.WEST);
		
		Component horizontalStrut_17 = Box.createHorizontalStrut(10);
		About.add(horizontalStrut_17, BorderLayout.EAST);
		
		btnAbout = new JButton("About");
		btnAbout.addActionListener(e -> btnAbout_Click());
		About.add(btnAbout, BorderLayout.CENTER);
		
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		DrawerPanelInternal.add(verticalStrut_3);
		
		JPanel LogoutExit = new JPanel();
		DrawerPanelInternal.add(LogoutExit);
		LogoutExit.setLayout(new BoxLayout(LogoutExit, BoxLayout.X_AXIS));
		
		Component rigidArea = Box.createRigidArea(new Dimension(10, 30));
		LogoutExit.add(rigidArea);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(e -> btnExit_Click());
		btnExit.setMaximumSize(new Dimension(95, 30));
		btnExit.setMinimumSize(new Dimension(95, 30));
		btnExit.setPreferredSize(new Dimension(95, 30));
		LogoutExit.add(btnExit);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		LogoutExit.add(horizontalGlue);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(e -> btnLogout_Click());
		btnLogout.setMinimumSize(new Dimension(95, 30));
		btnLogout.setPreferredSize(new Dimension(95, 30));
		btnLogout.setMaximumSize(new Dimension(95, 30));
		LogoutExit.add(btnLogout);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(10, 30));
		LogoutExit.add(rigidArea_1);
		
		Component verticalStrut_4 = Box.createVerticalStrut(13);
		DrawerPanelInternal.add(verticalStrut_4);
		
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
		
		JPanel panel = new JPanel();
		Title.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		btnToggleDrawer = new JButton("«");
		btnToggleDrawer.addActionListener(e -> toggleDrawer());
		panel.add(btnToggleDrawer);
		btnToggleDrawer.setFont(new Font("Dialog", Font.PLAIN, 15));
		
		Component horizontalStrut_18 = Box.createHorizontalStrut(10);
		panel.add(horizontalStrut_18, BorderLayout.WEST);
		
		JPanel panel_7 = new JPanel();
		Title.add(panel_7, BorderLayout.EAST);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut_19 = Box.createHorizontalStrut(51);
		panel_7.add(horizontalStrut_19);
		
		JPanel MainContentPanel = new JPanel();
		MainPanel.add(MainContentPanel);
		MainContentPanel.setLayout(new BoxLayout(MainContentPanel, BoxLayout.Y_AXIS));
		
		Component verticalStrut_12 = Box.createVerticalStrut(10);
		MainContentPanel.add(verticalStrut_12);
		
		JPanel MainContent = new JPanel();
		MainContentPanel.add(MainContent);
		MainContent.setLayout(new BoxLayout(MainContent, BoxLayout.X_AXIS));
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		MainContent.add(horizontalStrut);
		
		MainContentCentered = new JPanel();
		MainContent.add(MainContentCentered);
		MainContentCentered.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		MainContent.add(horizontalStrut_1);
		
		Component verticalStrut_13 = Box.createVerticalStrut(10);
		MainContentPanel.add(verticalStrut_13);
		
		JPanel NewsChannelDetail = new JPanel();
		MainPanel.add(NewsChannelDetail);
		NewsChannelDetail.setLayout(new BoxLayout(NewsChannelDetail, BoxLayout.X_AXIS));
		
		Component horizontalStrut_15 = Box.createHorizontalStrut(10);
		horizontalStrut_15.setMaximumSize(new Dimension(10, 0));
		NewsChannelDetail.add(horizontalStrut_15);
		
		JPanel NewsChannelDetailInternal = new JPanel();
		NewsChannelDetailInternal.setPreferredSize(new Dimension(10, 150));
		NewsChannelDetailInternal.setMaximumSize(new Dimension(32767, 150));
		NewsChannelDetailInternal.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "News channel details:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		NewsChannelDetail.add(NewsChannelDetailInternal);
		NewsChannelDetailInternal.setLayout(new BoxLayout(NewsChannelDetailInternal, BoxLayout.Y_AXIS));
		
		Component verticalStrut_22 = Box.createVerticalStrut(10);
		NewsChannelDetailInternal.add(verticalStrut_22);
		
		JPanel panel_13 = new JPanel();
		panel_13.setPreferredSize(new Dimension(10, 25));
		panel_13.setMaximumSize(new Dimension(32767, 25));
		NewsChannelDetailInternal.add(panel_13);
		panel_13.setLayout(new BoxLayout(panel_13, BoxLayout.X_AXIS));
		
		Component horizontalStrut_22 = Box.createHorizontalStrut(10);
		horizontalStrut_22.setMaximumSize(new Dimension(20, 0));
		panel_13.add(horizontalStrut_22);
		
		JLabel lblTitle = new JLabel("Title:");
		panel_13.add(lblTitle);
		
		Component horizontalStrut_23 = Box.createHorizontalStrut(4);
		horizontalStrut_23.setMaximumSize(new Dimension(20, 0));
		panel_13.add(horizontalStrut_23);
		
		vecChannelTitle = new ViewEditComponent<>(new JLabel(), new ValidableTextField());
		panel_13.add(vecChannelTitle);
		
		Component horizontalStrut_24 = Box.createHorizontalStrut(20);
		horizontalStrut_24.setMaximumSize(new Dimension(20, 0));
		panel_13.add(horizontalStrut_24);
		
		JLabel lblLink = new JLabel("Link:");
		panel_13.add(lblLink);
		
		Component horizontalStrut_25 = Box.createHorizontalStrut(4);
		horizontalStrut_25.setMaximumSize(new Dimension(20, 0));
		panel_13.add(horizontalStrut_25);
		
		vecChannelLink = new ViewEditComponent<>(new JLabel(), new ValidableTextField());
		panel_13.add(vecChannelLink);
		
		Component horizontalStrut_26 = Box.createHorizontalStrut(20);
		horizontalStrut_26.setMaximumSize(new Dimension(20, 0));
		panel_13.add(horizontalStrut_26);
		
		JLabel lblLanguage = new JLabel("Language:");
		panel_13.add(lblLanguage);
		
		Component horizontalStrut_27 = Box.createHorizontalStrut(4);
		horizontalStrut_27.setMaximumSize(new Dimension(20, 0));
		panel_13.add(horizontalStrut_27);
		
		vecChannelLanguage = new ViewEditComponent<>(new JLabel(), new ValidableTextField());
		panel_13.add(vecChannelLanguage);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalGlue_1.setMinimumSize(new Dimension(30, 0));
		panel_13.add(horizontalGlue_1);
		
		btnEdit = new JButton("Edit");
		btnEdit.setMaximumSize(new Dimension(65, 25));
		btnEdit.setMinimumSize(new Dimension(65, 25));
		btnEdit.setPreferredSize(new Dimension(65, 25));
		btnEdit.addActionListener(e -> btnEdit_Click(e));
		
		Component horizontalStrut_29 = Box.createHorizontalStrut(20);
		panel_13.add(horizontalStrut_29);
		panel_13.add(btnEdit);
		
		Component horizontalStrut_28 = Box.createHorizontalStrut(10);
		horizontalStrut_28.setMaximumSize(new Dimension(20, 0));
		panel_13.add(horizontalStrut_28);
		
		Component verticalStrut_23 = Box.createVerticalStrut(5);
		NewsChannelDetailInternal.add(verticalStrut_23);
		
		JPanel panel_14 = new JPanel();
		NewsChannelDetailInternal.add(panel_14);
		panel_14.setLayout(new BoxLayout(panel_14, BoxLayout.X_AXIS));
		
		Component horizontalStrut_30 = Box.createHorizontalStrut(10);
		horizontalStrut_30.setMaximumSize(new Dimension(20, 0));
		panel_14.add(horizontalStrut_30);
		
		JPanel panel_15 = new JPanel();
		panel_14.add(panel_15);
		panel_15.setLayout(new BoxLayout(panel_15, BoxLayout.Y_AXIS));
		
		JPanel panel_16 = new JPanel();
		panel_16.setMaximumSize(new Dimension(32767, 25));
		panel_15.add(panel_16);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Description:");
		panel_16.add(lblNewLabel_1);
		
		Component verticalStrut_26 = Box.createVerticalStrut(4);
		panel_15.add(verticalStrut_26);
		
		vecChannelDescription = new ViewEditComponent<>(new JLabel(), new JTextField());
		panel_15.add(vecChannelDescription);
		
		Component horizontalStrut_31 = Box.createHorizontalStrut(10);
		horizontalStrut_31.setMaximumSize(new Dimension(10, 0));
		panel_14.add(horizontalStrut_31);
		
		Component verticalStrut_24 = Box.createVerticalStrut(10);
		NewsChannelDetailInternal.add(verticalStrut_24);
		
		Component horizontalStrut_14 = Box.createHorizontalStrut(10);
		horizontalStrut_14.setMaximumSize(new Dimension(10, 0));
		NewsChannelDetail.add(horizontalStrut_14);
		
		Component verticalStrut_14 = Box.createVerticalStrut(10);
		MainPanel.add(verticalStrut_14);
	}
	
	public MainJFrame(User u) 
	{
		this();
		this.currentUser = u;
		
		try
		{
			NewsChannelDataProvider.getInstance().readDataFromFile();
		} catch (Exception e){}
		
		// Setting image and username
		avatarImg.setIcon(u.getAvatar());
		lblUsername.setText(u.getUsername());
		
		// Filling the NewsListPanel and adding it to the main frame
		nlp = new NewsListPanel(currentUser);
		MainContentCentered.add(nlp);
		
		fillNewsChannel(0);
		
		// Updating the statistics label
		lblDataFilePath.setText((new File(ApplicationConstants.dataBase + "\\data.xml")).getAbsolutePath());
		lblDataFilePath.setToolTipText(new File(ApplicationConstants.dataBase + "\\data.xml").getAbsolutePath());
		
		Integer i = 0;
		for (NewsChannel nc : NewsChannelDataProvider.getInstance().getData())
		{
			i += nc.getNews().size();
		}
		lblDataRecordCount.setText(i + "");
		
		lblAddedRecords.setText("0");
		nlp.setOnDataAddedListener((k) -> lblAddedRecords.setText(Integer.parseInt(lblAddedRecords.getText()) + k + ""));
		
		lblDeletedRecords.setText("0");
		nlp.setOnDataRemovedListener((k) -> lblDeletedRecords.setText(Integer.parseInt(lblDeletedRecords.getText()) + k + ""));
		
		
		vecChannelTitle.getEditComponent().updateValidationTest(s -> (!s.equals("")));
		vecChannelTitle.setViewToEditOperation((v,e) -> e.setText(v.getText()));
		vecChannelTitle.setEditToViewOperation((v,e) -> v.setText(e.getText()));
		
		vecChannelLink.getEditComponent().updateValidationTest(ApplicationUtilities::isValidURL);
		vecChannelLink.setViewToEditOperation((v,e) -> e.setText(v.getText()));
		vecChannelLink.setEditToViewOperation((v,e) -> v.setText(e.getText()));
		
		vecChannelLanguage.getEditComponent().updateValidationTest(ApplicationUtilities::isValidLanguage);
		vecChannelLanguage.setViewToEditOperation((v,e) -> e.setText(v.getText()));
		vecChannelLanguage.setEditToViewOperation((v,e) -> v.setText(e.getText()));
		
		vecChannelDescription.setViewToEditOperation((v,e) -> e.setText(v.getText()));
		vecChannelDescription.setEditToViewOperation((v,e) -> v.setText(e.getText()));
		
		updatePermissions();
		
	}
	
	private void fillNewsChannel(int defaultChannel)
	{
		cmbNewsChannel.removeAllItems();
		// Populating cmbNewsChannel and selecting the first news channel
		NewsChannelDataProvider.getInstance().getData().forEach(nc -> cmbNewsChannel.addItem(nc));
		if (cmbNewsChannel.getItemCount() > defaultChannel)
		{
			cmbNewsChannel.setSelectedIndex(defaultChannel);
		}else{
			if (cmbNewsChannel.getItemCount() > 0)
				cmbNewsChannel.setSelectedIndex(0);
		}
	}
	
	private void cmbNewChannel_SelectedItemChanged()
	{
		currentNewsChannel = (NewsChannel)cmbNewsChannel.getSelectedItem();
		nlp.updateCurrentNewsChannel(currentNewsChannel);
		
		if (currentNewsChannel != null)
		{
			// Updating channel details
			vecChannelTitle.getViewComponent().setText(currentNewsChannel.getTitle());		
			vecChannelLink.getViewComponent().setText(currentNewsChannel.getLink());		
			vecChannelLanguage.getViewComponent().setText(currentNewsChannel.getLanguage());
			vecChannelDescription.getViewComponent().setText(currentNewsChannel.getDescription());
			
		}
	}
	
	private void updatePermissions()
	{
		if (currentUser == null)
			return;
		
		if (currentUser.hasRole(Role.IMPORT_NEWS_FROM_FILE))
		{
			btnImportData.setVisible(true);
			btnImportData.setEnabled(true);
		}else{
			btnImportData.setVisible(false);
			btnImportData.setEnabled(false);
		}
		
		nlp.updatePermissions();
	}
	
	
	
	
	
	
	
	
	
	private void toggleDrawer()
	{
		DrawerPanel.setVisible(!DrawerPanel.isVisible());
		btnToggleDrawer.setText(btnToggleDrawer.getText().equals("«") ? "»" : "«");
	}
	
	private void btnExit_Click()
	{
		this.setVisible(false);
		this.dispose();
	}
	
	private void btnLogout_Click()
	{
		JFrame a = new LoginJFrame();
		a.pack();
		a.setLocationRelativeTo(null);
		btnExit_Click();
		a.setVisible(true);
	}
	
	private void btnEdit_Click(ActionEvent e)
	{
		if (btnEdit.getText().equals("Edit"))
		{
			vecChannelTitle.setEditState();
			vecChannelLink.setEditState();
			vecChannelLanguage.setEditState();
			vecChannelDescription.setEditState();
			
			btnEdit.setText("Save");
			
		}else{
			
			if (!vecChannelTitle.getEditComponent().isValid())
			{
				JOptionPane.showMessageDialog(this, "The new title must not null");
				return;
			}
			if (!vecChannelLink.getEditComponent().isValid())
			{
				JOptionPane.showMessageDialog(this, "The new link must be a valid URL");
				return;
			}
			if (!vecChannelLanguage.getEditComponent().isValid())
			{
				JOptionPane.showMessageDialog(this, "The new language must be a valid language (es: en-US)");
				return;
			}
			
			currentNewsChannel.setTitle(vecChannelTitle.getEditComponent().getText());
			currentNewsChannel.setLink(vecChannelLink.getEditComponent().getText());
			currentNewsChannel.setLanguage(vecChannelLanguage.getEditComponent().getText());
			currentNewsChannel.setDescription(vecChannelDescription.getEditComponent().getText());
			
			vecChannelTitle.setViewState();
			vecChannelLink.setViewState();
			vecChannelLanguage.setViewState();
			vecChannelDescription.setViewState();
			
			this.cmbNewsChannel.repaint();
			
			btnEdit.setText("Edit");
			
		}
	}

	private void btnImportData_Click()
	{
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            
            try
            {
            	int last = cmbNewsChannel.getSelectedIndex();
            	NewsChannelDataProvider.getInstance().readDataFromSelectedFile(file);
            	fillNewsChannel(last > 0 ? last : 0);
            	Integer i = 0;
        		for (NewsChannel nc : NewsChannelDataProvider.getInstance().getData())
        		{
        			i += nc.getNews().size();
        		}
        		lblDataRecordCount.setText(i + "");
            	
            }catch (Exception ex){
            	JOptionPane.showMessageDialog(this, "The application was not able to import the selected file.");
            }
            
        }
	}
	
	
	private void btnManageUsers_Click()
	{
		JDialog a = new ManageUsersJDialog(this, currentUser);
		a.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		a.pack();
		a.setLocationRelativeTo(null);
		a.setVisible(true);
		
		UserDataProvider.getInstance().saveDataToFile();
		
		avatarImg.setIcon(currentUser.getAvatar());
		lblUsername.setText(currentUser.getUsername());
		
		updatePermissions();
	}
	
    private void btnExport_Click()
    {
    	JDialog a = new ExportJDialog(this, (String)cmbSaveAllType.getSelectedItem());
		a.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		a.pack();
		a.setLocationRelativeTo(null);
		a.setVisible(true);
    }
	
	
	private void btnAbout_Click()
	{
		StringBuilder ans = new StringBuilder();
		
		HashMap<String, Integer> values = new HashMap<>();
		values.put("numberOfClasses", 0);
		values.put("numberOfMethods", 0);
		
		ArrayList<String> classNames = new ArrayList<>();
		classNames.add("ap2016.application.ApplicationConstants");
		classNames.add("ap2016.application.ApplicationUtilities");
		
		classNames.add("ap2016.entities.News");
		classNames.add("ap2016.entities.NewsChannel");
		classNames.add("ap2016.entities.Role");
		classNames.add("ap2016.entities.User");
		
		classNames.add("ap2016.gui.frames.ExportJDialog");
		classNames.add("ap2016.gui.frames.LoginJFrame");
		classNames.add("ap2016.gui.frames.MainJFrame");
		classNames.add("ap2016.gui.frames.RegisterJFrame");
		
		classNames.add("ap2016.gui.panels.NewsDetailsPanel");
		classNames.add("ap2016.gui.panels.NewsExtractDisplayPanel");
		classNames.add("ap2016.gui.panels.NewsListPanel");
		
		classNames.add("ap2016.gui.utilities.AvatarImageDisplay");
		classNames.add("ap2016.gui.utilities.Main");
		classNames.add("ap2016.gui.utilities.NewsChannelCheckboxListCellRenderer");
		classNames.add("ap2016.gui.utilities.RoleCheckboxListCellRenderer");
		classNames.add("ap2016.gui.utilities.ValidablePasswordField");
		classNames.add("ap2016.gui.utilities.ValidableTextField");
		classNames.add("ap2016.gui.utilities.ViewEditComponent");
		
		classNames.add("ap2016.io.DataProvider");
		classNames.add("ap2016.io.UserDataProvider");
		classNames.add("ap2016.io.NewsChannelDataProvider");
		
		
		classNames.forEach((s) -> {
			try{
				parseClass(values, Class.forName(s));
			}catch(ClassNotFoundException ex){
				JOptionPane.showMessageDialog(this, s + " doesn't exist");
			}
		});
		
		ans.append("NewsFeed, developed by Matteo Nardini \nfor the 2016 Advanced Programming exam.");
		ans.append("\n");
		ans.append("All rights reserved.\n\n");
		ans.append("The application currently uses: \n");
		
		ans.append("\t => ");
		ans.append(values.get("numberOfClasses"));
		ans.append(" normal classes;\n");
		
		ans.append("\t => ");
		ans.append(values.get("numberOfMethods"));
		ans.append(" normal methods.\n");
		
		
		classNames.clear();
		
		classNames.add("tests.ap2016.application.ApplicationUtilitiesTests");
		
		classNames.add("tests.ap2016.entities.NewsChannelTest");
		classNames.add("tests.ap2016.entities.NewsTests");
		classNames.add("tests.ap2016.entities.UserTests");
		
		classNames.add("tests.ap2016.gui.utilities.TestAvatarImageDisplay");
		classNames.add("tests.ap2016.gui.utilities.TestValidablePasswordField");
		classNames.add("tests.ap2016.gui.utilities.TestValidableTextField");
		classNames.add("tests.ap2016.gui.utilities.TestViewEditComponent");
		
		classNames.add("tests.ap2016.io.NewsChannelDataProviderTest");
		classNames.add("tests.ap2016.io.UserDataProviderTest");
		
		values.replace("numberOfClasses", 0);
		values.replace("numberOfMethods", 0);
		
		classNames.forEach((s) -> {
			try{
				parseClass(values, Class.forName(s));
			}catch(ClassNotFoundException ex){
				JOptionPane.showMessageDialog(this, s + " doesn't exist");
			}
		});
		
		ans.append("\t => ");
		ans.append(values.get("numberOfClasses"));
		ans.append(" test classes;\n");
		
		ans.append("\t => ");
		ans.append(values.get("numberOfMethods"));
		ans.append(" test methods.\n");
		
		ans.append("\n");
		
		JOptionPane.showMessageDialog(this, ans.toString());
	}
	
	private void parseClass(HashMap<String, Integer> values, Class<? extends Object> c)
	{	
		values.replace("numberOfClasses", values.get("numberOfClasses") + 1);
		values.replace("numberOfMethods", values.get("numberOfMethods") + (c.getMethods().length - c.getSuperclass().getMethods().length));
	}
	
}
