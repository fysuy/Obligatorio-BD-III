package obligatorio.grafica.controladores;

import java.rmi.RemoteException;

import obligatorio.exceptions.DueñoException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.IFachada;

public class ControladorBorrarDueñoMascota {
	private IFachada facade;
	
	public void borrarDueñoMascota(int cedula) throws LogicaException,
			PersistenciaException, DueñoException, RemoteException {
		facade.borrarDueñoMascotas(cedula);
	}
}