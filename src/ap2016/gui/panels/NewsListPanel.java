package ap2016.gui.panels;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class NewsListPanel extends JPanel
{
	private JTextField textField;
	public NewsListPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		Component verticalStrut = Box.createVerticalStrut(10);
		add(verticalStrut);
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 30));
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		textField = new JTextField();
		textField.setSize(new Dimension(500, 0));
		textField.setPreferredSize(new Dimension(500, 20));
		textField.setMinimumSize(new Dimension(500, 20));
		textField.setMaximumSize(new Dimension(500, 2147483647));
		textField.setColumns(10);
		panel.add(textField);
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		horizontalStrut.setMaximumSize(new Dimension(10, 0));
		panel.add(horizontalStrut);
		
		JButton button = new JButton("Search");
		panel.add(button);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalGlue.setMaximumSize(new Dimension(300, 0));
		panel.add(horizontalGlue);
		
		JButton button_1 = new JButton("Add");
		panel.add(button_1);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		horizontalStrut_1.setMaximumSize(new Dimension(10, 0));
		panel.add(horizontalStrut_1);
		
		JButton button_2 = new JButton("Remove");
		panel.add(button_2);
	}

}
