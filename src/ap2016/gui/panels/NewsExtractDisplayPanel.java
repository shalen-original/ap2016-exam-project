package ap2016.gui.panels;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import ap2016.entities.News;


/**
 * This panels allows to show the summary of a single news.
 * @author Matteo Nardini
 *
 */

public class NewsExtractDisplayPanel extends JPanel
{
	/**
	 * Serial version UUID.
	 */
	private static final long serialVersionUID = 8193596439517454883L;

	/**
	 * Contains the {@link ap2016.entities.News#News() News} currently displayed by this panel.
	 */
	private News currentNews;

	private JLabel lblTitle;
	private JLabel lblAuthor;
	private JLabel lblDescription;
	private JLabel lblPubDate;

	/**
	 * Constructor used by the GUI builder.
	 */
	public NewsExtractDisplayPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

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

		this.lblTitle = new JLabel("Title");
		this.lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_1.add(this.lblTitle);

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

		Component horizontalStrut = Box.createHorizontalStrut(4);
		panel_2.add(horizontalStrut);

		this.lblAuthor = new JLabel("AUTHOR");
		panel_2.add(this.lblAuthor);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panel_2.add(horizontalGlue_2);

		JLabel lblNewLabel_1 = new JLabel("Pubblication date:");
		panel_2.add(lblNewLabel_1);

		Component horizontalStrut_1 = Box.createHorizontalStrut(4);
		panel_2.add(horizontalStrut_1);

		this.lblPubDate = new JLabel("PUBLICATION DATE");
		panel_2.add(this.lblPubDate);

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
		horizontalGlue_3.setMinimumSize(new Dimension(20, 0));
		panel_3.add(horizontalGlue_3);

		this.lblDescription = new JLabel("Description");
		this.lblDescription.setMaximumSize(new Dimension(5000, 500));
		this.lblDescription.setVerticalAlignment(SwingConstants.TOP);
		this.lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(this.lblDescription);

		Component horizontalGlue_4 = Box.createHorizontalGlue();
		horizontalGlue_4.setMinimumSize(new Dimension(20, 0));
		panel_3.add(horizontalGlue_4);

		Component verticalStrut = Box.createVerticalStrut(15);
		verticalStrut.setMaximumSize(new Dimension(0, 10));
		panel.add(verticalStrut);
	}

	/**
	 * Creates a new news extract display panel that displays the summary of a given news.
	 * @param n The news which summary has to be displayed.
	 */
	public NewsExtractDisplayPanel(News n)
	{
		this();
		this.currentNews = n;
		this.lblTitle.setText(n.getTitle());
		this.lblAuthor.setText(n.getAuthor());
		this.lblPubDate.setText(n.getPubblicationDate());
		this.lblDescription.setText("<html><head><style>div#mc{width:" + (getPreferredSize().getWidth() - 10)
				+ "px;text-align:center;}</style></head><body><div id=\"mc\">" + this.currentNews.getDescription()
				+ "</div></body></html>");
	}

	/**
	 * Allows to access the new displayed by this panel.
	 * @return The {@link ap2016.entities.News#News() News} which summary is displayed by this panel.
	 */
	public News getNews()
	{
		return this.currentNews;
	}
}
