package ap2016.gui.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ap2016.entities.News;
import ap2016.entities.NewsChannel;
import ap2016.entities.Role;
import ap2016.entities.User;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class NewsListPanel extends JPanel
{
	private HashMap<String, Consumer<Integer>> listeners;
	private User currentUser;
	private NewsChannel currentNewsChannel;
	private JScrollPane scrollPaneMain;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnRemove;
	private JPanel scrollableMainPanel;
	
	public NewsListPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel pnlNewsList = new JPanel();
		add(pnlNewsList);
		pnlNewsList.setLayout(new BoxLayout(pnlNewsList, BoxLayout.Y_AXIS));
		
		scrollableMainPanel = new JPanel();
		scrollableMainPanel.setLayout(new BoxLayout(scrollableMainPanel, BoxLayout.Y_AXIS));
		
		scrollPaneMain = new JScrollPane(scrollableMainPanel);
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
		txtSearch.setPreferredSize(new Dimension(500, 20));
		txtSearch.setMinimumSize(new Dimension(500, 20));
		txtSearch.setMaximumSize(new Dimension(500, 2147483647));
		txtSearch.setColumns(10);
		panel.add(txtSearch);
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		horizontalStrut.setMaximumSize(new Dimension(10, 0));
		panel.add(horizontalStrut);
		
		btnSearch = new JButton("Search");
		panel.add(btnSearch);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalGlue.setMaximumSize(new Dimension(300, 0));
		panel.add(horizontalGlue);
		
		btnAdd = new JButton("Add");
		panel.add(btnAdd);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		horizontalStrut_1.setMaximumSize(new Dimension(10, 0));
		panel.add(horizontalStrut_1);
		
		btnRemove = new JButton("Remove");
		panel.add(btnRemove);
		
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
		
		scrollableMainPanel.removeAll();
		
		int height = 0;
		JPanel tmp;
		
		if (currentUser.hasRole(Role.READ))
		{
			for (News n : nc.getNews())
			{
				tmp = new NewsExtractDisplayPanel(n);
				scrollableMainPanel.add(tmp);
				height += tmp.getPreferredSize().getWidth();
			}
		}
		
		scrollableMainPanel.setPreferredSize(new Dimension(scrollableMainPanel.getWidth(), height));
		scrollableMainPanel.revalidate();
		
	}
	
	public void updateCurrentUser(User u)
	{
		this.currentUser = u;
		
		// For permission check
		updateCurrentNewsChannel(currentNewsChannel);
	}
}
