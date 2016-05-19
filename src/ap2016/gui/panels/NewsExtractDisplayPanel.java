package ap2016.gui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import ap2016.entities.News;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class NewsExtractDisplayPanel extends JPanel
{
	private JLabel lblTitle;
	private JLabel lblAuthor;
	private JLabel lblDescription;
	
	public NewsExtractDisplayPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		horizontalStrut.setMaximumSize(new Dimension(10, 0));
		add(horizontalStrut);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		verticalStrut_2.setMaximumSize(new Dimension(0, 10));
		panel.add(verticalStrut_2);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		Component horizontalGlue = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue);
		
		lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_1.add(lblTitle);
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_1.add(horizontalGlue_1);
		
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		verticalStrut_3.setMaximumSize(new Dimension(0, 10));
		panel.add(verticalStrut_3);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setMaximumSize(new Dimension(20, 0));
		panel_2.add(horizontalStrut_2);
		
		JLabel lblNewLabel = new JLabel("Author:");
		panel_2.add(lblNewLabel);
		
		lblAuthor = new JLabel("AUTHOR");
		panel_2.add(lblAuthor);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panel_2.add(horizontalGlue_2);
		
		JLabel lblNewLabel_1 = new JLabel("Pubblication date:");
		panel_2.add(lblNewLabel_1);
		
		JLabel lblPubDate = new JLabel("PUBLICATION DATE");
		panel_2.add(lblPubDate);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setMaximumSize(new Dimension(20, 0));
		panel_2.add(horizontalStrut_3);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		verticalStrut_4.setMaximumSize(new Dimension(0, 20));
		panel.add(verticalStrut_4);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		horizontalGlue_3.setMinimumSize(new Dimension(10, 0));
		panel_3.add(horizontalGlue_3);
		
		lblDescription = new JLabel("Description");
		lblDescription.setMaximumSize(new Dimension(5000, 500));
		lblDescription.setVerticalAlignment(SwingConstants.TOP);
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblDescription);
		
		Component horizontalGlue_4 = Box.createHorizontalGlue();
		horizontalGlue_4.setMinimumSize(new Dimension(10, 0));
		panel_3.add(horizontalGlue_4);
		
		Component verticalStrut = Box.createVerticalStrut(10);
		verticalStrut.setMaximumSize(new Dimension(0, 10));
		panel.add(verticalStrut);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		horizontalStrut_1.setMaximumSize(new Dimension(10, 0));
		add(horizontalStrut_1);
	}

	
	public NewsExtractDisplayPanel(News n) {
		this();
		lblTitle.setText(n.getTitle());
		lblAuthor.setText(n.getAuthor());
		lblDescription.setText("<html>" + n.getDescription() + "</html>");
	}
}
