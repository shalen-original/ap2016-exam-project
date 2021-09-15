package ap2016.gui.panels;


import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import ap2016.application.ApplicationUtilities;
import ap2016.entities.News;
import ap2016.entities.NewsChannel;
import ap2016.entities.Role;
import ap2016.entities.User;
import ap2016.gui.utilities.ValidableTextField;
import ap2016.gui.utilities.ViewEditComponent;


/**
 * This panel is used to display the news list, a news' details and the new news creation panel.
 * @author Shalen
 *
 */
public class NewsListPanel extends JPanel
{
	/**
	 * Serial version UUID.
	 */
	private static final long serialVersionUID = 8374559976786153328L;
	private HashMap<String, Consumer<Integer>> listeners;
	private User currentUser;
	private NewsChannel currentNewsChannel;
	private News currentNews;
	private JScrollPane scrollPaneMain;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JButton btnAdd;
	private JPanel scrollableMainPanel;
	private JPanel pnlNewsList;
	private JPanel pnlNewsDetail;
	private JButton btnBack;
	private JButton btnEdit;
	private JScrollPane spMain;
	private JPanel pnlContentLabelPanel;
	private JButton btnRemove;
	private JPanel pnlAddNews;
	private JButton btnAbort;
	private JButton btnSave;
	private ValidableTextField vtfTitle;
	private ValidableTextField vtfAuthor;
	private ValidableTextField vtfPubDate;
	private JTextArea taContent;
	private JLabel lblNewLabel;
	private JLabel lblDescription;
	private JTextArea taDescription;
	private JLabel lblLink;
	private ValidableTextField vtfLink;
	private ViewEditComponent<JLabel, ValidableTextField> vecTitle;
	private ViewEditComponent<JLabel, ValidableTextField> vecAuthor;
	private ViewEditComponent<JLabel, ValidableTextField> vecPubDate;
	private ViewEditComponent<JLabel, JTextArea> vecContent;
	private JPanel pnlLink;
	private ValidableTextField vtfLinkEdit;
	private JPanel pnlDescription;
	private JTextField txtDescriptionEdit;

