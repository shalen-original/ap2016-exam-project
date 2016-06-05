package tests.ap2016.gui.utilities;


import javax.swing.ImageIcon;

import org.junit.Test;

import ap2016.gui.utilities.AvatarImageDisplay;


@SuppressWarnings("unused")
public class TestAvatarImageDisplay
{

	@Test
	public void testAvatarImageDisplay()
	{
		AvatarImageDisplay a = new AvatarImageDisplay();
	}

	@Test
	public void testAvatarImageDisplayImageIcon()
	{
		AvatarImageDisplay a = new AvatarImageDisplay(new ImageIcon());
	}

	@Test
	public void testSetIconImageIcon()
	{
		AvatarImageDisplay a = new AvatarImageDisplay(new ImageIcon());
		a.setIcon(new ImageIcon());
	}

}
