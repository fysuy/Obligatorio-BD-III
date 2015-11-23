package obligatorio.grafica.controladores;

import java.rmi.RemoteException;

import obligatorio.exceptions.DueñoException;
import obligatorio.exceptions.LogicaException;
import obligatorio.exceptions.PersistenciaException;
import obligatorio.logica.IFachada;
import obligatorio.logica.valueObjects.VODueño;

public class ControladorIngresarDueño {
	private IFachada facade;

	public void ingresarDueño(int cedula, String nombre, String apellido)
			throws DueñoException, PersistenciaException, LogicaException,
			RemoteException {
		VODueño voDueño = new VODueño(cedula, nombre, apellido);
		facade.nuevoDueño(voDueño);
	}
}