	/**
	 * Constructor used by the GUI designer.
	 */
	public NewsListPanel()
	{
		JPanel panel_1;
		Component verticalStrut_1;
		JPanel panel_2;
		Component verticalStrut_2;
		JPanel panel_3;
		Component horizontalStrut_2;
		JLabel label_1;
		Component horizontalStrut_3;
		Component horizontalGlue_3;
		JLabel label_3;
		Component horizontalStrut_4;
		Component horizontalStrut_5;
		Component verticalStrut_3;
		JPanel panel_4;
		JPanel panel_5;
		Component horizontalGlue_6;
		Component verticalStrut_5;
		Component horizontalStrut_6;
		Component horizontalStrut_7;
		JPanel panel_6;
		Component verticalStrut_4;
		JPanel panel_7;
		Component horizontalGlue_4;
		Component horizontalGlue_5;
		Component verticalStrut_6;
		JPanel panel_8;
		Component horizontalStrut_8;
		JLabel label_2;
		Component horizontalStrut_9;
		Component horizontalGlue_7;
		JLabel label_5;
		Component horizontalStrut_10;
		Component horizontalStrut_11;
		Component verticalStrut_7;
		JPanel panel_9;
		Component verticalStrut_8;
		JPanel panel_10;
		Component horizontalGlue_8;
		Component horizontalStrut_12;
		Component horizontalStrut_13;
		Component verticalStrut_9;
		JPanel panel_11;
		JPanel panel_12;
		Component horizontalStrut_14;
		Component horizontalGlue_9;
		JPanel panel_13;
		JPanel panel_14;
		JPanel panel_15;
		Component horizontalStrut_15;
		Component horizontalGlue_10;
		Component verticalStrut_10;
		Component verticalStrut_11;
		Component verticalStrut_12;
		JPanel panel_16;
		Component horizontalStrut_16;
		Component horizontalStrut_17;
		Component horizontalStrut_18;
		JPanel panel_17;
		Component horizontalStrut_19;
		Component horizontalStrut_20;
		JPanel panel_18;
		Component horizontalStrut_21;
		Component horizontalStrut_22;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.pnlNewsList = new JPanel();
		add(this.pnlNewsList);
		this.pnlNewsList.setLayout(new BoxLayout(this.pnlNewsList, BoxLayout.Y_AXIS));

		this.scrollableMainPanel = new JPanel();
		this.scrollableMainPanel.setLayout(new BoxLayout(this.scrollableMainPanel, BoxLayout.Y_AXIS));

		this.scrollPaneMain = new JScrollPane(this.scrollableMainPanel);
		this.scrollPaneMain.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				onNewsExtractClick(
						((NewsExtractDisplayPanel) NewsListPanel.this.scrollableMainPanel.getComponentAt(arg0.getX(),
								arg0.getY() + NewsListPanel.this.scrollPaneMain.getVerticalScrollBar().getValue()))
										.getNews());

			}
		});
		this.scrollPaneMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.scrollPaneMain.setViewportBorder(null);
		this.pnlNewsList.add(this.scrollPaneMain);

		Component verticalStrut = Box.createVerticalStrut(10);
		this.pnlNewsList.add(verticalStrut);

		JPanel panel = new JPanel();
		this.pnlNewsList.add(panel);
		panel.setMaximumSize(new Dimension(32767, 30));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		this.txtSearch = new JTextField();
		this.txtSearch.setSize(new Dimension(500, 0));
		this.txtSearch.setPreferredSize(new Dimension(800, 20));
		this.txtSearch.setMinimumSize(new Dimension(800, 20));
		this.txtSearch.setMaximumSize(new Dimension(800, 2147483647));
		this.txtSearch.setColumns(10);
		panel.add(this.txtSearch);

		Component horizontalStrut = Box.createHorizontalStrut(10);
		horizontalStrut.setMaximumSize(new Dimension(10, 0));
		panel.add(horizontalStrut);

		this.btnSearch = new JButton("Search");
		this.btnSearch.addActionListener(e -> btnSearch_Click());
		panel.add(this.btnSearch);

		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);

		this.btnAdd = new JButton("Add");
		this.btnAdd.addActionListener(e -> btnAdd_Click());
		panel.add(this.btnAdd);

		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		horizontalStrut_1.setMaximumSize(new Dimension(10, 0));
		panel.add(horizontalStrut_1);

		this.pnlNewsDetail = new JPanel();
		this.pnlNewsDetail.setVisible(false);
		add(this.pnlNewsDetail);
		this.pnlNewsDetail.setLayout(new BoxLayout(this.pnlNewsDetail, BoxLayout.Y_AXIS));

		panel_1 = new JPanel();
		panel_1.setBorder(null);
		this.pnlNewsDetail.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		verticalStrut_1 = Box.createVerticalStrut(10);
		verticalStrut_1.setMaximumSize(new Dimension(0, 10));
		panel_1.add(verticalStrut_1);

		panel_2 = new JPanel();
		panel_2.setMaximumSize(new Dimension(32767, 40));
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));

		Component horizontalStrut_32 = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut_32);
		this.vecTitle = new ViewEditComponent<JLabel, ValidableTextField>(lblTitle,
				new ValidableTextField(s -> !s.isEmpty()));
		this.vecTitle.setMaximumSize(new Dimension(2147483647, 40));
		panel_2.add(this.vecTitle);

		Component horizontalStrut_33 = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut_33);

		verticalStrut_2 = Box.createVerticalStrut(10);
		verticalStrut_2.setMaximumSize(new Dimension(0, 10));
		panel_1.add(verticalStrut_2);

		panel_3 = new JPanel();
		panel_3.setMaximumSize(new Dimension(32767, 25));
		panel_1.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setMaximumSize(new Dimension(20, 0));
		panel_3.add(horizontalStrut_2);

		label_1 = new JLabel("Author:");
		panel_3.add(label_1);

		horizontalStrut_3 = Box.createHorizontalStrut(4);
		panel_3.add(horizontalStrut_3);

		this.vecAuthor = new ViewEditComponent<JLabel, ValidableTextField>(new JLabel("AUTHOR"),
				new ValidableTextField(s -> !s.isEmpty()));
		this.vecAuthor.setMaximumSize(new Dimension(700, 2147483647));
		panel_3.add(this.vecAuthor);
		horizontalGlue_3 = Box.createHorizontalGlue();
		panel_3.add(horizontalGlue_3);

		label_3 = new JLabel("Pubblication date:");
		panel_3.add(label_3);

		horizontalStrut_4 = Box.createHorizontalStrut(4);
		panel_3.add(horizontalStrut_4);

		this.vecPubDate = new ViewEditComponent<JLabel, ValidableTextField>(new JLabel("PUBLICATION DATE"),
				new ValidableTextField(s -> !s.isEmpty()));
		this.vecPubDate.setMaximumSize(new Dimension(700, 2147483647));
		this.vecPubDate.setMinimumSize(new Dimension(100, 16));
		panel_3.add(this.vecPubDate);

		horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalStrut_5.setMaximumSize(new Dimension(20, 0));
		panel_3.add(horizontalStrut_5);

		verticalStrut_3 = Box.createVerticalStrut(10);
		verticalStrut_3.setMaximumSize(new Dimension(0, 20));
		panel_1.add(verticalStrut_3);

		this.pnlLink = new JPanel();
		this.pnlLink.setVisible(false);
		panel_1.add(this.pnlLink);
		this.pnlLink.setLayout(new BoxLayout(this.pnlLink, BoxLayout.X_AXIS));

		Component horizontalStrut_24 = Box.createHorizontalStrut(20);
		this.pnlLink.add(horizontalStrut_24);

		JLabel lblLink_1 = new JLabel("Link:");
		this.pnlLink.add(lblLink_1);

		Component horizontalStrut_25 = Box.createHorizontalStrut(3);
		this.pnlLink.add(horizontalStrut_25);

		this.vtfLinkEdit = new ValidableTextField();
		this.vtfLinkEdit.updateValidationTest(ApplicationUtilities::isValidURL);
		this.vtfLinkEdit.setMaximumSize(new Dimension(2147483647, 30));
		this.pnlLink.add(this.vtfLinkEdit);

		Component horizontalStrut_26 = Box.createHorizontalStrut(20);
		this.pnlLink.add(horizontalStrut_26);

		Component verticalStrut_14 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_14);

		this.pnlDescription = new JPanel();
		this.pnlDescription.setVisible(false);
		panel_1.add(this.pnlDescription);
		this.pnlDescription.setLayout(new BoxLayout(this.pnlDescription, BoxLayout.Y_AXIS));

		JPanel panel_19 = new JPanel();
		this.pnlDescription.add(panel_19);
		panel_19.setLayout(new BoxLayout(panel_19, BoxLayout.X_AXIS));

		Component horizontalStrut_27 = Box.createHorizontalStrut(20);
		panel_19.add(horizontalStrut_27);

		JLabel lblDescription_1 = new JLabel("Description:");
		panel_19.add(lblDescription_1);

		Component horizontalStrut_30 = Box.createHorizontalStrut(20);
		panel_19.add(horizontalStrut_30);

		this.txtDescriptionEdit = new JTextField();
		this.txtDescriptionEdit.setMaximumSize(new Dimension(2147483647, 30));
		panel_19.add(this.txtDescriptionEdit);
		this.txtDescriptionEdit.setColumns(10);

		Component horizontalStrut_31 = Box.createHorizontalStrut(20);
		panel_19.add(horizontalStrut_31);

		Component verticalStrut_15 = Box.createVerticalStrut(10);
		this.pnlDescription.add(verticalStrut_15);

		panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));

		this.spMain = new JScrollPane();
		panel_4.add(this.spMain);

		JLabel lblContent = new JLabel("Content");
		lblContent.setVerticalAlignment(SwingConstants.TOP);
		lblContent.setHorizontalAlignment(SwingConstants.CENTER);

		this.pnlContentLabelPanel = new JPanel();
		this.spMain.setViewportView(this.pnlContentLabelPanel);
		this.pnlContentLabelPanel.setLayout(new BoxLayout(this.pnlContentLabelPanel, BoxLayout.X_AXIS));

		this.vecContent = new ViewEditComponent<JLabel, JTextArea>(lblContent, new JTextArea());
		this.pnlContentLabelPanel.add(this.vecContent);

		Component verticalStrut_13 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_13);

		verticalStrut_5 = Box.createVerticalStrut(10);
		this.pnlNewsDetail.add(verticalStrut_5);

		panel_5 = new JPanel();
		this.pnlNewsDetail.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));

		this.btnBack = new JButton("« Back to the news list");
		this.btnBack.setPreferredSize(new Dimension(200, 30));
		this.btnBack.setMinimumSize(new Dimension(200, 30));
		this.btnBack.setMaximumSize(new Dimension(200, 30));
		panel_5.add(this.btnBack);

		horizontalGlue_6 = Box.createHorizontalGlue();
		panel_5.add(horizontalGlue_6);

		JButton btnOpenNewsIn = new JButton("Open news in browser");
		panel_5.add(btnOpenNewsIn);
		btnOpenNewsIn.addActionListener(e -> btnOpenNewsIn_Click());
		btnOpenNewsIn.setMaximumSize(new Dimension(220, 30));
		btnOpenNewsIn.setMinimumSize(new Dimension(220, 30));
		btnOpenNewsIn.setPreferredSize(new Dimension(220, 30));

		Component horizontalStrut_23 = Box.createHorizontalStrut(10);
		horizontalStrut_23.setMaximumSize(new Dimension(20, 0));
		panel_5.add(horizontalStrut_23);

		this.btnRemove = new JButton("Remove");
		this.btnRemove.setMaximumSize(new Dimension(100, 30));
		this.btnRemove.setMinimumSize(new Dimension(100, 30));
		this.btnRemove.setPreferredSize(new Dimension(100, 30));
		this.btnRemove.addActionListener((e) -> btnRemove_Click());
		panel_5.add(this.btnRemove);

		this.btnEdit = new JButton("Edit");
		this.btnEdit.setPreferredSize(new Dimension(100, 30));
		this.btnEdit.setMinimumSize(new Dimension(100, 30));
		this.btnEdit.setMaximumSize(new Dimension(100, 30));
		this.btnEdit.addActionListener(e -> btnEdit_Click());

		horizontalStrut_6 = Box.createHorizontalStrut(10);
		horizontalStrut_6.setMaximumSize(new Dimension(20, 0));
		panel_5.add(horizontalStrut_6);
		panel_5.add(this.btnEdit);

		horizontalStrut_7 = Box.createHorizontalStrut(10);
		horizontalStrut_7.setMaximumSize(new Dimension(20, 0));
		panel_5.add(horizontalStrut_7);

		this.pnlAddNews = new JPanel();
		this.pnlAddNews.setVisible(false);
		add(this.pnlAddNews);
		this.pnlAddNews.setLayout(new BoxLayout(this.pnlAddNews, BoxLayout.Y_AXIS));

		panel_6 = new JPanel();
		this.pnlAddNews.add(panel_6);
		panel_6.setBorder(null);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));

		verticalStrut_4 = Box.createVerticalStrut(10);
		verticalStrut_4.setMaximumSize(new Dimension(0, 10));
		panel_6.add(verticalStrut_4);

		panel_7 = new JPanel();
		panel_6.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));

		horizontalGlue_4 = Box.createHorizontalGlue();
		horizontalGlue_4.setPreferredSize(new Dimension(100, 0));
		horizontalGlue_4.setMaximumSize(new Dimension(100, 0));
		panel_7.add(horizontalGlue_4);

		this.vtfTitle = new ValidableTextField(s -> !s.isEmpty());
		this.vtfTitle.setFont(new Font("Dialog", Font.PLAIN, 14));
		this.vtfTitle.setPreferredSize(new Dimension(4, 35));
		this.vtfTitle.setMaximumSize(new Dimension(2147483647, 35));
		panel_7.add(this.vtfTitle);

		horizontalGlue_5 = Box.createHorizontalGlue();
		horizontalGlue_5.setPreferredSize(new Dimension(100, 0));
		horizontalGlue_5.setMaximumSize(new Dimension(100, 0));
		panel_7.add(horizontalGlue_5);

		verticalStrut_6 = Box.createVerticalStrut(10);
		verticalStrut_6.setMaximumSize(new Dimension(0, 10));
		panel_6.add(verticalStrut_6);

		panel_8 = new JPanel();
		panel_8.setMaximumSize(new Dimension(32767, 25));
		panel_6.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.X_AXIS));

		horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalStrut_8.setMaximumSize(new Dimension(20, 0));
		panel_8.add(horizontalStrut_8);

		label_2 = new JLabel("Author:");
		panel_8.add(label_2);

		horizontalStrut_9 = Box.createHorizontalStrut(4);
		panel_8.add(horizontalStrut_9);

		this.vtfAuthor = new ValidableTextField(s -> !s.isEmpty());
		panel_8.add(this.vtfAuthor);

		horizontalGlue_7 = Box.createHorizontalGlue();
		horizontalGlue_7.setPreferredSize(new Dimension(200, 0));
		horizontalGlue_7.setMinimumSize(new Dimension(200, 0));
		panel_8.add(horizontalGlue_7);

		label_5 = new JLabel("Pubblication date:");
		panel_8.add(label_5);

		horizontalStrut_10 = Box.createHorizontalStrut(4);
		panel_8.add(horizontalStrut_10);

		this.vtfPubDate = new ValidableTextField(s -> !s.isEmpty());
		panel_8.add(this.vtfPubDate);

		horizontalStrut_11 = Box.createHorizontalStrut(20);
		horizontalStrut_11.setMaximumSize(new Dimension(20, 0));
		panel_8.add(horizontalStrut_11);

		verticalStrut_7 = Box.createVerticalStrut(10);
		verticalStrut_7.setMaximumSize(new Dimension(0, 20));
		panel_6.add(verticalStrut_7);

		panel_16 = new JPanel();
		panel_6.add(panel_16);
		panel_16.setLayout(new BoxLayout(panel_16, BoxLayout.X_AXIS));

		horizontalStrut_16 = Box.createHorizontalStrut(20);
		panel_16.add(horizontalStrut_16);

		this.lblLink = new JLabel("Link:");
		panel_16.add(this.lblLink);

		horizontalStrut_17 = Box.createHorizontalStrut(3);
		panel_16.add(horizontalStrut_17);

		this.vtfLink = new ValidableTextField(s -> ApplicationUtilities.isValidURL(s));
		this.vtfLink.setMaximumSize(new Dimension(2147483647, 30));
		panel_16.add(this.vtfLink);

		horizontalStrut_18 = Box.createHorizontalStrut(20);
		panel_16.add(horizontalStrut_18);

		verticalStrut_12 = Box.createVerticalStrut(10);
		panel_6.add(verticalStrut_12);

		panel_13 = new JPanel();
		panel_6.add(panel_13);
		panel_13.setLayout(new BoxLayout(panel_13, BoxLayout.X_AXIS));

		panel_14 = new JPanel();
		panel_13.add(panel_14);
		panel_14.setLayout(new BoxLayout(panel_14, BoxLayout.Y_AXIS));

		panel_15 = new JPanel();
		panel_14.add(panel_15);
		panel_15.setLayout(new BoxLayout(panel_15, BoxLayout.X_AXIS));

		horizontalStrut_15 = Box.createHorizontalStrut(20);
		panel_15.add(horizontalStrut_15);

		this.lblDescription = new JLabel("Description:");
		panel_15.add(this.lblDescription);

		horizontalGlue_10 = Box.createHorizontalGlue();
		panel_15.add(horizontalGlue_10);

		verticalStrut_10 = Box.createVerticalStrut(3);
		verticalStrut_10.setMaximumSize(new Dimension(0, 3));
		panel_14.add(verticalStrut_10);

		panel_17 = new JPanel();
		panel_14.add(panel_17);
		panel_17.setLayout(new BoxLayout(panel_17, BoxLayout.X_AXIS));

		horizontalStrut_20 = Box.createHorizontalStrut(20);
		panel_17.add(horizontalStrut_20);

		this.taDescription = new JTextArea();
		panel_17.add(this.taDescription);
		this.taDescription.setLineWrap(true);

		horizontalStrut_19 = Box.createHorizontalStrut(20);
		panel_17.add(horizontalStrut_19);

		verticalStrut_11 = Box.createVerticalStrut(10);
		panel_6.add(verticalStrut_11);

		panel_11 = new JPanel();
		panel_6.add(panel_11);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.X_AXIS));

		panel_9 = new JPanel();
		panel_11.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));

		panel_12 = new JPanel();
		panel_9.add(panel_12);
		panel_12.setLayout(new BoxLayout(panel_12, BoxLayout.X_AXIS));

		horizontalStrut_14 = Box.createHorizontalStrut(20);
		panel_12.add(horizontalStrut_14);

		this.lblNewLabel = new JLabel("Content:");
		panel_12.add(this.lblNewLabel);

		horizontalGlue_9 = Box.createHorizontalGlue();
		panel_12.add(horizontalGlue_9);

		verticalStrut_9 = Box.createVerticalStrut(3);
		verticalStrut_9.setMaximumSize(new Dimension(0, 3));
		panel_9.add(verticalStrut_9);

		panel_18 = new JPanel();
		panel_9.add(panel_18);
		panel_18.setLayout(new BoxLayout(panel_18, BoxLayout.X_AXIS));

		horizontalStrut_22 = Box.createHorizontalStrut(20);
		panel_18.add(horizontalStrut_22);

		this.taContent = new JTextArea();
		panel_18.add(this.taContent);
		this.taContent.setLineWrap(true);

		horizontalStrut_21 = Box.createHorizontalStrut(20);
		panel_18.add(horizontalStrut_21);

		verticalStrut_8 = Box.createVerticalStrut(10);
		this.pnlAddNews.add(verticalStrut_8);

		panel_10 = new JPanel();
		this.pnlAddNews.add(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.X_AXIS));

		horizontalStrut_13 = Box.createHorizontalStrut(10);
		panel_10.add(horizontalStrut_13);

		this.btnAbort = new JButton("\u00AB Abort");
		this.btnAbort.setPreferredSize(new Dimension(130, 30));
		this.btnAbort.setMinimumSize(new Dimension(130, 30));
		this.btnAbort.setMaximumSize(new Dimension(130, 30));
		this.btnAbort.addActionListener((e) -> btnAbort_Click());
		panel_10.add(this.btnAbort);

		horizontalGlue_8 = Box.createHorizontalGlue();
		panel_10.add(horizontalGlue_8);

		this.btnSave = new JButton("Save");
		this.btnSave.setPreferredSize(new Dimension(130, 30));
		this.btnSave.setMinimumSize(new Dimension(130, 30));
		this.btnSave.setMaximumSize(new Dimension(130, 30));
		this.btnSave.addActionListener(e -> btnSave_Click());
		panel_10.add(this.btnSave);

		horizontalStrut_12 = Box.createHorizontalStrut(10);
		horizontalStrut_12.setMaximumSize(new Dimension(20, 0));
		panel_10.add(horizontalStrut_12);
		this.btnBack.addActionListener(e -> btnBack_Click());

	}

	/**
	 * Creates a news panel that shows only the controls allowed by a user's roles.
	 * @param currentUser The user whose roles will be used to choose which controls to display and which to hide.
	 */
	public NewsListPanel(User currentUser)
	{
		this();
		this.listeners = new HashMap<>();
		updateCurrentUser(currentUser);

		this.vecTitle.setViewToEditOperation((l, t) -> t.setText(l.getText()));
		this.vecAuthor.setViewToEditOperation((l, t) -> t.setText(l.getText()));
		this.vecPubDate.setViewToEditOperation((l, t) -> t.setText(l.getText()));
		this.vecContent.setViewToEditOperation((l, t) -> t.setText(l.getText()));

		this.vecTitle.setEditToViewOperation((l, t) -> l.setText(t.getText()));
		this.vecAuthor.setEditToViewOperation((l, t) -> l.setText(t.getText()));
		this.vecPubDate.setEditToViewOperation((l, t) -> l.setText(t.getText()));
		this.vecContent.setEditToViewOperation((l, t) -> l.setText(t.getText()));
	}

	/**
	 * Allows to abort the attempt to add a news.
	 */
	private void btnAbort_Click()
	{
		this.pnlAddNews.setVisible(false);
		this.pnlNewsList.setVisible(true);
	}

	/**
	 * Shows the view that allows to add a news.
	 */
	private void btnAdd_Click()
	{
		this.pnlNewsList.setVisible(false);
		this.pnlAddNews.setVisible(true);

		this.vtfTitle.setText("Title");
		this.vtfAuthor.setText("");
		this.vtfPubDate.setText("");
		this.taContent.setText("");

	}

	/**
	 *  Goes back from "News detail" view to "News list" view.
	 */
	private void btnBack_Click()
	{
		updatePermissions();
		this.currentNews = null;
		this.pnlNewsDetail.setVisible(false);
		this.pnlNewsList.setVisible(true);
	}

	/**
	 * Allows to edit a displayed news. If the news is already in "edit mode", then this button allows to delete all the changes.
	 */
	private void btnEdit_Click()
	{
		if (this.btnEdit.getText().equals("Edit"))
		{
			this.btnEdit.setText("Abort");
			this.btnRemove.setText("Save");

			this.vecTitle.setEditState();
			this.vecAuthor.setEditState();
			this.vecPubDate.setEditState();
			this.vecContent.setEditState();

			this.vtfLinkEdit.setText(this.currentNews.getLink());
			this.txtDescriptionEdit.setText(this.currentNews.getDescription());
			this.pnlLink.setVisible(true);
			this.pnlDescription.setVisible(true);
		} else
		{
			this.btnEdit.setText("Edit");
			this.btnRemove.setText("Remove");

			this.vecTitle.setViewState();
			this.vecAuthor.setViewState();
			this.vecPubDate.setViewState();
			this.vecContent.setViewState();

			this.vtfLinkEdit.setText("");
			this.txtDescriptionEdit.setText("");
			this.pnlLink.setVisible(false);
			this.pnlDescription.setVisible(false);

			onNewsExtractClick(this.currentNews);

		}

	}

	/**
	 * Opens the currently displayed news in the default browser.
	 */
	private void btnOpenNewsIn_Click()
	{
		if (Desktop.isDesktopSupported())
		{
			try
			{
				Desktop.getDesktop().browse(new URI(this.currentNews.getLink()));
			} catch (IOException e)
			{
				JOptionPane.showMessageDialog(this,
						"The preferred browser did not respond properly and the application is not able to open the news.");
			} catch (URISyntaxException e)
			{
				JOptionPane.showMessageDialog(this,
						"This news' link is not valid, the application cannot open the news.");
			}
		} else
		{
			JOptionPane.showMessageDialog(this, "Preferred browser not found, the application cannot open the news.");
		}
	}

	/**
	 * Removes the selected news. If the news is in "edit mode", then this button saves the changes.
	 */
	private void btnRemove_Click()
	{
		if (this.btnRemove.getText().equals("Remove"))
		{
			Object[] options = { "Yes", "No" };
			if (JOptionPane.showOptionDialog(this, "Are you sure you want to delete this news?", "Warning",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
					options[0]) == JOptionPane.YES_OPTION)
			{
				this.currentNewsChannel.getNews().remove(this.currentNews);
				this.currentNews = null;
				this.listeners.get("onDataRemoved").accept(1);
				btnBack_Click();
			}

		} else
		{

			if (!this.vecTitle.getEditComponent().isValid())
			{
				JOptionPane.showMessageDialog(this, "The title should not be empty");
				return;
			}

			if (!this.vecAuthor.getEditComponent().isValid())
			{
				JOptionPane.showMessageDialog(this, "The author should not be empty");
				return;
			}

			if (!this.vecPubDate.getEditComponent().isValid())
			{
				JOptionPane.showMessageDialog(this, "The pubblication date should not be empty");
				return;
			}

			if (!this.vtfLinkEdit.isValid())
			{
				JOptionPane.showMessageDialog(this, "The link is not valid");
				return;
			}

			this.btnEdit.setText("Edit");
			this.btnRemove.setText("Remove");

			this.vecTitle.setViewState();
			this.vecAuthor.setViewState();
			this.vecPubDate.setViewState();
			this.vecContent.setViewState();

			this.pnlLink.setVisible(false);
			this.pnlDescription.setVisible(false);

			this.currentNews.setTitle(this.vecTitle.getViewComponent().getText());
			this.currentNews.setAuthor(this.vecAuthor.getViewComponent().getText());
			this.currentNews.setPubblicationDate(this.vecPubDate.getViewComponent().getText());
			this.currentNews.setContent(this.vecContent.getViewComponent().getText());
			this.currentNews.setLink(this.vtfLinkEdit.getText());
			this.currentNews.setDescription(this.txtDescriptionEdit.getText());

			this.vtfLinkEdit.setText("");
			this.txtDescriptionEdit.setText("");

		}
	}

	/**
	 * Allows to save a new news.
	 */
	private void btnSave_Click()
	{
		if (!this.vtfTitle.isValid())
		{
			JOptionPane.showMessageDialog(this, "The title is not valid.");
			return;
		}
		if (!this.vtfAuthor.isValid())
		{
			JOptionPane.showMessageDialog(this, "The author is not valid.");
			return;
		}
		if (!this.vtfPubDate.isValid())
		{
			JOptionPane.showMessageDialog(this, "The pubblication date is not valid.");
			return;
		}
		if (!this.vtfLink.isValid())
		{
			JOptionPane.showMessageDialog(this, "The link is not a valid URL.");
			return;
		}

		News n = new News();
		n.setTitle(this.vtfTitle.getText());
		n.setAuthor(this.vtfAuthor.getText());
		n.setContent(this.taContent.getText());
		n.setLink(this.vtfLink.getText());
		n.setDescription(this.taDescription.getText());
		n.setPubblicationDate(this.vtfPubDate.getText());

		this.currentNewsChannel.getNews().add(n);

		this.listeners.get("onDataAdded").accept(1);

		updatePermissions();

		this.pnlAddNews.setVisible(false);
		this.pnlNewsList.setVisible(true);

	}

	/**
	 * Updates the news displayed in the news list according to the content of the search bar and the permissions of the current user.
	 */
	private void btnSearch_Click()
	{
		updatePermissions();
	}

	/**
	 * This method searches the text contained in the "Search" text field in a given news. The check is case-insensitive.
	 * @param n The news in which the text contained in the "Search" text field should be searched.
	 * @return The method returns {@code true} if the news contains the text contained in the "Search" text field, {@code false} otherwise.
	 */
	private boolean newsContainsTest(News n)
	{
		String txt = this.txtSearch.getText().toLowerCase();
		boolean ans = false;

		if (n.getTitle().toLowerCase().contains(txt))
		{
			ans = true;
		}

		if (n.getAuthor().toLowerCase().contains(txt))
		{
			ans = true;
		}

		if (n.getContent().toLowerCase().contains(txt))
		{
			ans = true;
		}

		if (n.getDescription().toLowerCase().contains(txt))
		{
			ans = true;
		}

		if (n.getLink().toLowerCase().contains(txt))
		{
			ans = true;
		}

		if (n.getPubblicationDate().toLowerCase().contains(txt))
		{
			ans = true;
		}

		return ans;
	}

	/**
	 * This method hides the news list and shows the detail of a particular news.
	 * @param n The news which details should be shown.
	 */
	private void onNewsExtractClick(News n)
	{
		this.pnlNewsList.setVisible(false);
		this.pnlNewsDetail.setVisible(true);

		this.vecTitle.getViewComponent().setText(n.getTitle());
		this.vecAuthor.getViewComponent().setText(n.getAuthor());
		this.vecPubDate.getViewComponent().setText(n.getPubblicationDate());

		if (n.getContent().startsWith("<html>"))
		{
			this.vecContent.getViewComponent().setText(n.getContent());
		} else
		{
			this.vecContent.getViewComponent()
					.setText("<html><head><style>div#mc{width:" + this.pnlNewsList.getPreferredSize().getWidth()
							+ "px;}</style></head><body><div id=\"mc\">" + n.getContent() + "</div></body></html>");
		}

		this.currentNews = n;

		this.spMain.revalidate();
	}

	/**
	 * Allows to specify a function that should be called whenever a new news is added. A unique "news added" listener per NewsListPanel instance is allowed.
	 * @param listener The function that should be called whenever a new news is added.
	 */
	public void setOnDataAddedListener(Consumer<Integer> listener)
	{
		if (this.listeners.putIfAbsent("onDataAdded", listener) != null)
		{
			this.listeners.replace("onDataAdded", listener);
		}
	}

	/**
	 * Allows to specify a function that should be called whenever a news is deleted. A unique "news removed" listener per NewsListPanel instance is allowed.
	 * @param listener The function that should be called whenever a news is deleted.
	 */
	public void setOnDataRemovedListener(Consumer<Integer> listener)
	{
		if (this.listeners.putIfAbsent("onDataRemoved", listener) != null)
		{
			this.listeners.replace("onDataRemoved", listener);
		}
	}

	/**
	 * Updates the currently displayed news channel.
	 * @param nc The new news channel to display.
	 */
	public void updateCurrentNewsChannel(NewsChannel nc)
	{
		if (nc == null)
		{
			return;
		}

		this.currentNewsChannel = nc;

		updatePermissions();

	}

	/**
	 * Updates the user being used to determine which controls should be shown or not.
	 * @param u The new user that will be used to determine which controls should be shown or not.
	 */
	public void updateCurrentUser(User u)
	{
		this.currentUser = u;

		// For permission check
		updatePermissions();
	}

	/**
	 * Uses the current news channel and the current user to update the GUI considering the news contained in the current news channel and the roles that the user has.
	 */
	public void updatePermissions()
	{
		this.scrollableMainPanel.removeAll();

		if ((this.currentUser == null) || (this.currentNewsChannel == null))
		{
			return;
		}

		JPanel tmp;

		if (this.currentUser.hasRole(Role.READ))
		{
			for (News n : this.currentNewsChannel.getNews())
			{
				if (newsContainsTest(n))
				{
					tmp = new NewsExtractDisplayPanel(n);
					this.scrollableMainPanel.add(tmp);
				}
			}
		}

		if (this.currentUser.hasRole(Role.ADD_NEWS))
		{
			this.btnAdd.setVisible(true);
			this.btnAdd.setEnabled(true);
		} else
		{
			this.btnAdd.setVisible(false);
			this.btnAdd.setEnabled(false);
		}

		if (this.currentUser.hasRole(Role.DELETE_NEWS))
		{
			this.btnRemove.setVisible(true);
			this.btnRemove.setEnabled(true);
		} else
		{
			this.btnRemove.setVisible(false);
			this.btnRemove.setEnabled(false);
		}

		if (this.currentUser.hasRole(Role.SEARCH))
		{
			this.btnSearch.setVisible(true);
			this.btnSearch.setEnabled(true);
			this.txtSearch.setVisible(true);
			this.txtSearch.setEditable(true);
		} else
		{
			this.btnSearch.setVisible(false);
			this.btnSearch.setEnabled(false);
			this.txtSearch.setVisible(false);
			this.txtSearch.setEditable(false);
		}

		if (this.currentUser.hasRole(Role.EDIT_NEWS))
		{
			this.btnEdit.setVisible(true);
			this.btnEdit.setEnabled(true);
		} else
		{
			this.btnEdit.setVisible(false);
			this.btnEdit.setEnabled(false);
		}

		this.scrollableMainPanel.revalidate();
	}
}
