package ap2016.gui.utilities;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFormattedTextField;

import ap2016.application.ApplicationConstants;

public class ValidationKeyAdapter extends KeyAdapter
{

	@Override
	public void keyReleased(KeyEvent e) {
		
		JFormattedTextField jftf = (JFormattedTextField) e.getSource();
		
		if (jftf.isEditValid())
		{
			jftf.setBorder(ApplicationConstants.validBorder);
		}else{
			jftf.setBorder(ApplicationConstants.invalidBorder);
		}
	}
	
}
