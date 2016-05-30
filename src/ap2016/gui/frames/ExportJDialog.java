package ap2016.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import ap2016.entities.Role;
import ap2016.entities.User;
import ap2016.gui.utilities.RoleCheckboxListCellRenderer;

@SuppressWarnings("serial")
public class ExportJDialog extends JDialog
{
	User currentUser;
	private JList<Role> lChannels;
	
	public ExportJDialog() {
		setupGUI();
	}
	
	public ExportJDialog(JFrame parent, User currentUser){
		super(parent, "Manage users", true);
		setupGUI();
		
		this.currentUser = currentUser;
		
		DefaultListModel<Role> a = new DefaultListModel<>();
		
		for(Role r : Role.values())
		{
			a.addElement(r);
		}
		
		lChannels.setModel(a);
		lChannels.setCellRenderer(new RoleCheckboxListCellRenderer());		
		
	}
	
	private void setupGUI()
	{
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		setPreferredSize(new Dimension(300, 300));
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_4);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel.add(verticalStrut);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true), "Select channels to export:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setLayout(new BorderLayout(0, 0));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		panel_1.add(horizontalStrut_2, BorderLayout.WEST);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(10);
		panel_1.add(horizontalStrut_3, BorderLayout.EAST);
		
		Component verticalStrut_4 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_4, BorderLayout.NORTH);
		
		Component verticalStrut_5 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_5, BorderLayout.SOUTH);
		
		lChannels = new JList<>();
		lChannels.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				
				
				lChannels.repaint();
				
			}
		});
		panel_1.add(lChannels, BorderLayout.CENTER);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JButton btnAbort = new JButton("Abort");
		btnAbort.setMaximumSize(new Dimension(500, 26));
		panel_2.add(btnAbort);
		
		Component horizontalStrut = Box.createHorizontalStrut(40);
		panel_2.add(horizontalStrut);
		
		JButton btnExport = new JButton("Export");
		btnExport.setMaximumSize(new Dimension(500, 23));
		panel_2.add(btnExport);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_2);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_5);
	}

	
}
