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
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import ap2016.entities.News;
import ap2016.entities.NewsChannel;
import ap2016.entities.Role;
import ap2016.entities.User;

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
	
	public NewsListPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		pnlNewsList = new JPanel();
		add(pnlNewsList);
		pnlNewsList.setLayout(new BoxLayout(pnlNewsList, BoxLayout.Y_AXIS));
		
		scrollableMainPanel = new JPanel();
		scrollableMainPanel.setPreferredSize(new Dimension(300, 10));
		scrollableMainPanel.setMaximumSize(new Dimension(300, 32767));
		scrollableMainPanel.setLayout(new BoxLayout(scrollableMainPanel, BoxLayout.Y_AXIS));
		
		scrollPaneMain = new JScrollPane(scrollableMainPanel);
		scrollPaneMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				onNewsExtractClick(((NewsExtractDisplayPanel)
								scrollableMainPanel.getComponentAt(arg0.getX(), arg0.getY() + scrollPaneMain.getVerticalScrollBar().getValue())
								).getNews());
				
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
		panel.add(btnSearch);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel.add(horizontalGlue);
		
		btnAdd = new JButton("Add");
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
				tmp = new NewsExtractDisplayPanel(n);
				scrollableMainPanel.add(tmp);
				height += tmp.getPreferredSize().getHeight();
			}
		}
		
		if (currentUser.hasRole(Role.ADD_NEWS))
		{
			btnAdd.setVisible(true);
			btnAdd.setEnabled(true);
		}else{
			btnAdd.setVisible(false);
			btnAdd.setEnabled(false);
		}
		
		if (currentUser.hasRole(Role.DELETE_NEWS))
		{
			btnRemove.setVisible(true);
			btnRemove.setEnabled(true);
		}else{
			btnRemove.setVisible(false);
			btnRemove.setEnabled(false);
		}
		
		if (currentUser.hasRole(Role.SEARCH))
		{
			btnSearch.setVisible(true);
			btnSearch.setEnabled(true);
			txtSearch.setVisible(true);
			txtSearch.setEditable(true);
		}else{
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
		lblContent.setText("<html><head><style>div#mc{width:"+ pnlNewsList.getPreferredSize().getWidth() + "px;}</style></head><body><div id=\"mc\">" + n.getContent() + "</div></body></html>");
		
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
		Object[] options = {"Yes", "No"};
		if (JOptionPane.showOptionDialog(this, "Are you sure you want to delete this news?", "Warning",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]) == JOptionPane.YES_OPTION)
		{
			currentNewsChannel.removeNews(currentNews);
			currentNews = null;
			btnBack_Click();
		}
	}
}
