package ap2016.gui.frames;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ap2016.entities.News;
import ap2016.entities.NewsChannel;
import ap2016.gui.utilities.NewsChannelCheckboxListCellRenderer;
import ap2016.io.NewsChannelDataProvider;


public class ExportJDialog extends JDialog
{
	/**
	 * Serial version UUID.
	 */
	private static final long serialVersionUID = 6927333572998759671L;
	private String exportFormat;
	private JList<NewsChannel> lChannels;
	private ArrayList<NewsChannel> selectedChannels;

	/**
	 * Constructor used by the GUI builder.
	 */
	public ExportJDialog()
	{
		setupGUI();
	}

	/**
	 * Shows the export dialog that allows the user to choose which news channel to export.
	 * @param parent The JFrame parent of this dialog.
	 * @param exportFormat The format to use to export the selected news channel.
	 */
	public ExportJDialog(JFrame parent, String exportFormat)
	{
		super(parent, "Export news channel", true);
		setupGUI();

		this.exportFormat = exportFormat;
		this.selectedChannels = new ArrayList<>();

		DefaultListModel<NewsChannel> a = new DefaultListModel<>();

		for (NewsChannel r : NewsChannelDataProvider.getInstance().getData())
		{
			a.addElement(r);
		}

		this.lChannels.setModel(a);
		this.lChannels.setCellRenderer(new NewsChannelCheckboxListCellRenderer(this.selectedChannels));

	}

	/**
	 * Aborts the export operation.
	 */
	private void btnAbort_Click()
	{
		setVisible(false);
	}

	/**
	 * Exports the selected news channel to a file.
	 */
	private void btnExport_Click()
	{
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int ans = fc.showSaveDialog(this);

		boolean successful = false;

		if (ans == JFileChooser.APPROVE_OPTION)
		{
			switch (this.exportFormat)
			{
				case "HTML":
					successful = exportAsHTML(fc.getSelectedFile());
					break;
				case "RTF":
					successful = exportAsRTF(fc.getSelectedFile());
					break;
			}
		}

		if (successful)
		{
			JOptionPane.showMessageDialog(this, "File exported correctly");
		} else
		{
			JOptionPane.showMessageDialog(this, "The file was not exported correctly");
		}

		setVisible(false);
	}

	/**
	 * Converts the selected news channel to an HTML file.
	 * @param f The file in which the HTML output has to be written.
	 * @return The method returns {@code true} if the operation succeeded, {@code false} otherwise.
	 */
	private boolean exportAsHTML(File f)
	{
		Document doc = null;
		try
		{
			Element tmp;
			StringBuilder help = new StringBuilder();

			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

			Element html = doc.createElement("html");
			Element head = doc.createElement("head");

			tmp = doc.createElement("title");
			tmp.appendChild(doc.createTextNode("News feed data export"));
			head.appendChild(tmp);

			tmp = doc.createElement("meta");
			tmp.setAttribute("charset", "utf-8");
			head.appendChild(tmp);

			tmp = doc.createElement("style");
			tmp.appendChild(doc.createTextNode(""));
			head.appendChild(tmp);

			Element body = doc.createElement("body");

			tmp = doc.createElement("h1");
			tmp.appendChild(doc.createTextNode("News feed export"));
			body.appendChild(tmp);

			tmp = doc.createElement("h3");
			this.selectedChannels.forEach(nc -> help.append(nc + ", "));
			tmp.appendChild(
					doc.createTextNode("Exported news channel: " + help.subSequence(0, help.length() - 2).toString()));
			body.appendChild(tmp);

			Element div;
			Element table;
			Element tr;
			for (NewsChannel nc : this.selectedChannels)
			{
				div = doc.createElement("div");

				tmp = doc.createElement("h4");
				tmp.appendChild(doc.createTextNode("News channel: " + nc));
				div.appendChild(tmp);

				table = doc.createElement("table");

				tr = doc.createElement("tr");

				tmp = doc.createElement("td");
				tmp.appendChild(doc.createTextNode("Title"));
				tr.appendChild(tmp);

				tmp = doc.createElement("td");
				tmp.appendChild(doc.createTextNode("Author"));
				tr.appendChild(tmp);

				tmp = doc.createElement("td");
				tmp.appendChild(doc.createTextNode("Pubblication date"));
				tr.appendChild(tmp);

				tmp = doc.createElement("td");
				tmp.appendChild(doc.createTextNode("Link"));
				tr.appendChild(tmp);

				tmp = doc.createElement("td");
				tmp.appendChild(doc.createTextNode("Description"));
				tr.appendChild(tmp);

				tmp = doc.createElement("td");
				tmp.appendChild(doc.createTextNode("Content"));
				tr.appendChild(tmp);

				table.appendChild(tr);

				for (News n : nc.getNews())
				{
					tr = doc.createElement("tr");

					tmp = doc.createElement("td");
					tmp.appendChild(doc.createTextNode(n.getTitle()));
					tr.appendChild(tmp);

					tmp = doc.createElement("td");
					tmp.appendChild(doc.createTextNode(n.getAuthor()));
					tr.appendChild(tmp);

					tmp = doc.createElement("td");
					tmp.appendChild(doc.createTextNode(n.getPubblicationDate()));
					tr.appendChild(tmp);

					tmp = doc.createElement("td");
					tmp.appendChild(doc.createTextNode(n.getLink()));
					tr.appendChild(tmp);

					tmp = doc.createElement("td");
					tmp.appendChild(doc.createTextNode(n.getDescription()));
					tr.appendChild(tmp);

					tmp = doc.createElement("td");
					tmp.appendChild(doc.createTextNode(n.getContent()));
					tr.appendChild(tmp);

					table.appendChild(tr);
				}

				div.appendChild(table);
				body.appendChild(div);
			}

			html.appendChild(head);
			html.appendChild(body);
			doc.appendChild(html);

		} catch (ParserConfigurationException e)
		{
		}

		Transformer t;
		try
		{
			t = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(
					Paths.get(f.getAbsolutePath(), "HTMLexport-" + (new Date()).getTime() + ".html").toFile());
			t.transform(source, result);
		} catch (TransformerFactoryConfigurationError | TransformerException e)
		{
			return false;
		}

		return true;
	}

