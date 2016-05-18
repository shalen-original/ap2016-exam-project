package ap2016.gui.utilities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class AvatarImageDisplay extends JLabel
{
	ImageIcon img;
	
	public AvatarImageDisplay()
	{
		this(null);
	}
	
    public AvatarImageDisplay(ImageIcon icon)
    {
        super();
        this.img = icon;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D)g;
        
        if (img != null)
        {
        	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        	g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        	g.drawImage(img.getImage(),0,0,this.getWidth(),this.getHeight(),this);
        }
    }
   
    public void setIcon(ImageIcon i)
    {
    	img = i;
    	super.setIcon(i);
    }
}
