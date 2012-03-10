package de.fhb.mp3.da;

/**
 * Exception-Klasse
 * @author diesel
 *
 */
public class DataAccessException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	public DataAccessException()
	{
		super();
	}
	
	public DataAccessException(String message)
	{
		super(message);
	}
	
	public DataAccessException(String message, Exception ex)
	{
		super(message, ex);
	}
}
