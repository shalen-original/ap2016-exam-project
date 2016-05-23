package ap2016.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

@SuppressWarnings("serial")
public class NewsListPanel extends JPanel
{
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
	private JPanel panel_1;
	private Component verticalStrut_1;
	private JPanel panel_2;
	private Component horizontalGlue_1;
	private JLabel lblTitle;
	private Component horizontalGlue_2;
	private Component verticalStrut_2;
	private JPanel panel_3;
	private Component horizontalStrut_2;
	private JLabel label_1;
	private Component horizontalStrut_3;
	private JLabel lblAuthor;
	private Component horizontalGlue_3;
	private JLabel label_3;
	private Component horizontalStrut_4;
	private JLabel lblPubDate;
	private Component horizontalStrut_5;
	private Component verticalStrut_3;
	private JPanel panel_4;
	private JLabel lblContent;
	private JPanel panel_5;
	private JButton btnEdit;
	private Component horizontalGlue_6;
	private JScrollPane spMain;
	private Component verticalStrut_5;
	private JPanel pnlContentLabelPanel;
	private JButton btnRemove;
	private Component horizontalStrut_6;
	private Component horizontalStrut_7;
	private JPanel pnlAddNews;
	private JPanel panel_6;
	private Component verticalStrut_4;
	private JPanel panel_7;
	private Component horizontalGlue_4;
	private Component horizontalGlue_5;
	private Component verticalStrut_6;
	private JPanel panel_8;
	private Component horizontalStrut_8;
	private JLabel label_2;
	private Component horizontalStrut_9;
	private Component horizontalGlue_7;
	private JLabel label_5;
	private Component horizontalStrut_10;
	private Component horizontalStrut_11;
	private Component verticalStrut_7;
	private JPanel panel_9;
	private Component verticalStrut_8;
	private JPanel panel_10;
	private JButton btnAbort;
	private Component horizontalGlue_8;
	private JButton btnSave;
	private Component horizontalStrut_12;
	private ValidableTextField vtfTitle;
	private ValidableTextField vtfAuthor;
	private ValidableTextField vtfPubDate;
	private Component horizontalStrut_13;
	private JTextArea taContent;
	private JLabel lblNewLabel;
	private Component verticalStrut_9;
	private JPanel panel_11;
	private JPanel panel_12;
	private Component horizontalStrut_14;
	private Component horizontalGlue_9;
	private JPanel panel_13;
	private JPanel panel_14;
	private JPanel panel_15;
	private Component horizontalStrut_15;
	private JLabel lblDescription;
	private Component horizontalGlue_10;
	private Component verticalStrut_10;
	private JTextArea taDescription;
	private Component verticalStrut_11;
	private Component verticalStrut_12;
	private JPanel panel_16;
	private Component horizontalStrut_16;
	private JLabel lblLink;
	private Component horizontalStrut_17;
	private ValidableTextField vtfLink;
	private Component horizontalStrut_18;
	private JPanel panel_17;
	private Component horizontalStrut_19;
	private Component horizontalStrut_20;
	private JPanel panel_18;
	private Component horizontalStrut_21;
	private Component horizontalStrut_22;

	public NewsListPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		pnlNewsList = new JPanel();
		add(pnlNewsList);
		pnlNewsList.setLayout(new BoxLayout(pnlNewsList, BoxLayout.Y_AXIS));

		scrollableMainPanel = new JPanel();
		scrollableMainPanel.setPreferredSize(new Dimension(300, 10));
		scrollableMainPanel.setMaximumSize(new Dimension(300, 32767));
		scrollableMainPanel.setLayout(new BoxLayout(scrollableMainPanel, BoxLayout.Y_AXIS));

