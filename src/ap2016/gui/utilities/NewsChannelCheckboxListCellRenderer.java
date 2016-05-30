package ap2016.gui.utilities;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import ap2016.entities.NewsChannel;

public class NewsChannelCheckboxListCellRenderer implements ListCellRenderer<NewsChannel>
{
	private ArrayList<NewsChannel> selectedChannels;
	
	public NewsChannelCheckboxListCellRenderer(ArrayList<NewsChannel> selectedChannels)
	{
		this.selectedChannels = selectedChannels;
	}
	
	public void updateCurrentState(ArrayList<NewsChannel> selectedChannels)
	{
		this.selectedChannels = selectedChannels;
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends NewsChannel> list, NewsChannel value, int index,
			boolean isSelected, boolean cellHasFocus)
	{

		JCheckBox ans = new JCheckBox(value.toString());
		
		ans.setComponentOrientation(list.getComponentOrientation());
		ans.setFont(list.getFont());
		ans.setBackground(list.getBackground());
		ans.setForeground(list.getForeground());
		ans.setSelected(selectedChannels.contains(value));
		ans.setEnabled(list.isEnabled());

		return ans;
	}

}
