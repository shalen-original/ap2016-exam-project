package ap2016.gui.utilities;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Predicate;

import javax.swing.JPasswordField;

import ap2016.application.ApplicationUtilities;

/**
 * This class represents a JPasswordField that is "validable". To be "validable" means that the input is checked: if it is considered valid, then the component gets formatted one way, if it is invalid the component will be formatted in another one.
 * @author Matteo Nardini
 *
 */
@SuppressWarnings("serial")
public class ValidablePasswordField extends JPasswordField
{
	/**
	 * Contains the function used to validate the input.
	 */
	Predicate<char[]> test;
	/**
	 * Contains whether the current input is valid or not.
	 */
	boolean isValid;
	
	/**
	 * Creates a new "validable" password field.
	 * @param test The function that will be used to validate the input.
	 */
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
	
	/**
	 * Creates a new "validable" password field that considers valid any input.
	 */
	public ValidablePasswordField()
	{
		this(s -> true);
	}
	
	/**
	 * Allows to determine if the current input is valid or not.
	 */
	public boolean isValid()
	{
		return this.isValid;
	}
	
	/**
	 * Allows to update the function used to validate the input.
	 * @param test The new function that will be use to validate the input.
	 */
	public void updateValidationTest(Predicate<char[]> test)
	{
		this.test = test;
		updateValidationState();
	}
	
	/**
	 * Updates the validation state of the component, checking the current password with the given validation function. After the check, the component is adequately formatted, using the {@link ap2016.application.ApplicationUtilities#formatComponent(javax.swing.JComponent, boolean) formatComponent method}.
	 */
	public void updateValidationState()
	{	
		try {
			isValid = test.test(this.getPassword());
		}catch(Exception ex){
			isValid = false;
		}
		ApplicationUtilities.formatComponent(this, isValid);	
	}
	
	/**
	 * Updates the password displayed by the component and the validation state of the component.
	 */
	@Override
	public void setText(String t)
	{
		super.setText(t);
		updateValidationState();
	}
}
