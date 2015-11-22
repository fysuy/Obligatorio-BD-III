package obligatorio.grafica.controladores;

import java.rmi.RemoteException;

import obligatorio.exceptions.DueñoException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.Fachada;

public class ControladorBorrarDueñoMascota {

	public void borrarDueñoMascota(int cedula) throws LogicaException,
			PersistenciaException, DueñoException, RemoteException {

		// FIXME: cambiar por IFachada
		Fachada.getInstance().borrarDueñoMascotas(cedula);
	}
}