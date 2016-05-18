package ap2016.gui.utilities;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Predicate;

import javax.swing.JTextField;

import ap2016.application.ApplicationUtilities;

@SuppressWarnings("serial")
public class ValidableTextField extends JTextField
{

	Predicate<String> test;
	boolean isValid;
	
	public ValidableTextField(Predicate<String> test)
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
	
	public ValidableTextField()
	{
		this(s -> true);
	}
	
	public boolean isValid()
	{
		return this.isValid;
	}
	
	public void updateValidationState()
	{	
		try {
			isValid = test.test(this.getText());
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
	
	public void updateValidationTest(Predicate<String> test)
	{
		this.test = test;
		updateValidationState();
	}
	
}
