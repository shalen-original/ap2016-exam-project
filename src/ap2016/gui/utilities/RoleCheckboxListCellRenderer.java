package ap2016.gui.utilities;


import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import ap2016.entities.Role;
import ap2016.entities.User;


/**
 * Represent a list renderer in which each component is drawn as a JCheckBox.
 * @author Matteo Nardini
 *
 */
public class RoleCheckboxListCellRenderer implements ListCellRenderer<Role>
{
	/**
	 * Contains the user whose roles have to be displayed.
	 */
	private User currentUser;

	/**
	 * Creates a new renderer with a {@code null} user.
	 */
	public RoleCheckboxListCellRenderer()
	{
		this.currentUser = null;
	}

	/**
	 * Returns a component that is able to draw the current cell of the list.
	 */
	@Override
	public Component getListCellRendererComponent(JList<? extends Role> list, Role value, int index, boolean isSelected,
			boolean cellHasFocus)
	{

		JCheckBox ans = new JCheckBox(value.toString());

		ans.setComponentOrientation(list.getComponentOrientation());
		ans.setFont(list.getFont());
		ans.setBackground(list.getBackground());
		ans.setForeground(list.getForeground());
		ans.setSelected(this.currentUser == null ? false : this.currentUser.hasRole(value));
		ans.setEnabled(list.isEnabled());

		return ans;
	}

	/**
	 * Allows to set the user whose roles have to be displayed.
	 * @param newUser The new user whose roles have to be displayed.
	 */
	public void updateCurrentUser(User newUser)
	{
		this.currentUser = newUser;
	}

}
