package ap2016.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
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

import ap2016.entities.NewsChannel;
import ap2016.gui.utilities.NewsChannelCheckboxListCellRenderer;
import ap2016.io.NewsChannelDataProvider;

@SuppressWarnings("serial")
public class ExportJDialog extends JDialog
{
	private String exportFormat;
	private JList<NewsChannel> lChannels;
	private ArrayList<NewsChannel> selectedChannels;
	
	public ExportJDialog() {
		setupGUI();
	}
	
	public ExportJDialog(JFrame parent, String exportFormat){
		super(parent, "Export news channel", true);
		setupGUI();
		
		this.exportFormat = exportFormat;
		this.selectedChannels = new ArrayList<>();
		
		DefaultListModel<NewsChannel> a = new DefaultListModel<>();
		
		for(NewsChannel r : NewsChannelDataProvider.getInstance().getData())
		{
			a.addElement(r);
		}
		
		lChannels.setModel(a);
		lChannels.setCellRenderer(new NewsChannelCheckboxListCellRenderer(selectedChannels));		
		
	}
	
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
				
				NewsChannel nc = (NewsChannel)lChannels.getSelectedValue();
				if (selectedChannels.contains(nc))
				{
					selectedChannels.remove(nc);
				}else{
					selectedChannels.add(nc);
				}
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

	
	private void btnAbort_Click()
	{
		setVisible(false);
	}
	
	private void btnExport_Click()
	{
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int ans = fc.showSaveDialog(this);
		
		boolean successful = false;
		
		if (ans == JFileChooser.APPROVE_OPTION)
		{
			switch (exportFormat)
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
		}else{
			JOptionPane.showMessageDialog(this, "The file was not exported correctly");
		}
		
		setVisible(false);
	}
	
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
			
				/*tmp = doc.createElement("title");
				tmp.appendChild(doc.createTextNode("News feed data export"));
				head.appendChild(tmp);
				
				tmp = doc.createElement("meta");
				tmp.setAttribute("charset", "utf-8");
				head.appendChild(tmp);
	
				tmp = doc.createElement("style");
				tmp.appendChild(doc.createTextNode(""));
				head.appendChild(tmp);*/
			
			Element body = doc.createElement("body");
				
				/*tmp = doc.createElement("h1");
				tmp.appendChild(doc.createTextNode("News feed export"));
				body.appendChild(tmp);
				
				
				
				
				tmp = doc.createElement("h4");
				
				
				
				tmp.appendChild(doc.createTextNode("Exported news channel:"));
				body.appendChild(tmp);*/
			
			
			
			html.appendChild(head);
			html.appendChild(body);
			doc.appendChild(html);
			
		} catch (ParserConfigurationException e) {}
		

		Transformer t;
		try
		{
			t = TransformerFactory.newInstance().newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(f.getAbsolutePath() + "\\HTMLexport-" + (new Date()).getTime() + ".html"));
			t.transform(source, result);
		} catch (TransformerFactoryConfigurationError | TransformerException e) 
		{
			return false;
		}
		
		return true;
	}
	
	private boolean exportAsRTF(File f)
	{
		return false;
	}
	
}