	/**
	 * Converts the selected news channel to a RTF file.
	 * @param f The file in which the RTF output has to be written.
	 * @return The method returns {@code true} if the operation succeeded, {@code false} otherwise.
	 */
	private boolean exportAsRTF(File f)
	{
		StringBuilder rtf = new StringBuilder();

		rtf.append("{\\rtf1\\ansi\\deff0");

		for (NewsChannel nc : this.selectedChannels)
		{
			rtf.append(nc.getTitle() + "\\line\\par");
			rtf.append("\\line");
			rtf.append("\\trowd");
			rtf.append("\\cellx1000");
			rtf.append("\\cellx2000");
			rtf.append("\\cellx3000");
			rtf.append("\\cellx4000");
			rtf.append("\\cellx5000");
			rtf.append("\\cellx6000\n");
			rtf.append("Title\\intbl\\cell\n");
			rtf.append("Author\\intbl\\cell\n");
			rtf.append("Pubblication date\\intbl\\cell\n");
			rtf.append("Link\\intbl\\cell\n");
			rtf.append("Description\\intbl\\cell\n");
			rtf.append("Content\\intbl\\cell\n");
			rtf.append("\\row\n");

			for (News n : nc.getNews())
			{
				rtf.append(n.getTitle() + "\\intbl\\cell\n");
				rtf.append(n.getAuthor() + "\\intbl\\cell\n");
				rtf.append(n.getPubblicationDate() + "\\intbl\\cell\n");
				rtf.append(n.getLink() + "\\intbl\\cell\n");
				rtf.append(n.getDescription() + "\\intbl\\cell\n");
				rtf.append(n.getContent() + "\\intbl\\cell\n");
				rtf.append("\\row\n");
			}
			rtf.append("\\pard\\par\n");
		}

		rtf.append("}");

		try (FileWriter fw = new FileWriter(
				Paths.get(f.getAbsolutePath(), "RTFexport-" + (new Date()).getTime() + ".rtf").toFile()))
		{
			fw.write(rtf.toString());
		} catch (IOException ex)
		{
			return false;
		}

		return true;
	}

	/**
	 * Creates all the GUI of the dialog.
	 */
	private void setupGUI()
	{
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		setPreferredSize(new Dimension(400, 400));

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_4);

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		Component verticalStrut = Box.createVerticalStrut(20);
		panel.add(verticalStrut);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(192, 192, 192), 1, true),
				"Select channels to export:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setLayout(new BorderLayout(0, 0));

		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		panel_1.add(horizontalStrut_2, BorderLayout.WEST);

		Component horizontalStrut_3 = Box.createHorizontalStrut(10);
		panel_1.add(horizontalStrut_3, BorderLayout.EAST);

		Component verticalStrut_4 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_4, BorderLayout.NORTH);

		Component verticalStrut_5 = Box.createVerticalStrut(10);
		panel_1.add(verticalStrut_5, BorderLayout.SOUTH);

		this.lChannels = new JList<>();
		this.lChannels.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent arg0)
			{

				NewsChannel nc = ExportJDialog.this.lChannels.getSelectedValue();
				if (ExportJDialog.this.selectedChannels.contains(nc))
				{
					ExportJDialog.this.selectedChannels.remove(nc);
				} else
				{
					ExportJDialog.this.selectedChannels.add(nc);
				}
				ExportJDialog.this.lChannels.repaint();

			}
		});
		panel_1.add(this.lChannels, BorderLayout.CENTER);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_1);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		JButton btnAbort = new JButton("Abort");
		btnAbort.setPreferredSize(new Dimension(65, 30));
		btnAbort.setMinimumSize(new Dimension(65, 30));
		btnAbort.setMaximumSize(new Dimension(500, 30));
		btnAbort.addActionListener(e -> btnAbort_Click());
		panel_2.add(btnAbort);

		Component horizontalStrut = Box.createHorizontalStrut(40);
		panel_2.add(horizontalStrut);

		JButton btnExport = new JButton("Export");
		btnExport.setMinimumSize(new Dimension(71, 30));
		btnExport.setPreferredSize(new Dimension(71, 30));
		btnExport.addActionListener(e -> btnExport_Click());
		btnExport.setMaximumSize(new Dimension(500, 30));
		panel_2.add(btnExport);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel.add(verticalStrut_2);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		getContentPane().add(horizontalStrut_5);
	}

}
