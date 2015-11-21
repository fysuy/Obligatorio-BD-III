package obligatorio.exceptions;

import java.io.Serializable;

public class MascotaException  extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	public MascotaException(String msj)
	{
		super(msj);
	}
}