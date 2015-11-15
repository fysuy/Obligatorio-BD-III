package obligatorio.logica.exceptions;

import java.io.Serializable;

public class ExceptionsMascotas  extends Exception implements Serializable{

	private static final long serialVersionUID = 1L;

	public ExceptionsMascotas(String msj)
	{
		super(msj);
	}
}