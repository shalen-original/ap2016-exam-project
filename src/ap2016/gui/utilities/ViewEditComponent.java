package ap2016.gui.utilities;

import java.awt.BorderLayout;
import java.util.function.BiConsumer;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ViewEditComponent<V extends JComponent, E extends JComponent> extends JPanel
{
	private V viewElement;
	private E editElement;
	
	private JPanel viewPanel, editPanel;
	
	private BiConsumer<V, E> fromViewToEdit;
	private BiConsumer<V, E> fromEditToView;
	
	public ViewEditComponent(V viewElement, E editElement)
	{
		this(viewElement, editElement, true);
	}
	
	public ViewEditComponent(V viewElement, E editElement, boolean isInitialStateView)
	{
		if (viewElement == null || editElement == null)
			throw new IllegalArgumentException("The viewElement and the editElement cannot be null");
		
		
		this.viewElement = viewElement;
		this.editElement = editElement;
		
		viewPanel = new JPanel();
		viewPanel.setLayout(new BorderLayout());
		viewPanel.add(viewElement, BorderLayout.CENTER);
		add(viewPanel);
		
		editPanel = new JPanel();
		editPanel.setLayout(new BorderLayout());
		editPanel.add(editElement, BorderLayout.CENTER);
		add(editPanel);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		if (isInitialStateView)
			setViewState();
		else
			setEditState();
	}
	
	public void setViewState()
	{
		if (fromEditToView != null)
			fromEditToView.accept(viewElement, editElement);
		
		editPanel.setVisible(false);
		viewPanel.setVisible(true);
	}
	
	public void setEditState()
	{
		if (fromViewToEdit != null)
			fromViewToEdit.accept(viewElement, editElement);
		
		viewPanel.setVisible(false);
		editPanel.setVisible(true);
		
	}
	
	public boolean isViewState()
	{
		return viewPanel.isVisible();
	}
	
	public boolean isEditState()
	{
		return editPanel.isVisible();
	}
	
	public void setViewToEditOperation(BiConsumer<V, E> op)
	{
		this.fromViewToEdit = op;
	}
	
	public void setEditToViewOperation(BiConsumer<V, E> op)
	{
		this.fromEditToView = op;
	}
	
	public V getViewComponent()
	{
		return this.viewElement;
	}
	
	public E getEditComponent()
	{
		return this.editElement;
	}
}
