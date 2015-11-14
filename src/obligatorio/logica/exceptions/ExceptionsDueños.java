/**
 * 
 */
package obligatorio.logica.exceptions;

import java.io.Serializable;

public class ExceptionsDueños  extends Exception implements Serializable{

	private static final long serialVersionUID = 1L;

	public ExceptionsDueños(String msj)
	{
		super(msj);
	}

}
