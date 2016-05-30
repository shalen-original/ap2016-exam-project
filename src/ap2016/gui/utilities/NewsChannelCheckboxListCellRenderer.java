package ap2016.gui.utilities;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import ap2016.entities.Role;
import ap2016.entities.User;

public class NewsChannelCheckboxListCellRenderer implements ListCellRenderer<Role>
{
	private User currentUser;
	
	public NewsChannelCheckboxListCellRenderer()
	{
		this.currentUser = null;
	}
	
	public void updateCurrentUser(User newUser)
	{
		this.currentUser = newUser;
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Role> list, Role value, int index,
			boolean isSelected, boolean cellHasFocus)
	{

		JCheckBox ans = new JCheckBox(value.toString());
		
		ans.setComponentOrientation(list.getComponentOrientation());
		ans.setFont(list.getFont());
		ans.setBackground(list.getBackground());
		ans.setForeground(list.getForeground());
		ans.setSelected(currentUser == null ? false : currentUser.hasRole(value));
		ans.setEnabled(list.isEnabled());

		return ans;
	}

}
