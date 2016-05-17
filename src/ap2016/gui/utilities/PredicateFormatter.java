package ap2016.gui.utilities;

import java.text.ParseException;
import java.util.function.Predicate;

import javax.swing.text.DefaultFormatter;

@SuppressWarnings("serial")
public class PredicateFormatter extends DefaultFormatter
{
	Predicate<String> test;
	
	public PredicateFormatter(Predicate<String> test)
	{
		this.test = test;
		super.setCommitsOnValidEdit(true);
	}
	
	@Override
	public Object stringToValue(String arg0) throws ParseException
	{
		if (test.test(arg0))
			return super.stringToValue(arg0);
		else
			throw new ParseException("Error while parsing value: " + arg0, 0);
	}
	
	

}
