package ap2016.gui.utilities;


import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import ap2016.entities.NewsChannel;


/**
 * Represent a list renderer in which each component is drawn as a JCheckBox.
 * @author Matteo Nardini
 *
 */
public class NewsChannelCheckboxListCellRenderer implements ListCellRenderer<NewsChannel>
{
	/**
	 * Contains the list of the element that should be drawn as "selected".
	 */
	private ArrayList<NewsChannel> selectedChannels;

	/**
	 * Creates a new renderer with a given list of elements that should be drawn as "selected".
	 * @param selectedChannels The list of elements that should be drawn as "selected".
	 */
	public NewsChannelCheckboxListCellRenderer(ArrayList<NewsChannel> selectedChannels)
	{
		this.selectedChannels = selectedChannels;
	}

	/**
	 * Returns a component that is able to draw the current cell of the list.
	 */
	@Override
	public Component getListCellRendererComponent(JList<? extends NewsChannel> list, NewsChannel value, int index,
			boolean isSelected, boolean cellHasFocus)
	{

		JCheckBox ans = new JCheckBox(value.toString());

		ans.setComponentOrientation(list.getComponentOrientation());
		ans.setFont(list.getFont());
		ans.setBackground(list.getBackground());
		ans.setForeground(list.getForeground());
		ans.setSelected(this.selectedChannels.contains(value));
		ans.setEnabled(list.isEnabled());

		return ans;
	}

	/**
	 * Allows to update the list of elements that should be drawn as "selected".
	 * @param selectedChannels The list of elements that should be drawn as "selected".
	 */
	public void updateCurrentState(ArrayList<NewsChannel> selectedChannels)
	{
		this.selectedChannels = selectedChannels;
	}

}
