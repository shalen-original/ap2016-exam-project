package ap2016.gui.utilities;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Predicate;

import javax.swing.JPasswordField;

import ap2016.application.ApplicationUtilities;

@SuppressWarnings("serial")
public class ValidablePasswordField extends JPasswordField
{
	Predicate<char[]> test;
	boolean isValid;
	
	public ValidablePasswordField(Predicate<char[]> test)
	{
		this.test = test;
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateValidationState();
			}
		});
		
		updateValidationState();
	}
	
	public ValidablePasswordField()
	{
		this(s -> true);
	}
	
	public boolean isValid()
	{
		return this.isValid;
	}
	
	public void updateValidationTest(Predicate<char[]> test)
	{
		this.test = test;
		updateValidationState();
	}
	
	public void updateValidationState()
	{	
		try {
			isValid = test.test(this.getPassword());
		}catch(Exception ex){
			isValid = false;
		}
		ApplicationUtilities.formatComponent(this, isValid);	
	}
	
	@Override
	public void setText(String t)
	{
		super.setText(t);
		updateValidationState();
	}
}
