package ap2016.gui.frames;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Paths;
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
import ap2016.io.DataProvider;
import ap2016.io.NewsChannelDataProvider;
import ap2016.io.UserDataProvider;


public class MainJFrame extends JFrame
{

	/**
	 * Serial version UUID.
	 */
	private static final long serialVersionUID = 7429232711575561885L;

	@SuppressWarnings("unused")
	private boolean isRTTIUsed = false;

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

	/**
	 * Constructor used by the GUI builder.
	 */
	public MainJFrame()
	{
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent arg0)
			{
				NewsChannelDataProvider.getInstance().saveDataToFile();
				UserDataProvider.getInstance().saveDataToFile();
			}
		});
		setSize(new Dimension(1200, 649));
		setPreferredSize(new Dimension(1000, 649));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("NewsFeed");
		getContentPane().setLayout(new BorderLayout(0, 0));

		this.DrawerPanel = new JPanel();
		getContentPane().add(this.DrawerPanel, BorderLayout.WEST);
		this.DrawerPanel.setLayout(new BorderLayout(5, 0));

		JPanel DrawerPanelInternal = new JPanel();
		DrawerPanelInternal.setSize(new Dimension(270, 0));
		DrawerPanelInternal.setMaximumSize(new Dimension(270, 32767));
		DrawerPanelInternal.setPreferredSize(new Dimension(270, 200));
		DrawerPanelInternal.setMinimumSize(new Dimension(270, 200));
		DrawerPanelInternal.setBounds(new Rectangle(0, 0, 250, 0));
		this.DrawerPanel.add(DrawerPanelInternal);
		DrawerPanelInternal.setLayout(new BoxLayout(DrawerPanelInternal, BoxLayout.Y_AXIS));

		Component verticalStrut = Box.createVerticalStrut(10);
		DrawerPanelInternal.add(verticalStrut);

		JPanel UsernameDisplay = new JPanel();
		UsernameDisplay.setMaximumSize(new Dimension(32767, 97));
		UsernameDisplay.setMinimumSize(new Dimension(10, 97));
		UsernameDisplay.setPreferredSize(new Dimension(10, 97));
		DrawerPanelInternal.add(UsernameDisplay);
		UsernameDisplay.setLayout(null);

		this.avatarImg = new AvatarImageDisplay();
		this.avatarImg.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.avatarImg.setBackground(Color.WHITE);
		this.avatarImg.setBounds(10, 11, 75, 75);
		UsernameDisplay.add(this.avatarImg);
		this.avatarImg.setText("Test");
		this.avatarImg.setMinimumSize(new Dimension(50, 50));
		this.avatarImg.setPreferredSize(new Dimension(50, 50));

		this.lblUsername = new JLabel("Username");
		this.lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.lblUsername.setBounds(102, 54, 138, 32);
		UsernameDisplay.add(this.lblUsername);

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
		SelectNewsChannelInternal.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true),
				"Select news channel to show:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		SelectNewsChannel.add(SelectNewsChannelInternal);
		SelectNewsChannelInternal.setLayout(new BoxLayout(SelectNewsChannelInternal, BoxLayout.X_AXIS));

		Component horizontalStrut_12 = Box.createHorizontalStrut(10);
		horizontalStrut_12.setMaximumSize(new Dimension(10, 0));
		SelectNewsChannelInternal.add(horizontalStrut_12);

		this.cmbNewsChannel = new JComboBox<>();
		this.cmbNewsChannel.addActionListener(e -> cmbNewChannel_SelectedItemChanged());
		this.cmbNewsChannel.setMaximumSize(new Dimension(32767, 20));
		SelectNewsChannelInternal.add(this.cmbNewsChannel);

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
		QuickActionsInternal.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true),
				"Quick actions:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

		this.cmbSaveAllType = new JComboBox<>();
		this.cmbSaveAllType.setModel(new DefaultComboBoxModel<String>(new String[] { "HTML", "RTF" }));
		this.cmbSaveAllType.setSelectedIndex(0);
		this.cmbSaveAllType.setMaximumSize(new Dimension(200, 25));
		this.cmbSaveAllType.setPreferredSize(new Dimension(80, 25));
		this.cmbSaveAllType.setMinimumSize(new Dimension(40, 25));
		panel_1.add(this.cmbSaveAllType);

		Component horizontalStrut_5 = Box.createHorizontalStrut(10);
		horizontalStrut_5.setMaximumSize(new Dimension(10, 30));
		panel_1.add(horizontalStrut_5);

		this.btnExport = new JButton("Go");
		this.btnExport.setPreferredSize(new Dimension(60, 25));
		this.btnExport.addActionListener(e -> btnExport_Click());
		panel_1.add(this.btnExport);
		this.btnExport.setMinimumSize(new Dimension(60, 25));
		this.btnExport.setMaximumSize(new Dimension(60, 25));

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

		this.btnManageUsers = new JButton("Manage users");
		this.btnManageUsers.addActionListener(e -> btnManageUsers_Click());
		panel_6.add(this.btnManageUsers, BorderLayout.NORTH);

		Component verticalStrut_8 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_8);

		JPanel panel_4 = new JPanel();
		panel_4.setMaximumSize(new Dimension(32767, 25));
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		this.btnImportData = new JButton("Import data file");
		this.btnImportData.addActionListener((e) -> btnImportData_Click());
		panel_4.add(this.btnImportData);

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
		StatisticsInternal.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192)),
				"Current session's statistics:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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

		this.lblDataFilePath = new JLabel("DATA FILE PATH");
		this.lblDataFilePath.setMinimumSize(new Dimension(200, 16));
		this.lblDataFilePath.setMaximumSize(new Dimension(200, 16));
		panel_8.add(this.lblDataFilePath);

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

		this.lblDataRecordCount = new JLabel("DR");
		panel_9.add(this.lblDataRecordCount);

		Component verticalStrut_18 = Box.createVerticalStrut(5);
		panel_12.add(verticalStrut_18);

		JPanel panel_10 = new JPanel();
		panel_12.add(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.Y_AXIS));

		JLabel lblNumberOfAdded = new JLabel("Number of added records:");
		panel_10.add(lblNumberOfAdded);

		Component verticalStrut_19 = Box.createVerticalStrut(3);
		panel_10.add(verticalStrut_19);

		this.lblAddedRecords = new JLabel("AR");
		panel_10.add(this.lblAddedRecords);

		Component verticalStrut_20 = Box.createVerticalStrut(5);
		panel_12.add(verticalStrut_20);

		JPanel panel_11 = new JPanel();
		panel_12.add(panel_11);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.Y_AXIS));

		JLabel lblNumberOfDeleted = new JLabel("Number of deleted records:");
		panel_11.add(lblNumberOfDeleted);

		Component verticalStrut_21 = Box.createVerticalStrut(3);
		panel_11.add(verticalStrut_21);

		this.lblDeletedRecords = new JLabel("DR");
		panel_11.add(this.lblDeletedRecords);

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

		this.btnAbout = new JButton("About");
		this.btnAbout.addActionListener(e -> btnAbout_Click());
		About.add(this.btnAbout, BorderLayout.CENTER);

		Component verticalStrut_3 = Box.createVerticalStrut(10);
		DrawerPanelInternal.add(verticalStrut_3);

		JPanel LogoutExit = new JPanel();
		DrawerPanelInternal.add(LogoutExit);
		LogoutExit.setLayout(new BoxLayout(LogoutExit, BoxLayout.X_AXIS));

		Component rigidArea = Box.createRigidArea(new Dimension(10, 30));
		LogoutExit.add(rigidArea);

		this.btnExit = new JButton("Exit");
		this.btnExit.addActionListener(e -> btnExit_Click());
		this.btnExit.setMaximumSize(new Dimension(95, 30));
		this.btnExit.setMinimumSize(new Dimension(95, 30));
		this.btnExit.setPreferredSize(new Dimension(95, 30));
		LogoutExit.add(this.btnExit);

		Component horizontalGlue = Box.createHorizontalGlue();
		LogoutExit.add(horizontalGlue);

		this.btnLogout = new JButton("Logout");
		this.btnLogout.addActionListener(e -> btnLogout_Click());
		this.btnLogout.setMinimumSize(new Dimension(95, 30));
		this.btnLogout.setPreferredSize(new Dimension(95, 30));
		this.btnLogout.setMaximumSize(new Dimension(95, 30));
		LogoutExit.add(this.btnLogout);

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

		this.btnToggleDrawer = new JButton("«");
		this.btnToggleDrawer.addActionListener(e -> toggleDrawer());
		panel.add(this.btnToggleDrawer);
		this.btnToggleDrawer.setFont(new Font("Dialog", Font.PLAIN, 15));

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

		this.MainContentCentered = new JPanel();
		MainContent.add(this.MainContentCentered);
		this.MainContentCentered.setLayout(new BorderLayout(0, 0));

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
		NewsChannelDetailInternal.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true),
				"News channel details:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
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

		this.vecChannelTitle = new ViewEditComponent<>(new JLabel(), new ValidableTextField());
		this.vecChannelTitle.getViewComponent().setMinimumSize(new Dimension(150, 0));
		this.vecChannelTitle.getViewComponent().setPreferredSize(new Dimension(150, 0));
		this.vecChannelTitle.getViewComponent().setMaximumSize(new Dimension(150, 0));
		this.vecChannelTitle.setPreferredSize(new Dimension(300, 0));
		this.vecChannelTitle.setMaximumSize(new Dimension(300, 2147483647));
		panel_13.add(this.vecChannelTitle);

		Component horizontalStrut_24 = Box.createHorizontalStrut(20);
		horizontalStrut_24.setMaximumSize(new Dimension(20, 0));
		panel_13.add(horizontalStrut_24);

		JLabel lblLink = new JLabel("Link:");
		panel_13.add(lblLink);

		Component horizontalStrut_25 = Box.createHorizontalStrut(4);
		horizontalStrut_25.setMaximumSize(new Dimension(20, 0));
		panel_13.add(horizontalStrut_25);

		this.vecChannelLink = new ViewEditComponent<>(new JLabel(), new ValidableTextField());
		this.vecChannelLink.getViewComponent().setMinimumSize(new Dimension(150, 0));
		this.vecChannelLink.getViewComponent().setMaximumSize(new Dimension(150, 0));
		this.vecChannelLink.getViewComponent().setPreferredSize(new Dimension(150, 0));
		panel_13.add(this.vecChannelLink);

		Component horizontalStrut_26 = Box.createHorizontalStrut(20);
		horizontalStrut_26.setMaximumSize(new Dimension(20, 0));
		panel_13.add(horizontalStrut_26);

		JLabel lblLanguage = new JLabel("Language:");
		panel_13.add(lblLanguage);

		Component horizontalStrut_27 = Box.createHorizontalStrut(4);
		horizontalStrut_27.setMaximumSize(new Dimension(20, 0));
		panel_13.add(horizontalStrut_27);

		this.vecChannelLanguage = new ViewEditComponent<>(new JLabel(), new ValidableTextField());
		this.vecChannelLanguage.getViewComponent().setMinimumSize(new Dimension(40, 0));
		this.vecChannelLanguage.getViewComponent().setMaximumSize(new Dimension(40, 0));
		this.vecChannelLanguage.getViewComponent().setPreferredSize(new Dimension(40, 0));
		panel_13.add(this.vecChannelLanguage);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalGlue_1.setMinimumSize(new Dimension(30, 0));
		panel_13.add(horizontalGlue_1);

		this.btnEdit = new JButton("Edit");
		this.btnEdit.setMaximumSize(new Dimension(65, 25));
		this.btnEdit.setMinimumSize(new Dimension(65, 25));
		this.btnEdit.setPreferredSize(new Dimension(65, 25));
		this.btnEdit.addActionListener(e -> btnEdit_Click());

		Component horizontalStrut_29 = Box.createHorizontalStrut(20);
		panel_13.add(horizontalStrut_29);
		panel_13.add(this.btnEdit);

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

		this.vecChannelDescription = new ViewEditComponent<>(new JLabel(), new JTextField());
		panel_15.add(this.vecChannelDescription);

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

	/**
	 * Shows the main windows of the application.
	 * @param u The user whose permission will be used to choose which parts of the frame have to be displayed or hidden.
	 */
	public MainJFrame(User u)
	{
		this();
		this.currentUser = u;

		try
		{
			NewsChannelDataProvider.getInstance().readDataFromFile();
		} catch (Exception e)
		{
		}

		// Setting image and username
		this.avatarImg.setIcon(u.getAvatar());
		this.lblUsername.setText(u.getUsername());

		// Filling the NewsListPanel and adding it to the main frame
		this.nlp = new NewsListPanel(this.currentUser);
		this.MainContentCentered.add(this.nlp);

		fillNewsChannel(0);

		// Updating the statistics label
		this.lblDataFilePath.setText(Paths.get(ApplicationConstants.dataBase, "data.xml").toString());
		this.lblDataFilePath.setToolTipText(Paths.get(ApplicationConstants.dataBase, "data.xml").toString());

		Integer i = 0;
		for (NewsChannel nc : NewsChannelDataProvider.getInstance().getData())
		{
			i += nc.getNews().size();
		}
		this.lblDataRecordCount.setText(i + "");

		this.lblAddedRecords.setText("0");
		this.nlp.setOnDataAddedListener(
				(k) -> this.lblAddedRecords.setText(Integer.parseInt(this.lblAddedRecords.getText()) + k + ""));

		this.lblDeletedRecords.setText("0");
		this.nlp.setOnDataRemovedListener(
				(k) -> this.lblDeletedRecords.setText(Integer.parseInt(this.lblDeletedRecords.getText()) + k + ""));

		this.vecChannelTitle.getEditComponent().updateValidationTest(s -> (!s.equals("")));
		this.vecChannelTitle.setViewToEditOperation((v, e) -> e.setText(v.getText()));
		this.vecChannelTitle.setEditToViewOperation((v, e) -> v.setText(e.getText()));

		this.vecChannelLink.getEditComponent().updateValidationTest(ApplicationUtilities::isValidURL);
		this.vecChannelLink.setViewToEditOperation((v, e) -> e.setText(v.getText()));
		this.vecChannelLink.setEditToViewOperation((v, e) -> v.setText(e.getText()));

		this.vecChannelLanguage.getEditComponent().updateValidationTest(ApplicationUtilities::isValidLanguage);
		this.vecChannelLanguage.setViewToEditOperation((v, e) -> e.setText(v.getText()));
		this.vecChannelLanguage.setEditToViewOperation((v, e) -> v.setText(e.getText()));

		this.vecChannelDescription.setViewToEditOperation((v, e) -> e.setText(v.getText()));
		this.vecChannelDescription.setEditToViewOperation((v, e) -> v.setText(e.getText()));

		updatePermissions();

		if ((this instanceof MainJFrame) && (UserDataProvider.getInstance() instanceof DataProvider))
		{
			this.isRTTIUsed = true;
		}

	}

	/**
	 * Shows some information about the developer and the application.
	 */
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

		classNames.add("ap2016.exceptions.InvalidAvatarNameException");
		classNames.add("ap2016.exceptions.InvalidLanguageStringException");
		classNames.add("ap2016.exceptions.InvalidPasswordException");
		classNames.add("ap2016.exceptions.InvalidURLException");
		classNames.add("ap2016.exceptions.InvalidUsernameException");

		classNames.add("ap2016.gui.frames.ExportJDialog");
		classNames.add("ap2016.gui.frames.LoginJFrame");
		classNames.add("ap2016.gui.frames.MainJFrame");
		classNames.add("ap2016.gui.frames.ManageUsersJDialog");
		classNames.add("ap2016.gui.frames.RegisterJFrame");

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
			try
			{
				parseClass(values, Class.forName(s));
			} catch (ClassNotFoundException ex)
			{
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
		ans.append(" normal methods;\n");

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
			try
			{
				parseClass(values, Class.forName(s));
			} catch (ClassNotFoundException ex)
			{
				JOptionPane.showMessageDialog(this, s + " doesn't exist");
			}
		});

		ans.append("\t => ");
		ans.append(values.get("numberOfClasses"));
		ans.append(" test classes;\n");

		ans.append("\t => ");
		ans.append(values.get("numberOfMethods"));
		ans.append(" test methods.\n");

		ans.append("\t => ");
		ans.append(3914);
		ans.append(" lines of normal code.\n");

		ans.append("\t => ");
		ans.append(1020);
		ans.append(" lines of test code.\n");

		ans.append("\n");

		JOptionPane.showMessageDialog(this, ans.toString());
	}

	/**
	 * Allows to edit the current news channel's details.
	 */
	private void btnEdit_Click()
	{
		if (this.btnEdit.getText().equals("Edit"))
		{
			this.vecChannelTitle.setEditState();
			this.vecChannelLink.setEditState();
			this.vecChannelLanguage.setEditState();
			this.vecChannelDescription.setEditState();

			this.btnEdit.setText("Save");

		} else
		{

			if (!this.vecChannelTitle.getEditComponent().isValid())
			{
				JOptionPane.showMessageDialog(this, "The new title must not null");
				return;
			}
			if (!this.vecChannelLink.getEditComponent().isValid())
			{
				JOptionPane.showMessageDialog(this, "The new link must be a valid URL");
				return;
			}
			if (!this.vecChannelLanguage.getEditComponent().isValid())
			{
				JOptionPane.showMessageDialog(this, "The new language must be a valid language (es: en-US)");
				return;
			}

			this.currentNewsChannel.setTitle(this.vecChannelTitle.getEditComponent().getText());
			this.currentNewsChannel.setLink(this.vecChannelLink.getEditComponent().getText());
			this.currentNewsChannel.setLanguage(this.vecChannelLanguage.getEditComponent().getText());
			this.currentNewsChannel.setDescription(this.vecChannelDescription.getEditComponent().getText());

			this.vecChannelTitle.setViewState();
			this.vecChannelLink.setViewState();
			this.vecChannelLanguage.setViewState();
			this.vecChannelDescription.setViewState();

			this.cmbNewsChannel.repaint();

			this.btnEdit.setText("Edit");

		}
	}

	/**
	 * Exits from the application.
	 */
	private void btnExit_Click()
	{
		NewsChannelDataProvider.getInstance().saveDataToFile();
		UserDataProvider.getInstance().saveDataToFile();
		setVisible(false);
		dispose();
	}

	/**
	 * Shows the window that allows the user to export the news channel in the choosen format.
	 */
	private void btnExport_Click()
	{
		JDialog a = new ExportJDialog(this, (String) this.cmbSaveAllType.getSelectedItem());
		a.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		a.pack();
		a.setLocationRelativeTo(null);
		a.setVisible(true);
	}

	/**
	 * Allows the user to import a data file into the application.
	 */
	private void btnImportData_Click()
	{
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			File file = fc.getSelectedFile();

			try
			{
				int last = this.cmbNewsChannel.getSelectedIndex();
				NewsChannelDataProvider.getInstance().readDataFromSelectedFile(file);
				fillNewsChannel(last > 0 ? last : 0);
				Integer i = 0;
				for (NewsChannel nc : NewsChannelDataProvider.getInstance().getData())
				{
					i += nc.getNews().size();
				}
				this.lblDataRecordCount.setText(i + "");

			} catch (Exception ex)
			{
				JOptionPane.showMessageDialog(this, "The application was not able to import the selected file.");
			}

		}
	}

	/**
	 * Logs-out from the application an shows again the login screen.
	 */
	private void btnLogout_Click()
	{
		JFrame a = new LoginJFrame();
		a.pack();
		a.setLocationRelativeTo(null);
		btnExit_Click();
		a.setVisible(true);
	}

	/**
	 * Shows the window that allows the user to manage its own account and, if allowed, the permission granted to the other users.
	 */
	private void btnManageUsers_Click()
	{
		JDialog a = new ManageUsersJDialog(this, this.currentUser);
		a.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		a.pack();
		a.setLocationRelativeTo(null);
		a.setVisible(true);

		UserDataProvider.getInstance().saveDataToFile();

		this.avatarImg.setIcon(this.currentUser.getAvatar());
		this.lblUsername.setText(this.currentUser.getUsername());

		updatePermissions();
	}

	/**
	 * Updates the GUI to reflect the the currently selected news channel.
	 */
	private void cmbNewChannel_SelectedItemChanged()
	{
		this.currentNewsChannel = (NewsChannel) this.cmbNewsChannel.getSelectedItem();
		this.nlp.updateCurrentNewsChannel(this.currentNewsChannel);

		if (this.currentNewsChannel != null)
		{
			// Updating channel details
			this.vecChannelTitle.getViewComponent().setText(this.currentNewsChannel.getTitle());
			this.vecChannelLink.getViewComponent().setText(this.currentNewsChannel.getLink());
			this.vecChannelLanguage.getViewComponent().setText(this.currentNewsChannel.getLanguage());
			this.vecChannelDescription.getViewComponent().setText(this.currentNewsChannel.getDescription());

		}
	}

	/**
	 * Fills the news channel combobox.
	 * @param defaultChannel The index of the element to be selected at the end of the refilling. If the element at this index is not present, then the first item is selected. If there are no item, no item is selected.
	 */
	private void fillNewsChannel(int defaultChannel)
	{
		this.cmbNewsChannel.removeAllItems();
		// Populating cmbNewsChannel and selecting the first news channel
		NewsChannelDataProvider.getInstance().getData().forEach(nc -> this.cmbNewsChannel.addItem(nc));
		if (this.cmbNewsChannel.getItemCount() > defaultChannel)
		{
			this.cmbNewsChannel.setSelectedIndex(defaultChannel);
		} else
		{
			if (this.cmbNewsChannel.getItemCount() > 0)
			{
				this.cmbNewsChannel.setSelectedIndex(0);
			}
		}
	}

	/**
	 * Parses a class object and updates the total count.
	 * @param values The map containing the total counts.
	 * @param c The class objects to be parsed.
	 */
	private void parseClass(HashMap<String, Integer> values, Class<? extends Object> c)
	{
		values.replace("numberOfClasses", values.get("numberOfClasses") + 1);
		values.replace("numberOfMethods",
				values.get("numberOfMethods") + (c.getMethods().length - c.getSuperclass().getMethods().length));
	}

	/**
	 * Toggles the drawer panel on the left.
	 */
	private void toggleDrawer()
	{
		this.DrawerPanel.setVisible(!this.DrawerPanel.isVisible());
		this.btnToggleDrawer.setText(this.btnToggleDrawer.getText().equals("«") ? "»" : "«");
	}

	/**
	 * Shows or hides the elements of the GUI according to the {@code currentUser}'s permissions.
	 */
	private void updatePermissions()
	{
		if (this.currentUser == null)
		{
			return;
		}

		if (this.currentUser.hasRole(Role.IMPORT_NEWS_FROM_FILE))
		{
			this.btnImportData.setVisible(true);
			this.btnImportData.setEnabled(true);
		} else
		{
			this.btnImportData.setVisible(false);
			this.btnImportData.setEnabled(false);
		}

		this.nlp.updatePermissions();
	}

}
