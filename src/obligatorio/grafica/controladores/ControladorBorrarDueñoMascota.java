package obligatorio.grafica.controladores;

import java.rmi.RemoteException;

import obligatorio.exceptions.LogicaException;
import obligatorio.logica.Fachada;

public class ControladorBorrarDueñoMascota {

	public void borrarDueñoMascota(int cedula) throws LogicaException, RemoteException {

		// FIXME: cambiar por IFachada
		Fachada.getInstance().borrarDueñoMascotas(cedula);

	}

}