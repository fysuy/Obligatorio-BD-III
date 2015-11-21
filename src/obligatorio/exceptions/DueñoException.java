package obligatorio.exceptions;

import java.io.Serializable;

public class DueñoException  extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	public DueñoException(String msj)
	{
		super(msj);
	}
}