		scrollPaneMain = new JScrollPane(scrollableMainPanel);
		scrollPaneMain.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseReleased(MouseEvent arg0)
				{
					onNewsExtractClick(((NewsExtractDisplayPanel) scrollableMainPanel.getComponentAt(arg0.getX(),
							arg0.getY() + scrollPaneMain.getVerticalScrollBar().getValue())).getNews());

				}
			});
		scrollPaneMain.setBackground(Color.GREEN);
		scrollPaneMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMain.setViewportBorder(null);
		scrollPaneMain.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlNewsList.add(scrollPaneMain);

		Component verticalStrut = Box.createVerticalStrut(10);
		pnlNewsList.add(verticalStrut);

		JPanel panel = new JPanel();
		pnlNewsList.add(panel);
		panel.setMaximumSize(new Dimension(32767, 30));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		txtSearch = new JTextField();
		txtSearch.setSize(new Dimension(500, 0));
		txtSearch.setPreferredSize(new Dimension(800, 20));
		txtSearch.setMinimumSize(new Dimension(800, 20));
		txtSearch.setMaximumSize(new Dimension(800, 2147483647));
		txtSearch.setColumns(10);
		panel.add(txtSearch);

		Component horizontalStrut = Box.createHorizontalStrut(10);
		horizontalStrut.setMaximumSize(new Dimension(10, 0));
		panel.add(horizontalStrut);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(e -> btnSearch_Click());
		panel.add(btnSearch);

		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(e -> btnAdd_Click());
		panel.add(btnAdd);

		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		horizontalStrut_1.setMaximumSize(new Dimension(10, 0));
		panel.add(horizontalStrut_1);

		pnlNewsDetail = new JPanel();
		pnlNewsDetail.setVisible(false);
		add(pnlNewsDetail);
		pnlNewsDetail.setLayout(new BoxLayout(pnlNewsDetail, BoxLayout.Y_AXIS));

		panel_1 = new JPanel();
		panel_1.setBorder(null);
		pnlNewsDetail.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		verticalStrut_1 = Box.createVerticalStrut(10);
		verticalStrut_1.setMaximumSize(new Dimension(0, 10));
		panel_1.add(verticalStrut_1);

		panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		horizontalGlue_1 = Box.createHorizontalGlue();
		panel_2.add(horizontalGlue_1);

		lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_2.add(lblTitle);

		horizontalGlue_2 = Box.createHorizontalGlue();
		panel_2.add(horizontalGlue_2);

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

		lblAuthor = new JLabel("AUTHOR");
		panel_3.add(lblAuthor);

		horizontalGlue_3 = Box.createHorizontalGlue();
		panel_3.add(horizontalGlue_3);

		label_3 = new JLabel("Pubblication date:");
		panel_3.add(label_3);

		horizontalStrut_4 = Box.createHorizontalStrut(4);
		panel_3.add(horizontalStrut_4);

		lblPubDate = new JLabel("PUBLICATION DATE");
		panel_3.add(lblPubDate);

		horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalStrut_5.setMaximumSize(new Dimension(20, 0));
		panel_3.add(horizontalStrut_5);

		verticalStrut_3 = Box.createVerticalStrut(20);
		verticalStrut_3.setMaximumSize(new Dimension(0, 20));
		panel_1.add(verticalStrut_3);

		panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));

		spMain = new JScrollPane();
		panel_4.add(spMain);

		lblContent = new JLabel("Content");
		lblContent.setVerticalAlignment(SwingConstants.TOP);
		lblContent.setHorizontalAlignment(SwingConstants.CENTER);

		pnlContentLabelPanel = new JPanel();
		pnlContentLabelPanel.setLayout(new BorderLayout(0, 0));
		pnlContentLabelPanel.add(lblContent);
		spMain.setViewportView(pnlContentLabelPanel);

		verticalStrut_5 = Box.createVerticalStrut(10);
		pnlNewsDetail.add(verticalStrut_5);

		panel_5 = new JPanel();
		pnlNewsDetail.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));

		btnBack = new JButton("« Back to the news list");
		btnBack.setPreferredSize(new Dimension(170, 30));
		btnBack.setMinimumSize(new Dimension(170, 30));
		btnBack.setMaximumSize(new Dimension(170, 30));
		panel_5.add(btnBack);

		horizontalGlue_6 = Box.createHorizontalGlue();
		panel_5.add(horizontalGlue_6);

		btnEdit = new JButton("Edit");
		btnEdit.setPreferredSize(new Dimension(80, 30));
		btnEdit.setMinimumSize(new Dimension(80, 30));
		btnEdit.setMaximumSize(new Dimension(80, 30));
		panel_5.add(btnEdit);

		horizontalStrut_6 = Box.createHorizontalStrut(10);
		horizontalStrut_6.setMaximumSize(new Dimension(20, 0));
		panel_5.add(horizontalStrut_6);

		btnRemove = new JButton("Remove");
		btnRemove.setMaximumSize(new Dimension(80, 30));
		btnRemove.setMinimumSize(new Dimension(80, 30));
		btnRemove.setPreferredSize(new Dimension(80, 30));
		btnRemove.addActionListener((e) -> btnRemove_Click());
		panel_5.add(btnRemove);

		horizontalStrut_7 = Box.createHorizontalStrut(10);
		horizontalStrut_7.setMaximumSize(new Dimension(20, 0));
		panel_5.add(horizontalStrut_7);

		pnlAddNews = new JPanel();
		pnlAddNews.setVisible(false);
		add(pnlAddNews);
		pnlAddNews.setLayout(new BoxLayout(pnlAddNews, BoxLayout.Y_AXIS));

		panel_6 = new JPanel();
		pnlAddNews.add(panel_6);
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

		vtfTitle = new ValidableTextField(s -> !s.isEmpty());
		vtfTitle.setFont(new Font("Dialog", Font.PLAIN, 14));
		vtfTitle.setPreferredSize(new Dimension(4, 35));
		vtfTitle.setMaximumSize(new Dimension(2147483647, 35));
		panel_7.add(vtfTitle);

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

		vtfAuthor = new ValidableTextField(s -> !s.isEmpty());
		panel_8.add(vtfAuthor);

		horizontalGlue_7 = Box.createHorizontalGlue();
		horizontalGlue_7.setPreferredSize(new Dimension(200, 0));
		horizontalGlue_7.setMinimumSize(new Dimension(200, 0));
		panel_8.add(horizontalGlue_7);

		label_5 = new JLabel("Pubblication date:");
		panel_8.add(label_5);

		horizontalStrut_10 = Box.createHorizontalStrut(4);
		panel_8.add(horizontalStrut_10);

		vtfPubDate = new ValidableTextField(s -> !s.isEmpty());
		panel_8.add(vtfPubDate);

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

		lblLink = new JLabel("Link:");
		panel_16.add(lblLink);

		horizontalStrut_17 = Box.createHorizontalStrut(3);
		panel_16.add(horizontalStrut_17);

		vtfLink = new ValidableTextField(s -> ApplicationUtilities.isValidURL(s));
		vtfLink.setMaximumSize(new Dimension(2147483647, 30));
		panel_16.add(vtfLink);

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

		lblDescription = new JLabel("Description:");
		panel_15.add(lblDescription);

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

		taDescription = new JTextArea();
		panel_17.add(taDescription);
		taDescription.setLineWrap(true);
		
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

		lblNewLabel = new JLabel("Content:");
		panel_12.add(lblNewLabel);

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

		taContent = new JTextArea();
		panel_18.add(taContent);
		taContent.setLineWrap(true);
		
		horizontalStrut_21 = Box.createHorizontalStrut(20);
		panel_18.add(horizontalStrut_21);

		verticalStrut_8 = Box.createVerticalStrut(10);
		pnlAddNews.add(verticalStrut_8);

		panel_10 = new JPanel();
		pnlAddNews.add(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.X_AXIS));

		horizontalStrut_13 = Box.createHorizontalStrut(10);
		panel_10.add(horizontalStrut_13);

		btnAbort = new JButton("\u00AB Abort");
		btnAbort.setPreferredSize(new Dimension(130, 30));
		btnAbort.setMinimumSize(new Dimension(130, 30));
		btnAbort.setMaximumSize(new Dimension(130, 30));
		btnAbort.addActionListener((e) -> btnAbort_Click());
		panel_10.add(btnAbort);

		horizontalGlue_8 = Box.createHorizontalGlue();
		panel_10.add(horizontalGlue_8);

		btnSave = new JButton("Save");
		btnSave.setPreferredSize(new Dimension(130, 30));
		btnSave.setMinimumSize(new Dimension(130, 30));
		btnSave.setMaximumSize(new Dimension(130, 30));
		btnSave.addActionListener(e -> btnSave_Click());
		panel_10.add(btnSave);

		horizontalStrut_12 = Box.createHorizontalStrut(10);
		horizontalStrut_12.setMaximumSize(new Dimension(20, 0));
		panel_10.add(horizontalStrut_12);
		btnBack.addActionListener(e -> btnBack_Click());

	}

	public NewsListPanel(User currentUser, NewsChannel currentNewsChannel)
	{
		this();
		listeners = new HashMap<>();
		updateCurrentUser(currentUser);
		updateCurrentNewsChannel(currentNewsChannel);
	}

	
	
	public void setOnDataAddedListener(Consumer<Integer> listener)
	{
		if (listeners.putIfAbsent("onDataAdded", listener) != null)
			listeners.replace("onDataAdded", listener);
	}

	public void setOnDataRemovedListener(Consumer<Integer> listener)
	{
		if (listeners.putIfAbsent("onDataRemoved", listener) != null)
			listeners.replace("onDataRemoved", listener);
	}

	
	
	public void updateCurrentNewsChannel(NewsChannel nc)
	{
		if (nc == null)
			return;

		this.currentNewsChannel = nc;

		reloadNewsList();

	}

	public void updateCurrentUser(User u)
	{
		this.currentUser = u;

		// For permission check
		reloadNewsList();
	}

	public void reloadNewsList()
	{
		scrollableMainPanel.removeAll();

		if (currentUser == null || currentNewsChannel == null)
			return;

		int height = 0;
		JPanel tmp;

		if (currentUser.hasRole(Role.READ))
		{
			for (News n : currentNewsChannel.getNews())
			{
				if (newsContainsTest(n))
				{	
					tmp = new NewsExtractDisplayPanel(n);
					scrollableMainPanel.add(tmp);
					height += tmp.getPreferredSize().getHeight();
				}
			}
		}

		if (currentUser.hasRole(Role.ADD_NEWS))
		{
			btnAdd.setVisible(true);
			btnAdd.setEnabled(true);
		} else
		{
			btnAdd.setVisible(false);
			btnAdd.setEnabled(false);
		}

		if (currentUser.hasRole(Role.DELETE_NEWS))
		{
			btnRemove.setVisible(true);
			btnRemove.setEnabled(true);
		} else
		{
			btnRemove.setVisible(false);
			btnRemove.setEnabled(false);
		}

		if (currentUser.hasRole(Role.SEARCH))
		{
			btnSearch.setVisible(true);
			btnSearch.setEnabled(true);
			txtSearch.setVisible(true);
			txtSearch.setEditable(true);
		} else
		{
			btnSearch.setVisible(false);
			btnSearch.setEnabled(false);
			txtSearch.setVisible(false);
			txtSearch.setEditable(false);
		}

		scrollableMainPanel.setPreferredSize(new Dimension(500, height));
		scrollableMainPanel.revalidate();
	}

	
	
	private void onNewsExtractClick(News n)
	{
		pnlNewsList.setVisible(false);
		pnlNewsDetail.setVisible(true);

		lblTitle.setText(n.getTitle());
		lblAuthor.setText(n.getAuthor());
		lblPubDate.setText(n.getPubblicationDate());
		lblContent.setText("<html><head><style>div#mc{width:" + pnlNewsList.getPreferredSize().getWidth()
				+ "px;}</style></head><body><div id=\"mc\">" + n.getContent() + "</div></body></html>");

		currentNews = n;

		spMain.revalidate();
	}

	
	
	
	
	private void btnBack_Click()
	{
		reloadNewsList();
		currentNews = null;
		pnlNewsDetail.setVisible(false);
		pnlNewsList.setVisible(true);
	}

	private void btnRemove_Click()
	{
		Object[] options = { "Yes", "No" };
		if (JOptionPane.showOptionDialog(this, "Are you sure you want to delete this news?", "Warning",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
				options[0]) == JOptionPane.YES_OPTION)
		{
			currentNewsChannel.removeNews(currentNews);
			currentNews = null;
			listeners.get("onDataRemoved").accept(1);
			btnBack_Click();
		}
	}

	private void btnAdd_Click()
	{
		pnlNewsList.setVisible(false);
		pnlAddNews.setVisible(true);

		vtfTitle.setText("Title");
		vtfAuthor.setText("");
		vtfPubDate.setText("");
		taContent.setText("");

	}

	private void btnAbort_Click()
	{
		pnlAddNews.setVisible(false);
		pnlNewsList.setVisible(true);
	}

	private void btnSave_Click()
	{
		if (!vtfTitle.isValid())
		{
			JOptionPane.showMessageDialog(this, "The title is not valid.");
			return;
		}
		if (!vtfAuthor.isValid())
		{
			JOptionPane.showMessageDialog(this, "The author is not valid.");
			return;
		}
		if (!vtfPubDate.isValid())
		{
			JOptionPane.showMessageDialog(this, "The pubblication date is not valid.");
			return;
		}
		if (!vtfLink.isValid())
		{
			JOptionPane.showMessageDialog(this, "The link is not a valid URL.");
			return;
		}

		News n = new News();
		n.setTitle(vtfTitle.getText());
		n.setAuthor(vtfAuthor.getText());
		n.setContent(taContent.getText());
		n.setLink(vtfLink.getText());
		n.setDescription(taDescription.getText());
		
		currentNewsChannel.addNews(n);
		
		listeners.get("onDataAdded").accept(1);
		
		pnlAddNews.setVisible(false);
		pnlNewsList.setVisible(true);

	}
	
	private void btnSearch_Click()
	{
		reloadNewsList();
	}
	
	private boolean newsContainsTest(News n)
	{
		boolean ans = false;
		
		if (n.getTitle().contains(txtSearch.getText()))
		{
			ans = true;
		}
		
		if (n.getAuthor().contains(txtSearch.getText()))
		{
			ans = true;
		}
		
		if (n.getContent().contains(txtSearch.getText()))
		{
			ans = true;
		}
		
		if (n.getDescription().contains(txtSearch.getText()))
		{
			ans = true;
		}
		
		if (n.getLink().contains(txtSearch.getText()))
		{
			ans = true;
		}
		
		if (n.getPubblicationDate().contains(txtSearch.getText()))
		{
			ans = true;
		}
		
		return ans;
	}
}
