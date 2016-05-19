package ap2016.gui.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.function.Consumer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

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
	private JScrollPane scrollPaneMain;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnRemove;
	private JPanel scrollableMainPanel;
	private JPanel pnlNewsList;
	private JPanel pnlNewsDetail;
	private JButton btnBack;
	
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
		
		pnlNewsDetail = new JPanel();
		pnlNewsDetail.setBackground(Color.ORANGE);
		pnlNewsDetail.setVisible(false);
		add(pnlNewsDetail);
		
		btnBack = new JButton("Back to the news list");
		btnBack.addActionListener(e -> btnBack_Click());
		pnlNewsDetail.add(btnBack);
		
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
		pnlNewsDetail.add(new JLabel(n.getTitle()));
	}
	
	private void btnBack_Click()
	{
		reloadNewsList();
		pnlNewsDetail.setVisible(false);
		pnlNewsList.setVisible(true);
	}
}
