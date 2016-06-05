package tests.ap2016.gui.utilities;


import static org.junit.Assert.assertEquals;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.junit.Test;

import ap2016.gui.utilities.ViewEditComponent;


public class TestViewEditComponent
{
	ViewEditComponent<JLabel, JTextField> a;

	@Test
	public void testGetEditComponent()
	{
		JTextField b = new JTextField("fgfgf");
		this.a = new ViewEditComponent<>(new JLabel("test"), b, false);

		assertEquals(this.a.getEditComponent().equals(b), true);
	}

	@Test
	public void testGetViewComponent()
	{
		JLabel b = new JLabel("fgfgf");
		this.a = new ViewEditComponent<>(b, new JTextField("test"), false);

		assertEquals(this.a.getViewComponent().equals(b), true);
	}

	@Test
	public void testSetEditState_00()
	{
		this.a = new ViewEditComponent<>(new JLabel("test"), new JTextField("test2"), true);
		this.a.setEditState();
		assertEquals(this.a.isEditState(), true);
	}

	@Test
	public void testSetEditState_01()
	{
		this.a = new ViewEditComponent<>(new JLabel("test"), new JTextField("test2"), false);
		this.a.setEditState();
		assertEquals(this.a.isEditState(), true);
	}

	@Test
	public void testSetEditToViewOperation()
	{
		this.a = new ViewEditComponent<>(new JLabel(""), new JTextField("test"), false);
		this.a.setEditToViewOperation((v, e) -> v.setText(e.getText()));
		this.a.setViewState();
		assertEquals(this.a.getEditComponent().getText(), "test");
	}

	@Test
	public void testSetViewStateIsViewState_00()
	{
		this.a = new ViewEditComponent<>(new JLabel("test"), new JTextField("test2"), true);
		this.a.setViewState();
		assertEquals(this.a.isViewState(), true);
	}

	@Test
	public void testSetViewStateIsViewState_01()
	{
		this.a = new ViewEditComponent<>(new JLabel("test"), new JTextField("test2"), false);
		this.a.setViewState();
		assertEquals(this.a.isViewState(), true);
	}

	@Test
	public void testSetViewToEditOperation()
	{
		this.a = new ViewEditComponent<>(new JLabel("test"), new JTextField(""), true);
		this.a.setViewToEditOperation((v, e) -> e.setText(v.getText()));
		this.a.setEditState();
		assertEquals(this.a.getEditComponent().getText(), "test");
	}

	@Test
	public void testViewEditComponentVE()
	{
		this.a = new ViewEditComponent<>(new JLabel("test"), new JTextField("test2"));
		assertEquals(this.a.getViewComponent().getText(), "test");
		assertEquals(this.a.getEditComponent().getText(), "test2");
		assertEquals(this.a.isViewState(), true);
	}

	@Test
	public void testViewEditComponentVEBoolean_00()
	{
		this.a = new ViewEditComponent<>(new JLabel("test"), new JTextField("test2"), true);
		assertEquals(this.a.getViewComponent().getText(), "test");
		assertEquals(this.a.getEditComponent().getText(), "test2");
		assertEquals(this.a.isViewState(), true);
	}

	@Test
	public void testViewEditComponentVEBoolean_01()
	{
		this.a = new ViewEditComponent<>(new JLabel("test"), new JTextField("test2"), false);
		assertEquals(this.a.getViewComponent().getText(), "test");
		assertEquals(this.a.getEditComponent().getText(), "test2");
		assertEquals(this.a.isViewState(), false);
	}

}
