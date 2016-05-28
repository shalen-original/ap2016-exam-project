package ap2016.gui.frames;

import javax.swing.JDialog;
import javax.swing.JFrame;

import ap2016.entities.User;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import javax.swing.JList;

@SuppressWarnings("serial")
public class ManageUsersJDialog extends JDialog
{
	User currentUser;
	private JComboBox cmbUsers;
	
	public ManageUsersJDialog() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(10);
		getContentPane().add(horizontalStrut_4);
		
		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_4.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut);
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 28));
		panel_2.add(panel);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Currently inspecting user:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		panel_3.add(verticalStrut_2);
		
		cmbUsers = new JComboBox();
		panel_3.add(cmbUsers);
		
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		panel_3.add(verticalStrut_3);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1);
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Roles of the selected user:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		panel_1.add(horizontalStrut_2, BorderLayout.WEST);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(10);
		panel_1.add(horizontalStrut_3, BorderLayout.EAST);
		
		Component verticalStrut_4 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_4, BorderLayout.NORTH);
		
		Component verticalStrut_5 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_5, BorderLayout.SOUTH);
		
		JList list = new JList();
		panel_1.add(list, BorderLayout.CENTER);
		
		Component verticalStrut_6 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_6);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(10);
		getContentPane().add(horizontalStrut_5);
		
	}
	
	public ManageUsersJDialog(JFrame parent) {

		super(parent, "Manage users", true);
		
	}
	
	public ManageUsersJDialog(JFrame parent, User currentUser){
		this(parent);
		
		this.currentUser = currentUser;
	}

}
