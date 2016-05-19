package tests.ap2016.gui.utilities;

import static org.junit.Assert.*;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.junit.Test;

import ap2016.gui.utilities.ViewEditComponent;

public class TestViewEditComponent
{
	ViewEditComponent<JLabel, JTextField> a;
	
	@Test
	public void testViewEditComponentVE()
	{
		a = new ViewEditComponent<>(new JLabel("test"), new JTextField("test2"));
		assertEquals(a.getViewComponent().getText(), "test");
		assertEquals(a.getEditComponent().getText(), "test2");
		assertEquals(a.isViewState(), true);
	}

	@Test
	public void testViewEditComponentVEBoolean_00()
	{
		a = new ViewEditComponent<>(new JLabel("test"), new JTextField("test2"), true);
		assertEquals(a.getViewComponent().getText(), "test");
		assertEquals(a.getEditComponent().getText(), "test2");
		assertEquals(a.isViewState(), true);
	}
	@Test
	public void testViewEditComponentVEBoolean_01()
	{
		a = new ViewEditComponent<>(new JLabel("test"), new JTextField("test2"), false);
		assertEquals(a.getViewComponent().getText(), "test");
		assertEquals(a.getEditComponent().getText(), "test2");
		assertEquals(a.isViewState(), false);
	}

	@Test
	public void testSetViewStateIsViewState_00()
	{
		a = new ViewEditComponent<>(new JLabel("test"), new JTextField("test2"), true);
		a.setViewState();
		assertEquals(a.isViewState(), true);
	}
	
	@Test
	public void testSetViewStateIsViewState_01()
	{
		a = new ViewEditComponent<>(new JLabel("test"), new JTextField("test2"), false);
		a.setViewState();
		assertEquals(a.isViewState(), true);
	}

	@Test
	public void testSetEditState_00()
	{
		a = new ViewEditComponent<>(new JLabel("test"), new JTextField("test2"), true);
		a.setEditState();
		assertEquals(a.isEditState(), true);
	}
	@Test
	public void testSetEditState_01()
	{
		a = new ViewEditComponent<>(new JLabel("test"), new JTextField("test2"), false);
		a.setEditState();
		assertEquals(a.isEditState(), true);
	}

	@Test
	public void testSetViewToEditOperation()
	{
		a = new ViewEditComponent<>(new JLabel("test"), new JTextField(""), true);
		a.setViewToEditOperation((v, e) -> e.setText(v.getText()));
		a.setEditState();
		assertEquals(a.getEditComponent().getText(), "test");
	}

	@Test
	public void testSetEditToViewOperation()
	{
		a = new ViewEditComponent<>(new JLabel(""), new JTextField("test"), false);
		a.setEditToViewOperation((v, e) -> v.setText(e.getText()));
		a.setViewState();
		assertEquals(a.getEditComponent().getText(), "test");
	}

	@Test
	public void testGetViewComponent()
	{
		JLabel b = new JLabel("fgfgf");
		a = new ViewEditComponent<>(b, new JTextField("test"), false);
		
		assertEquals(a.getViewComponent().equals(b), true);
	}

	@Test
	public void testGetEditComponent()
	{
		JTextField b = new JTextField("fgfgf");
		a = new ViewEditComponent<>(new JLabel("test"), b, false);
		
		assertEquals(a.getEditComponent().equals(b), true);
	}

}
