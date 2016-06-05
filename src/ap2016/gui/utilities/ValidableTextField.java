package ap2016.gui.utilities;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.function.Predicate;

import javax.swing.JTextField;

import ap2016.application.ApplicationUtilities;


/**
 * This class represents a JTextField that is "validable". To be "validable" means that the input is checked: if it is considered valid, then the component gets formatted one way, if it is invalid the component will be formatted in another one.
 * @author Matteo Nardini
 *
 */

public class ValidableTextField extends JTextField
{
	/**
	 * Serial version UUID.
	 */
	private static final long serialVersionUID = 2608755108361553161L;
	/**
	 * Contains the function used to validate the input.
	 */
	Predicate<String> test;
	/**
	 * Contains whether the current input is valid or not.
	 */
	boolean isValid;

	/**
	 * Creates a new "validable" text field that considers valid any input.
	 */
	public ValidableTextField()
	{
		this(s -> true);
	}

	/**
	 * Creates a new "validable" text field.
	 * @param test The function that will be used to validate the input.
	 */
	public ValidableTextField(Predicate<String> test)
	{
		this.test = test;
		addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				updateValidationState();
			}
		});

		updateValidationState();
	}

	/**
	 * Allows to determine if the current input is valid or not.
	 */
	@Override
	public boolean isValid()
	{
		return this.isValid;
	}

	/**
	 * Updates the text displayed by the component and the validation state of the component.
	 */
	@Override
	public void setText(String t)
	{
		super.setText(t);
		updateValidationState();
	}

	/**
	 * Updates the validation state of the component, checking the current text with the given validation function. After the check, the component is adequately formatted, using the {@link ap2016.application.ApplicationUtilities#formatComponent(javax.swing.JComponent, boolean) formatComponent method}.
	 */
	public void updateValidationState()
	{
		try
		{
			this.isValid = this.test.test(this.getText());
		} catch (Exception ex)
		{
			this.isValid = false;
		}
		ApplicationUtilities.formatComponent(this, this.isValid);
	}

	/**
	 * Allows to update the function used to validate the input.
	 * @param test The new function that will be use to validate the input.
	 */
	public void updateValidationTest(Predicate<String> test)
	{
		this.test = test;
		updateValidationState();
	}

}
