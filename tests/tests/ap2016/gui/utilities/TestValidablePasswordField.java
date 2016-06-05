package tests.ap2016.gui.utilities;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ap2016.gui.utilities.ValidablePasswordField;


public class TestValidablePasswordField
{

	@Test
	public void testIsValid()
	{
		ValidablePasswordField a = new ValidablePasswordField(c -> c[0] == '0');
		a.setText("0 text");
		assertEquals(a.isValid(), true);
		a.setText("1 text");
		assertEquals(a.isValid(), false);
	}

	@Test
	public void testUpdateValidationState()
	{
		ValidablePasswordField a = new ValidablePasswordField(c -> c[0] == '0');
		a.setText("0 text");
		a.updateValidationState();
	}

	@Test
	public void testUpdateValidationTest()
	{
		ValidablePasswordField a = new ValidablePasswordField(c -> c[0] == '0');
		a.setText("0 text");
		assertEquals(a.isValid(), true);
		a.updateValidationTest(c -> c[0] == '1');
		a.setText("1 text");
		assertEquals(a.isValid(), true);
	}

	@Test
	public void testValidablePasswordField()
	{
		@SuppressWarnings("unused")
		ValidablePasswordField a = new ValidablePasswordField();
	}

	@Test
	public void testValidablePasswordFieldPredicateOfchar_00()
	{
		ValidablePasswordField a = new ValidablePasswordField(c -> c[0] == '0');
		a.setText("0 text");
		assertEquals(a.isValid(), true);
	}

	@Test
	public void testValidablePasswordFieldPredicateOfchar_01()
	{
		ValidablePasswordField a = new ValidablePasswordField(c -> c[0] != '0');
		a.setText("0 text");
		assertEquals(a.isValid(), false);
	}

}
