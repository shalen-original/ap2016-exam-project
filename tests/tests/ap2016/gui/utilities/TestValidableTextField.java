package tests.ap2016.gui.utilities;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ap2016.gui.utilities.ValidableTextField;


public class TestValidableTextField
{

	@Test
	public void testIsValid()
	{
		ValidableTextField a = new ValidableTextField(c -> c.charAt(0) == '0');
		a.setText("0 text");
		assertEquals(a.isValid(), true);
		a.setText("1 text");
		assertEquals(a.isValid(), false);
	}

	@Test
	public void testUpdateValidationState()
	{
		ValidableTextField a = new ValidableTextField(c -> c.charAt(0) == '0');
		a.setText("0 text");
		a.updateValidationState();
	}

	@Test
	public void testUpdateValidationTest()
	{
		ValidableTextField a = new ValidableTextField(c -> c.charAt(0) == '0');
		a.setText("0 text");
		assertEquals(a.isValid(), true);
		a.updateValidationTest(c -> c.charAt(0) == '1');
		a.setText("1 text");
		assertEquals(a.isValid(), true);
	}

	@Test
	public void testValidableTextField()
	{
		@SuppressWarnings("unused")
		ValidableTextField a = new ValidableTextField();
	}

	@Test
	public void testValidableTextFieldPredicateOfchar_00()
	{
		ValidableTextField a = new ValidableTextField(c -> c.charAt(0) == '0');
		a.setText("0 text");
		assertEquals(a.isValid(), true);
	}

	@Test
	public void testValidableTextFieldPredicateOfchar_01()
	{
		ValidableTextField a = new ValidableTextField(c -> c.charAt(0) != '0');
		a.setText("0 text");
		assertEquals(a.isValid(), false);
	}

}
